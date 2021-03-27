package br.com.stockquote.controller;

import br.com.stockquote.domain.Stock;
import br.com.stockquote.service.YahooFinancialQuoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/yahoo")
@AllArgsConstructor
public class StockYahooController {
    private final YahooFinancialQuoteService yahooFinancialQuoteService;

    @GetMapping("/search/{ticket}")
    public Stock getStock(@PathVariable String ticket) {
        return yahooFinancialQuoteService.getYahooFinanceStockQuote(ticket);
    }

}
