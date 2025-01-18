package api.kun_uz.controller;

import api.kun_uz.dto.articletype.ArticleTypeCreateDTO;
import api.kun_uz.dto.articletype.ArticleTypeResponseDTO;
import api.kun_uz.dto.articletype.ArticleTypeUpdateDTO;
import api.kun_uz.dto.region.RegionResponseDTO;
import api.kun_uz.enums.Language;
import api.kun_uz.mapper.ArticleTypeMapper;
import api.kun_uz.service.ArticleTypeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article-type")
public class ArticleTypeController {
    private ArticleTypeService articleTypeService;

    public ArticleTypeController(ArticleTypeService articleTypeService) {
        this.articleTypeService = articleTypeService;
    }
    @PostMapping("/create")
    public ResponseEntity<String> createArticleType(@RequestBody ArticleTypeCreateDTO articleDTO){
       return ResponseEntity.ok(articleTypeService.createArticleType(articleDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ArticleTypeResponseDTO> updateArticleType(@RequestBody ArticleTypeUpdateDTO articleDTO,
                                                                    @PathVariable String id){
        return ResponseEntity.ok(articleTypeService.updateArticleType(articleDTO, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return ResponseEntity.ok(articleTypeService.deleteArticleType(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ArticleTypeResponseDTO>> getAll(@RequestParam(name = "page",defaultValue = "1") int page,
                                                               @RequestParam(name = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(articleTypeService.getAll(page-1, size));
    }

    @GetMapping("/lang")
    public ResponseEntity<List<ArticleTypeMapper>> getByLang(@RequestHeader(name = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(articleTypeService.getByLang(lang));
    }

}