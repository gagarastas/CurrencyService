package com.gagara.currency.clients.mediaClients;

import com.gagara.currency.clients.DownloadingGifInterface;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "media0", url = "${mediaUrl0}", qualifier = "media0")
public interface MediaClient0 extends DownloadingGifInterface {
}
