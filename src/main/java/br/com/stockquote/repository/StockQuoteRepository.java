package br.com.stockquote.repository;

import br.com.stockquote.domain.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockQuoteRepository extends CrudRepository<Stock, String> {
    Stock findFirstByTicket(String ticket);
}
