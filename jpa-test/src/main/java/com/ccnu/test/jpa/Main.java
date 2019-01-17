package com.ccnu.test.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

//        testSave();
//
//        testFind();

//        testUpdate();

//        testDelete();

        testCollection();
    }


    public static void testCollection() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Student_details");
        EntityManager em = emf.createEntityManager();

        System.out.println("Start work...");
        em.getTransaction().begin();

        Address a1 = new Address();
        a1.setPincode(201301);
        a1.setCity("Guangzhou");
        a1.setState("GuangDong");

        Address a2 = new Address();
        a2.setPincode(302001);
        a2.setCity("Haikou");
        a2.setState("Hainan");

        Employee e1 = new Employee();
        e1.setId(1);
        e1.setName("Maxsu");
        e1.getAddress().add(a1);

        Employee e2 = new Employee();
        e2.setId(2);
        e2.setName("John");
        e2.getAddress().add(a2);

        em.persist(e1);
        em.persist(e2);

        em.getTransaction().commit();

        em.close();
        emf.close();
        System.out.println("End work...");
    }

    public static void testDelete() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Student_details");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        User user = em.find(User.class, 2L);

        em.remove(user);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }


    public static void testUpdate() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Student_details");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        User user = em.find(User.class, 1L);

        user.setName("zhangsan");

        em.persist(user);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    public static void testFind() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Student_details");
        EntityManager em = emf.createEntityManager();

        User user = em.find(User.class, 1L);

        System.out.println(user);

        em.close();
        emf.close();
    }

    public static void testSave() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Student_details");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        User user1 = new User("张三", 15, new Date());
        em.persist(user1);

        User user2 = new User("李四", 25, new Date());
        em.persist(user2);

        User user3 = new User("王五", 35, new Date());
        em.persist(user3);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
