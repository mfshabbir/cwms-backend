package uk.gov.hmcts.cwms_backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CWTaskDto {
    private Long id;
    private String strTitle;
    private String strDescription;
    private String strStatus;
    private LocalDateTime dueDateTime;

}
