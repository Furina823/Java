package datamodel;

import utility.TextFileModifier;

import java.util.ArrayList;

public class PersonalInfo {

    private String empid;
    private String firstname;
    private String lastname;
    private String nric;
    private String passport;
    private String gender;
    private String date_of_birth;
    private String place_of_birth;
    private String race;
    private String religious;
    private String contact_number;
    private String mailing_address;
    private String email;
    private String marital_status;

    // Default constructor
    public PersonalInfo() {
    }

    // Parameterized constructor
    public PersonalInfo(String empid, String firstname, String lastname, String nric, String passport, String gender,
                        String date_of_birth, String place_of_birth, String race, String religious, String contact_number, String mailing_address, String email, String marital_status) {
        this.empid = empid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nric = nric;
        this.passport = passport;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.place_of_birth = place_of_birth;
        this.race = race;
        this.religious = religious;
        this.contact_number = contact_number;
        this.mailing_address = mailing_address;
        this.email = email;
        this.marital_status = marital_status;
    }

    // Getters and setters
    public String getEmpid() { return empid; }
    public void setEmpid(String empid) { this.empid = empid; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getNric() { return nric; }
    public void setNric(String nric) { this.nric = nric; }

    public String getPassport() { return passport; }
    public void setPassport(String passport) { this.passport = passport; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDate_of_birth() { return date_of_birth; }
    public void setDate_of_birth(String date_of_birth) { this.date_of_birth = date_of_birth; }

    public String getPlace_of_birth() { return place_of_birth; }
    public void setPlace_of_birth(String place_of_birth) { this.place_of_birth = place_of_birth; }

    public String getRace() { return race; }
    public void setRace(String race) { this.race = race; }

    public String getReligious() { return religious; }
    public void setReligious(String religious) { this.religious = religious; }

    public String getContact_number() { return contact_number; }
    public void setContact_number(String contact_number) { this.contact_number = contact_number; }

    public String getMailing_address() { return mailing_address; }
    public void setMailing_address(String mailing_address) { this.mailing_address = mailing_address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMarital_status() { return marital_status; }
    public void setMarital_status(String marital_status) { this.marital_status = marital_status; }

    // Method to retrieve personal info based on empID
    public static PersonalInfo getPersonalInfo(String empID) {
        TextFileModifier tfm = new TextFileModifier("personal_information");
        for (String[] array : tfm.toArrayListofStringArray()) {
            if (array[0].equals(empID)) {
                return new PersonalInfo(
                        array[0], // empid
                        array[1], // firstname
                        array[2], // lastname
                        array[3], // nric
                        array[4], // passport
                        array[5], // gender
                        array[6], // date_of_birth
                        array[7], // place_of_birth
                        array[8], // race
                        array[9], // religious
                        array[10], // contact_number
                        array[11], // mailing_address
                        array[12], // email
                        array[13]  // marital_status
                );
            }
        }
        // Return null or handle case where empID is not found
        return null;
    }

    public ArrayList<PersonalInfo> getPersonalInfoRecords() {
        ArrayList<PersonalInfo> records = new ArrayList<>();
        TextFileModifier tfm = new TextFileModifier("personal_information");

        // Fetch records from the text file and populate PersonalInfo objects
        for (String[] s : tfm.toArrayListofStringArray()) {
            PersonalInfo personalInfo = new PersonalInfo(
                    s[0], // empid
                    s[1], // firstname
                    s[2], // lastname
                    s[3], // nric
                    s[4], // passport
                    s[5], // gender
                    s[6], // date_of_birth
                    s[7], // place_of_birth
                    s[8], // race
                    s[9], // religious
                    s[10], // contact_number
                    s[11], // mailing_address
                    s[12], // email
                    s[13]  // marital_status
            );
            records.add(personalInfo);
        }

        return records;
    }


}
