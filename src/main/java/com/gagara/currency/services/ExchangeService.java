package com.gagara.currency.services;

import com.gagara.currency.clients.ExchangeClient;
import com.gagara.currency.payload.ExchangeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

@Service
public class ExchangeService {
    @Value("${openexchangeratesToken}")
    private String token;

    @Value("${baseCurrencyCode}")
    private String baseCurrencyCode;

    private ExchangeClient exchangeClient;

    public ExchangeService(ExchangeClient exchangeClient) {
        this.exchangeClient = exchangeClient;
    }

    public boolean isCourseBiggerThenYesterday(String requiredCurrencyCode){
        ExchangeResponse todayExchangeResponse = getTodayExchangeRate();
        ExchangeResponse yesterdayExchangeResponse = getYesterdayExchangeRate();
        double todayCourse = calculateCourseRelativeToBaseCurrency(todayExchangeResponse, requiredCurrencyCode.toUpperCase());
        double yesterdayCourse = calculateCourseRelativeToBaseCurrency(yesterdayExchangeResponse, requiredCurrencyCode.toUpperCase());
        return todayCourse > yesterdayCourse;
    }
    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private ExchangeResponse getTodayExchangeRate(){
        Calendar todayCalendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(todayCalendar.getTime());
        return exchangeClient.getCourse(today, token);
    }

    private ExchangeResponse getYesterdayExchangeRate(){
        Calendar yesterdayCalendar = Calendar.getInstance();
        yesterdayCalendar.add(Calendar.DATE, -1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String yesterday = dateFormat.format(yesterdayCalendar.getTime());
        return exchangeClient.getCourse(yesterday, token);
    }

    //Так как базовую валюту, по отношению к которой смотрится курс, можно менять только при наличии платной подписки на сервис курса валют,
    //то пришлось пересчитывать курс через доллор(он является базовой валютой)
    private double calculateCourseRelativeToBaseCurrency(ExchangeResponse exchangeResponse, String requiredCurrencyCode) throws NullPointerException{
        Map<String, Double> rates = exchangeResponse.getRates();
        double dollarToBaseCurrencyCourse = rates.get(baseCurrencyCode);
        double dollarToRequiredCurrencyCourse = rates.get(requiredCurrencyCode);
        return dollarToBaseCurrencyCourse/dollarToRequiredCurrencyCourse;

    }

}
