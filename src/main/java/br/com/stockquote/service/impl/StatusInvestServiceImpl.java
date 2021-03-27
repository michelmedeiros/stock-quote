package br.com.stockquote.service.impl;

import br.com.stockquote.converters.StockStatisticConverter;
import br.com.stockquote.domain.Stock;
import br.com.stockquote.domain.StockQuote;
import br.com.stockquote.domain.StockStatistics;
import br.com.stockquote.dto.StockStatisticsDTO;
import br.com.stockquote.integration.StatusInvestClient;
import br.com.stockquote.integration.dto.StockDTO;
import br.com.stockquote.repository.StockStatisticsRepository;
import br.com.stockquote.service.StatusInvestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class StatusInvestServiceImpl implements StatusInvestService {

    public static final String SEARCH_QUERY = "{}";

    private final StockStatisticsRepository stockStatisticsRepository;
    private final StatusInvestClient statusInvestClient;

    @Override
    public StockStatistics getStockStatisticByTicker(String ticker) {
        return stockStatisticsRepository.findFirstByTicker(ticker);
    }

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

    @Override
    public Iterable<StockStatistics> generateStockStatistics() {
        final List<StockStatisticsDTO> stockStatistics = statusInvestClient.getStockStatistics(SEARCH_QUERY, 1);
        this.deleteAllStatistics();
        return stockStatisticsRepository.saveAll(StockStatisticConverter.convertEntity(stockStatistics));
    }

    public void deleteAllStatistics() {
        stockStatisticsRepository.deleteAll();
    }

    private Stock convertEntity(StockDTO stock) {
        return Stock.builder()
                .quote(StockQuote.builder()
                        .price(new BigDecimal(stock.getPrice().replaceAll(",", ".")))
                        .build())
                .ticket(stock.getCode())
                .build();
    }
}
