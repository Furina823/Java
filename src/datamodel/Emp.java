package datamodel;

import utility.TextFileModifier;

import java.util.ArrayList;

public class Emp {

    public String getEmpID() {
        return empID;
    }

    private String empID;
    private String empEmail;
    private String empPassword;
    private String IsBan;
    private String dateJoin;
    private String dateLeave;
    private String salary;
    private String position;
    private String department;
    private String role;

    public Emp() {
        // Default constructor
    }

    public Emp(String empID, String empEmail, String empPassword, String IsBan, String dateJoin, String dateLeave, String salary, String position, String department, String role) {
        this.empID = empID;
        this.empEmail = empEmail;
        this.empPassword = empPassword;
        this.IsBan = IsBan;
        this.dateJoin = dateJoin;
        this.dateLeave = dateLeave;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.role = role;
    }

    // Getters and setters in a single line
    public void setEmpID(String empID) { this.empID = empID; }

    public String getEmpEmail() { return empEmail; }
    public void setEmpEmail(String empEmail) { this.empEmail = empEmail; }

    public String getEmpPassword() { return empPassword; }
    public void setEmpPassword(String empPassword) { this.empPassword = empPassword; }

    public void setIsBan(String isBan) {IsBan = isBan;}
    public String getIsBan() {return IsBan;}

    public String getDateJoin() { return dateJoin; }
    public void setDateJoin(String dateJoin) { this.dateJoin = dateJoin; }

    public String getDateLeave() { return dateLeave; }
    public void setDateLeave(String dateLeave) { this.dateLeave = dateLeave; }

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }


    public String toString(){
        return "empID: " +  empID + "\n empEmail: " +  empEmail + "\n empPassword: " +  empPassword + "\n dateJoin: " +  dateJoin + "\n dateLeave: " +  dateLeave + "\n salary: " +  salary + "\n position: " +  position + "\n department: " +  department + "\n role: " +  role;
    }

    public ArrayList<Emp> getEmpRecords() {
        ArrayList<Emp> records = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("employee");
        // Fetch records from the text file and populate Emp objects
        for (String[] s : tfm.toArrayListofStringArray()) {
            Emp emp = new Emp(
                    s[0], // empID
                    s[1], // empEmail
                    s[2], // empPassword
                    s[3], // IsBan
                    s[4], // dateJoin
                    s[5], // dateLeave
                    s[6], // salary
                    s[7], // position
                    s[8], // department
                    s[9]  // role
            );
            records.add(emp);
        }

        return records;
    }

    public static Emp getRecord(String empID){
        TextFileModifier tfm  = new TextFileModifier("employee");
        for (String[] s : tfm.toArrayListofStringArray()) {
            Emp emp = new Emp(
                    s[0], // empID
                    s[1], // empEmail
                    s[2], // empPassword
                    s[3], // IsBan
                    s[4], // dateJoin
                    s[5], // dateLeave
                    s[6], // salary
                    s[7], // position
                    s[8], // department
                    s[9]  // role
            );
            if (emp.getEmpID().equals(empID)) {
                return emp;
            }
        }
        return null;
    }


}
