package com.com.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args){

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try{
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            int studentId = 1;

            System.out.println("\nGetting studetn with id: " + studentId);
            Student student = session.get(Student.class, studentId);

            System.out.println("\nUpdating student...");
            student.setFirstName("Scooby");

            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("\nUpdate email for all students");
            session.createQuery("UPDATE Student set email='test@automail.com'").executeUpdate();

            session.getTransaction().commit();

            System.out.println("\nDone.");
        }finally {
            factory.close();
        }
    }
}
