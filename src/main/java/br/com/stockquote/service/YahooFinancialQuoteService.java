package br.com.stockquote.service;


import br.com.stockquote.domain.Stock;

public interface YahooFinancialQuoteService {
    Stock getYahooFinanceStockQuote(String ticket);
}
