package first.com;


public class clinicnumber {
    private String clinicnum;
    private int num;

    public clinicnumber(String cli,int nums) {
        this.clinicnum=cli;
        this.num=nums;
    }

    public String getClinicnum(){
        return  clinicnum;
    }

    public int getNum(){
        return num;
    }
}
