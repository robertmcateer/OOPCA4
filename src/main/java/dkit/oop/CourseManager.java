package dkit.oop;
//RobertMcateer D00233414

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

    /**
     * copy instance of a course is returned to the system through this method only if the
     * id entered matches one in the map.
     * @param courseID
     * @return
     */
    public Course getCourse(String courseID) {

        if (this.courseMap.containsKey(courseID)){
            Course course = this.courseMap.get(courseID);
            Course copy = new Course(course);
            return copy;
        }
        return null;
    }

    /**
     * returns list of all courses in the system back to the main app
     * @return
     */
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

    /**
     * this method creates a copy of a new course instance
     * @param c
     */
    public void addCourse(Course c) {


    Course copy = new Course(c);
    if (copy == null)
        throw new IllegalArgumentException();
    courseMap.put(copy.getCourseId(),copy);

}

    /**
     * If a course matches the id input it is then removed from the course map.
     *
     * @param courseId
     */
    public void removeCourse(String courseId) {

        if (this.courseMap.containsKey(courseId)){
            courseMap.remove(courseId);
        }else{
            System.out.println("Course Invalid");
        }


    }

    /**
     * method to load in all course data from courses.txt file
     */
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


    /**
     * this method writes back to the txt file once changes has been made to course data by the user
     */
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







