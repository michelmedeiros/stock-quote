package br.com.stockquote.service;

import br.com.stockquote.domain.StockStatistics;

public interface StatusInvestService {
    StockStatistics getStockStatisticByTicker(String ticker);

}
