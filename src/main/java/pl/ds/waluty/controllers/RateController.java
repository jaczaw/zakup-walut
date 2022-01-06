package pl.ds.waluty.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ds.waluty.domain.dto.CurrencyRateDto;
import pl.ds.waluty.services.CurrencyExchRateService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rate")
public class RateController {

    private final CurrencyExchRateService currencyExchRateService;

    @GetMapping("/mid/{code}/{date}")
    public ResponseEntity<CurrencyRateDto> getRateMid(@PathVariable String code, @PathVariable String date) {
        CurrencyRateDto currencyExchRateDto = currencyExchRateService.cacheCurrency("A", code, date);
        return ResponseEntity.ok(currencyExchRateDto);
    }
    @GetMapping("/sale/{code}/{date}")
    public ResponseEntity<CurrencyRateDto> getRateSale(@PathVariable String code, @PathVariable String date) {
        CurrencyRateDto currencyExchRateDto = currencyExchRateService.cacheCurrency("C", code, date);
        return ResponseEntity.ok(currencyExchRateDto);
    }

}
