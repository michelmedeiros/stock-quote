package br.com.stockquote.service;

import br.com.stockquote.domain.Stock;
import br.com.stockquote.integration.dto.StockDTO;

import java.util.List;

public interface StockQuoteService {
    Stock getStockByTicketName(String ticket);
    Iterable<Stock> searchAll();
    Stock save(StockDTO stockQuote);
}
