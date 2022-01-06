package pl.ds.waluty.reposytory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ds.waluty.domain.entity.CurrencyExchRate;

import java.util.List;

@Repository
public interface CurrencyExchRateRepository extends JpaRepository<CurrencyExchRate, Long> {

    @Query("select c from CurrencyExchRate c " +
            "where " +
            "UPPER(c.tabela) = UPPER(?1) and " +
            "UPPER(c.code) = UPPER(?2) and " +
            "c.effectiveDate = ?3")
    List<CurrencyExchRate> findByTabelaAndCodeAndDate(String tabela, String code, String effectiveDate);

    List<CurrencyExchRate> findByCode(String code);
}