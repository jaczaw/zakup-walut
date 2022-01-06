package pl.ds.waluty.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Table(name = "currency_exch_rate")
@Entity
public class CurrencyExchRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String tabela;
    private String currency;
    private String code;
    private String no;
    private String effectiveDate;
    private String bid;
    private String ask;
    private String mid;



}