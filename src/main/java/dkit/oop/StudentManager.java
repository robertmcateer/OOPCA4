package dkit.oop;
//RobertMcateer D00233414

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

    /**
     * returns a copy of a student record if there is a matching cao number within the map.
     * Null returned other wise
     * @param caoNumber
     * @return
     */
    public Student getStudent(int caoNumber) {

        if(this.studentMap.containsKey(caoNumber)){
            Student student = this.studentMap.get(caoNumber);
            Student copy = new Student(student);
            return copy;
        }
        return null;
    }

    /**
     * adds a copy instance of a student to the student map.
     *
     * @param s
     */
    public void addStudent(Student s) {

        Student copy = new Student(s);
        if (copy == null)
            throw new IllegalArgumentException();
        studentMap.put(copy.getCaoNumber(), copy);
    }

    /**
     * Removes a student record from studentmap if the caonumber passes in matches one in the map
     * @param caoNumber
     */
    public void removeStudent(int caoNumber) {

        if(this.studentMap.containsKey(caoNumber)){
            studentMap.remove(caoNumber);
        }
        else{
            System.out.println("Invalid Student");
        }
    }

    /**
     * Method checks for a instance of a student in the studentmap.
     * If one is found then the student is registered and true is returned.
     *
     * @param caoNumber
     * @return
     */
    public boolean isRegistered(int caoNumber) {

        boolean isreg = false;
        if (studentMap.containsKey(caoNumber)) {
            isreg = true;
        }

        return isreg;
    }

    /**
     * method loads in student details from student.txt file
     */
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

    /**
     * New records or deletion of student records are saved and written to students.txt using this method
     */
    public void wrtieout(){

        try(BufferedWriter studentfile = new BufferedWriter(new FileWriter("students.txt"))){
            Set<Integer> keyset = studentMap.keySet();
            for(Integer key:keyset){
                studentfile.write(studentMap.get(key).getCaoNumber()+","+studentMap.get(key).getDayOfBirth()+","+studentMap.get(key).getPassword()+","+studentMap.get(key).getEmail());
                studentfile.write("\n");
            }


        } catch (IOException e) {
            System.out.println("could not save students data");
        }
    }










}
