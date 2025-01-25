import java.util.Date;
import java.util.Scanner;

public class ExternalTreatment extends Treatment{
    private Date acceptanceD = null;
    private int clinicNum;
    private PhysicianRecord physicianRecord;

    public ExternalTreatment(Date startD, int cost, Date acceptanceD, int clinicNum, PhysicianRecord physicianRecord) {
        super(startD, cost);
        this.acceptanceD = acceptanceD;
        this.clinicNum = clinicNum;
        this.physicianRecord = physicianRecord;
    }

    public static Treatment readExtTreatment() {
        Scanner read = new Scanner(System.in);
        Date startD = Reading.return_date("Enter Start Date");
        int cost = Reading.return_int("Enter cost");
        int clinicNum = Reading.return_clinicNum();
        int PHID = Reading.return_int("Enter Physician ID ");
        PhysicianRecord PHR = PhysicianRecord.get_physicianRecordFromID(PHID);
        if(PHR.getType() == PhysicianType.Contracted){
            PHR.setSalary(PHR.getSalary()+(0.5*cost));
        }
        Treatment exT = new ExternalTreatment(startD, cost,null, clinicNum, PHR);
        System.out.println("Treatment's ID "+ exT.getNumOfTreatment());
        return exT;
    }
    public Date getAcceptanceD() {
        return acceptanceD;
    }

    public void setAcceptanceD(Date acceptanceD) {
        this.acceptanceD = acceptanceD;
    }

    public int getClinicNum() {
        return clinicNum;
    }

    public void setClinicNum(int clinicNum) {
        this.clinicNum = clinicNum;
    }

    public PhysicianRecord getPhysicianRecord() {
        return physicianRecord;
    }

    public void setPhysicianRecord(PhysicianRecord physicianRecord) {
        this.physicianRecord = physicianRecord;
    }
    public void printTreatmentInfo(){
        super.printTreatmentInfo();
        System.out.println("clinic num : " + clinicNum);
        System.out.println("Supervising Physician : "+ physicianRecord.getName());
        if(acceptanceD == null){

            System.out.println("--------------------------------------");
        }else{
            System.out.println("patient was accepted in one of the hospitals' departments in " + acceptanceD);
            System.out.println("--------------------------------------");
        }
    }
}
