package com.gagara.currency.clients;

import com.gagara.currency.payload.ExchangeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "exchange", url = "${openexchangeratesUrl}")
public interface ExchangeClient {
    @RequestMapping(value = "/historical/{date}.json", method = RequestMethod.GET, params = {"app_id"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    ExchangeResponse getCourse(@PathVariable String date,@RequestParam(value = "app_id") String appId);
}
