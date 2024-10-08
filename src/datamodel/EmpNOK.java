package datamodel;

import utility.TextFileModifier;
import java.util.ArrayList;
import java.util.List;

public class EmpNOK {

    private String kinID;
    private String empID;
    private String empNextOfKinName;
    private String relationship;
    private String contactNumber;

    public EmpNOK() {
    }

    public EmpNOK( String kinID, String empID,String empNextOfKinName, String relationship, String contactNumber) {
        this.kinID = kinID;
        this.empID = empID;
        this.empNextOfKinName = empNextOfKinName;
        this.relationship = relationship;
        this.contactNumber = contactNumber;
    }

    // Getters and setters in one line for brevity
    public String getEmpID() { return empID; }
    public void setEmpID(String empID) { this.empID = empID; }

    public String getKinID() { return kinID; }
    public void setKinID(String kinID) { this.kinID = kinID; }

    public String getEmpNextOfKinName() { return empNextOfKinName; }
    public void setEmpNextOfKinName(String empNextOfKinName) { this.empNextOfKinName = empNextOfKinName; }

    public String getRelationship() { return relationship; }
    public void setRelationship(String relationship) { this.relationship = relationship; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public static ArrayList<EmpNOK> getRecords() {
        ArrayList<EmpNOK> records = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("next_of_kin");
        for (String[] s : tfm.toArrayListofStringArray()) {
            EmpNOK empNOK = new EmpNOK(s[0], s[1], s[2], s[3], s[4]);
            records.add(empNOK);
        }
        return records;
    }

    public EmpNOK getRecordByID(String empID, String kinID) {
        TextFileModifier tfm = new TextFileModifier("next_of_kin");
        for (String[] s : tfm.toArrayListofStringArray()) {
            if (s[1].equals(empID) && s[0].equals(kinID)) {
                return new EmpNOK(s[0], s[1], s[2], s[3], s[4]);
            }
        }
        return null;
    }

    public static List<EmpNOK> getRecordByEmpID(String empID){
        List<EmpNOK> records = new ArrayList<>();
        for(EmpNOK e : getRecords()){
            if(e.getEmpID().equals(empID)){
            records.add(e);}
        }
        return records;
    }
}
