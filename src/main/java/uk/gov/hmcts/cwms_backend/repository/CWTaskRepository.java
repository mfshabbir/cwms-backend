package uk.gov.hmcts.cwms_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.hmcts.cwms_backend.entity.CWTask;

public interface CWTaskRepository extends JpaRepository<CWTask, Long> {
}
