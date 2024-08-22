package finance.config;

import com.fasterxml.jackson.databind.DeserializationFeature;

public class JsonMapper {

    public final static com.fasterxml.jackson.databind.json.JsonMapper JSON_MAPPER = com.fasterxml.jackson.databind.json.JsonMapper.builder()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .build();

    private JsonMapper() {

    }
}
