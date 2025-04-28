package uk.gov.hmcts.cwms_backend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.cwms_backend.dto.CWTaskDto;
import uk.gov.hmcts.cwms_backend.entity.CWTask;
import uk.gov.hmcts.cwms_backend.exception.ResourceNotFoundException;
import uk.gov.hmcts.cwms_backend.mapper.CWTaskMapper;
import uk.gov.hmcts.cwms_backend.repository.CWTaskRepository;
import uk.gov.hmcts.cwms_backend.service.CWTaskService;

import java.util.List;

@Service
@AllArgsConstructor
public class CWTaskServiceImpl implements CWTaskService {

    private CWTaskRepository cwTaskRepository;

    @Override
    public CWTaskDto createCWTask(CWTaskDto cwTaskDto) {
        CWTask cwTask = CWTaskMapper.mapToCWTask(cwTaskDto);
        CWTask savedCWTask = cwTaskRepository.save(cwTask);
        return CWTaskMapper.mapToCWTaskDto(savedCWTask);
    }

    @Override
    public CWTaskDto getCWTaskById(Long taskId) {
        CWTask cwTask = cwTaskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task with id " + taskId + " not found"));

        return CWTaskMapper.mapToCWTaskDto(cwTask);
    }

    @Override
    public List<CWTaskDto> getAllCWTaskById() {
        List<CWTask> cwTasks = cwTaskRepository.findAll();
        return cwTasks.stream().map(CWTaskMapper::mapToCWTaskDto)
                                    .toList();

    }

    @Override
    public CWTaskDto updateCWTask(Long taskId, CWTaskDto cwTaskDto) {
        CWTask cwTask = cwTaskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task with id " + taskId + " not found"));
//        cwTask.setStrCaseNumber(cwTaskDto.getStrCaseNumber());
        cwTask.setStrTitle(cwTaskDto.getStrTitle());
        cwTask.setStrDescription(cwTaskDto.getStrDescription());
        cwTask.setStrStatus(cwTaskDto.getStrStatus());
        cwTask.setDueDateTime(cwTaskDto.getDueDateTime());
        CWTask updatedCWTask = cwTaskRepository.save(cwTask);
        return CWTaskMapper.mapToCWTaskDto(updatedCWTask);

    }

    @Override
    public void deleteCWTask(Long taskId) {
        CWTask cwTask = cwTaskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task with id " + taskId + " not found"));
        cwTaskRepository.deleteById(taskId);

    }
}
