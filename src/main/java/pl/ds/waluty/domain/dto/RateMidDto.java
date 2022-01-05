package pl.ds.waluty.domain.dto;

import lombok.Data;

@Data
public class RateMidDto {

    private String table;
    private String currency;
    private String code;
    private String no;
    private String effectiveDate;
    private String mid;
}
