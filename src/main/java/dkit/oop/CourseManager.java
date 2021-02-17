package dkit.oop;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    private Map<String,Course> courseMap;

    public CourseManager() {
        Course c1 = new Course("DK821","8"," BSC in Computing in Software Developemt","DKIT");
        Course c2 = new Course("DK555","8"," BSC in Computing in Games Development","DKIT");

        this.courseMap = new HashMap<>();
        courseMap.put(c1.getCourseId(),c1);
        courseMap.put(c2.getCourseId(),c2);

    }

    public Course getCourse(String courseID) {

        Course copy = null;
        Course course = this.courseMap.get(courseID);

        if (course!= null)
        {
            copy = new Course(course);
        }
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
    if (copy != null)
        throw new IllegalArgumentException();
    courseMap.put(copy.getCourseId(),copy);

}

    public void removeCourse(String courseId) {

        courseMap.remove(courseId);

    }

    // editCourse(courseId);       // not required for this iteration

}







