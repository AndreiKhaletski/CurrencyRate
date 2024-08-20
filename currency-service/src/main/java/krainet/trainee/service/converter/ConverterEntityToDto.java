package krainet.trainee.service.converter;

import krainet.trainee.dao.entity.RateEntity;
import krainet.trainee.service.core.dto.RateDto;
import org.springframework.stereotype.Component;

@Component
public class ConverterEntityToDto {

    public RateDto convert(RateEntity item){
        RateDto rateDto = new RateDto();
        rateDto.setCur_ID(item.getCurID());
        rateDto.setCur_Name(item.getCurName());
        rateDto.setDate(item.getDate());
        rateDto.setCur_Abbreviation(item.getCurAbbreviation());
        rateDto.setCur_OfficialRate(item.getCurOfficialRate());
        rateDto.setCur_Scale(item.getCurScale());
        return rateDto;
    }
}
