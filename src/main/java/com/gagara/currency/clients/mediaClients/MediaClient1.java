package com.gagara.currency.clients.mediaClients;

import com.gagara.currency.clients.DownloadingGifInterface;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "media1", url = "${mediaUrl1}", qualifier = "media1")
public interface MediaClient1 extends DownloadingGifInterface {
}
