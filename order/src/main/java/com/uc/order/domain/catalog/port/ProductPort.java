package com.uc.order.domain.catalog.port;

import com.uc.order.domain.catalog.model.Product;

import java.util.List;

public interface ProductPort {
     Product getByProductId(Long id);
     List<Product> getByProductIds(List<Long> ids);
}
