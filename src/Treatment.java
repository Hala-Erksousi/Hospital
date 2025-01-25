import java.util.Date;

public  abstract class Treatment {
    private static int counter = 1;
    private int numOfTreatment;
    private Date startD;
    private int cost;
    public Treatment(Date startD, int cost) {
        numOfTreatment = counter;
        counter++;
        this.startD = startD;
        this.cost = cost;
    }

    /*public Treatment(Date startD) {
        numOfTreatment = counter;
        counter++;
        this.startD = startD;
        this.cost = Reading.return_int("Enter cost");
    }

    public Treatment(){
        numOfTreatment = counter;
        counter++;
        Scanner read = new Scanner(System.in);
        this.startD = Reading.return_date("Enter Start Date");
        this.cost = Reading.return_int("Enter cost");
    }*/

    public int getNumOfTreatment() {
        return numOfTreatment;
    }
    public void setNumOfTreatment(int numOfTreatment) {
        this.numOfTreatment = numOfTreatment;
    }

    public Date getStartD() {
        return startD;
    }

    public void setStartD(Date startD) {
        startD = startD;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    public void printTreatmentInfo()
    {
        System.out.printf("ID of treatment: %d\n",numOfTreatment);
        System.out.println("start date: " + startD);
        System.out.println("cost: " + cost);

    }
}
