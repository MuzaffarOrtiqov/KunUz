package api.kun_uz.repository;

import api.kun_uz.entity.ArticleTypeEntity;
import api.kun_uz.mapper.ArticleTypeMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleTypeRepository extends CrudRepository<ArticleTypeEntity, String> {

    @Query(value = "select id, order_number as orderNumber, case :name " +
            "when 'UZ' then name_uz " +
            "when 'RU' then name_ru " +
            "when 'EN' then name_en " +
            "end as name " +
            "from article_type ",nativeQuery = true)
    List<ArticleTypeMapper> findByLang(@Param("name") String name);
}
