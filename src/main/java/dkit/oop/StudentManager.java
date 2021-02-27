package dkit.oop;
// StudentManager encapsulates the storage and ability
// to manipulate student objects

import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class StudentManager {


    private Map<Integer, Student> studentMap = new HashMap<>();

    public StudentManager() {

        loadStudents();





    }
//    code get student and test in app

    public Student getStudent(int caoNumber) {

        Student student = this.studentMap.get(caoNumber);
        Student copy = new Student(student);
        return copy;
    }

    public void addStudent(Student s) {

        Student copy = new Student(s);
        if (copy == null)
            throw new IllegalArgumentException();
        studentMap.put(copy.getCaoNumber(), copy);
    }


    public void removeStudent(int caoNumber) {

        studentMap.remove(caoNumber);
    }

    public boolean isRegistered(int caoNumber) {

        boolean isreg = false;
        if (studentMap.containsKey(caoNumber)) {
            isreg = true;
        }

        return isreg;
    }


    public void loadStudents() {

        try (Scanner sc = new Scanner(new File("students.txt"));) {
            sc.useDelimiter("[,\r\n]+");
            while (sc.hasNext()) {

                int cao = sc.nextInt();
                String DOB = sc.next();
                String password = sc.next();
                String email = sc.next();


                Student s = new Student(cao, DOB, password, email);
                Student s1 = new Student(s);

                studentMap.put(s1.getCaoNumber(),s1);


            }


        } catch (IOException e) {
            System.out.println("IOException thrown in loadStudentsFromFile() " + e.getMessage());
        } catch (InputMismatchException exception) {
            System.out.println("InputMismatchexception caught." + exception);
        }

    }










}
