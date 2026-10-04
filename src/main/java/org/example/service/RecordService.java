package org.example.service;

import org.example.dao.RecordDao;

import org.example.dto.RecordsContainerDto;
import org.example.entity.Record;
import org.example.entity.RecordStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordService {
    private final RecordDao recordDao;

    @Autowired
    public RecordService(RecordDao recordDao){
        this.recordDao = recordDao;
    }

    public RecordsContainerDto findAll(String filterMode){
        List<Record> records = recordDao.findAll();
        int numberOfDoneRecords =(int) records.stream().filter(record -> record.getStatus() == RecordStatus.DONE).count();
        int numberOfActiveRecords =(int) records.stream().filter(record -> record.getStatus() == RecordStatus.ACTIVE).count();
        if(filterMode == null || filterMode.isBlank()) {
            return new RecordsContainerDto(records, numberOfDoneRecords, numberOfActiveRecords);
        }
        String upperCaseFilter = filterMode.toUpperCase();
        List<String> allowedFiltersMode =  Arrays.stream(RecordStatus.values())
                .map(Enum::name)
                .toList();
        if(allowedFiltersMode.contains(upperCaseFilter)) {
            List<Record> filteredRecords = records.stream()
                    .filter(record -> record.getStatus() == RecordStatus.valueOf(upperCaseFilter))
                    .collect(Collectors.toList());
            return new RecordsContainerDto(filteredRecords, numberOfDoneRecords, numberOfActiveRecords);
        }
        else
            return new RecordsContainerDto(records, numberOfDoneRecords, numberOfActiveRecords);
    }

    public void saveRecord(String title){
        if(title != null && !title.isBlank()) {
            recordDao.saveRecord(new Record(title));
        }
    }
    public void updateRecordStatus(int id, RecordStatus newStatus){
        recordDao.updateRecordStatus(id, newStatus);
    }
    public void deleteRecord(int id){
        recordDao.deleteRecord(id);
    }
}
