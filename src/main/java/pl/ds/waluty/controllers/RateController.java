package pl.ds.waluty.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ds.waluty.domain.dto.RateMidDto;
import pl.ds.waluty.domain.dto.RateSaleDto;
import pl.ds.waluty.domain.dto.WalutaDto;
import pl.ds.waluty.services.CurrencySaleNbpClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rate")
public class RateController {
    private final CurrencySaleNbpClient currencySaleNbpClient;

    @GetMapping("/mid/{code}")
    public ResponseEntity<RateMidDto> getRateMidByCode(@PathVariable String code) {
        RateMidDto rateMidDto = currencySaleNbpClient.getRateMidByCode(code);
        return ResponseEntity.ok(rateMidDto);
    }

    @GetMapping("/sale/{code}")
    public ResponseEntity<RateSaleDto> getRateSaleByCode(@PathVariable String code) {
        RateSaleDto rateSaleDto = currencySaleNbpClient.getRateSaleByCode(code);
        return ResponseEntity.ok(rateSaleDto);
    }
}
