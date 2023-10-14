package com.uc.catalog.infra.adapters.category;


import com.uc.catalog.infra.adapters.category.jpa.entity.CategoryEntity;
import com.uc.common.rest.exception.EntityNotFoundException;
import com.uc.catalog.domain.category.model.Category;
import com.uc.catalog.domain.category.port.CategoryPort;
import com.uc.catalog.domain.category.usecase.CreateCategoryUseCase;
import com.uc.catalog.domain.category.usecase.DeleteCategoryUseCase;
import com.uc.catalog.domain.category.usecase.UpdateCategoryUseCase;
import com.uc.catalog.infra.adapters.category.jpa.repository.CategoryRepository;
import com.uc.catalog.infra.adapters.category.mapper.CategoryEntityToCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryAdapter implements CategoryPort {

    private final CategoryRepository categoryRepository;
    private final CategoryEntityToCategoryMapper categoryEntityToCategoryMapper;

    @Override
    public Category create(CreateCategoryUseCase createCategoryUseCase) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setTitle(createCategoryUseCase.getTitle());
        return categoryEntityToCategoryMapper.convert(categoryRepository.save(categoryEntity));
    }

    @Override
    public Category update(UpdateCategoryUseCase value) {
        CategoryEntity category=findByCategoryId(value.getId());
        category.setTitle(value.getTitle());
        return categoryEntityToCategoryMapper.convert(categoryRepository.save(category));
    }
    @Override
    public Category findById(Long id) {
        return categoryEntityToCategoryMapper.convert(findByCategoryId(id));
    }

    @Override
    public void delete(DeleteCategoryUseCase value) {
        categoryRepository.deleteById(value.getId());
    }

    public CategoryEntity findByCategoryId(Long id) {
        CategoryEntity category=categoryRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Kategori bulunamadı"));
        return category;
    }

}
