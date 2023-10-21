package com.uc.order.domain.orderline.usecase;

import com.uc.common.DomainComponent;
import com.uc.common.usecase.UseCaseHandler;
import com.uc.order.domain.catalog.model.Product;
import com.uc.order.domain.catalog.port.ProductPort;
import com.uc.order.domain.order.port.OrderPort;
import com.uc.order.domain.orderline.model.OrderLine;
import com.uc.order.domain.orderline.port.OrderLinePort;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;


@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class CreateOrderLineUseCaseHandler implements UseCaseHandler<OrderLine,CreateOrderLineUseCase> {

   private final OrderLinePort orderLinePort;
   private final OrderPort orderPort;
   private final ProductPort productPort;
    @Override
    public OrderLine handle(CreateOrderLineUseCase value) {
        OrderLine orderLine= new OrderLine();
        orderLine.setOrder(orderPort.getByOrderId(value.getOrderId()));
        orderLine.setProductId(value.getProductId());
        orderLine.setQuantity(value.getQuantity());
        Product product=productPort.getByProductId(value.getProductId());
        orderLine.setTotalPrice(new BigDecimal(value.getQuantity()).multiply(product.getPrice()));
        return orderLinePort.save(orderLine);
    }
}
