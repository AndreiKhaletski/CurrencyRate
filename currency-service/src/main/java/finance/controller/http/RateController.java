package finance.controller.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import finance.service.api.IRateService;
import finance.service.core.dto.RateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/rate")
public class RateController {


    private final IRateService rateService;

    public RateController(IRateService rateService) {
        this.rateService = rateService;
    }


    @GetMapping(value = "/get-list")
    public List<RateDto> getList(@RequestParam ("periodicity") String periodicity,
                                 @RequestParam ("ondate") LocalDate date) {
        try {
            List<RateDto> list = rateService.getListCurrency(date, periodicity);
                return ResponseEntity.ok().body(list).getBody();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/get-currency")
    public ResponseEntity<RateDto> getCurrency(@RequestParam("ondate") LocalDate date,
                                               @RequestParam("code") String code){

        return ResponseEntity.ok().body(rateService.getCurrency(date, code));
    }
}
