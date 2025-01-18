package api.kun_uz.service;

import api.kun_uz.dto.region.RegionUpdateDTO;
import api.kun_uz.dto.region.RegionCreateDTO;
import api.kun_uz.dto.region.RegionResponseDTO;
import api.kun_uz.entity.RegionEntity;
import api.kun_uz.enums.Language;
import api.kun_uz.exception.ValueNotFoundException;
import api.kun_uz.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service

public class RegionService {
    private RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public String createRegion(RegionCreateDTO regionDTO) {
        RegionEntity regionEntity = new RegionEntity();
        regionEntity.setOrderNumber(regionDTO.getOrderNumber());
        regionEntity.setNameUz(regionDTO.getNameUz());
        regionEntity.setNameRu(regionDTO.getNameRu());
        regionEntity.setNameEn(regionDTO.getNameEn());
        regionRepository.save(regionEntity);
        return regionEntity.getId();
    }


    public RegionResponseDTO updateRegion(String id, RegionUpdateDTO regionDTO) {
        RegionEntity regionEntity = findById(id);
        regionEntity.setNameUz(regionDTO.getNameUz() == null ? regionEntity.getNameUz() : regionDTO.getNameUz());
        regionEntity.setNameRu(regionDTO.getNameRu() == null ? regionEntity.getNameRu() : regionDTO.getNameRu());
        regionEntity.setNameEn(regionDTO.getNameEn() == null ? regionEntity.getNameEn() : regionDTO.getNameEn());
        regionRepository.save(regionEntity);
        return new RegionResponseDTO(
                id,
                regionDTO.getNameRu(),
                regionDTO.getNameEn(),
                regionDTO.getNameUz()

        );
    }

    public RegionEntity findById(String id) {
        return regionRepository.findById(id).orElseThrow(() -> new ValueNotFoundException("No such region found"));
    }

    public String deleteRegion(String id) {
        RegionEntity regionEntity = findById(id);
        regionEntity.setVisible(false);
        regionRepository.save(regionEntity);
        return "Deleted region : " + regionEntity.getId();
    }

    public List<RegionResponseDTO> getAll() {
        Iterable<RegionEntity> regionEntityIterable = regionRepository.findAll();
        List<RegionResponseDTO> regionResponseDTOList = new LinkedList<>();
        regionEntityIterable.forEach(regionEntity -> {
            regionResponseDTOList.add(new RegionResponseDTO(regionEntity.getId(),
                    regionEntity.getNameRu(),
                    regionEntity.getNameEn(),
                    regionEntity.getNameUz(),
                    regionEntity.getVisible(),
                    regionEntity.getCreatedDate()));


        });
        return regionResponseDTOList;
    }

    public List<RegionResponseDTO> getByLang(Language lang) {
        Iterable<RegionEntity> regionEntityIterable = regionRepository.findAllByVisibleTrueOrderByCreatedDateDesc();
        List<RegionResponseDTO> regionResponseDTOList = new LinkedList<>();
        regionEntityIterable.forEach(regionEntity -> {
            RegionResponseDTO regionResponseDTO = new RegionResponseDTO();
            regionResponseDTO.setRegionId(regionEntity.getId());
            switch (lang.name()) {
                case "EN":
                    regionResponseDTO.setNameEn(regionEntity.getNameEn()); break;
                case "RU":
                    regionResponseDTO.setNameRu(regionEntity.getNameRu()); break;
                case "UZ":
                    regionResponseDTO.setNameUz(regionEntity.getNameUz()); break;
            }
            regionResponseDTOList.add(regionResponseDTO);
        });
        return regionResponseDTOList;
    }
}
