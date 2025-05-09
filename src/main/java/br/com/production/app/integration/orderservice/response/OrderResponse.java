package br.com.production.app.integration.orderservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class OrderResponse {

    private String id;
    private LocalDateTime date;
    private String clientId;
    private BigDecimal totalValue;
    private String status;
    private List<OrderItemResponse> items;
    private String additionalInfo;

}
