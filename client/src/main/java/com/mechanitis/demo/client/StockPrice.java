package com.mechanitis.demo.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor // needed for code
@NoArgsConstructor  // needed for JSON deserialisation
public class StockPrice {
    private String symbol;
    private Double price;
    private LocalDateTime time;
}
