package dataAccess.repositories;

import abstractions.repositories.IOwnerRepository;
import models.cats.Cat;
import models.operations.OperationResult;
import models.owners.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Repository
public class OwnerRepository implements IOwnerRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public OwnerRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Owner createOwner(String name, Date birthDate, String password, String role) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        if (name == null || birthDate == null) {
            return null;
        }

        Owner owner = new Owner();
        owner.setBirthDate(birthDate);
        owner.setName(name);
        owner.setPassword(password);
        owner.setRole(role);

        entityManager.persist(owner);

        transaction.commit();
        entityManager.close();

        return owner;
    }

    @Override
    public Owner findOwnerById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        Owner owner = entityManager.find(Owner.class, id);
        transaction.commit();
        entityManager.close();

        return owner;
    }

    @Override
    public Owner findOwnerByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Owner owner = null;

        try {
            transaction.begin();
            Query query = entityManager.createNativeQuery("SELECT * FROM owners WHERE name = :name", Owner.class);
            query.setParameter("name", name);
            owner = (Owner) query.getSingleResult();
            transaction.commit();
        } catch (NoResultException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return owner;
    }

    @Override
    public List<Owner> getAllOwners() {
        EntityManager entityManager = null;
        List<Owner> owners = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            TypedQuery<Owner> query = entityManager.createQuery("SELECT o FROM Owner o", Owner.class);

            owners = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return owners;
    }

    @Override
    public OperationResult becomeOwner(Owner owner, Cat cat) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            cat.setOwner(owner);
            entityManager.persist(cat);

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

    @Override
    public OperationResult deleteOwnerById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Owner owner = entityManager.find(Owner.class, id);
            if (owner != null) {
                Query query = entityManager.createQuery("SELECT c FROM Cat c WHERE c.owner = :owner");
                query.setParameter("owner", owner);
                List<Cat> cats = query.getResultList();

                cats.stream().forEach(cat -> {
                    cat.setOwner(null);
                    entityManager.persist(cat);
                });

                entityManager.remove(owner);
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

    @Override
    public List<Cat> getCatListById(Long id) {
        EntityManager entityManager = null;
        List<Cat> cats = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            Query query = entityManager.createQuery(
                    "SELECT c FROM Cat c WHERE c.owner.id = :ownerId");
            query.setParameter("ownerId", id);

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
}