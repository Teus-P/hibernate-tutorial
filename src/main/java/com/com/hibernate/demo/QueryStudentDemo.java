package com.com.hibernate.demo;

import com.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args){

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            session.beginTransaction();

            List<Student> students = session.createQuery("from Student").getResultList();
            System.out.println("\nList of all students:");
            displayStudents(students);

            System.out.println("\nList of students whose last name is equal to 'Doe'");
            students = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
            displayStudents(students);

            System.out.println("\nList of students whose last name is equal to 'Doe' or first name is equal to 'Daffy'");
            students = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'").getResultList();
            displayStudents(students);

            System.out.println("\nList of students whose email is equal to '@automail.com'");
            students = session.createQuery("FROM Student s WHERE s.email LIKE '%automail.com'").getResultList();
            displayStudents(students);

            session.getTransaction().commit();
            System.out.println("\nDone.");
        }
        finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> students) {
        for (Student tempStudent : students) {
            System.out.println(tempStudent);
        }
    }
}
