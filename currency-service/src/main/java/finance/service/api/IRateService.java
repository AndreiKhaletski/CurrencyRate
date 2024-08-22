package finance.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import finance.service.core.dto.RateDto;

import java.time.LocalDate;
import java.util.List;

public interface IRateService {

    List<RateDto> getListCurrency(LocalDate date, String periodicity) throws JsonProcessingException;

    RateDto getCurrency(LocalDate date, String code);

}
