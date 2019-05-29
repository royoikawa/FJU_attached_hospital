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
}