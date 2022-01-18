package pl.ds.waluty.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ds.waluty.domain.dto.CurrencyRateDto;
import pl.ds.waluty.domain.entity.CurrencyExchRate;
import pl.ds.waluty.reposytory.CurrencyExchRateRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyExchRateServiceTest {

    @InjectMocks
    CurrencyExchRateService currencyExchRateService;

    @Mock
    CurrencyExchRateRepository currencyExchRateRepository;
    @Mock
    CurrencySaleNbpClient currencySaleNbpClient;

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @MethodSource("CurrencyTestProvider")
    void findByTabelaAndCodeAndDate_is_OK(String table,String code,String date) {

         CurrencyRateDto currencyRateOne = CurrencyRateDto.builder()
                .id(1L)
                .code("EUR")
                .tabela("A")
                .effectiveDate("2022-01-05")
                .mid("4.5343")
                .build();

        List<CurrencyExchRate> listaNotEmpty = List.of(currencyRateOne)
                .stream()
                .map(CurrencyRateDto::convert)
                .collect(Collectors.toList());

        when(currencyExchRateRepository.findByTabelaAndCodeAndDate(table,code,date)).thenReturn(listaNotEmpty);

        CurrencyRateDto currencyRateDto = currencyExchRateService.cacheCurrency(table,code,date);
        assertEquals("A",currencyRateDto.getTabela());
        verify(currencyExchRateRepository,times(1)).findByTabelaAndCodeAndDate(table,code,date);
    }

    @ParameterizedTest
    @MethodSource("CurrencyTestProvider")
    void Not_findByTabelaAndCodeAndDate_is_OK(String table,String code,String date) {
        List<CurrencyExchRate> listaIsEmpty = List.of();

        when(currencyExchRateRepository.findByTabelaAndCodeAndDate(table,code,date)).thenReturn(listaIsEmpty);

        boolean isFalse = currencyExchRateService.isCurrencyInCache(table,code,date);
        assertFalse(isFalse);
        verify(currencyExchRateRepository,times(1)).findByTabelaAndCodeAndDate(table,code,date);
    }

    static Stream<Arguments> CurrencyTestProvider() {
        return Stream.of(
                arguments("A", "EUR", "2022-01-05"),
                arguments("B", "EUR", "2021-02-05"),
                arguments("A", "USD", "2020-03-05"),
                arguments("B", "USD", "2019-04-05")
        );
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    void getCurrencyById() {
        fail("W przygotowaniu");
    }

    @Test
    void getIdsCurrencyByIds() {
        fail("W przygotowaniu");
    }

    @Test
    void getAllCurrencyByIds() {
        fail("W przygotowaniu");
    }


}