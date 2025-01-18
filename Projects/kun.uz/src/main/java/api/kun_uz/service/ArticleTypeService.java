package api.kun_uz.service;

import api.kun_uz.dto.articletype.ArticleTypeCreateDTO;
import api.kun_uz.dto.articletype.ArticleTypeResponseDTO;
import api.kun_uz.dto.articletype.ArticleTypeUpdateDTO;
import api.kun_uz.dto.category.CategoryResponseDTO;
import api.kun_uz.entity.ArticleTypeEntity;
import api.kun_uz.entity.CategoryEntity;
import api.kun_uz.enums.Language;
import api.kun_uz.exception.ValueNotFoundException;
import api.kun_uz.mapper.ArticleTypeMapper;
import api.kun_uz.repository.ArticleTypeRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeService {
    private ArticleTypeRepository articleTypeRepository;

    public ArticleTypeService(ArticleTypeRepository articleTypeRepository) {
        this.articleTypeRepository = articleTypeRepository;
    }

    public String createArticleType(ArticleTypeCreateDTO articleDTO) {
        ArticleTypeEntity articleTypeEntity = new ArticleTypeEntity();
        articleTypeEntity.setOrderNumber(articleDTO.getOrderNumber());
        articleTypeEntity.setNameUz(articleDTO.getNameUz());
        articleTypeEntity.setNameEn(articleDTO.getNameEn());
        articleTypeEntity.setNameRu(articleDTO.getNameRu());
        articleTypeRepository.save(articleTypeEntity);
        return "Article type created with id: " + articleTypeEntity.getId();
    }

    public ArticleTypeResponseDTO updateArticleType(ArticleTypeUpdateDTO articleDTO, String id) {
        ArticleTypeEntity articleTypeEntity = getArticleTypeById(id);
        articleTypeEntity.setOrderNumber(getUpdatedValue(articleTypeEntity.getOrderNumber(), articleDTO.getOrderNumber()));
        articleTypeEntity.setNameUz(getUpdatedValue(articleDTO.getNameUz(), articleTypeEntity.getNameUz()));
        articleTypeEntity.setNameRu(getUpdatedValue(articleDTO.getNameRu(), articleTypeEntity.getNameRu()));
        articleTypeEntity.setNameEn(getUpdatedValue(articleDTO.getNameEn(), articleTypeEntity.getNameEn()));
        articleTypeRepository.save(articleTypeEntity);
        return toDTO(articleTypeEntity);

    }

    private ArticleTypeEntity getArticleTypeById(String id) {
        return articleTypeRepository.findById(id).orElseThrow(() -> new ValueNotFoundException("No such article type found"));
    }

    private <T> T getUpdatedValue(T newValue, T oldValue) {
        return Optional.ofNullable(newValue).orElse(oldValue);
    }

    public ArticleTypeResponseDTO toDTO(ArticleTypeEntity articleTypeEntity) {
        ArticleTypeResponseDTO articleTypeResponseDTO = new ArticleTypeResponseDTO();
        articleTypeResponseDTO.setOrderNumber(articleTypeEntity.getOrderNumber());
        articleTypeResponseDTO.setNameUz(articleTypeEntity.getNameUz());
        articleTypeResponseDTO.setNameEn(articleTypeEntity.getNameEn());
        articleTypeResponseDTO.setNameRu(articleTypeEntity.getNameRu());
        return articleTypeResponseDTO;
    }

    public String deleteArticleType(String id) {
        ArticleTypeEntity articleTypeEntity = getArticleTypeById(id);
        articleTypeEntity.setVisible(false);
        articleTypeRepository.save(articleTypeEntity);
        return "Article type deleted with id: " + articleTypeEntity.getId();
    }

    public PageImpl<ArticleTypeResponseDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Iterable<ArticleTypeEntity> articleTypeEntities = articleTypeRepository.findAll();
        List<ArticleTypeResponseDTO> articleTypeResponseDTOList = new LinkedList<>();
        articleTypeEntities.forEach(articleTypeEntity ->
                articleTypeResponseDTOList.add(toDTO(articleTypeEntity)));
        return new PageImpl<>(articleTypeResponseDTOList, pageable, articleTypeResponseDTOList.size());

    }

    public List<ArticleTypeMapper> getByLang(Language lang) {
        List<ArticleTypeMapper> articleTypeEntityList = articleTypeRepository.findByLang(lang.name());
        return articleTypeEntityList;
    }
}
