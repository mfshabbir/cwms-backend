package uk.gov.hmcts.cwms_backend.service;

import uk.gov.hmcts.cwms_backend.dto.CWTaskDto;

import java.util.List;

public interface CWTaskService {

    CWTaskDto createCWTask(CWTaskDto cwTaskDto);
    CWTaskDto getCWTaskById(Long taskId);
    List<CWTaskDto> getAllCWTaskById();
    CWTaskDto updateCWTask(Long taskId,CWTaskDto cwTaskDto );
    void deleteCWTask(Long taskId);

}
