package krainet.trainee.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import krainet.trainee.dao.api.IRateResource;
import krainet.trainee.dao.entity.RateEntity;
import krainet.trainee.service.RestTemplateConfig.RestTemplateConfig;
import krainet.trainee.service.api.IRateService;
import krainet.trainee.service.converter.ConverterDtoToEntity;
import krainet.trainee.service.converter.ConverterEntityToDto;
import krainet.trainee.service.core.dto.RateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class RateService implements IRateService {

    private final RestTemplate restTemplate;
    private final IRateResource rateResource;
    private final ConverterDtoToEntity converterDtoToEntity;
    private final ConverterEntityToDto converterEntityToDto;


    public RateService(RestTemplateConfig config,
                       IRateResource rateResource,
                       ConverterDtoToEntity converterDtoToEntity,
                       ConverterEntityToDto converterEntityToDto) {
        this.restTemplate = config.restTemplate();
        this.rateResource = rateResource;
        this.converterDtoToEntity = converterDtoToEntity;
        this.converterEntityToDto = converterEntityToDto;
    }

    private final String WEB_URL = "https://api.nbrb.by/exrates/rates";

    @Override
    @Transactional
    public List<RateDto> getListCurrency(LocalDate date, String periodicity) {
        String webSiteList = WEB_URL + "?ondate=" + date + "&periodicity=" + periodicity;

        List<RateDto> listDto = new ArrayList<>();

        try {
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(webSiteList, JsonNode.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                JsonNode rootNode = response.getBody();
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                List<RateEntity> rateDtos = objectMapper.readValue(rootNode.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, RateEntity.class));


                for (RateEntity el : rateDtos) {

                    RateEntity rateEntity = el;
                    LocalDate dataTime = rateEntity.getDate();
                    String abbreviation = rateEntity.getCurAbbreviation();

                    if (rateResource.existsByDateAndCurAbbreviation(dataTime, abbreviation)) {
                        System.out.println("Совпадение найдено: " + rateEntity.getCurAbbreviation());
                        rateEntity = rateResource.findByDateAndCurAbbreviation(dataTime, abbreviation);
                        listDto.add(converterEntityToDto.convert(rateEntity));
                    } else {
                        rateEntity.setUuid(UUID.randomUUID());
                        rateResource.saveAndFlush(rateEntity);
                        listDto.add(converterEntityToDto.convert(rateEntity));
                    }
                }
                return listDto;

            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                if (rateResource.existsByDate(date)) {
                    List<RateEntity> listEntity = rateResource.findAllByDate(date);
                    for (RateEntity rateEntity : listEntity) {
                        listDto.add(converterEntityToDto.convert(rateEntity));
                    }
                }
                return listDto;
            } else {
                throw new RuntimeException("Курсы валют за указанную дату отсутствуют", e);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при получении данных", e);
        }
        return listDto;
    }


    @Override
    @Transactional
    public RateDto getCurrency(LocalDate date, String code) {
        String webSiteCurrency = WEB_URL + "/" + code + "?ondate=" + date + "&parammode=2";

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(webSiteCurrency, JsonNode.class);

        RateEntity rateEntity;

        if (response.getStatusCode().is2xxSuccessful()){
            JsonNode jsonNode = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            try {
                if (rateResource.findByDateAndCurAbbreviation(date, code) == null){
                    rateEntity = objectMapper.readValue(jsonNode.toString(), RateEntity.class);
                    rateEntity.setUuid(UUID.randomUUID());
                    rateResource.saveAndFlush(rateEntity);
                }else {
                    rateEntity = rateResource.findByDateAndCurAbbreviation(date, code);
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }else{
            Optional <RateEntity> entityDb = Optional.ofNullable(rateResource.findByDateAndCurAbbreviation(date, code));
            if(entityDb.isPresent()){
                return converterEntityToDto.convert(entityDb.get());
            }else{
                throw new IllegalArgumentException("Курса валюты не найдено");
            }
        }
        return converterEntityToDto.convert(rateEntity);
    }
}
