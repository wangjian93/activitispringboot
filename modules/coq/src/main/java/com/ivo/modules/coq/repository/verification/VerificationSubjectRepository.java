package com.ivo.modules.coq.repository.verification;

import com.ivo.modules.coq.domain.verification.VerificationSubject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: wj
 * @Date: 2019-09-19 14:17
 * @Version 1.0
 */
public interface VerificationSubjectRepository extends JpaRepository<VerificationSubject, Long> {

    VerificationSubject findFirstByVerificationSubject(String verificationSubject);
}
