package br.com.production.app.usecases;

import br.com.production.app.integration.orderservice.OrderServiceIntegration;
import br.com.production.app.integration.orderservice.response.OrderItemResponse;
import br.com.production.app.integration.orderservice.response.OrderResponse;
import br.com.production.app.integration.orderservice.response.PaginationIntegration;
import br.com.production.webui.dtos.response.GetListOrdersResponse;
import br.com.production.webui.dtos.response.PaginationResponse;
import br.com.production.webui.dtos.response.UpdateOrderStatusResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateOrderStatusCaseTest {

    @InjectMocks
    private UpdateOrderStatusCase updateOrderStatusCase;
    @Mock
    private OrderServiceIntegration orderServiceIntegration;

    @Test
    void shouldUpdateOrderStatusSuccessfully() {
        String orderId = "123";
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(orderId);

        when(orderServiceIntegration.updateOrderStatus(orderId))
                .thenReturn(orderResponse);

        UpdateOrderStatusResponse result = updateOrderStatusCase.updateOrderStatus(orderId);

        assertNotNull(result);
        assertEquals(orderId, result.getId());
    }

    @Test
    void shouldReturnPaginatedOrderListSuccessfully() {
        int page = 0;
        int limit = 10;
        String status = "PENDING";

        OrderResponse order = new OrderResponse();
        List<OrderItemResponse> items = new ArrayList<>();
        items.add(new OrderItemResponse(1L, 1, new BigDecimal("1.0"),"teste"));
        items.add(new OrderItemResponse(2L, 3, new BigDecimal("1.0"),"teste"));

        order.setId("123");
        order.setItems(items);

        PaginationIntegration<OrderResponse> mockPagination = new PaginationIntegration<>();
        mockPagination.setItems(List.of(order));
        mockPagination.setHasNext(false);
        mockPagination.setHasPrevious(false);
        mockPagination.setPageNumber(page);
        mockPagination.setPageSize(limit);

        when(orderServiceIntegration.getListOrders(page, limit, status))
                .thenReturn(mockPagination);

        PaginationResponse<GetListOrdersResponse> result = updateOrderStatusCase.getListOrders(page, limit, status);

        assertNotNull(result);
        assertFalse(result.getItems().isEmpty());
        assertEquals("123", result.getItems().get(0).getId());
    }
}