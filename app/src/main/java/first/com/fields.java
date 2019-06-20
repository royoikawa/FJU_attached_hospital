package first.com;


public class fields {
    private String ID;

    private String Name;
    private String Password;
    private String Phone_number;
    private String Borned;
    public fields(String id, String name, String password, String phone_number, String borned) {
        this.ID = id;
        this.Name = name;
        this.Password = password;
        this.Phone_number = phone_number;
        this.Borned = borned;

    }





    public String getName() {

        return Name;

    }
    public String getPhone() {
        return Phone_number;
    }


    public String getID() {
        return ID;
    }


    public String getBorned() {
        return Borned;
    }
    public String getPass() {
        return Password;
    }

    public void setName(String name) {

        Name = name;

    }
    public void setBorned(String borned){
        Borned = borned;
    }

    public void setPassword(String password){
        Password = password;
    }

    public void setPhone_number(String phone_number){
        Phone_number = phone_number;
    }

    public void setID(String id){
        ID = id;
    }

}
