package com.gagara.currency.clients.mediaClients;

import com.gagara.currency.clients.DownloadingGifInterface;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "media2", url = "${mediaUrl2}", qualifier = "media2")
public interface MediaClient2 extends DownloadingGifInterface {
}
