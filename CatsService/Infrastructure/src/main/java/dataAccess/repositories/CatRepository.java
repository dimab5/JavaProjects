package dataAccess.repositories;

import abstractions.repositories.ICatRepository;
import models.cats.Cat;
import models.operations.OperationResult;
import models.owners.Owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Repository
public class CatRepository implements ICatRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public CatRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Cat createCat(String name, Date birthDate, String breed, String color, Owner owner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        if (name == null || birthDate == null || breed == null || color == null) {
            return null;
        }

        Cat cat = new Cat();
        cat.setName(name);
        cat.setBirthDate(birthDate);
        cat.setBreed(breed);
        cat.setColor(color);
        cat.setOwner(owner);

        entityManager.persist(cat);

        transaction.commit();
        entityManager.close();

        return cat;
    }

    @Override
    public Cat findCatById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        Cat cat = entityManager.find(Cat.class, id);
        transaction.commit();
        entityManager.close();

        return cat;
    }

    @Override
    public OperationResult makeFriendsCats(Cat firstCat, Cat secondCat) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            firstCat.addFriend(secondCat);

            entityManager.merge(firstCat);

            transaction.commit();

            return OperationResult.SUCCESS;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            e.printStackTrace();
            return OperationResult.FAIL;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Cat> findCatsByColor(String color) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Cat> query = entityManager.createQuery(
                "SELECT c FROM Cat c WHERE c.color = :color",
                Cat.class);

        query.setParameter("color", color);
        return query.getResultList();
    }

    @Override
    public List<Cat> getAllCats() {
        EntityManager entityManager = null;
        List<Cat> cats = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            TypedQuery<Cat> query = entityManager.createQuery(
                    "SELECT c FROM Cat c",
                    Cat.class);

            cats = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return cats;
    }

    @Override
    public OperationResult deleteCatById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Cat cat = entityManager.find(Cat.class, id);
            if (cat != null) {
                entityManager.remove(cat);
            }
            transaction.commit();

            return OperationResult.SUCCESS;
        }
        catch (Exception e)
        {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();

            return OperationResult.FAIL;
        }
        finally
        {
            entityManager.close();
        }
    }
}