package pl.ds.waluty.domain.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RateDto {

    private String table;
    private String currency;
    private String code;
    private List<RateTab> rates = new ArrayList<>();


}
