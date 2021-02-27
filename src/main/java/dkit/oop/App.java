package dkit.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * Notes:
 *  Synchronisation of multiple reads and writes to file is not considered here.
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("CAO Online - CA4");
        new App().start();
    }

    private void start() {

        // load students
        StudentManager studentManager = new StudentManager();


        // load courses
        CourseManager courseManager = new CourseManager();

        CourseChoicesManager mgr = new CourseChoicesManager(studentManager, courseManager);

        startupmenu(mgr);



    }

    public void startupmenu(CourseChoicesManager mgr) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to CAO");

        boolean menu = false;
        while (!menu) {
            System.out.println();
            System.out.println("1. Student Login");
            System.out.println("2. ADMIN ");
            System.out.println("3. Exit");


            System.out.println("Choose an option");
            System.out.print(": ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    menu1(mgr);
                    break;
                case 2:
                    admin(mgr);
                    break;

                case 3:
                    System.out.println("GoodBye");
                    menu = true;
                    break;
            }
        }
    }

    public void menu1(CourseChoicesManager mgr) {

        Scanner sc = new Scanner(System.in);
        boolean loggedin = false;
        int caonumber = 0;


        while(!loggedin) {

            System.out.println("** Student Menu **");
            System.out.println();
            System.out.println("** Student Login **");
            System.out.println();
            System.out.println("Enter in CAO Number ->");
            caonumber = sc.nextInt();
            System.out.println("Enter Date Of Birth->");
            String DOB = sc.next();
            System.out.println("Enter Password ->");
            String password = sc.next();
            System.out.println("Enter Email ->");
            String email = sc.next();

            if (mgr.login(caonumber, DOB, password)) {
                loggedin = true;


            }
        }

            System.out.println("Welcome Student " + caonumber);

            boolean menu = false;
            while (!menu) {
                System.out.println();
                System.out.println("1. Display a course ");
                System.out.println("2. Display all courses ");
                System.out.println("3. Display Current Choice");
                System.out.println("4. Update Choices");
                System.out.println("5. Logout ");

                System.out.println("Choose an option");
                System.out.print(": ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("1. Display a course ");
                        System.out.println("Enter in a CourseID");
                        String courseid = sc.next();

                        if (mgr.getCourse(courseid)==null)
                        {
                            System.out.println("Course Not Found");
                        }
                        else
                            {
                            System.out.println(mgr.getCourse(courseid));
                        }
                        break;
                    case 2:
                        System.out.println("2. Display all courses ");
                        System.out.println(mgr.getAllCourses());
                        break;

                    case 3:
                        System.out.println("3. Display Current Choice");
                        System.out.println(mgr.getStudentChoices(caonumber));
                        break;
                    case 4:
                        System.out.println("4. Update Choices");
                        System.out.println("How many new Choices would you like to add->");
                        int num = sc.nextInt();
                        List<String> updatechoice = new ArrayList<>();

                        for (int i = 1; i <= num; i++) {
                            System.out.print("Enter Choice "+i+": ");

                            String update = sc.next();
                            updatechoice.add(update);

                        }
                        mgr.updateChoices(caonumber, updatechoice);
                        System.out.println("choices Updated");



                        break;
                    case 5:
                        System.out.println("logout");
                        mgr.saveall();
                        menu = true;
                        break;
                }
            }

        }

        public void admin(CourseChoicesManager mgr){

            Scanner sc = new Scanner(System.in);

            System.out.println("Welcome Admin");

            boolean menu = false;
            while (!menu) {
                System.out.println();
                System.out.println("1. Add Course");
                System.out.println("2. Remove Course ");
                System.out.println("3. Display All Courses");
                System.out.println("4. Display Particular Course (CourseID)");
                System.out.println("5. Add Student ");
                System.out.println("6. Remove Student ");
                System.out.println("7. Display Student ");
                System.out.println("8. Save and Exit ");

                System.out.println("Choose an option");
                System.out.print(": ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("1. Add Course");
                        System.out.println("enter in CourseID");
                        String id = sc.next();
                        System.out.println("enter in level");
                        String level = sc.next();
                        System.out.println("Enter in Title");
                        String title = sc.next();
                        System.out.println("Enter in Institution");
                        String institute = sc.next();
                        Course c = new Course(id,level,title,institute);
                        mgr.addCourse(c);
                        break;
                    case 2:
                        System.out.println("2. Remove Course ");
                        System.out.println("Enter in Course id");
                        String courseid = sc.next();
                        mgr.removeCourse(courseid);

                        break;

                    case 3:
                        System.out.println("3. Display All Courses");
                        System.out.println(mgr.getAllCourses());

                        break;
                    case 4:
                        System.out.println("4. Display Particular Course (CourseID)");
                        System.out.println("Enter in CourseID");
                        String Courseid = sc.next();

                        if (mgr.getCourse(Courseid)==null)
                        {
                            System.out.println("Course Not Found");
                        }else
                        {
                            System.out.println(mgr.getCourse(Courseid));
                        }

                        break;
                    case 5:
                        System.out.println("5. Add Student ");
                        System.out.println("enter in CaoNumber");
                        int cao = sc.nextInt();
                        System.out.println("enter in Date of Birth");
                        String DOB = sc.next();
                        System.out.println("Enter in Password");
                        String password = sc.next();
                        System.out.println("Enter in Email Address");
                        String email = sc.next();
                        Student s = new Student(cao,DOB,password,email);
                        mgr.addstudent(s);

                        break;
                    case 6:
                        System.out.println("6. Remove Student ");
                        System.out.println("Enter in CaoNumber");
                        int num = sc.nextInt();
                        mgr.removeStudent(num);
                        System.out.println("Student "+num+" removed");
                        break;
                    case 7:
                        System.out.println("7. Display Student ");
                        System.out.println("Enter in CaoNumber");
                        int num1 = sc.nextInt();

                        if (mgr.getStudent(num1)==null)
                        {
                            System.out.println("Student not found");
                        } else
                        {
                            System.out.println(mgr.getStudent(num1));
                        }
                        break;
                    case 8:
                        System.out.println("Save And Exit");
                        mgr.saveall();
                        menu = true;
                        break;
                }
            }




        }










    }







