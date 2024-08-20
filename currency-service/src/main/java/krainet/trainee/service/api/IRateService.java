package krainet.trainee.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import krainet.trainee.service.core.dto.RateDto;

import java.time.LocalDate;
import java.util.List;

public interface IRateService {

    List<RateDto> getListCurrency(LocalDate date, String periodicity) throws JsonProcessingException;

    RateDto getCurrency(LocalDate date, String code);

}
