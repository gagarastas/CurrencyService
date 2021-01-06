package com.gagara.currency.clients;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface DownloadingGifInterface {
    @RequestMapping(value = "{path}?{queryString}",method = RequestMethod.GET, consumes = MediaType.IMAGE_GIF_VALUE)
    byte[] downloadGif(@PathVariable String path, @PathVariable String queryString);
}
