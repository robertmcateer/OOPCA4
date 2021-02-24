package dkit.oop;
// StudentManager encapsulates the storage and ability
// to manipulate student objects


import java.util.HashMap;
import java.util.Map;

public class StudentManager {


private Map<Integer,Student> studentMap;

    public StudentManager() {
        Student s1 = new Student(12345,"06/11/2000","killeavy2","R@r.com");
        Student s2 = new Student(54321,"11/11/2001","shoes!","k@k.com");

        this.studentMap = new HashMap<>();
        studentMap.put(s1.getCaoNumber(),s1);
        studentMap.put(s2.getCaoNumber(),s2);


        // later, load from text file "students.dat" and populate studentsMap
    }
//    code get student and test in app

    public Student getStudent(int caoNumber) {


        Student copy = null;
        Student student = this.studentMap.get(caoNumber);

        if (student!= null)
        {
            copy = new Student(student);
        }
        return copy;
    }

    public void addStudent(Student s) {

        Student copy = new Student(s);
        if (copy == null)
            throw new IllegalArgumentException();
        studentMap.put(copy.getCaoNumber(),copy);


    }

    public void removeStudent(int caoNumber) {

        studentMap.remove(caoNumber);
    }

    public boolean isRegistered(int caoNumber){

        boolean isreg = false;

        if(studentMap.containsKey(caoNumber)){
            isreg = true;

        }

        return isreg;
    }

}
