package br.com.production.app.integration.orderservice;

import br.com.production.app.integration.orderservice.response.OrderResponse;
import br.com.production.app.integration.orderservice.response.PaginationIntegration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "order-service", url = "${integration.order-service.url}")
public interface OrderServiceIntegration {

    @PostMapping(value = "/api/v1/order/status/{id}", consumes = "application/json")
    OrderResponse updateOrderStatus(@PathVariable("id") String orderId);

    @GetMapping(value = "/api/v1/order/list", consumes = "application/json")
    PaginationIntegration<OrderResponse> getListOrders(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "limit") Integer limit,
            @RequestParam(name = "orderStatus") String orderStatus);

}
