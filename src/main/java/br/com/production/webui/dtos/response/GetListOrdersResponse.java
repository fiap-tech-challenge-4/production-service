package br.com.production.webui.dtos.response;

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
public class GetListOrdersResponse {

    private String id;
    private LocalDateTime date;
    private String clientId;
    private BigDecimal totalValue;
    private String status;
    private List<UpdateOrderItem> items;
    private String additionalInfo;
}
