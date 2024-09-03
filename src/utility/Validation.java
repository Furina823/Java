package utility;

import datamodel.Attendance;
import datamodel.Emp;
import datamodel.WorkSchedule;
import rolemodel.*;
import pages.MyFrame;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Validation {

    private String userID;
    private boolean successful;

    private static ArrayList<String> errorUsername = new ArrayList<>();

    public Validation(MyFrame frame, String username, String password) {

        TextFileModifier tfm = new TextFileModifier("employee");
        for (String s : tfm.list) {
            String[] array = s.trim().split(",");
            // Login Success
            if(array[1].trim().equals(username) && array[2].trim().equals(password) && array[3].trim().equals("0")) {
                userID = array[0];

                LocalDate date = LocalDate.now();
                LocalTime now = LocalTime.now();
                DateTimeFormatter hour_min = DateTimeFormatter.ofPattern("HH:mm");
                String hourTime = hour_min.format(now);
                boolean isValid = false;

                for(Attendance as : Attendance.getAttendanceInfo(userID)){

                    if (as.getDate().equals(date.toString())) {
                        isValid = true;
                        break;
                    }
                }

                if(!isValid){
                    TextFileModifier t = new TextFileModifier("attendance");
                    String[] content = getStrings(date, userID, hourTime);
                    t.createRecord(content);
                }

                fetchAllInformation fai = new fetchAllInformation(userID);
                successful = true;
                String role = fai.getEmp().getRole().trim();

                switch (role) {
                    case "Department Manager" -> new DepManager(
                            frame,
                            fai.getEmp(),
                            fai.getPersonalInfo(),
                            fai.getEmpNOKs(),
                            fai.getEmpBackground(),
                            fai.getAttendances(),
                            fai.getLeaves(),
                            fai.getLeaveRequests(),
                            fai.getSalaries(),
                            fai.getPaides(),
                            fai.getPositions(),
                            fai.getBonuses()
                    );
                    case "Employee" -> new Employee(
                            frame,
                            fai.getEmp(),
                            fai.getPersonalInfo(),
                            fai.getEmpNOKs(),
                            fai.getEmpBackground(),
                            fai.getAttendances(),
                            fai.getLeaves(),
                            fai.getLeaveRequests(),
                            fai.getSalaries(),
                            fai.getPaides(),
                            fai.getPositions(),
                            fai.getBonuses()
                    );
                    case "Payroll Officer" -> new PayRollOfficer(
                            frame,
                            fai.getEmp(),
                            fai.getPersonalInfo(),
                            fai.getEmpNOKs(),
                            fai.getEmpBackground(),
                            fai.getAttendances(),
                            fai.getLeaves(),
                            fai.getLeaveRequests(),
                            fai.getSalaries(),
                            fai.getPaides(),
                            fai.getPositions(),
                            fai.getBonuses()
                    );
                    case "Human Resource Officer" -> new HROfficer(
                            frame,
                            fai.getEmp(),
                            fai.getPersonalInfo(),
                            fai.getEmpNOKs(),
                            fai.getEmpBackground(),
                            fai.getAttendances(),
                            fai.getLeaves(),
                            fai.getLeaveRequests(),
                            fai.getSalaries(),
                            fai.getPaides(),
                            fai.getPositions(),
                            fai.getBonuses()
                    );
                    case "System Administrator" -> new SysAdministrator(
                            frame,
                            fai.getEmp(),
                            fai.getPersonalInfo(),
                            fai.getEmpNOKs(),
                            fai.getEmpBackground(),
                            fai.getAttendances(),
                            fai.getLeaves(),
                            fai.getLeaveRequests(),
                            fai.getSalaries(),
                            fai.getPaides(),
                            fai.getPositions(),
                            fai.getBonuses()
                    );
                    default -> successful = false;
                }

                break; // Exit loop once a valid user is found
            }
            else if(array[1].trim().equals(username) && array[2].trim().equals(password) && array[3].trim().equals("null")) {
                DisplayJoption.showMessage("This account hasn't been modified by the Human Resource Officer. Please seek help from the Human Resource Officer.");
                successful = true;
                break;
            }else if(array[1].trim().equals(username) && !array[2].trim().equals(password) && array[3].trim().equals("1") ||
                    array[1].trim().equals(username) && !array[2].trim().equals(password) && array[3].trim().equals("null")){
                DisplayJoption.showMessage("Invalid credentials");
                successful = true;
                break;
            }
            else if(array[1].trim().equals(username) && !array[2].trim().equals(password) && array[3].trim().equals("0")
            ) {
                errorUsername.add(username);
                DisplayJoption.showMessage("Invalid credentials");
                successful = true;
                break;
            } else if (array[1].trim().equals(username) && array[2].trim().equals(password) && array[3].trim().equals("1")){
                DisplayJoption.showMessage("This account has been locked. Please seek assistance from the System Administrator.");
                successful = true;
                break;
            }else {
                successful = false;
            }

        }

        if(hasThreeIdenticalStrings()) {

            Emp emp = Emp.getIDbyEmail(username);

            String[] updatedRecord = {
                    emp.getEmpEmail(),
                    emp.getEmpPassword(),
                    "1",  // Lock the account
                    emp.getDateJoin(),
                    emp.getDateLeave(),
                    emp.getSalary(),
                    emp.getPosition(),
                    emp.getDepartment(),
                    emp.getRole()
            };

            tfm.updateRecord(emp.getEmpID(), updatedRecord);
            DisplayJoption.showMessage("This account has been locked due to 3 wrong attempts.");
            successful = true;
        }
    }

    private static String[] getStrings(LocalDate date, String userID, String hourTime) {
        String a = "";
        String b = "";

        for(WorkSchedule workSchedule : WorkSchedule.getRecords()){
            if(workSchedule.getDate().equals(date.toString())){
                a = workSchedule.getStandardizedStartWorkTime();
                b = workSchedule.getStandardizedEndWorkTime();
            }
        }

        String[] content = {
                userID, date.toString(),
                "Present", a,b,hourTime,"00:00"
        };
        return content;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public static boolean hasThreeIdenticalStrings() {
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String s : errorUsername) {
            frequencyMap.put(s, frequencyMap.getOrDefault(s, 0) + 1);

            if (frequencyMap.get(s) == 3) {
                return true;
            }
        }
        return false;
    }

}
