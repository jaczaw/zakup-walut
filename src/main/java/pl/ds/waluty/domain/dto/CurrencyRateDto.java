package pl.ds.waluty.domain.dto;

import lombok.Builder;
import lombok.Data;
import pl.ds.waluty.domain.entity.CurrencyExchRate;

@Builder
@Data
public class CurrencyRateDto {

    private Long id;
    private String tabela;
    private String currency;
    private String code;
    private String no;
    private String effectiveDate;
    private String mid;
    private String bid;
    private String ask;


    public static CurrencyRateDto convert(CurrencyExchRate currencyExchRate) {
        if (currencyExchRate == null) {
            return null;
        }
        return new CurrencyRateDto(
                currencyExchRate.getId(),
                currencyExchRate.getTabela(),
                currencyExchRate.getCurrency(),
                currencyExchRate.getCode(),
                currencyExchRate.getNo(),
                currencyExchRate.getEffectiveDate(),
                currencyExchRate.getMid(),
                currencyExchRate.getBid(),
                currencyExchRate.getAsk()
        );
    }

    public CurrencyExchRate convert() {
        CurrencyExchRate currencyExchRate = new CurrencyExchRate();
        currencyExchRate.setId(id);
        currencyExchRate.setTabela(tabela);
        currencyExchRate.setCurrency(currency);
        currencyExchRate.setCode(code);
        currencyExchRate.setNo(no);
        currencyExchRate.setEffectiveDate(effectiveDate);
        currencyExchRate.setMid(mid);
        currencyExchRate.setBid(bid);
        currencyExchRate.setAsk(ask);
        return currencyExchRate;
    }
}
