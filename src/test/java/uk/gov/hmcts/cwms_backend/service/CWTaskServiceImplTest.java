package uk.gov.hmcts.cwms_backend.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.gov.hmcts.cwms_backend.dto.CWTaskDto;
import uk.gov.hmcts.cwms_backend.entity.CWTask;
import uk.gov.hmcts.cwms_backend.exception.ResourceNotFoundException;
import uk.gov.hmcts.cwms_backend.repository.CWTaskRepository;
import uk.gov.hmcts.cwms_backend.service.impl.CWTaskServiceImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CWTaskServiceImplTest {

    @Mock
    private CWTaskRepository cwTaskRepository;

    @InjectMocks
    private CWTaskServiceImpl cwTaskService;

    private CWTask cwTask;
    private CWTaskDto cwTaskDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cwTask = CWTask.builder()
                .id(1L)
                .strTitle("Test Title")
                .strDescription("Test Description")
                .strStatus("OPEN")
                .dueDateTime(LocalDateTime.now().plusDays(1))
                .build();

        cwTaskDto = CWTaskDto.builder()
                .id(1L)
                .strTitle("Test Title")
                .strDescription("Test Description")
                .strStatus("OPEN")
                .dueDateTime(cwTask.getDueDateTime())
                .build();
    }

    @Test
    void shouldCreateCWTask() {
        when(cwTaskRepository.save(any(CWTask.class))).thenReturn(cwTask);

        CWTaskDto result = cwTaskService.createCWTask(cwTaskDto);

        assertThat(result).isNotNull();
        assertThat(result.getStrTitle()).isEqualTo(cwTask.getStrTitle());
        verify(cwTaskRepository, times(1)).save(any(CWTask.class));
    }

    @Test
    void shouldGetCWTaskById_WhenFound() {
        when(cwTaskRepository.findById(1L)).thenReturn(Optional.of(cwTask));

        CWTaskDto result = cwTaskService.getCWTaskById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        verify(cwTaskRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowException_WhenCWTaskNotFound() {
        when(cwTaskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> cwTaskService.getCWTaskById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Task with id 1 not found");
        verify(cwTaskRepository, times(1)).findById(1L);
    }

    @Test
    void shouldGetAllCWTasks() {
        when(cwTaskRepository.findAll()).thenReturn(Arrays.asList(cwTask));

        List<CWTaskDto> result = cwTaskService.getAllCWTaskById();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getStrTitle()).isEqualTo("Test Title");
        verify(cwTaskRepository, times(1)).findAll();
    }

    @Test
    void shouldUpdateCWTask_WhenFound() {
        when(cwTaskRepository.findById(1L)).thenReturn(Optional.of(cwTask));
        when(cwTaskRepository.save(any(CWTask.class))).thenReturn(cwTask);

        CWTaskDto updatedDto = CWTaskDto.builder()
                .strTitle("Updated Title")
                .strDescription("Updated Description")
                .strStatus("IN_PROGRESS")
                .dueDateTime(LocalDateTime.now().plusDays(2))
                .build();

        CWTaskDto result = cwTaskService.updateCWTask(1L, updatedDto);

        assertThat(result).isNotNull();
        assertThat(result.getStrTitle()).isEqualTo(updatedDto.getStrTitle());
        verify(cwTaskRepository, times(1)).findById(1L);
        verify(cwTaskRepository, times(1)).save(any(CWTask.class));
    }

    @Test
    void shouldDeleteCWTask_WhenFound() {
        when(cwTaskRepository.findById(1L)).thenReturn(Optional.of(cwTask));

        cwTaskService.deleteCWTask(1L);

        verify(cwTaskRepository, times(1)).findById(1L);
        verify(cwTaskRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowException_WhenDeletingNonExistentTask() {
        when(cwTaskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> cwTaskService.deleteCWTask(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Task with id 1 not found");
        verify(cwTaskRepository, times(1)).findById(1L);
    }
}

