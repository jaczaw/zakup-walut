package pl.ds.waluty.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.ds.waluty.config.Constants;
import pl.ds.waluty.domain.dto.RateDto;
import pl.ds.waluty.domain.dto.RateMidDto;
import pl.ds.waluty.domain.dto.RateSaleDto;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencySaleNbpClient {

    private final RestTemplate rest;

    public List<RateSaleDto> getAllWalutyKursSprzedaz() {
        return rest.exchange(Constants.TABEL_C_KURS_SPRZEDAZ,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<RateSaleDto>>() {})
                .getBody();
    }

    public  List<RateMidDto> getAllWalutyKursMid() {
        return rest.exchange(Constants.TABEL_A_KURS_SREDNI,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<RateMidDto>>() {})
                .getBody();
    }

    public RateDto getRateFromNbp(String table,String code,String date) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("table", table);
        urlVariables.put("code", code);
        urlVariables.put("date", date);
        URI url = UriComponentsBuilder
                .fromHttpUrl(Constants.KURS_TABELA_KOD_DATA)
                .build(urlVariables);
        log.info(String.format("Pobrano kurs z NBP {TABELA: %s, CODE: %s Data: %s}",table,code,date));
        return rest.getForEntity(url, RateDto.class).getBody();
    }
}
