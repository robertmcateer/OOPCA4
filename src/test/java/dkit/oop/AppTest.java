package dkit.oop;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
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
}
