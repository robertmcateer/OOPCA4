package dkit.oop;

import java.util.List;
import java.util.Scanner;

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

        // load students
        StudentManager studentManager = new StudentManager();

        // load courses
        CourseManager courseManager= new CourseManager();

        CourseChoicesManager mgr = new CourseChoicesManager(studentManager,courseManager);

        studentmenu(mgr);






    }




    public void studentmenu(CourseChoicesManager mgr){

        Scanner sc = new Scanner(System.in);
        System.out.println("** Student Menu **");
        System.out.println();
        System.out.println("** Student Login **");
        System.out.println();
        System.out.println("Enter in CAO Number ->");
        int caonumber = sc.nextInt();
        System.out.println("Enter Date Of Birth->");
        String DOB = sc.next();
        System.out.println("Enter Password ->");
        String password = sc.next();
        System.out.println("Enter Email ->");
        String email = sc.next();


        if (mgr.login(caonumber,DOB,password)!=true){
            System.out.println("Login Details incorrect: Try again.");
        }
        else{
            System.out.println("Welcome Student "+caonumber);

            boolean menu = false;
            while(menu)
            {
                System.out.println();
                System.out.println("1. Display a course ");
                System.out.println("2. Display all courses ");
                System.out.println("3. Display Current Choice");
                System.out.println("4. Update Choices");
                System.out.println("5. Logout ");

                System.out.println("Choose an option");
                System.out.print(": ");

                int choice = sc.nextInt();

                switch (choice)
                {
                    case 1:

                        break;
                    case 2:

                        break;

                    case 3:

                        break;
                    case 4:
                        ;
                        break;
                    case 5:
                        System.out.println("logout");
                        menu = false;
                        break;
                }
            }

        }









    }
}
