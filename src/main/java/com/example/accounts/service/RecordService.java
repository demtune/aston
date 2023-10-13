package com.example.accounts.service;

import com.example.accounts.model.Record;
import com.example.accounts.repository.RecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class RecordService {

    private final RecordRepository recordRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public Record createRecordForNewAccount(String accountNumber) {
        log.info("Запись создана при создании аккаунта: {}", accountNumber);
        return recordRepository.save(new Record(null, null, new Date()));
    }

    public Record createRecordForFillBalance(String accountNumber) {
        log.info("Пополнение аккаунта: {}", accountNumber);
        return recordRepository.save(new Record(new Date(), null, null));
    }

    public Record createRecordForChargeBalance(String accountNumber) {
        log.info("Списание с аккаунта: {}", accountNumber);
        return recordRepository.save(new Record(null, new Date(), null));
    }
}
