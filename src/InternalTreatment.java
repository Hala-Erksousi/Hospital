import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class InternalTreatment extends Treatment{
    private int departmentNum;
    private List<PhysicianRecord> supervisingPH = new ArrayList<>();
    private Date dischargingD = null;
    public InternalTreatment(Date startD, int cost, int departmentNum, List<PhysicianRecord> supervisingPH, Date dischargingD) {
        super(startD, cost);
        this.departmentNum = departmentNum;
        this.supervisingPH = supervisingPH;
        this.dischargingD = dischargingD;
    }
    public static Treatment readIntTreatment(){
        Scanner read = new Scanner(System.in);
        Date startD = Reading.return_date("Enter Start Date");
        int cost = Reading.return_int("Enter cost");
        List<PhysicianRecord> supPH = new ArrayList<>();
        int num = Reading.return_int("Enter the number of supervisors on this patient's emergency treatment");
        while(num > PhysicianRecord.getPhysicianRecords().size()){
            System.out.println("Sorry, there are not enough doctors");
            num = Reading.return_int("Enter the number of supervisors on this patient's emergency treatment");
        }
        System.out.println("Enter patient supervisors' IDs");
        for(int i=0; i<num; i++){
            boolean ok = false;
            int PHID;
            PhysicianRecord PHR;
            do{
                ok = false;
                System.out.print((i+1) + " : ");
                PHID = read.nextInt();
                PHR = PhysicianRecord.get_physicianRecordFromID(PHID);
                for(PhysicianRecord ExiPHR : supPH){
                    if(PHR.equals(ExiPHR)){
                        System.out.println(PHR.getName() + " is already a supervisor");
                        ok = true;
                        break;
                    }
                }
                if(!ok){
                    supPH.add(PHR);
                }else{
                    System.out.println("please enter again");
                }
            }while(ok);
            if(PHR.getType()==PhysicianType.Contracted){
                PHR.setSalary(PHR.getSalary()+(0.5*(double) cost/num));
            }
        }
        Treatment IntT = new InternalTreatment(startD, cost, 1, supPH, null);
        System.out.println("Treatment's ID "+ IntT.getNumOfTreatment());
        return IntT;
    }
    public static Treatment readIntTreatment(Date accD){
        Scanner read = new Scanner(System.in);
        int cost = Reading.return_int("Enter cost");
        int depNum = Reading.return_departNum();
        List<PhysicianRecord> supPH = new ArrayList<PhysicianRecord>();
        int num = Reading.return_int("Enter the number of supervisors on this patient's internal treatment");
        while(num > PhysicianRecord.getPhysicianRecords().size()){
            System.out.println("Sorry, there are not enough doctors");
            num = Reading.return_int("Enter the number of supervisors on this patient's emergency treatment");
        }
        System.out.println("Enter patient supervisors' IDs");
        for(int i=0; i<num; i++){
            boolean ok = false;
            int PHID;
            PhysicianRecord PHR;
            do{
                ok = false;
                System.out.print((i+1) + " : ");
                PHID = read.nextInt();
                PHR = PhysicianRecord.get_physicianRecordFromID(PHID);
                for(PhysicianRecord ExiPHR : supPH){
                    if(PHR.equals(ExiPHR)){
                        System.out.println(PHR.getName() + " is already a supervisor");
                        ok = true;
                        break;
                    }
                }
                if(!ok){
                    supPH.add(PHR);
                }else{
                    System.out.println("please enter again");
                }
            }while(ok);
            if(PHR.getType()==PhysicianType.Contracted){
                PHR.setSalary(PHR.getSalary()+(0.5*(double) cost/num));
            }
        }
        Treatment IntT = new InternalTreatment(accD, cost, depNum, supPH, null);
        System.out.println("Treatment's ID "+ IntT.getNumOfTreatment());
        return IntT;
    }
    /*public InternalTreatment(){
        Scanner read = new Scanner(System.in);
        departmentNum = Reading.return_departNum();
        this.setStartD(Reading.return_date("Enter Acceptance Date"));
        int num = Reading.return_int("enter the number of supervisors on this patients internal treatment");
        System.out.println("enter patient supervisors' IDs");
        for(int i=0; i<num; i++){
            System.out.print((i+1) + " ");
            int PHID = read.nextInt();
            PhysicianRecord PHR = PhysicianRecord.get_physicianRecordFromID(PHID);
            supervisingPH.add(PHR);
        }

    }*/
    /*public InternalTreatment(Date ExAcceptanceD){
        super(ExAcceptanceD);
        Scanner read = new Scanner(System.in);
        departmentNum = Reading.return_departNum();
        int num = Reading.return_int("enter the number of supervisors on this patients internal treatment");
        System.out.println("enter patient supervisors' ID");
        for(int i=0; i<num; i++){
            System.out.print((i+1) + " ");
            int PHID = read.nextInt();
            PhysicianRecord PHR = PhysicianRecord.get_physicianRecordFromID(PHID);
            for(PhysicianRecord ExiPHR:supervisingPH){
                if(PHR.equals(ExiPHR)){
                    System.out.println(PHR.getName() + " is already a supervisor");
                    continue;
                }
                supervisingPH.add(PHR);
            }
        }
    }*/
    public int getDepartmentNum() {
        return departmentNum;
    }

    public void setDepartmentNum(int departmentNum) {
        this.departmentNum = departmentNum;
    }

    public List<PhysicianRecord> getSupervisingPH() {
        return supervisingPH;
    }

    public void setSupervisingPH(List<PhysicianRecord> supervisingPH) {
        this.supervisingPH = supervisingPH;
    }

    public Date getDischargingD() {
        return dischargingD;
    }

    public void setDischargingD(Date dischargingD) {
        this.dischargingD = dischargingD;
    }
    public void printTreatmentInfo() {
        super.printTreatmentInfo();
        System.out.println("Department num : " + departmentNum);
        System.out.println("Supervising physicians : ");
        this.printAllSupervisingPHs();
    }
    public void printAllSupervisingPHs(){
        for(PhysicianRecord PHR : supervisingPH){
//            PHR.printInfo();
            System.out.println(PHR.getName());
        }
    }
}
