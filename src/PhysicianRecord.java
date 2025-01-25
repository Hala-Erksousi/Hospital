import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PhysicianRecord extends Record{
    private static List<PhysicianRecord> physicianRecords = new ArrayList<>();
    private static List<PhysicianRecord> archive = new ArrayList<>();
    private static int counter = 1;
    private static int FSalary = 500000;
    private PhysicianType type;
    private double salary;
    //    Fixed
    private int numOfDepart = 0;
    //    Intern
    private Date internStartD = null;
    private Date internEndD = null;
    //    Contracted
    private Date startD = null;
    private Date endD = null;
    private static int ContractCount = 0;

    // Fixed Physician record
    public PhysicianRecord(String name, Address address, Date birthD, PhysicianType type, int salary, int numOfDepart, Date internStartD, Date internEndD, Date startD, Date endD) {
        super(name, address,birthD);
        this.type = type;
        this.salary = salary;
        this.numOfDepart = numOfDepart;
        this.internStartD = internStartD;
        this.internEndD = internEndD;
        this.startD = startD;
        this.endD = endD;
        this.setID(counter);
        counter++;
        if (this.type == PhysicianType.Contracted) {
            ContractCount++;
        }
        physicianRecords.add(this);
    }
    public static PhysicianRecord readFixedPhysician(){
        Scanner read = new Scanner(System.in);
        System.out.println("Enter name");
        String name = read.nextLine();
        System.out.println("Enter address: city, district, details");
        String city = read.nextLine();
        String district = read.nextLine();
        String details = read.nextLine();
        Address address = new Address(city, district, details);
        Date birthD = Reading.return_date("Enter birthday");
        int numD = Reading.return_departNum();
        Date SD = Reading.return_date("Enter start date");
        PhysicianRecord PHR = new PhysicianRecord(name,address,birthD,PhysicianType.Fixed, FSalary, numD, null, null, SD, null);
        System.out.println("your ID is "+ PHR.getID());
        return PHR;
    }

    public static PhysicianRecord readInternPhysician(){
        Scanner read = new Scanner(System.in);
        System.out.println("Enter name");
        String name = read.nextLine();
        System.out.println("Enter address: city, district, details");
        String city = read.nextLine();
        String district = read.nextLine();
        String details = read.nextLine();
        Address address = new Address(city, district, details);
        Date birthD = Reading.return_date("Enter birthday");
        Date intSD = Reading.return_date("Enter start date");
        Date todayD = new Date();
        int salary;
        long diff = todayD.getTime() - intSD.getTime();
        long years = diff / (1000L * 60 * 60 * 24 * 365);
        if (years < 1) {
            salary = (int) (FSalary * 0.5);
        } else{/* if(years < 2){*/
            salary = (int) (FSalary * 0.75);
        }/*else{
            System.out.println("is not an intern, please enter as a fixed physician");
            return readFixedPhysician();
        }*/
        PhysicianRecord PHR = new PhysicianRecord(name,address,birthD, PhysicianType.Intern, salary, 0, intSD, null, null, null);
        System.out.println("your ID is "+ PHR.getID());
        return PHR;
    }

    public static PhysicianRecord readContractedPhysician(){
        Scanner read = new Scanner(System.in);
        System.out.println("Enter name");
        String name = read.nextLine();
        System.out.println("Enter address: city, district, details");
        String city = read.nextLine();
        String district = read.nextLine();
        String details = read.nextLine();
        Address address = new Address(city, district, details);
        Date birthD = Reading.return_date("Enter birthday");
        Date SD = Reading.return_date("Enter start date");
        PhysicianRecord PHR = new PhysicianRecord(name,address,birthD,PhysicianType.Contracted, 0, 0, null, null, SD, null);
        System.out.println("your ID is "+ PHR.getID());
        return PHR;
    }
    public static void readPhysician() {
        Scanner read = new Scanner(System.in);
        PhysicianRecord PHR;
        do {
            System.out.println("For a Fixed enter 1\nFor an Intern enter 2\nFor a Contractor enter 3");
            int num = read.nextInt();
            switch (num) {
                case 1: {
                    PHR = readFixedPhysician();
                    break;
                }
                case 2: {
                    PHR = readInternPhysician();
                    break;
                }
                case 3: {
                    PHR = readContractedPhysician();
                    break;
                }
                default :{
                    PHR = null;
                    System.out.println("Wrong input, please try again");
                    break;
                }
            }
        }while(PHR==null);
    }
    public static void numOfContract() {
        System.out.println(ContractCount);
    }
    public void acceptPatient(PatientRecord PR, Treatment t, Date acc){
        ((ExternalTreatment) t).setAcceptanceD(acc);
        PR.getTreatments().add(InternalTreatment.readIntTreatment(acc));
    }
    public static void physicianAcceptsPatient() {
        Scanner read = new Scanner(System.in);
        int PHID = Reading.return_int("Hello physician please enter your ID to continue accepting your patient");
        PhysicianRecord PHR = PhysicianRecord.get_physicianRecordFromID(PHID);
        if(PHR==null) {
            return;
        }
        int PID = Reading.return_int("enter patient's ID");
        PatientRecord PR = PatientRecord.get_patientRecordFromID(PID);
        if(PR==null){
            return;
        }
        int TID = Reading.return_int("enter treatment's ID");
        Treatment t = PatientRecord.get_treatmentFromID(TID, PR);
        if(t==null){
            return;
        }
        while(!(t instanceof ExternalTreatment)) {
            System.out.println("wrong patient ID or Treatment ID, please try again");
            PR = PatientRecord.get_patientRecordFromID(PID);
            if(PR==null)
                return;
            t = PatientRecord.get_treatmentFromID(TID, PR);
            if(t==null)
                return;
        }
        if(((ExternalTreatment)t).getAcceptanceD()!=null){
            System.out.println("This treatment has already ended");
            return;
        }
        if(((ExternalTreatment)t).getPhysicianRecord()!=PHR){
            System.out.println("Sorry, this is not your patient");
            return;
        }
        Date acc = Reading.return_date("enter acceptance date");
        PHR.acceptPatient(PR, t, acc);
    }
    public static PhysicianRecord get_physicianRecordFromID(int ID){
        if(!(physicianRecords.isEmpty())){
            for(PhysicianRecord PHR : physicianRecords){
                if(PHR.getID()==ID){
                    return PHR;
                }
            }
            System.out.println("Sorry wrong ID please enter again ");
            int newID = Reading.return_int("Enter ID of the physician ");
            return get_physicianRecordFromID(newID);
        }
        else{
            System.out.println("Sorry, there are no physicians in the hospital yet");
            return null;
        }
    }
    public void printInfo(){
        super.printInfo();
        System.out.println(this.getName() + " is a " + this.type + " doctor");
        if(this.type == PhysicianType.valueOf("Fixed")) {
            System.out.println(this.getName() + " works in department number "+ numOfDepart);
            if(this.internStartD == null) {
                System.out.println(this.getName() + " didn't get his/her internship from our hospital");
            } else {
                System.out.println(this.getName() + "was an intern from " + internStartD + " to " + internEndD);
            }
        }if(this.type == PhysicianType.valueOf("Intern")){
            if(this.internEndD==null){
                System.out.println(this.getName() + " started working in " + internStartD + " and is still working");
            }
            else{
                System.out.println(this.getName() + " started working in " + internStartD + " and left in " + internEndD);
            }
        }else {
            if (this.endD == null) {
                System.out.println(this.getName() + " started working in " + startD + " and is still working");
            } else {
                System.out.println(this.getName() + " started working in " + startD + " and left in " + endD);
            }
        }
        if(this.type == PhysicianType.valueOf("Contracted")){
            System.out.println("Fee is "+ salary);
            System.out.println("--------------------------------------");

        }else{
            System.out.println("Salary is "+ salary);
            System.out.println("--------------------------------------");

        }
    }

    public static int getFSalary() {
        return FSalary;
    }

    public static void setFSalary(int FSalary) {
        PhysicianRecord.FSalary = FSalary;
    }

    public PhysicianType getType() {
        return type;
    }

    public void setType(PhysicianType type) {
        this.type = type;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getNumOfDepart() {
        return numOfDepart;
    }

    public void setNumOfDepart(int numOfDepart) {
        this.numOfDepart = numOfDepart;
    }

    public Date getStartD() {
        return startD;
    }

    public void setStartD(Date startD) {
        this.startD = startD;
    }

    public Date getEndD() {
        return endD;
    }

    public void setEndD(Date endD) {
        this.endD = endD;
    }

    public Date getInternStartD() {
        return internStartD;
    }

    public void setInternStartD(Date internStartD) {
        this.internStartD = internStartD;
    }

    public Date getInternEndD() {
        return internEndD;
    }

    public void setInternEndD(Date internEndD) {
        this.internEndD = internEndD;
    }

    public static List<PhysicianRecord> getPhysicianRecords() {
        return physicianRecords;
    }

    public static void setPhysicianRecords(List<PhysicianRecord> physicianRecords) {
        PhysicianRecord.physicianRecords = physicianRecords;
    }
    public void remove(Date endD){
        archive.add(this);
        physicianRecords.remove(this);
        if(this.getType() == PhysicianType.Intern){
            this.internEndD = endD;
        }else{
            this.endD = endD;
        }
        if(this.getType() == PhysicianType.Contracted){
            ContractCount--;
        }
    }
    public static void removePHR(){
        Scanner read = new Scanner(System.in);
        int ID = Reading.return_int("enter ID to remove physician");
        PhysicianRecord PHR = PhysicianRecord.get_physicianRecordFromID(ID);
        if(PHR==null) {
            return;
        }
        Date endD = Reading.return_date("Enter end Date");
        PHR.remove(endD);
    }
    public void promote(Date promotionD) {
        long diff = promotionD.getTime() - this.internStartD.getTime();
        long years = diff / (1000L * 60 * 60 * 24 * 365);
        if (years >= 2) {
            System.out.println("Congrats!, You're now promoted to a fixed doctor");
            this.type = PhysicianType.valueOf("Fixed");
            this.startD = promotionD;
            this.internEndD = promotionD;
            this.salary = FSalary;
            this.numOfDepart = Reading.return_departNum();
        }else{
            System.out.println("Sorry, you can't be promoted yet");
        }
    }
    public static void promoteInternPhysician(){
        int ID = Reading.return_int("Enter ID of the physician ");
        PhysicianRecord PHR = get_physicianRecordFromID(ID);
        if(PHR==null) {
            return;
        }
        if(PHR.type == PhysicianType.Intern){
            Date promotionD = Reading.return_date("Enter promotion date");
            PHR.promote(promotionD);
        }else{
            System.out.println("Sorry you can't be promoted you're not even an intern");
        }
    }
    public static void printAllPhysiciansDuring(Date startp, Date endp) {
        boolean ok = false;
        for (PhysicianRecord a : archive) {
            if(a.type==PhysicianType.Intern) {
                if(a.getInternStartD().after(endp) || a.getInternEndD().before(startp)) {
                    continue;
                }
                else {
                    a.printInfo();
                    ok = true;
                }
            }else{
                if(a.getStartD().after(endp) || a.getEndD().before(startp)) {
                    continue;
                } else {
                    ok = true;
                    a.printInfo();
                }
            }
        }
        for (PhysicianRecord a : physicianRecords) {
            if(a.type==PhysicianType.Intern) {
                if(a.getInternStartD().after(endp)) {
                    continue;
                }
                else {
                    ok = true;
                    a.printInfo();
                }
            }else{
                if(a.getStartD().after(endp)) {
                    continue;
                } else {
                    ok = true;
                    a.printInfo();
                }
            }
        }
        if(!ok){
            System.out.println("Sorry, there are none");
        }
    }
    public static void printAllPhysicians(){
        if(physicianRecords.isEmpty()){
            System.out.println("Sorry there are no doctors working in the hospital now");
        }else{
            for(PhysicianRecord PHR : physicianRecords){
                PHR.printInfo();
            }
        }
    }
    public static void printAllArchive(){
        if(archive.isEmpty()){
            System.out.println("Sorry there are no doctors in the hospital's archive");
        }else{
            for(PhysicianRecord PHR : archive){
                PHR.printInfo();
            }
        }
    }
}
