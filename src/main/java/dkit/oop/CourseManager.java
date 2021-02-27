package dkit.oop;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * CoursesManager
 * This software component Encapsulates the storage and management of
 * all courses available through the CAO system.
 * Only administrators would typically be allowed to update this data,
 * but other users can get a COPY of all the courses by calling getAllCourses().
 * The Web Client would need this data to display the course codes,
 * course titles and institutions to the student.
 */

public class CourseManager {

    private Map<String,Course> courseMap= new HashMap<>();


    public CourseManager() {
        loadcources();


    }

    public Course getCourse(String courseID) {

        Course course = this.courseMap.get(courseID);
        Course copy = new Course(course);
        return copy;
    }


    public ArrayList<Course> getAllCourses() {

        ArrayList<Course> courses = new ArrayList<>();
        Set<String> keySet = courseMap.keySet();

        for (String key : keySet)
        {
            Course c = courseMap.get(key);
            Course Copy = new Course(c);
            courses.add(c);

        }

        return courses;
    }

public void addCourse(Course c) {


    Course copy = new Course(c);
    if (copy == null)
        throw new IllegalArgumentException();
    courseMap.put(copy.getCourseId(),copy);

}

    public void removeCourse(String courseId) {

        courseMap.remove(courseId);

    }

    public void loadcources() {

        try (Scanner sc = new Scanner(new File("courses.txt"));) {
            sc.useDelimiter("[,\r\n]+");
            while (sc.hasNext())
            {

                String courseid = sc.next();
                String level = sc.next();
                String title = sc.next();
                String insitute = sc.next();

                Course s = new Course(courseid,level,title,insitute);
                Course s1 = new Course(s);

                courseMap.put(s1.getCourseId(),s1);
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

        try(BufferedWriter coursefile = new BufferedWriter(new FileWriter("courses.txt"))){
           Set<String> keyset = courseMap.keySet();
            for(String key:keyset){
                coursefile.write(courseMap.get(key).getCourseId()+","+courseMap.get(key).getLevel()+","+courseMap.get(key).getTitle()+","+courseMap.get(key).getInstitution());
                coursefile.write("\n");
            }


        } catch (IOException e) {
            System.out.println("could not save courrse data");
        }
    }



}







