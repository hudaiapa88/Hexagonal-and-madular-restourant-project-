package com.uc.inventory.infra.adapters.inventory.mapper;

import com.uc.inventory.domain.inventory.model.Inventory;
import com.uc.inventory.infra.adapters.inventory.jpa.entity.InventoryEntity;
import com.uc.inventory.infra.common.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryEntityToInventoryMapper extends BaseMapper<InventoryEntity, Inventory> {
}
