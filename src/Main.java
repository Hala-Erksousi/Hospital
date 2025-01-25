import java.util.Date;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("welcome to the Hospital \nyou should first add the Doctors");
        int number = Reading.return_int("enter the number you want to add of doctors");
        if (number == 0) {
            System.out.println("sorry, you should at least enter one doctor");
            number++;
        }
        for (int i = 0; i < number; i++) {
            PhysicianRecord.readPhysician();
            System.out.println("--------------------------------------");
        }
        int num;
        do {
            System.out.println("if you want to :");
            System.out.println("add new physician Record enter 1");
            System.out.println("remove physician record enter 2");
            System.out.println("Know How much contracted Doctors there are in Hospital enter 3");
            System.out.println("add new Patient record enter 4");
            System.out.println("promote an intern physician enter 5");
            System.out.println("show all Doctors in Hospital within a certain period enter 6");
            System.out.println("add a Treatment to a Patient's record enter 7");
            System.out.println("show records of all Patients treated in all hospital departments during a specific period of time enter 8");
            System.out.println("show all the treatments that a patient has undergone enter 9");
            System.out.println("know the number of patients present in a department during a specific period of time enter 10");
            System.out.println("show all physicians in the Hospital enter 11");
            System.out.println("discharge patient from hospital enter 12");
            System.out.println("accept a patient in a department enter 13");
            System.out.println("EXIT enter 0");
            num = sc.nextInt();
            switch (num) {
                case 1: {
                    System.out.println("enter the number Doctors you want to add");
                    int numDoctors = sc.nextInt();
                    for (int i = 0; i < numDoctors; i++) {
                        PhysicianRecord.readPhysician();
                        System.out.println("--------------------------------------");
                    }
                    break;
                }
                case 2: {
                    PhysicianRecord.removePHR();
                    System.out.println("--------------------------------------");
                    break;
                }
                case 3: {
                    PhysicianRecord.numOfContract();
                    System.out.println("--------------------------------------");
                    break;
                }
                case 4: {
                    System.out.println("enter the number Patient you want to add");
                    int numPatients = sc.nextInt();
                    for (int i = 0; i < numPatients; i++) {
                        PatientRecord.readPatient();
                        System.out.println("--------------------------------------");
                    }
                    break;
                }
                case 5: {
                    PhysicianRecord.promoteInternPhysician();
                    System.out.println("--------------------------------------");
                    break;
                }
                case 6: {
                    Scanner read = new Scanner(System.in);
                    Date startp = Reading.return_date("enter the start date");
                    Date endp = Reading.return_date("enter the  end date");
                    PhysicianRecord.printAllPhysiciansDuring(startp, endp);
                    System.out.println("--------------------------------------");
                    break;
                }
                case 7: {
                    PatientRecord.addTreatmentToPatient();
                    System.out.println("--------------------------------------");
                    break;
                }
                case 8: {
                    Date startD = Reading.return_date("Enter start date");
                    Date endD = Reading.return_date("Enter end date");
                    PatientRecord.print_All_PRs_During(startD,endD);
                    System.out.println("--------------------------------------");
                    break;
                }
                case 9: {
                    PatientRecord.printAllTreatmentsOfPatient();
                    System.out.println("--------------------------------------");
                    break;
                }
                case 10: {
                    int depNum = Reading.return_departNum();
                    Date startD = Reading.return_date("Enter start date");
                    Date endD = Reading.return_date("Enter end date");
                    PatientRecord.print_numOf_PRs_inDep_During(depNum,startD,endD);
                    System.out.println("--------------------------------------");
                    break;
                }
                case 11:
                {
                    PhysicianRecord.printAllPhysicians();
                    System.out.println("--------------------------------------");
                    break;
                }
                case 12: {
                    PatientRecord.dischargePatient();
                    System.out.println("--------------------------------------");
                    break;
                }
                case 13: {
                    PhysicianRecord.physicianAcceptsPatient();
                    System.out.println("--------------------------------------");
                    break;
                }
                case 0:
                {
                    System.out.println("--------------------------------------");
                    break;
                }
                default: {
                    System.out.println("sorry , wrong input please try again");
                    System.out.println("--------------------------------------");
                    break;
                }
            }
        } while (num != 0);
    }
}