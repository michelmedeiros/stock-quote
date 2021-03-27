package br.com.stockquote.controller;

import br.com.stockquote.domain.Stock;
import br.com.stockquote.integration.dto.StockDTO;
import br.com.stockquote.service.StockQuoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
@AllArgsConstructor
public class StockQuotaController {

    private final StockQuoteService stockQuoteService;

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


}
