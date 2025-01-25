import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PatientRecord extends Record{
    private static List<PatientRecord> patientRecords = new ArrayList<>();
    private static int counter = 1;
    private List<Treatment> treatments = new ArrayList<>();
    public PatientRecord(String name, Address address, Date birthD){
        super(name,address,birthD);
        this.setID(counter);
        counter++;
        patientRecords.add(this);
    }
    public static void readPatient(){
        Scanner read = new Scanner(System.in);
        System.out.println("Enter name");
        String name = read.nextLine();
        System.out.println("Enter address: city, district, details");
        String city = read.nextLine();
        String district = read.nextLine();
        String details = read.nextLine();
        Address address = new Address(city, district, details);
        Date birthD = Reading.return_date("Enter birthday");
        PatientRecord PR = new PatientRecord(name, address, birthD);
        System.out.println("your ID is "+ PR.getID());
    }
    public static Treatment readTreatment(){
        System.out.println("For External Treatment Enter 1\nFor emergency Treatment Enter 2");
        Scanner read = new Scanner(System.in);
        int num ;
        do{
            num = read.nextInt();
            switch(num){
                case 1:{
                    return ExternalTreatment.readExtTreatment();
                }
                case 2:{
                    return InternalTreatment.readIntTreatment();
                }
                default:{
                    System.out.println("Invalid number enter another");
                    break;
                }
            }
        }while(num != 1 && num!=2);
        return null;
    }
    public void addTreatment(Treatment t){
        this.treatments.add(t);
    }
    public static void addTreatmentToPatient(){
        int ID = Reading.return_int("Enter patient's ID to add a treatment to his record");
        PatientRecord PR = get_patientRecordFromID(ID);
        if(PR == null){
            return;
        }
        Treatment t = readTreatment();
        PR.addTreatment(t);
    }
    public static PatientRecord get_patientRecordFromID(int ID) {
        if(!(patientRecords.isEmpty())){
            for (PatientRecord PR : patientRecords) {
                if (PR.getID() == ID) {
                    return PR;
                }
            }
            System.out.println("Sorry wrong ID please enter again ");
            int newID = Reading.return_int("Enter ID of the patient ");
            return get_patientRecordFromID(newID);
        }
        else{
            System.out.println("Sorry, there are no Patient records yet");
            return null;
        }
    }


    public static Treatment get_treatmentFromID(int ID, PatientRecord PR) {
        if(PR.treatments.isEmpty()){
            System.out.println("Sorry, there are no Treatments for this patient yet");
            return null;
        }
        for (Treatment t : PR.treatments) {
            if (t.getNumOfTreatment() == ID) {
                return t;
            }
        }
        System.out.println("Sorry wrong ID please enter again ");
        int newID = Reading.return_int("Enter ID of the treatment ");
        return get_treatmentFromID(newID, PR);
    }
    public void printPatientInfo() {
        super.printInfo();
        for (Treatment t : this.treatments) {
            if (t instanceof ExternalTreatment) {
                ((ExternalTreatment)t).printTreatmentInfo();
            } else {
                ((InternalTreatment)t).printTreatmentInfo();
            }
        }
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><>");
    }
    public void dischargeFromTreatmentInDate(Treatment t, Date dischargingD){
        ((InternalTreatment)t).setDischargingD(dischargingD);
    }
    public static void dischargePatient(){
        int PID = Reading.return_int("Please enter patient's ID");
        PatientRecord PR = PatientRecord.get_patientRecordFromID(PID);
        if(PR == null){
            return;
        }
        int ID = Reading.return_int("Please enter treatment's ID you want to end");
        Treatment t = PatientRecord.get_treatmentFromID(ID,PR);
        if(t == null){
            return;
        }
        while(!(t instanceof InternalTreatment)){
            System.out.println("Wrong patient or wrong treatment please enter again");
            PID = Reading.return_int("Please enter patient's ID");
            PR = PatientRecord.get_patientRecordFromID(PID);
            if(PR == null){
                return;
            }
            if(((InternalTreatment)t).getDischargingD()!=null){
                System.out.println("This treatment has already ended");
                return;
            }
            ID = Reading.return_int("Please enter treatment's ID you want to end");
            t = PatientRecord.get_treatmentFromID(ID,PR);
            if(t == null){
                return;
            }
        }
        Date dischD = Reading.return_date("Please enter discharging date");
        PR.dischargeFromTreatmentInDate(t,dischD);
    }
    public static void print_All_PRs_During(Date startD, Date endD){
        boolean ok = false;
        for(PatientRecord PR : patientRecords){
            for(Treatment t : PR.getTreatments()){
                if(t instanceof InternalTreatment){
                    if(((InternalTreatment) t).getDischargingD()==null){
                        if(t.getStartD().after(endD)){
                            continue;
                        }
                        else{
                            ok = true;
                            PR.printPatientInfo();
                        }
                    }else{
                        if(t.getStartD().after(endD)|| ((InternalTreatment) t).getDischargingD().before(startD)){
                            continue;
                        }
                        else{
                            ok = true;
                            PR.printPatientInfo();
                        }
                    }
                }
                else{
                    if(((ExternalTreatment) t).getAcceptanceD()==null){
                        if(t.getStartD().after(endD)){
                            continue;
                        }
                        else{
                            PR.printPatientInfo();
                            ok = true;
                        }
                    }else{
                        if(t.getStartD().after(endD)|| ((ExternalTreatment) t).getAcceptanceD().before(startD)){
                            continue;
                        }
                        else{
                            PR.printPatientInfo();
                            ok = true;
                        }
                    }
                }
            }
        }
        if(!ok){
            System.out.println("Sorry, There are none");
        }
    }
    public static void print_numOf_PRs_inDep_During(int depNum, Date startD, Date endD){
        int co = 0;
        for(PatientRecord PR : patientRecords){
            for(Treatment t : PR.getTreatments()){
                if(t instanceof InternalTreatment){
                    if(((InternalTreatment)t).getDepartmentNum()== depNum){
                        if(((InternalTreatment) t).getDischargingD()==null){
                            if(t.getStartD().after(endD)){
                                continue;
                            }
                            else{
                                co++;
                            }
                        }else{
                            if(t.getStartD().after(endD)|| ((InternalTreatment) t).getDischargingD().before(startD)){
                                continue;
                            }
                            else{
                                co++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println( "  Patient number in this department "+ co);
    }
    public void printAllTreatments(){
        for(Treatment t : this.treatments){
            t.printTreatmentInfo();
        }
    }
    public static void printAllTreatmentsOfPatient(){
        int PID = Reading.return_int("Enter patient's ID");
        PatientRecord PR = PatientRecord.get_patientRecordFromID(PID);
        if(PR==null)
        {
            return;
        }
        PR.printAllTreatments();
    }

    public static List<PatientRecord> getPatientRecords() {
        return patientRecords;
    }

    public static void setPatientRecords(List<PatientRecord> patientRecords) {
        PatientRecord.patientRecords = patientRecords;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }
}
