package pl.ds.waluty.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ds.waluty.domain.dto.RateMidDto;
import pl.ds.waluty.domain.dto.RateSaleDto;
import pl.ds.waluty.services.CurrencySaleNbpClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tabela")
public class TableCurrentController {
    private final CurrencySaleNbpClient currencySaleNbpClient;

    @GetMapping("/a")
    public ResponseEntity<List<RateMidDto>> getWalutySredniaAll() {
        return ResponseEntity.ok(currencySaleNbpClient.getAllWalutyKursMid());
    }

    @GetMapping("/c")
    public ResponseEntity<List<RateSaleDto>> getWalutyTabelaSprzedazAll() {
        return ResponseEntity.ok(currencySaleNbpClient.getAllWalutyKursSprzedaz());
    }
}
