package com.hexaware.lms.repository;

import com.hexaware.lms.entity.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository  extends JpaRepository<Authentication, Long> {
    void deleteByUserId(long l);
}
