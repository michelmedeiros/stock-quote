package br.com.stockquote.service;

import br.com.stockquote.domain.Stock;
import br.com.stockquote.domain.StockStatistics;

import java.util.List;

public interface StatusInvestService {
    StockStatistics getStockStatisticByTicker(String ticker);
    List<Stock> statusInvestStockQuote(String ticket);
    Iterable<StockStatistics> generateStockStatistics();

}
