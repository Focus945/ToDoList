package org.example.entity;

// @Entity + @Id + @GeneratedValue!!!
public class Record {
    private static int counter = 0;
    private final int id;
    private final String title;
    private RecordStatus status;

    public Record(String title){
        this.id = counter++;
        this.title = title;
        this.status = RecordStatus.ACTIVE;
    }

    public Record(String title, RecordStatus status){
        this.id = counter++;
        this.status = status;
        this.title = title;
    }

    public RecordStatus getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }
}
