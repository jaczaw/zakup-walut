package pl.ds.waluty.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.ds.waluty.domain.dto.CurrencyRateDto;
import pl.ds.waluty.services.CurrencyExchRateService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RateController.class)
public class RateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyExchRateService service;

    @Test
    void poprawne_odpowiedz_z_enpointa() throws Exception {
        CurrencyRateDto curRate = CurrencyRateDto.builder()
                .tabela("A")
                .currency("euro")
                .code("EUR")
                .no("003/A/NBP/2022")
                .effectiveDate("2022-01-05")
                .mid("4.5672")
                .build();
        when(service.getCurrencyById(1L)).thenReturn(curRate);
        this.mockMvc
                .perform(get("/api/v1/rate/mid/eur/2022-01-05"))
                .andDo(print()).andExpect(status().isOk());

    }
}