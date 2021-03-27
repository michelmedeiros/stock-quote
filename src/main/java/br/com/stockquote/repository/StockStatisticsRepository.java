package br.com.stockquote.repository;

import br.com.stockquote.domain.StockStatistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockStatisticsRepository extends CrudRepository<StockStatistics, String> {
    StockStatistics findFirstByTicker(String ticker);
}
