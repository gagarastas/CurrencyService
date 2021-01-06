package com.gagara.currency;

import com.gagara.currency.clients.ExchangeClient;
import com.gagara.currency.payload.ExchangeResponse;
import com.gagara.currency.services.ExchangeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class CurrencyApplicationTests {
    @Value("${openexchangeratesToken}")
    private String token;

    @MockBean
    private ExchangeClient exchangeClient;

    @Autowired
    private ExchangeService exchangeService;

    private String today;
    private String yesterday;
    Map<String, Double> todayRates;
    Map<String, Double> yesterdayRates;


    @BeforeEach
    public void init() {
        Calendar todayCalendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        today = dateFormat.format(todayCalendar.getTime());
        todayCalendar.add(Calendar.DATE, -1);
        yesterday = dateFormat.format(todayCalendar.getTime());

        todayRates = new HashMap<>();
        todayRates.put("EUR",2.0);
        todayRates.put("RUB",1.0);
        todayRates.put("JPY", 1.0);

        yesterdayRates = new HashMap<>();
        yesterdayRates.put("EUR",1.0);
        yesterdayRates.put("RUB",1.0);
        yesterdayRates.put("JPY", 2.0);
    }

    @Test
    void isCourseBiggerThenYesterdayTest() {
        Mockito.when(exchangeClient.getCourse(today, token))
            .thenReturn(new ExchangeResponse(todayRates));

        Mockito.when(exchangeClient.getCourse(yesterday, token))
            .thenReturn(new ExchangeResponse(yesterdayRates));

        boolean falseIsBigger = exchangeService.isCourseBiggerThenYesterday("EUR");
        boolean trueIsBigger = exchangeService.isCourseBiggerThenYesterday("JPY");

        assertFalse(falseIsBigger);
        assertTrue(trueIsBigger);

    }
}
