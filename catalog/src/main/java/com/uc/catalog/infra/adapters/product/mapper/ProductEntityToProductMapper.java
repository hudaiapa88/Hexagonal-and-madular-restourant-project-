package com.uc.catalog.infra.adapters.product.mapper;

import com.uc.catalog.domain.product.model.Product;
import com.uc.catalog.infra.adapters.product.jpa.entity.ProductEntity;
import com.uc.catalog.infra.common.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityToProductMapper extends BaseMapper<ProductEntity, Product> {
}
