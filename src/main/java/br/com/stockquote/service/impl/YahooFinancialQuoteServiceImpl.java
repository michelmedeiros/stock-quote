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
            log.info("Starting find stock in Yahoo Finance by ticker {}", ticket);
            final yahoofinance.Stock stock = YahooFinance.get(StockUtils.getFormattedTicket(ticket));
            log.info("Finished find stock in Yahoo Finance");

            if(Objects.nonNull(stock)) {
                final Stock stockQuote = StockConverter.convertEntity(stock);
                log.info("Starting save stock in ES");
                Stock stockSaved = this.save(stockQuote);
                log.info("Finished save stock in ES");
                return stockSaved;
            } else {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.error("Error to call Yahoo Finance", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Stock save(Stock stockQuote) {
        log.info("Starting find stock in ES");
        final Stock stockFound = getStock(stockQuote.getTicket().toLowerCase());
        log.info("Finished find stock in ES");
        if(Objects.nonNull(stockFound)) {
            return stockQuoteRepository.save(stockQuote.withId(stockFound.getId()));
        }
        return  stockQuoteRepository.save(stockQuote);
    }

    private Stock getStock(String ticket) {
        return this.stockQuoteRepository.findFirstByTicket(ticket);
    }

}