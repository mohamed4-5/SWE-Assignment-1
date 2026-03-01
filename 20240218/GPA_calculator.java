import java.util.Scanner;
public class GPA_calculator {

    
    public static void main (String args[])
    {
        Scanner in =  new Scanner(System.in);
        
        System.out.println("Please enter number of courses");
        int numofcourses=in.nextInt();
        String [] coursename=new String [numofcourses];
        int [] coursecredithours=new int [numofcourses];
        String [] coursegrade = new String [numofcourses];
        double [] points = new double [numofcourses];
       
        entercourses(in, coursename, coursecredithours, coursegrade, numofcourses);
        calcpoint(points, coursegrade, numofcourses);
        calcGPA(numofcourses, coursecredithours, coursename, coursegrade, points);
        in.close();
    }
    public static void entercourses(Scanner in,String [] coursename,int [] coursecredithours,String [] coursegrade,int numofcourses )
    {
        for (int i=0; i<numofcourses; i++)
        {
            in.nextLine();
            System.out.println("Please enter course "+(i+1) + " name");
            coursename[i]= in.nextLine();
            System.out.println("Please enter credit hours of course "+(i+1));
            coursecredithours[i]= in.nextInt();
            System.out.println("Please enter grade of course "+(i+1));
            coursegrade[i]= in.next();
        }
    }
    public static void calcpoint (double [] points,String [] coursegrade,int numofcourses)
    {
 for (int i=0; i<numofcourses; i++)
        {
            if (coursegrade[i].equals("A+"))
            {
                points[i] =4;
            }
            else if (coursegrade[i].equals("A"))
            {
                points[i] =3.7;
            }
            else if (coursegrade[i].equals("A-"))
            {
                points[i] =3.4;
            }
            else if (coursegrade[i].equals("B+"))
            {
                points[i] =3.2;
            }
            else if (coursegrade[i].equals("B"))
            {
                points[i] =3;
            }
            else if (coursegrade[i].equals("B-"))
            {
                points[i] =2.8;
            }
            else if (coursegrade[i].equals("C+"))
            {
                points[i] =2.6;
            }
            else if (coursegrade[i].equals("C"))
            {
                points[i] =2.4;
            }            
            else if (coursegrade[i].equals("C-"))

            {
                points[i] =2.2;
            }
            else if (coursegrade[i].equals("D+"))
            {
                points[i] =2;
            }            else if (coursegrade[i].equals("D"))

            {
                points[i] =1.5;
            }            
            else if (coursegrade[i].equals("D-"))

            {
                points[i] =1;
            }
            else if (coursegrade[i].equals("F"))
            {
                points[i] =0;
            }
            
            
        }
    }
    public static void calcGPA(int numofcourses , int []coursecredithours , String [] coursename , String  [] coursegrade, double [] points)
    {
    double GPA = 0;

    for (int i=0; i< numofcourses; i++)
    {
        GPA = GPA + points[i]* coursecredithours[i];
    }
    displayGPA(GPA, numofcourses, coursecredithours, coursename, coursegrade);

    }
    public static void displayGPA(double GPA, int numofcourses , int []coursecredithours , String [] coursename , String  [] coursegrade)
    {
        System.out.println("Your courses are: ");

        for (int i=0; i< numofcourses; i++)
        {

            System.out.println("Course "+(i+1)+": " + coursename[i] + " Credit Hours: "+coursecredithours[i] +" Grade: "+coursegrade[i]);
        }
        int totalcredihours=0;
        for (int i=0; i<numofcourses; i++)
        {
            totalcredihours = totalcredihours+ coursecredithours[i];
        }
        GPA= GPA /totalcredihours;
        System.out.printf("Your GPA is: %.2f", GPA);
        System.out.print("\n");
        System.out.print("Your Grade is: ");
        if(GPA >=3.5 )
            System.out.println("Excellent");
        else if (GPA >=3 && GPA <3.5)
            System.out.println("Very Good");
        else if (GPA<3 && GPA >=2.5)
            System.out.println("Good");
        else if(GPA <2.5 &&GPA >=2)
            System.out.println("Acceptable");
    }
}