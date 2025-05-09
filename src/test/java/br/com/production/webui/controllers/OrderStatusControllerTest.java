package br.com.production.webui.controllers;

import br.com.production.app.usecases.UpdateOrderStatusCase;
import br.com.production.webui.dtos.response.GetListOrdersResponse;
import br.com.production.webui.dtos.response.PaginationResponse;
import br.com.production.webui.dtos.response.UpdateOrderStatusResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderStatusControllerTest {

    @InjectMocks
    private OrderStatusController orderStatusController;
    @Mock
    private UpdateOrderStatusCase updateOrderStatusCase;

    @Test
    void shouldUpdateOrderStatus() {
        String orderId = "123";
        UpdateOrderStatusResponse updateOrderStatusResponse = new UpdateOrderStatusResponse();
        updateOrderStatusResponse.setId(orderId);

        when(updateOrderStatusCase.updateOrderStatus(orderId))
                .thenReturn(updateOrderStatusResponse);

        ResponseEntity<UpdateOrderStatusResponse> response = orderStatusController.updateOrderStatus(orderId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(orderId, response.getBody().getId());
    }

    @Test
    void shouldReturnListOrders() {
        Integer page = 1;
        Integer limit = 25;
        String status = "PENDING";
        GetListOrdersResponse getListOrdersResponse = new GetListOrdersResponse();
        getListOrdersResponse.setId("123");

        PaginationResponse<GetListOrdersResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setItems(List.of(getListOrdersResponse));

        when(updateOrderStatusCase.getListOrders(page, limit, status))
                .thenReturn(paginationResponse);

        ResponseEntity<PaginationResponse<GetListOrdersResponse>> response =
                orderStatusController.getListOrders(page, limit, status);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().getItems().isEmpty());
        assertEquals("123", response.getBody().getItems().get(0).getId());
    }
}