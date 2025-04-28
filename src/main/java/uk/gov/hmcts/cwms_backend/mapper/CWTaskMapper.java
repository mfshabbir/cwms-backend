package uk.gov.hmcts.cwms_backend.mapper;

import uk.gov.hmcts.cwms_backend.dto.CWTaskDto;
import uk.gov.hmcts.cwms_backend.entity.CWTask;

public class CWTaskMapper {

    public static CWTaskDto mapToCWTaskDto(CWTask cwTask)
    {
        return  new CWTaskDto(
                cwTask.getId(),
//                cwTask.getStrCaseNumber(),
                cwTask.getStrTitle(),
                cwTask.getStrDescription(),
                cwTask.getStrStatus(),
                cwTask.getDueDateTime()
        );
    }
    public static CWTask mapToCWTask(CWTaskDto cwTaskDto)
    {
        return  new CWTask(
                cwTaskDto.getId(),
//                cwTaskDto.getStrCaseNumber(),
                cwTaskDto.getStrTitle(),
                cwTaskDto.getStrDescription(),
                cwTaskDto.getStrStatus(),
                cwTaskDto.getDueDateTime()
        );
    }

}
