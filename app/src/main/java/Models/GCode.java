package Models;

public class GCode {

    private int ID;
    private String GCode;

    public GCode() { }

    public GCode(String GCode) {
        this.GCode = GCode;
    }

    public GCode(int ID,String GCode) {
        this.ID = ID;
        this.GCode = GCode;
    }

    public int getGCodeID() {
        return ID;
    }

    public void setGCodeID(int ID) {
        this.ID = ID;
    }

    public String getGCode() {
        return GCode;
    }

    public void setGCode(String GCode) {
        this.GCode = GCode;
    }
}
