package datamodel;

import utility.TextFileModifier;
import java.util.ArrayList;

public class Paid {

    private String paidId;
    private String empId;
    private String date;
    private String basicGross;
    private String bonus;
    private String allowance;
    private String lateAttendance;
    private String unpaidLeave;
    private String epfEmpe;
    private String socsoEmpe;
    private String eisEmpe;
    private String pcbEmpe;
    private String epfEmpy;
    private String socsoEmpy;
    private String eisEmpy;
    private String pcbEmpy;

    public Paid() {
    }

    public Paid(String paidId, String empId, String date, String basicGross, String bonus, String allowance, String lateAttendance, String unpaidLeave, String epfEmpe, String socsoEmpe, String eisEmpe, String pcbEmpe, String epfEmpy, String socsoEmpy, String eisEmpy, String pcbEmpy) {
        this.paidId = paidId;
        this.empId = empId;
        this.date = date;
        this.basicGross = basicGross;
        this.bonus = bonus;
        this.allowance = allowance;
        this.lateAttendance = lateAttendance;
        this.unpaidLeave = unpaidLeave;
        this.epfEmpe = epfEmpe;
        this.socsoEmpe = socsoEmpe;
        this.eisEmpe = eisEmpe;
        this.pcbEmpe = pcbEmpe;
        this.epfEmpy = epfEmpy;
        this.socsoEmpy = socsoEmpy;
        this.eisEmpy = eisEmpy;
        this.pcbEmpy = pcbEmpy;
    }

    // Getters and setters in one line for brevity
    public String getPaidId() { return paidId; }
    public void setPaidId(String paidId) { this.paidId = paidId; }

    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getBasicGross() { return basicGross; }
    public void setBasicGross(String basicGross) { this.basicGross = basicGross; }

    public String getBonus() { return bonus; }
    public void setBonus(String bonus) { this.bonus = bonus; }

    public String getAllowance() { return allowance; }
    public void setAllowance(String allowance) { this.allowance = allowance; }

    public String getLateAttendance() { return lateAttendance; }
    public void setLateAttendance(String lateAttendance) { this.lateAttendance = lateAttendance; }

    public String getUnpaidLeave() { return unpaidLeave; }
    public void setUnpaidLeave(String unpaidLeave) { this.unpaidLeave = unpaidLeave; }

    public String getEpfEmpe() { return epfEmpe; }
    public void setEpfEmpe(String epfEmpe) { this.epfEmpe = epfEmpe; }

    public String getSocsoEmpe() { return socsoEmpe; }
    public void setSocsoEmpe(String socsoEmpe) { this.socsoEmpe = socsoEmpe; }

    public String getEisEmpe() { return eisEmpe; }
    public void setEisEmpe(String eisEmpe) { this.eisEmpe = eisEmpe; }

    public String getPcbEmpe() { return pcbEmpe; }
    public void setPcbEmpe(String pcbEmpe) { this.pcbEmpe = pcbEmpe; }

    public String getEpfEmpy() { return epfEmpy; }
    public void setEpfEmpy(String epfEmpy) { this.epfEmpy = epfEmpy; }

    public String getSocsoEmpy() { return socsoEmpy; }
    public void setSocsoEmpy(String socsoEmpy) { this.socsoEmpy = socsoEmpy; }

    public String getEisEmpy() { return eisEmpy; }
    public void setEisEmpy(String eisEmpy) { this.eisEmpy = eisEmpy; }

    public String getPcbEmpy() { return pcbEmpy; }
    public void setPcbEmpy(String pcbEmpy) { this.pcbEmpy = pcbEmpy; }

    public ArrayList<Paid> getRecords() {
        ArrayList<Paid> records = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("paid");
        for (String[] s : tfm.toArrayListofStringArray()) {
            Paid paid = new Paid(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7], s[8], s[9], s[10], s[11], s[12], s[13], s[14], s[15]);
            records.add(paid);
        }
        return records;
    }

    public static Paid getRecordByTaxID(String TaxID) {
        TextFileModifier tfm = new TextFileModifier("paid");
        for (String[] s : tfm.toArrayListofStringArray()) {
            if (s[0].equals(TaxID)) {
                return new Paid(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7], s[8], s[9], s[10], s[11], s[12], s[13], s[14], s[15]);
            }
        }
        return null;
    }

    public static ArrayList<Paid> getRecordByEmpId(String empId) {
        ArrayList<Paid> p = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("paid");
        for (String[] s : tfm.toArrayListofStringArray()) {
            if (s[1].equals(empId)) {
                Paid paid = new Paid(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7], s[8], s[9], s[10], s[11], s[12], s[13], s[14], s[15]);
                p.add(paid);
            }
        }
        return p;
    }

}
