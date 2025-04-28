package uk.gov.hmcts.cwms_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cwtasks")
public class CWTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String strTitle;
    @Column (name = "description")
    private String strDescription;
    @Column (name = "status")
    private String strStatus;
    @Column (name = "date_created" , nullable = false)
    private LocalDateTime dueDateTime;

//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }
}
