package br.com.stockquote.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.MathContext;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Document(indexName = "stock_idx")
public class Stock {
    public static final MathContext MATH_CONTEXT = new MathContext(2);
    @Id
    private String id;
    private String ticket;
    private String name;
    private String currency;
    private String stockExchange;

    private StockQuote quote;
    private StockStats stats;
    private StockDividend dividend;

}
