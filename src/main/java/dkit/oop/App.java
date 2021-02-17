package dkit.oop;

import java.util.List;

/**
 *
 * Notes:
 *  Synchronisation of multiple reads and writes to file is not considered here.
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "CAO Online - CA4" );
        new App() .start();
    }

    private void start() {

//        Student s1 = new Student(12345,"04/03/2000","killeavy2","r@r.com");
//        System.out.println(s1);
//        Student s2 = new Student(s1);
//        System.out.println();
//        System.out.println(s1);
//        System.out.println(s2);



        // load students
        StudentManager studentManager = new StudentManager();

        // load courses
        CourseManager courseManager= new CourseManager();

        // load manager to provide functionality to allow a student
        // to login and add/update their course selections
        // This CourseChoicesManager component depends on the
        // StudentManager and the CourseManager,
        // so we 'inject' or pass-in these objects.
        //
        CourseChoicesManager mgr = new CourseChoicesManager(studentManager, courseManager);

        // display a menu to do things
        // manual testing of mgr public interface

//        if ( mgr.login(22224444, "xxxx","bbbb") )
//        {
//            Student student = mgr.getStudentDetails(22224444);
//
//            System.out.println("Student: " + student);
//        }
//        else
//            System.out.println("Not logged in - try again");


        //mgr.saveToFile();

        //        get getstudent

//        Student s3 = new Student(studentManager.getStudent(12345));
//        System.out.println(s3);
//        Course c3 = new Course("DK556","8"," BSC in Computing in Games Development","DKIT");
//        courseManager.addCourse(c3);
//        courseManager.getCourse("DK555");
        courseManager.getAllCourses();




    }
}
