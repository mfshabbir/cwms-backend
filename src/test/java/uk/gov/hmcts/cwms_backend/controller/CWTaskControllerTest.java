package uk.gov.hmcts.cwms_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.hmcts.cwms_backend.dto.CWTaskDto;
import uk.gov.hmcts.cwms_backend.service.CWTaskService;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CWTaskController.class)
class CWTaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CWTaskService cwTaskService;

    @Autowired
    private ObjectMapper objectMapper;

    private CWTaskDto cwTaskDto;

    @BeforeEach
    void setUp() {
        cwTaskDto = CWTaskDto.builder()
                .id(1L)
                .strTitle("Test Title")
                .strDescription("Test Description")
                .strStatus("OPEN")
                .dueDateTime(LocalDateTime.now().plusDays(1))
                .build();
    }

    @Test
    void shouldCreateCWTask() throws Exception {
        Mockito.when(cwTaskService.createCWTask(any(CWTaskDto.class))).thenReturn(cwTaskDto);

        mockMvc.perform(post("/api/cwtasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cwTaskDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.strTitle").value("Test Title"));
    }

    @Test
    void shouldGetCWTaskById() throws Exception {
        Mockito.when(cwTaskService.getCWTaskById(1L)).thenReturn(cwTaskDto);

        mockMvc.perform(get("/api/cwtasks/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.strTitle").value("Test Title"));
    }

    @Test
    void shouldGetAllCWTasks() throws Exception {
        Mockito.when(cwTaskService.getAllCWTaskById()).thenReturn(Collections.singletonList(cwTaskDto));

        mockMvc.perform(get("/api/cwtasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].strTitle").value("Test Title"));
    }

    @Test
    void shouldUpdateCWTask() throws Exception {
        CWTaskDto updatedDto = CWTaskDto.builder()
                .id(1L)
                .strTitle("Updated Title")
                .strDescription("Updated Description")
                .strStatus("IN_PROGRESS")
                .dueDateTime(LocalDateTime.now().plusDays(2))
                .build();

        Mockito.when(cwTaskService.updateCWTask(eq(1L), any(CWTaskDto.class))).thenReturn(updatedDto);

        mockMvc.perform(put("/api/cwtasks/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.strTitle").value("Updated Title"))
                .andExpect(jsonPath("$.strStatus").value("IN_PROGRESS"));
    }

    @Test
    void shouldDeleteCWTask() throws Exception {
        Mockito.doNothing().when(cwTaskService).deleteCWTask(1L);

        mockMvc.perform(delete("/api/cwtasks/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Case worker Task with id 1 deleted successfully"));
    }
}
