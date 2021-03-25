package br.com.stockquote.controller;

import br.com.stockquote.domain.Stock;
import br.com.stockquote.domain.StockStatistics;
import br.com.stockquote.integration.dto.StockDTO;
import br.com.stockquote.service.StockQuoteService;
import br.com.stockquote.service.YahooFinancialQuoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
@AllArgsConstructor
public class StockQuotaController {

    private final StockQuoteService stockQuoteService;
    private final YahooFinancialQuoteService yahooFinancialQuoteService;

    @PostMapping()
    public Stock createStockQuote(@RequestBody StockDTO stockQuote) {
        return stockQuoteService.save(stockQuote);
    }

    @GetMapping("/search")
    public Iterable<Stock> searchStocks() {
        return stockQuoteService.searchAll();
    }

    @GetMapping("/search/{ticket}")
    public Stock searchStock(@PathVariable String ticket) {
        return stockQuoteService.getStockByTicketName(ticket);
    }

    @GetMapping("yahoo/{ticket}")
    public Stock getStock(@PathVariable String ticket) {
        return yahooFinancialQuoteService.getYahooFinanceStockQuote(ticket);
    }

}
