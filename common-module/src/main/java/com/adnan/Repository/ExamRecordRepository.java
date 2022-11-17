package com.adnan.Repository;

import com.adnan.entity.ExamRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRecordRepository extends JpaRepository<ExamRecord, Long> {
}
