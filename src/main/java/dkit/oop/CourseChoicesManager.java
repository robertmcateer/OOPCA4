package dkit.oop;

// Stores all student CAO choices.
// Allows student to make course choices, save them and update them later.
//
// emphasis on speed of access when multiple users are accessing this at same time
//
// This component would interact with a Network component that would, in turn, send
// data over the internet to a web client.
//
// Clone all received and returned objects - encapsulation

import java.util.*;
import java.util.stream.Collectors;

public class CourseChoicesManager {

    // reference to constructor injected studentManager
    private StudentManager studentManager;

    // reference to constructor injected courseManager
    private CourseManager courseManager;


    // Store all the Course details -  fast access
    private Map<String,Course> courseMap;

    // caoNumber, course selection list - for fast access
    private Map <Integer, List<Course>> choicesMap;




    // CourseChoicesManager DEPENDS on both the StudentManager and CourseManager to access
    // student details and course details.  So, we receive a reference to each via
    // the constructor.
    // This is called "Dependency Injection", meaning that we
    // inject (or pass in) objects that this class requires to do its job.
    //
    CourseChoicesManager(StudentManager studentManager, CourseManager courseManager) {
        this.studentManager = studentManager;
        this.courseManager = courseManager;


        List<Course> courses= courseManager.getAllCourses();
        for (Course c : courses)
        {
            Course copy = new Course(c);
            courseMap.put(copy.getCourseId(),copy);
        }

        List<Course> choicelist = new ArrayList<>();
        choicesMap = new HashMap<>();
        Student s = new Student(studentManager.getStudent(12345));


        choicelist.add(new Course(courseManager.getCourse("DK555")));
        choicelist.add(new Course(courseManager.getCourse("DK821")));
        choicelist.add(new Course(courseManager.getCourse("DKIT1980")));

        choicesMap.put(s.getCaoNumber(),choicelist);


    }

    public Student getStudentDetails(int caonumber) {

      Student s = new Student(studentManager.getStudent(caonumber));

      if (s != null)
          return s;
      else
          return null;


    }

    public Course getCourseDetails(String courseid) {
        Course c = new Course(courseManager.getCourse(courseid));

        if (c != null)
            return c;
        else
            return null;
    }

    public List<Course> getStudentChoices(int caonumber) {

        List<Course> choicelist = choicesMap.get(caonumber);
        return choicelist;

    }

    public void updateChoices(int caonumber,List<String> courseIDs ) {

        List<Course> choicelist = getStudentChoices(caonumber);
        for(int i =0; i < courseIDs.size(); i++){

            Course choice = courseMap.get(courseIDs.get(i));
            choicelist.add(new Course(choice));
        }
        this.choicesMap.put(caonumber,choicelist);


    }
//
    public List<Course> getAllCourses() {
        return courseManager.getAllCourses();
    }
//
    public boolean login(int caonumber,String dateOfBirth,String password) {

        boolean verify = false;

        Student login = studentManager.getStudent(caonumber);

        if (login.equals(studentManager.isRegistered(caonumber))){
            verify = true;
        }

        return verify;


    }


}
