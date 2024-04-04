//import abstractions.repositories.ICatRepository;
//import abstractions.repositories.IOwnerRepository;
//import dataAccess.repositories.CatRepository;
//import abstractions.repositories.OwnerReposirory;
//import models.cats.Cat;
//import models.cats.CatColor;
//import models.owners.Owner;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class Tests {
//
//    @Test
//    public void testCreateCat() {
//        EntityManager entityManager = Mockito.mock(EntityManager.class);
//        EntityTransaction transaction = Mockito.mock(EntityTransaction.class);
//        EntityManagerFactory entityManagerFactory = Mockito.mock(EntityManagerFactory.class);
//
//        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
//        when(entityManager.getTransaction()).thenReturn(transaction);
//
//        String name = "Fluffy";
//        Date birthDate = new Date();
//        String breed = "Persian";
//        CatColor color = CatColor.BLACK;
//
//        Owner owner = new Owner();
//        owner.setName("Dima");
//        owner.setBirthDate(new Date());
//
//        ICatRepository catRepository = new CatRepository();
//        catRepository.setEntityManagerFactory(entityManagerFactory);
//
//        Cat cat = catRepository.createCat(name, birthDate, breed, color, owner);
//
//        assertEquals(name, cat.getName());
//        assertEquals(birthDate, cat.getBirthDate());
//        assertEquals(breed, cat.getBreed());
//        assertEquals(color, cat.getColor());
//        assertEquals(owner, cat.getOwner());
//
//        verify(entityManagerFactory).createEntityManager();
//        verify(entityManager).getTransaction();
//        verify(transaction).begin();
//        verify(entityManager).persist(any(Cat.class));
//        verify(transaction).commit();
//        verify(entityManager).close();
//    }
//
//    @Test
//    public void testCreateOwner() {
//        EntityManager entityManager = Mockito.mock(EntityManager.class);
//        EntityTransaction transaction = Mockito.mock(EntityTransaction.class);
//        EntityManagerFactory entityManagerFactory = Mockito.mock(EntityManagerFactory.class);
//
//        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
//        when(entityManager.getTransaction()).thenReturn(transaction);
//
//        String name = "Dima";
//        Date birthDate = new Date();
//
//        IOwnerRepository ownerRepository = new OwnerReposirory();
//        ownerRepository.setEntityManagerFactory(entityManagerFactory);
//
//        Owner owner = ownerRepository.createOwner(name, birthDate);
//
//        assertEquals(name, owner.getName());
//        assertEquals(birthDate, owner.getBirthDate());
//
//        verify(entityManagerFactory).createEntityManager();
//        verify(entityManager).getTransaction();
//        verify(transaction).begin();
//        verify(entityManager).persist(any(Owner.class));
//        verify(transaction).commit();
//        verify(entityManager).close();
//    }
//}
