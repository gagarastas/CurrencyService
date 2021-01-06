package com.gagara.currency.clients;

import com.gagara.currency.payload.SearchingGifResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gify", url = "${searchingGifUrl}" )
public interface SearchingGifClient {
    @RequestMapping(value = "/v1/gifs/search", method = RequestMethod.GET, params = {"api_key", "q"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    SearchingGifResponse searchGif(@RequestParam(value = "api_key") String token, @RequestParam(value = "q") String searchingQuery);
}
