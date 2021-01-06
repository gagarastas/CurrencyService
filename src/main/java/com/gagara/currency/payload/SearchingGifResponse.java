package com.gagara.currency.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchingGifResponse {
    private GifObject[] data;

    public GifObject[] getData() {
        return data;
    }

    public void setData(GifObject[] data) {
        this.data = data;
    }
}
