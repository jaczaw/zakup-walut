package pl.ds.waluty;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import pl.ds.waluty.controllers.RateController;
import pl.ds.waluty.services.CurrencyExchRateService;
import pl.ds.waluty.services.CurrencySaleNbpClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WalutyZakupApplicationTests {

	@Autowired
	private RateController rateController;
	@Autowired
	private CurrencyExchRateService currencyExchRateService;
	@Autowired
	private CurrencySaleNbpClient currencySaleNbpClient;

	@Test
	public void contextLoads() throws Exception {
		assertThat(rateController).isNotNull();
		assertThat(currencyExchRateService).isNotNull();
		assertThat(currencySaleNbpClient).isNotNull();


	}

}
