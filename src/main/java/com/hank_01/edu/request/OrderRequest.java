package com.hank_01.edu.request;

import com.hank_01.edu.enums.OrderStatus;

import java.math.BigDecimal;

public class OrderRequest {
    private Long playerId;
    private String playerName;
    private BigDecimal goldCount;
    private BigDecimal orderPrice;
    private OrderStatus status;

}
