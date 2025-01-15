package api.kun_uz.repository;

import api.kun_uz.entity.CategoryEntity;
import api.kun_uz.mapper.CategoryMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity, String> {
    @Query(value = "select id, order_number, " +
            "case :lang " +
            "when 'UZ' then name_uz " +
            "when 'RU' then name_ru " +
            "when 'EN' then name_en " +
            "end as name " +
            "from category as c ",nativeQuery = true)
    List<CategoryMapper> getByLang(String lang);
}
