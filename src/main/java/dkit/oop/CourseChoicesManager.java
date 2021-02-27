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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    private Map <Integer, List<String>> choicesMap;




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
        courseMap = new HashMap<>();
        for (Course c : courses)
        {
            Course copy = new Course(c);
            courseMap.put(copy.getCourseId(),copy);
        }

        List<String> choicelist = new ArrayList<>();
        choicesMap = new HashMap<>();

        loadchoices();


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

        List<String> choicelist = new ArrayList<>();
        List<Course> courselist = new ArrayList<>();

        choicelist = choicesMap.get(caonumber);

        for (String c:choicelist){
            courselist.add(new Course(courseManager.getCourse(c)));
        }

        return courselist;

    }

    public void updateChoices(int caonumber,List<String> courseIDs ) {

        if (studentManager.isRegistered(caonumber)){
            choicesMap.put(caonumber,courseIDs);
        }
        wrtieout();


    }
//
    public List<Course> getAllCourses() {
        return courseManager.getAllCourses();
    }
//
    public boolean login(int caonumber,String dateOfBirth,String password) {

        boolean verify = false;

        Student login = studentManager.getStudent(caonumber);

        if (caonumber == login.getCaoNumber() && dateOfBirth.equals(login.getDayOfBirth()) && password.equals(login.getPassword()))
        {
            System.out.println("log in Successful");
            verify = true;
        }


        return verify;


    }

    public void addstudent (Student student){

        if (student != null){
            studentManager.addStudent(student);
        }else
        {
            System.out.println("invalid input");
        }
    }
    public void removeStudent (int caonum) {
        studentManager.removeStudent(caonum);
    }

    public Student getStudent (int caonum) {

        Student copy = null;
        Student student = studentManager.getStudent(caonum);

        if (student != null) {
            copy = new Student(student);
        }
        return copy;

    }

    public Course getCourse (String courseid) {
        Course copy = null;
        Course course = courseManager.getCourse(courseid);

        if (course != null) {
            copy = new Course(course);
        }
        return copy;
    }

    public void addCourse (Course course){

        if (course != null){
            courseManager.addCourse(course);
        }else
        {
            System.out.println("invalid input");
        }
    }

    public void removeCourse(String courseid) {
        courseManager.removeCourse(courseid);
    }


    public void loadchoices() {

        try (Scanner sc = new Scanner(new File("choices.txt"));) {
            sc.useDelimiter("[,\r\n]+");
            while (sc.hasNext()) {

                String line = sc.nextLine();
                String [] data = line.split(",");

                int Caonumber = Integer.parseInt(data[0]);
                List<String> list = new ArrayList<>();
                for(int i = 1; i < data.length;i++){
                    list.add(data[i]);
                }
                choicesMap.put(Caonumber,list);
            }

        }
        catch (IOException e) {
            System.out.println("IOException thrown in loadStudentsFromFile() "+e.getMessage());
        }
        catch (InputMismatchException exception)
        {
            System.out.println("InputMismatchexception caught." + exception);
        }
    }

    public void wrtieout(){

        try(BufferedWriter choicesfile = new BufferedWriter(new FileWriter("choices.txt"))){
            Set<Integer> keyset = choicesMap.keySet();
            for(int key:keyset){
                choicesfile.write(""+key);
                List<String> courseid = choicesMap.get(key);
                for (String course : courseid){
                    choicesfile.write(","+course);
                }
                choicesfile.write("\n");
            }


        } catch (IOException e) {
            System.out.println("could not save courrse data");
        }
    }

    public void saveall(){
        studentManager.wrtieout();
        courseManager.wrtieout();
    }






}
