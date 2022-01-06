package pl.ds.waluty.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.ds.waluty.domain.dto.CurrencyRateDto;
import pl.ds.waluty.domain.dto.RateDto;
import pl.ds.waluty.domain.dto.RateTab;
import pl.ds.waluty.domain.entity.CurrencyExchRate;
import pl.ds.waluty.exceptions.ResourceNotFoundException;
import pl.ds.waluty.reposytory.CurrencyExchRateRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyExchRateService {

    private final CurrencyExchRateRepository currencyExchRateRepository;
    private final CurrencySaleNbpClient currencySaleNbpClient;

    public CurrencyRateDto getCurrencyById(Long id) {
        CurrencyExchRate currencyExchRate = currencyExchRateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CurrencyExchRate", "id", id));
        return CurrencyRateDto.convert(currencyExchRate);
    }

    public List<Long> getIdsCurrencyByIds(List<String> codes) {
        return currencyExchRateRepository.findAll()
                .stream()
                .filter(currency->codes.contains(currency.getCode()))
                .map(CurrencyExchRate::getId)
                .collect(Collectors.toList());
    }

    public List<CurrencyRateDto> getAllCurrencyByIds(List<Long> ids) {
        return currencyExchRateRepository.findAllById(ids)
                .stream()
                .map(CurrencyRateDto::convert)
                .collect(Collectors.toList());
    }

    public List<CurrencyRateDto> getAllCurrency() {
        return currencyExchRateRepository.findAll()
                .stream()
                .map(CurrencyRateDto::convert)
                .collect(Collectors.toList());
    }

    public CurrencyExchRate addCurrency(CurrencyRateDto currencyRateDto) {
        return currencyExchRateRepository.save(Objects.requireNonNull(currencyRateDto.convert()));
    }

    public CurrencyRateDto cacheCurrency(String table, String code, String date) {

        Optional<CurrencyExchRate> optional = currencyExchRateRepository.findByTabelaAndCodeAndDate(table,code, date)
                .stream().findFirst();
        if(optional.isEmpty()){
            CurrencyRateDto currencyRateDto = getFromNbp(table,code, date);
            CurrencyExchRate saveCurrencyExchRate = addCurrency(currencyRateDto);
            log.info(String.format("Zapisano kurs waluty o ID: %s do bazy",saveCurrencyExchRate.getId()));
            return CurrencyRateDto.convert(saveCurrencyExchRate);
        }
        else {
            log.info(String.format("Pobrano kurs waluty o ID: %s z bazy",optional.get().getId()));
            return CurrencyRateDto.convert(optional.get());
        }
    }

    public CurrencyRateDto getFromNbp(String table, String code, String date){
        RateDto rateDto = currencySaleNbpClient.getRateFromNbp(table,code, date);
        RateTab rateTab = rateDto.getRates().get(0);
        return CurrencyRateDto.builder()
                .tabela(rateDto.getTable())
                .currency(rateDto.getCurrency())
                .code(rateDto.getCode())
                .no(rateTab.getNo())
                .effectiveDate(rateTab.getEffectiveDate())
                .mid(rateTab.getMid())
                .bid(rateTab.getBid())
                .ask(rateTab.getAsk())
                .build();
    }

}