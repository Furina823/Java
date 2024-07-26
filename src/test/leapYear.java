package test;

public class leapYear {

    private boolean Isleap;

    public boolean getIsleap() {
        return Isleap;
    }

    public void setIsleap(boolean isleap) {
        this.Isleap = isleap;
    }

    public leapYear(int year){
        if(year%4==0 && year%100!=0 || year%400==0){
            this.Isleap = true;
        }
    }
}
