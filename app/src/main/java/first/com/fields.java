package first.com;


public class fields {
    private String Borned;
    private String Name;
    private String Password;
    private String Phone_number;
    private String ID;
    public fields(String borned, String name, String password, String phone_number, String id) {

        Borned = borned;
        Name = name;
        Password = password;
        Phone_number = phone_number;
        ID = id;

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
