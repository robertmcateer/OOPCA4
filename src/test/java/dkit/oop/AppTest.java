package dkit.oop;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
        mgr.getStudent(s1.getCaoNumber());

    }
    @Test
    public void removestudent(){
        Student s1 = new Student(34567,"10/04/1999","killeavy","i@i.com");

        mgr.addstudent(s1);
        mgr.getStudent(s1.getCaoNumber());
        mgr.removeStudent(s1.getCaoNumber());
        mgr.getStudent(s1.getCaoNumber());

    }



}
