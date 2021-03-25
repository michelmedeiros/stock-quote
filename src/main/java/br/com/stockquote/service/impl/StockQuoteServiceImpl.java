package br.com.stockquote.service.impl;

import br.com.stockquote.converters.StockConverter;
import br.com.stockquote.domain.Stock;
import br.com.stockquote.domain.StockQuote;
import br.com.stockquote.integration.StatusInvestClient;
import br.com.stockquote.integration.dto.StockDTO;
import br.com.stockquote.repository.StockQuoteRepository;
import br.com.stockquote.service.StockQuoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@AllArgsConstructor
public class StockQuoteServiceImpl implements StockQuoteService {

    private final StockQuoteRepository stockQuoteRepository;

    private final StatusInvestClient statusInvestClient;

    @Override
    public List<Stock> statusInvestStockQuote(String ticket) {
        try {
            final List<StockDTO> stocks = statusInvestClient.getStock(ticket);
            return stocks.stream().map(this::convertEntity)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error to call Status Invest", ex);
        }
        return null;
    }

    private Stock convertEntity(StockDTO stock) {
        return Stock.builder()
                .quote(StockQuote.builder()
                        .price(new BigDecimal(stock.getPrice().replaceAll(",", ".")))
                        .build())
                .ticket(stock.getCode())
                .build();
    }


    @Override
    public Iterable<Stock> searchAll() {
        return stockQuoteRepository.findAll();
    }

    @Override
    public Stock save(StockDTO stockQuote) {
        return stockQuoteRepository.save(StockConverter.convertEntity(stockQuote));
    }

    @Override
    public Stock getStockByTicketName(String ticket) {
        return stockQuoteRepository.findFirstByTicket(ticket);
    }

}
