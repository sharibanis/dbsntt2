package dbsntt2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ApplicationControllerTest {
	static ApplicationController appController = new ApplicationController();
	static LocalDateTime now = LocalDateTime.now().minusMinutes(5L);
	static String timeStamp = now.format(DateTimeFormatter.ISO_DATE_TIME);

	@BeforeAll
	public static void setUp() throws Exception {
		Quote quote;
		for (int i = 0; i < 10; i++) {
			quote = new Quote(timeStamp, "D05.SI", 5000, 10.00, "up", 0.17);
			appController.postQuote(quote);
		}
		for (int i = 0; i < 20; i++) {
			quote = new Quote(timeStamp, "D06.SI", 5000, 10.00, "up", 0.17);
			appController.postQuote(quote);
		}
		for (int i = 0; i < 30; i++) {
			quote = new Quote(timeStamp, "D07.SI", 5000, 10.00, "up", 0.17);
			appController.postQuote(quote);
		}
		for (int i = 0; i < 40; i++) {
			quote = new Quote(timeStamp, "D08.SI", 5000, 10.00, "up", 0.17);
			appController.postQuote(quote);
		}
		for (int i = 0; i < 50; i++) {
			quote = new Quote(timeStamp, "D09.SI", 5000, 10.00, "up", 0.17);
			appController.postQuote(quote);
		}
		for (int i = 0; i < 60; i++) {
			quote = new Quote(timeStamp, "D10.SI", 5000, 10.00, "up", 0.17);
			appController.postQuote(quote);
		}
		for (int i = 0; i < 70; i++) {
			quote = new Quote(timeStamp, "D11.SI", 5000, 10.00, "up", 0.17);
			appController.postQuote(quote);
		}
		for (int i = 0; i < 80; i++) {
			quote = new Quote(timeStamp, "D12.SI", 5000, 10.00, "up", 0.17);
			appController.postQuote(quote);
		}
		for (int i = 0; i < 90; i++) {
			quote = new Quote(timeStamp, "D13.SI", 5000, 10.00, "up", 0.17);
			appController.postQuote(quote);
		}
		for (int i = 0; i < 100; i++) {
			quote = new Quote(timeStamp, "D14.SI", 5000, 10.00, "up", 0.17);
			appController.postQuote(quote);
		}
	}
	
	@Test
	void postQuotesTest() {
		assertEquals(550, appController.listQuotes.size());
	}

	@Test
	void getQuotesSizeTest() {
		assertTrue((5 >= appController.getQuotes().size()) ? true : false);
	}

	@Test
	void getQuotesTest() {
		List<String> list = appController.getQuotes();
		String[] result = {"D14.SI", "D13.SI", "D12.SI", "D11.SI", "D10.SI"};
		assertArrayEquals(result, list.toArray(new String[5]));
	}
}
