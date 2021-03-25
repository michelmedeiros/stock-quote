package br.com.stockquote.service.impl;

import br.com.stockquote.domain.StockStatistics;
import br.com.stockquote.repository.StockStatisticsReactiveRepository;
import br.com.stockquote.service.StatusInvestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class StatusInvestServiceImpl implements StatusInvestService {
    private final StockStatisticsReactiveRepository stockStatisticsReactiveRepository;

    @Override
    public StockStatistics getStockStatisticByTicker(String ticker) {
        return stockStatisticsReactiveRepository.findFirstByTicker(ticker);
    }
}
