package br.com.production.app.usecases;

import br.com.production.app.integration.orderservice.OrderServiceIntegration;
import br.com.production.app.integration.orderservice.response.OrderResponse;
import br.com.production.app.integration.orderservice.response.PaginationIntegration;
import br.com.production.webui.converters.OrderConverter;
import br.com.production.webui.dtos.response.GetListOrdersResponse;
import br.com.production.webui.dtos.response.PaginationResponse;
import br.com.production.webui.dtos.response.UpdateOrderStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.production.webui.converters.OrderConverter.convertToUpdateOrderStatusResponse;

@Service
@RequiredArgsConstructor
public class UpdateOrderStatusCase {

    private final OrderServiceIntegration orderServiceIntegration;

    public UpdateOrderStatusResponse updateOrderStatus(String orderId) {
        OrderResponse orderResponse = orderServiceIntegration.updateOrderStatus(orderId);
        return convertToUpdateOrderStatusResponse(orderResponse);
    }

    public PaginationResponse<GetListOrdersResponse> getListOrders(Integer page, Integer limit, String orderStatus) {
        PaginationIntegration<OrderResponse> paginationOrders = orderServiceIntegration.getListOrders(page, limit, orderStatus);
        return OrderConverter.convertToPaginationGetListOrdersResponse(paginationOrders);
    }

}
