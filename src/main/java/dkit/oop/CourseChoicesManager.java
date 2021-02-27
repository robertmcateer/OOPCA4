package dkit.oop;

//RobertMcateer D00233414

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

    /**
     * returns a list of course choices that have been loaded into the system by the load method.
     *
     * @param caonumber
     * @return
     */

    public List<Course> getStudentChoices(int caonumber) {

        List<String> choicelist = new ArrayList<>();
        List<Course> courselist = new ArrayList<>();

        choicelist = choicesMap.get(caonumber);

        for (String c:choicelist){
            courselist.add(new Course(courseManager.getCourse(c)));
        }

        return courselist;

    }

    /**
     * this method alter student course choices and writes them to the choices txt file once
     * they have been updated
     * @param caonumber
     * @param courseIDs
     */

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

    /**
     * this method is used to call the add student method from the student manager
     * @param student
     */
    public void addstudent (Student student){

        if (student != null){
            studentManager.addStudent(student);
        }else
        {
            System.out.println("invalid input");
        }
    }

    /**
     * this method calls the remove student from the student manager class to be used in main app
     * @param caonum
     */
    public void removeStudent (int caonum) {
        studentManager.removeStudent(caonum);
    }
    /**
     * this method calls the get student from the student manager class to be used in main app
     * @param caonum
     */
    public Student getStudent (int caonum) {

        Student copy = null;
        Student student = studentManager.getStudent(caonum);

        if (student != null) {
            copy = new Student(student);
        }
        return copy;

    }
    /**
     * this method calls the get course from the course manager class to be used in main app.
     * checking if the return is null or valid
     * @param courseid
     */
    public Course getCourse (String courseid) {
        Course copy = null;
        Course course = courseManager.getCourse(courseid);

        if (course != null) {
            copy = new Course(course);
        }
        return copy;
    }
    /**
     * this method calls the add course from the course manager class to be used in main app.
     * checking if the return is null or valid
     * @param course
     */

    public void addCourse (Course course){

        if (course != null){
            courseManager.addCourse(course);
        }else
        {
            System.out.println("invalid input");
        }
    }
    /**
     * this method calls the remove course from the course manager class to be used in main app.
     *
     * @param courseid
     */
    public void removeCourse(String courseid) {
        courseManager.removeCourse(courseid);
    }

    /**
     * method is used to load in all student choices information from choices txt file to
     * be displayed or altered in the main app
     */
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

    /**
     * this methods writes any changed that have been made to a students course choice back to the choice txt file
     */
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

    /**
     * saveall method is used to call both writeout methodds from student and course manager classes
     * this ensures all information alter by admin or student user is saved for future use.
     */
    public void saveall(){
        studentManager.wrtieout();
        courseManager.wrtieout();

    }






}
