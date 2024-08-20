package krainet.trainee.service.core.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RateDto {
    private Integer Cur_ID;
    private LocalDate Date;
    private String Cur_Abbreviation;
    private Integer Cur_Scale;
    private String Cur_Name;
    private Double Cur_OfficialRate;
}
