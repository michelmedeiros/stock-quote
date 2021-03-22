package br.com.stockquote.service.impl;

import br.com.stockquote.converters.StockConverter;
import br.com.stockquote.domain.Stock;
import br.com.stockquote.repository.StockQuoteRepository;
import br.com.stockquote.service.YahooFinancialQuoteService;
import br.com.stockquote.utils.StockUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import yahoofinance.YahooFinance;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class YahooFinancialQuoteServiceImpl implements YahooFinancialQuoteService {

    private final StockQuoteRepository stockQuoteRepository;

    @Override
    public Stock getYahooFinanceStockQuote(String ticket) {
        try {
            final yahoofinance.Stock stock = YahooFinance.get(StockUtils.getFormattedTicket(ticket));
            if(Objects.nonNull(stock)) {
                final Stock stockQuote = StockConverter.convertEntity(stock);
                return this.save(stockQuote);
            } else {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.error("Error to call Yahoo Finance", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Stock save(Stock stockQuote) {
        final Stock stockFound = getStock(stockQuote.getTicket().toLowerCase());
        if(Objects.nonNull(stockFound)) {
            return stockQuoteRepository.save(stockQuote.withId(stockFound.getId()));
        }
        return  stockQuoteRepository.save(stockQuote);
    }

    private Stock getStock(String ticket) {
        return this.stockQuoteRepository.findFirstByTicket(ticket);
    }

}