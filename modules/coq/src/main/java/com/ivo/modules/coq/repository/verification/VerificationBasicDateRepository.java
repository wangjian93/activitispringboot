package com.ivo.modules.coq.repository.verification;

import com.ivo.modules.coq.domain.verification.VerificationBasicDate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: wj
 * @Date: 2019-09-19 14:17
 * @Version 1.0
 */
public interface VerificationBasicDateRepository extends JpaRepository<VerificationBasicDate, Long> {

    VerificationBasicDate findFirstByYear(int year);
}
