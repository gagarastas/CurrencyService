package com.gagara.currency.clients.mediaClients;

import com.gagara.currency.clients.DownloadingGifInterface;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "media3", url = "${mediaUrl3}", qualifier = "media3")
public interface MediaClient3 extends DownloadingGifInterface {
}
