package br.com.stockquote.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "stock_portfolio_item_idx")
public class StockPortfolioItem {

    private Integer quantity;
    private BigDecimal averagePrice;
    private BigDecimal purchasePrice;
    private BigDecimal lastPrice;
    private BigDecimal currentPosition;
    private BigDecimal profitability;
    private Double profitabilityPercentage;
    private String ticket;

}
