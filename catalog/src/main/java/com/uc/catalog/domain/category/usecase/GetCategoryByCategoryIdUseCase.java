package com.uc.catalog.domain.category.usecase;

import  com.uc.catalog.domain.common.usecase.UseCase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetCategoryByCategoryIdUseCase implements UseCase {
    private Long id;
}
