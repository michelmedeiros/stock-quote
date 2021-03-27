package br.com.stockquote.controller;

import br.com.stockquote.domain.Stock;
import br.com.stockquote.domain.StockStatistics;
import br.com.stockquote.service.StatusInvestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statusInvest")
@AllArgsConstructor
public class StockStatusInvestController {

    private final StatusInvestService statusInvestService;

    @GetMapping("/statistics/{ticker}")
    public StockStatistics getStocksStatisticsByTicker(@PathVariable String ticker) {
        return statusInvestService.getStockStatisticByTicker(ticker);
    }

    @GetMapping("/stocks/{ticket}")
    public List<Stock> getStocks(@PathVariable String ticket) {
        return statusInvestService.statusInvestStockQuote(ticket);
    }

    @GetMapping("/statistics/generate")
    public  Iterable<StockStatistics> generateStocksStatistics() {
        return statusInvestService.generateStockStatistics();
    }

}
