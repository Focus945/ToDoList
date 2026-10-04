package org.example.dao;

import org.example.entity.Record;
import org.example.entity.RecordStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RecordDao {
    private final List<Record> records = new ArrayList<>(
            Arrays.asList(
                    new Record("Take a shower", RecordStatus.ACTIVE),
                    new Record("Buy flowers", RecordStatus.ACTIVE),
                    new Record("GO to the GYM", RecordStatus.ACTIVE)
            )
    );
    public List<Record> findAll() {
        return new ArrayList<>(records);
    }

    public void saveRecord(Record record){
        records.add(record);
    }
    public void updateRecordStatus(int id, RecordStatus newStatus){
        for(Record item : records){
            if(item.getId() == id){
                item.setStatus(newStatus);
                break;
            }
        }
    }
    public void deleteRecord(int id){
        records.removeIf(item -> item.getId() == id);
    }

}
