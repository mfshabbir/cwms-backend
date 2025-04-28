package uk.gov.hmcts.cwms_backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.hmcts.cwms_backend.dto.CWTaskDto;
import uk.gov.hmcts.cwms_backend.service.CWTaskService;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/api/cwtasks")
public class CWTaskController {

    private CWTaskService cwTaskService;
    // Build ADD CW Task REST API
    @PostMapping
    public ResponseEntity<CWTaskDto> createCWTask( @RequestBody CWTaskDto cwTaskDto)
    {
        CWTaskDto savedCwTask = cwTaskService.createCWTask(cwTaskDto);
        return new ResponseEntity<>(savedCwTask, HttpStatus.CREATED);
    }
    // Build Get CW Task REST API by ID
    @GetMapping("{id}")
    public ResponseEntity<CWTaskDto> getCWTaskById( @PathVariable("id") Long cwTaskId)
    {
        CWTaskDto cwTaskDto = cwTaskService.getCWTaskById(cwTaskId);
        return  ResponseEntity.ok(cwTaskDto);
    }
    // Build Get All CW Tasks REST API
    @GetMapping
    public ResponseEntity<List<CWTaskDto>> getCWTaskById()
    {
        List<CWTaskDto> cwTasksDto = cwTaskService.getAllCWTaskById();
        return  ResponseEntity.ok(cwTasksDto);
    }
    // Build Update CW Task REST API
    @PutMapping("{id}")
    public ResponseEntity<CWTaskDto> updateCWTask(@PathVariable("id") Long cwTaskId, @RequestBody CWTaskDto cwTaskDto)
    {
        CWTaskDto updatedCwTaskDto = cwTaskService.updateCWTask(cwTaskId,cwTaskDto);
        return  ResponseEntity.ok(updatedCwTaskDto);
    }
    // Build Delete CW Task REST API by ID
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCWTaskById( @PathVariable("id") Long cwTaskId)
    {
        cwTaskService.deleteCWTask(cwTaskId);
        return  ResponseEntity.ok("Case worker Task with id "+ cwTaskId+" deleted successfully");
    }
}
