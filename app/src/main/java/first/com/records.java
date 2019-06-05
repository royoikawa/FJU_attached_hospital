package first.com;

public class records {
    private records[] records;
    private String id ;
    private fields fields;
    private String createTime;

    public records(String id, fields fields, String createTime) {
        this.id = id;
        this.fields = fields;
        this.createTime = createTime;
    }
    public records[] getRecords(){
        return records;
    }

    public String getId(int i) {

        return records[i].id;

    }



    public String getFieldsID() {

        return fields.getID();

    }



    public String getFieldsName(){

        return fields.getName();

    }
    public String getFieldsPhone(){

        return fields.getPhone();

    }
    public String getFieldsBorned(){

        return fields.getBorned();

    }
    public String getFieldsPass(){

        return fields.getPass();

    }


    public fields getFields() {

        return fields;

    }



    public String getCreateTime() {

        return createTime;

    }
}