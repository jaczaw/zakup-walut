package pl.ds.waluty.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.ds.waluty.config.Constants;
import pl.ds.waluty.domain.dto.RateMidDto;
import pl.ds.waluty.domain.dto.RateSaleDto;
import pl.ds.waluty.domain.dto.WalutaDto;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencySaleNbpClient {

    private final  RestTemplate rest;

    public List<WalutaDto> getAllWalutyKursSredni() {
        return rest.exchange(Constants.TABEL_A_KURS_SREDNI,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<WalutaDto>>() {})
                .getBody();
    }

    public List<RateSaleDto> getAllWalutyKursSprzedaz() {
        return rest.exchange(Constants.TABEL_C_KURS_SPRZEDAZ,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<RateSaleDto>>() {})
                .getBody();
    }

    public WalutaDto getAllWalutyKursMid() {
        return rest.exchange(Constants.TABEL_A_KURS_SREDNI,
                HttpMethod.GET, null, new ParameterizedTypeReference<WalutaDto>() {})
                .getBody();
    }

    public RateMidDto getRateMidByCode(String code) {
        ResponseEntity<RateMidDto> responseEntity =
                rest.getForEntity(Constants.KURS_SREDNI,
                        RateMidDto.class, code);
        return responseEntity.getBody();
    }

    public RateSaleDto getRateSaleByCode(String code) {
        ResponseEntity<RateSaleDto> responseEntity =
                rest.getForEntity(Constants.KURS_SPRZEDAZ,
                        RateSaleDto.class, code);
        return responseEntity.getBody();
    }

}
