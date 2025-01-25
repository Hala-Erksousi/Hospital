import java.util.Date;

public  abstract class Record {
    private int ID;
    private String name;
    private Address address;
    private Date birthD;

    public Record(String name, Address address, Date birthD) {
        this.name = name;
        this.address = address;
        this.birthD = birthD;
    }

    public void printInfo() {
        System.out.println("Name : " + this.name);
        System.out.println("ID : " + this.ID);
        System.out.println("Address : " + this.address.getCity() + " : " + this.address.getDistrict() + " : " + this.address.getDetails());
        System.out.println("Date of birth " + this.birthD);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getBirthD() {
        return birthD;
    }

    public void setBirthD(Date birthD) {
        this.birthD = birthD;
    }
}
