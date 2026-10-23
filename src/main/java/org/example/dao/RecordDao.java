package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.example.entity.Record;
import org.example.entity.RecordStatus;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RecordDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Record> findAll() {
        Query query = entityManager.createQuery("SELECT r FROM Record r ORDER BY r.id ASC");
        List<Record> records = query.getResultList();
        return records;
    }

    @Transactional
    public void saveRecord(Record record) {

        entityManager.persist(record);
    }

    @Transactional
    public void updateRecordStatus(int id, RecordStatus newStatus) {
        Query query = entityManager.createQuery("UPDATE Record SET status = :newStatus WHERE id = :id");
        query.setParameter("newStatus", newStatus);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public void deleteRecord(int id) {
        Query query = entityManager.createQuery("DELETE FROM Record WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
