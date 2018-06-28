package Models;

public class Person {

    int ID, local;

    String Name;
    String Family;

    String Grade;

    public Person() {

    }

    public Person(String name,String family) {
        Name = name;
        Family = family;
    }

    public Person(String name,String family,int local,String grade) {
        this.Name = name;
        this.Family = family;
        this.local = local;
        this.Grade = grade;
    }

    public Person(int id, String name, String family) {
        this.ID = id;
        this.Name = name;
        this.Family = family;
    }

    public int getPersonID() {
        return ID;
    }

    public void setPersonID(int ID) {
        this.ID = ID;
    }

    public String getPersonName() {
        return Name;
    }

    public void setPersonName(String name) {
        Name = name;
    }

    public String getPersonFamily() {
        return Family;
    }

    public void setPersonFamily(String family) {
        Family = family;
    }

    public int getLocal() {
        return local;
    }

    public void setLocal(int local) {
        this.local = local;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }
}
