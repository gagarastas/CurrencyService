package com.gagara.currency.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageObject {
    private Map<String, String> original;

    private Map<String,String> downsized;

    public Map<String, String> getOriginal() {
        return original;
    }

    public void setOriginal(Map<String, String> original) {
        this.original = original;
    }

    public Map<String, String> getDownsized() {
        return downsized;
    }

    public void setDownsized(Map<String, String> downsized) {
        this.downsized = downsized;
    }
}
