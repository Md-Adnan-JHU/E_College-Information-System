package com.adnan.Repository;

import com.adnan.entity.StudentFee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentFeeRepository extends JpaRepository<StudentFee, Long> {
}
