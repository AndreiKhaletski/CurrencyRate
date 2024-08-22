package finance.service.converter;

import finance.dao.entity.RateEntity;
import finance.service.core.dto.RateDto;
import org.springframework.stereotype.Component;

@Component
public class ConverterDtoToEntity {

    public RateEntity convert(RateDto item){
        RateEntity rateEntity = new RateEntity();
        rateEntity.setCurID(item.getCur_ID());
        rateEntity.setCurName(item.getCur_Name());
        rateEntity.setDate(item.getDate());
        rateEntity.setCurAbbreviation(item.getCur_Abbreviation());
        rateEntity.setCurOfficialRate(item.getCur_OfficialRate());
        rateEntity.setCurScale(item.getCur_Scale());
        return rateEntity;
    }
}
