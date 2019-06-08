package first.com;

public class resfields {
    private String getId;
    private String getdocId;
    private String getNUM;
    private String gettimeid;

    public resfields(String GetId, String GetdocId, String GetNUM, String Gettimeid) {

        getId = GetId;
        getdocId = GetdocId;
        getNUM = GetNUM;
        gettimeid = Gettimeid;

    }
    public String getID() {

        return getId;

    }
    public String getdocID() {
        return getdocId;
    }

    public  String getCicnum(){
        return getNUM;
    }
    public String gettimeID() {
        return gettimeid;
    }


}