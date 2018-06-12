package Models;

public class Person {

    int ID;

    String Name, Family;

    public Person() {

    }

    public Person(String name, String family) {
        this.Name = name;
        this.Family = family;
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
}
