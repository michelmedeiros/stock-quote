package br.com.stockquote.controller;

import br.com.stockquote.domain.StockStatistics;
import br.com.stockquote.service.StatusInvestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
public class StockStatusInvestController {

    private final StatusInvestService statusInvestService;

    @GetMapping("/statusInvest/{ticker}")
    public StockStatistics getStocksStatisticsByTicker(@PathVariable String ticker) {
        return statusInvestService.getStockStatisticByTicker(ticker);
    }

}
