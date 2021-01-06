package com.gagara.currency.controllers;

import com.gagara.currency.services.ExchangeService;
import com.gagara.currency.services.GifService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
public class ExchangeRatesController {
    private ExchangeService exchangeService;
    private GifService gifService;

    public ExchangeRatesController(ExchangeService exchangeService, GifService gifService) {
        this.exchangeService = exchangeService;
        this.gifService = gifService;
    }

    @GetMapping("exchangerates")
    public ResponseEntity GetRates(@RequestParam(name = "currency_code") String currencyCode) {
        try {
            boolean isCourseBigger = exchangeService.isCourseBiggerThenYesterday(currencyCode);
            byte[] gifBytes = gifService.getRandomGif(isCourseBigger);

            return ResponseEntity.ok().contentType(MediaType.IMAGE_GIF)
                .body(gifBytes);
        }
        catch (NullPointerException exception1){
            return ResponseEntity.badRequest().body("Запрашиваемая вами валюта не существует");
        }
        catch (IOException exception2){
            return ResponseEntity.badRequest().body("Во время получение гифки что то прошло не так");
        }
    }
}
