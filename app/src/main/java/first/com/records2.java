package first.com;

public class records2 {
    private records2[] records2;
    private String id ;
    private fields2 fields2;
    private String createTime;

    public records2(String id, fields2 fields, String createTime) {
        this.id = id;
        this.fields2 = fields;
        this.createTime = createTime;
    }


/*
=======
>>>>>>> 0d5d96509de9b87c14755e777a2ca8f8c566eea2
    public records[] getRecords(){
        return records;
    }

    public String getId(int i) {

        return records[i].id;

    }



    public String getFieldsID() {

        return fields.getID();

    }

    public String getFieldsID(int i){
        return records[i].getFields().getID();
    }
    public String getFieldsName(int i){
        return records[i].getFields().getName();
    }
    public String getFieldsPhone(int i){
        return records[i].getFields().getPhone();
    }
    public String getFieldsBorned(int i){
        return records[i].getFields().getBorned();
    }
    public String getFieldsPass(int i){
        return records[i].getFields().getPass();
    }
<<<<<<< HEAD

    public String getFieldsName(){
        return fields.getName();
    }*/
    /*public String getFieldsPhone(){
=======
    /*
    public String getFieldsName(){
        return fields.getName();
    }*/



    public fields2 getFields() {

        return fields2;

    }



    public String getCreateTime() {

        return createTime;
    }
}