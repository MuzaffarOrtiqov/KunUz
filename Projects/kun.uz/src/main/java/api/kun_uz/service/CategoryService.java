package api.kun_uz.service;

import api.kun_uz.dto.category.CategoryCreateDTO;
import api.kun_uz.dto.category.CategoryResponseDTO;
import api.kun_uz.dto.category.CategoryUpdateDTO;
import api.kun_uz.entity.CategoryEntity;
import api.kun_uz.enums.Language;
import api.kun_uz.exception.ValueNotFoundException;
import api.kun_uz.mapper.CategoryMapper;
import api.kun_uz.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public String createCategory(CategoryCreateDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setOrderNumber(categoryDTO.getOrderNumber());
        categoryEntity.setNameUz(categoryDTO.getNameUz());
        categoryEntity.setNameRu(categoryDTO.getNameRu());
        categoryEntity.setNameEn(categoryDTO.getNameEn());
        categoryRepository.save(categoryEntity);
        return categoryEntity.getId();
    }

    public CategoryResponseDTO updateCategory(String id, CategoryUpdateDTO categoryDTO) {
        CategoryEntity categoryEntity = findCategoryById(id);
        categoryEntity.setOrderNumber(categoryDTO.getOrderNumber());
        categoryEntity.setNameUz(getUpdatedValue(categoryDTO.getNameUz(), categoryEntity.getNameUz()));
        categoryEntity.setNameRu(getUpdatedValue(categoryDTO.getNameRu(), categoryEntity.getNameRu()));
        categoryEntity.setNameEn(getUpdatedValue(categoryDTO.getNameEn(), categoryEntity.getNameEn()));
        categoryRepository.save(categoryEntity);
        return toDTO(categoryEntity);
    }

    public String deleteCategory(String id) {
        CategoryEntity categoryEntity = findCategoryById(id);
        categoryEntity.setVisible(false);
        categoryRepository.save(categoryEntity);
        return "Deleted category: " + categoryEntity.getId();
    }

    public CategoryEntity findCategoryById(String categoryId) {
        return categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new ValueNotFoundException("No such category found"));
    }

    public CategoryResponseDTO toDTO(CategoryEntity categoryEntity) {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setCategoryId(categoryEntity.getId());
        categoryResponseDTO.setOrderNumber(categoryEntity.getOrderNumber());
        categoryResponseDTO.setNameUz(categoryEntity.getNameUz());
        categoryResponseDTO.setNameRu(categoryEntity.getNameRu());
        categoryResponseDTO.setNameEn(categoryEntity.getNameEn());
        return categoryResponseDTO;
    }

    private   <T> T getUpdatedValue(T newValue, T oldValue) {
        return Optional.ofNullable(newValue).orElse(oldValue);
    }


    public List<CategoryResponseDTO> getAll() {

        Iterable<CategoryEntity> categoryEntityIterable = categoryRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDTOList = new LinkedList<>();
        categoryEntityIterable.forEach(categoryEntity -> {
            CategoryResponseDTO categoryResponseDTO = toDTO(categoryEntity);
            categoryResponseDTO.setVisible(categoryEntity.getVisible());
            categoryResponseDTO.setCreatedDate(categoryEntity.getCreatedDate());
            categoryResponseDTOList.add(categoryResponseDTO);
        });
        return categoryResponseDTOList;

    }

    public List<CategoryMapper> getByLang(Language lang) {
        List<CategoryMapper> categoryMapperList = categoryRepository.getByLang(lang.name());
        return categoryMapperList;
    }
}
