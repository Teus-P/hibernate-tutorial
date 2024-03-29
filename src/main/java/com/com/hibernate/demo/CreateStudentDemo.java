package com.com.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {

    public static void main(String[] args){

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            System.out.println("Creating new student object...");
            Student student = new Student("Paul", "Wall", "paulwall@automail.com");

            session.beginTransaction();

            System.out.println("Saving the student...");
            session.save(student);

            session.getTransaction().commit();

            System.out.println("Done.");

        } finally {
            factory.close();
        }
    }
}
