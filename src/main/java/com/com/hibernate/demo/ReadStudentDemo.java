package com.com.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

    public static void main(String[] args){

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            System.out.println("Creating new student object...");
            Student student = new Student("Daffy", "Duck", "daffy@automail.com");

            session.beginTransaction();

            System.out.println("Saving the student...");
            System.out.println(student);
            session.save(student);

            session.getTransaction().commit();

            System.out.println("Saved student. Generated id: " + student.getId());

            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Getting sutdent with id: " + student.getId());

            Student myStudent = session.get(Student.class, student.getId());

            System.out.println("Get complete: " + myStudent);

            session.getTransaction().commit();

            System.out.println("Done.");

        } finally {
            factory.close();
        }
    }
}
