package api.kun_uz.controller;

import api.kun_uz.dto.RegionUpdateDTO;
import api.kun_uz.dto.category.CategoryCreateDTO;
import api.kun_uz.dto.category.CategoryResponseDTO;
import api.kun_uz.dto.category.CategoryUpdateDTO;
import api.kun_uz.dto.region.RegionResponseDTO;
import api.kun_uz.enums.Language;
import api.kun_uz.mapper.CategoryMapper;
import api.kun_uz.service.CategoryService;
import api.kun_uz.service.RegionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final RegionService regionService;

    public CategoryController(CategoryService categoryService, RegionService regionService) {
        this.categoryService = categoryService;
        this.regionService = regionService;
    }
    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody CategoryCreateDTO categoryDTO){
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponseDTO>update(@PathVariable String id, @RequestBody CategoryUpdateDTO categoryDTO){

        CategoryResponseDTO categoryResponseDTO = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(categoryResponseDTO);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDTO>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/lang")
    public ResponseEntity<List<CategoryMapper>> getLang(@RequestHeader(name = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(categoryService.getByLang(lang));
    }
}
