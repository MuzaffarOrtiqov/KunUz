package api.kun_uz.repository;

import api.kun_uz.dto.region.RegionResponseDTO;
import api.kun_uz.entity.RegionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegionRepository extends CrudRepository<RegionEntity,String> {

    Iterable<RegionEntity> findAllByVisibleTrueOrderByCreatedDateDesc();
}
