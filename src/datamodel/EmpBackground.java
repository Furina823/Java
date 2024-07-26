package datamodel;

import utility.TextFileModifier;
import java.util.ArrayList;

public class EmpBackground {

    private String empID;
    private String workingExperience;
    private String educationLevel;
    private String professionalCertification;
    private String professionalMembership;
    private String skills;
    private String languageProficiency;

    public EmpBackground() {
    }

    public EmpBackground(String empID, String workingExperience, String educationLevel, String professionalCertification,
                         String professionalMembership, String skills, String languageProficiency) {
        this.empID = empID;
        this.workingExperience = workingExperience;
        this.educationLevel = educationLevel;
        this.professionalCertification = professionalCertification;
        this.professionalMembership = professionalMembership;
        this.skills = skills;
        this.languageProficiency = languageProficiency;
    }

    // Getters and setters in one line for brevity
    public String getEmpID() { return empID; }
    public void setEmpID(String empID) { this.empID = empID; }

    public String getWorkingExperience() { return workingExperience; }
    public void setWorkingExperience(String workingExperience) { this.workingExperience = workingExperience; }

    public String getEducationLevel() { return educationLevel; }
    public void setEducationLevel(String educationLevel) { this.educationLevel = educationLevel; }

    public String getProfessionalCertification() { return professionalCertification; }
    public void setProfessionalCertification(String professionalCertification) { this.professionalCertification = professionalCertification; }

    public String getProfessionalMembership() { return professionalMembership; }
    public void setProfessionalMembership(String professionalMembership) { this.professionalMembership = professionalMembership; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public String getLanguageProficiency() { return languageProficiency; }
    public void setLanguageProficiency(String languageProficiency) { this.languageProficiency = languageProficiency; }

    public ArrayList<EmpBackground> getRecords() {
        ArrayList<EmpBackground> records = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("background");
        for (String[] s : tfm.toArrayListofStringArray()) {
            EmpBackground empBackground = new EmpBackground(s[0], s[1], s[2], s[3], s[4], s[5], s[6]);
            records.add(empBackground);
        }
        return records;
    }

    public EmpBackground getRecordByID(String empID) {
        TextFileModifier tfm = new TextFileModifier("background");
        for (String[] s : tfm.toArrayListofStringArray()) {
            if (s[0].equals(empID)) {
                return new EmpBackground(s[0], s[1], s[2], s[3], s[4], s[5], s[6]);
            }
        }
        return null;
    }
}
