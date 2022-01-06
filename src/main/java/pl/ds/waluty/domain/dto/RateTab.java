package pl.ds.waluty.domain.dto;

import lombok.Data;

@Data
public class RateTab {

    private String no;
    private String effectiveDate;
    private String mid;
    private String bid;
    private String ask;
}
