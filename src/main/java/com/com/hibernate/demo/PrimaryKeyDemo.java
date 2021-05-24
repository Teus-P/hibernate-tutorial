package com.com.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class PrimaryKeyDemo {

    public static void main(String[] args){
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            System.out.println("Creating 3 student object...");
            Student student1 = new Student("John", "Doe", "paul@automail.com");
            Student student2 = new Student("Mary", "Public", "mary@automail.com");
            Student student3 = new Student("Bonita", "Apple", "bonita@automail.com");

            session.beginTransaction();

            System.out.println("Saving the students...");

            session.save(student1);
            session.save(student2);
            session.save(student3);

            session.getTransaction().commit();

            System.out.println("Done.");
        } finally {
            session.close();
        }
    }
}
