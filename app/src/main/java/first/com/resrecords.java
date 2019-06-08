package first.com;

public class resrecords {
    private resrecords[] records;
    private String id ;
    private resfields fields;
    private String createTime;
    public resrecords(String id, resfields fields, String createTime) {
        this.id = id;
        this.fields = fields;
        this.createTime = createTime;
    }
    public resrecords[] getRecords(){
        return records;
}
    public String getuserId(int i) {

        return fields.getID();

    }



    public String getDOC() {
        return fields.getdocID();

    }



    public String getclinum(){

        return fields.getCicnum();

    }
    public String getNumoftime(){

        return fields.gettimeID();

    }

    public resfields getFields() {

        return fields;

    }



    public String getCreateTime() {

        return createTime;

    }
}
