package dkit.oop;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    StudentManager studentManager = new StudentManager();
    CourseManager courseManager = new CourseManager();
    CourseChoicesManager mgr = new CourseChoicesManager(studentManager, courseManager);
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testStudentClone(){
        Student S1 = new Student(1234, "2000-08-25", "Password123", "email@email.com");
        Student S2 = new Student(S1);

        assert(S1 != S2);

        assert(S1.equals(S2));

        S2.setPassword("Hello123");

        assert(S1.equals(S2)); // should fail

    }

    @Test
    public void addstudent(){
        Student s1 = new Student(34567,"10/04/1999","killeavy","i@i.com");

        mgr.addstudent(s1);
        System.out.println(mgr.getStudent(s1.getCaoNumber()));

        // student added to the map

    }
    @Test
    public void removestudent(){
        Student s1 = new Student(34567,"10/04/1999","killeavy","i@i.com");

        mgr.addstudent(s1);
        mgr.getStudent(s1.getCaoNumber());
        mgr.removeStudent(s1.getCaoNumber());
        System.out.println(mgr.getStudent(s1.getCaoNumber()));

        //null returned

    }
    @Test
    public void addCourse(){
        Course s1 = new Course("DK109","level 8","computers","DKIT");

        mgr.addCourse(s1);
        System.out.println(mgr.getAllCourses());
        // course is added to the map

    }
    @Test
    public void removeCourse(){
        Course s1 = new Course("DK109","level 8","computers","DKIT");

        mgr.addCourse(s1);
        mgr.getCourse(s1.getCourseId());
        mgr.removeCourse(s1.getCourseId());
        System.out.println(mgr.getCourse(s1.getCourseId()));
        // should be null

    }

    @Test
    public void updatechoices(){
        Course c1 = new Course("DK109","level 8","computers","DKIT");

        List<String>update = new ArrayList<>();
        String course = "DK100";
        update.add(course);

        mgr.updateChoices(12345,update);
        System.out.println("here");
        System.out.println(mgr.getStudentChoices(12345));
        // choice updated

    }



}
