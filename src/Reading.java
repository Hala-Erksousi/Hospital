import java.util.Date;
import java.util.Scanner;

public class Reading {
    static Scanner read = new Scanner(System.in);
    public static Date return_date(String print){
        System.out.println(print);
        int day = read.nextInt();
        int month = read.nextInt();
        int year = read.nextInt();
        return new Date(month + "//" + day +"//" + year);
    }
    public static int return_departNum(){
        System.out.println("Enter Department ");
        System.out.println("For Ambulance enter 1\nfor Ophthalmology enter 2\n" +
                "for Thoracic enter 3\nfor GeneralSurgery enter 4");
        int dNum = read.nextInt();
        if(dNum>0 && dNum<5){
            return dNum;
        }
        else {
            System.out.println("wrong input please try again");
            return return_departNum();
        }
    }
    public static int return_clinicNum(){
        System.out.println("For Internal Clinic Enter 1\nFor Eye Clinic Enter 2\n" +
                "for Digestive Clinic Enter 3");
        int dNum = read.nextInt();
        if(dNum>0 && dNum<4){
            return dNum;
        }
        else {
            System.out.println("wrong input please try again");
            return return_clinicNum();
        }
    }

    public static int return_int(String print){
        Scanner read = new Scanner(System.in);
        System.out.println(print);
        int x = read.nextInt();
        return x;
    }
}
