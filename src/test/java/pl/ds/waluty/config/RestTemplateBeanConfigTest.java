package pl.ds.waluty.config;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.ds.waluty.domain.dto.RateDto;
import pl.ds.waluty.domain.dto.RateTab;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class RestTemplateBeanConfigTest {

    @Autowired
    RestTemplate restTemplate;
    RateDto rateDto;
    RateTab rateTab;

    @BeforeEach
    void setUp() {
        rateDto = new RateDto();
        rateTab = new RateTab();
        rateTab.setNo("005/C/NBP/2022");
        rateTab.setEffectiveDate("2022-01-10");
        rateTab.setMid(null);
        rateTab.setAsk("4.5929");
        rateTab.setBid("4.5019");
        rateDto.setTable("C");
        rateDto.setCode("eur");
        rateDto.setCurrency("euro");
        rateDto.setRates(List.of(rateTab));
    }

    @Test
    void givenRestTemplate_whenRequested_thenModifyResponseHeader() {

        HttpEntity<RateDto> requestEntity
                = new HttpEntity<RateDto>(rateDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("table", "C");
        urlVariables.put("code", "eur");
        urlVariables.put("date", "2022-01-10");
        URI url = UriComponentsBuilder
                .fromHttpUrl(Constants.KURS_TABELA_KOD_DATA)
                .build(urlVariables);

        ResponseEntity<RateDto> responseEntity
                = restTemplate.getForEntity(url, RateDto.class );

        assertAll(
                ()-> assertThat(responseEntity.getStatusCode(),is(equalTo(HttpStatus.OK))),
                ()-> assertThat(responseEntity.getHeaders().get("LOGOWANIE").get(0),is(equalTo("TRUE"))),
                ()-> assertThat(responseEntity.getBody().getRates().get(0).getAsk(),is(equalTo(rateDto.getRates().get(0).getAsk())))
        );

    }

}