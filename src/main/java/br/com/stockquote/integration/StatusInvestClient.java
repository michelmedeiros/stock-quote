package br.com.stockquote.integration;

import br.com.stockquote.integration.dto.StockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "statusInvestClient", url = "${status-invest.url}")
public interface StatusInvestClient {
    @GetMapping("/home/mainsearchquery")
    List<StockDTO> getStock(@RequestParam("q") String query);
}
