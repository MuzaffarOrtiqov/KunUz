package api.kun_uz.dto.region;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionResponseDTO {
    private String regionId;
    private String nameRu;
    private String nameEn;
    private String nameUz;
    private Boolean visible;
    private LocalDateTime createdDate;


    public RegionResponseDTO() {
    }

    public RegionResponseDTO(String regionId, String nameRu, String nameEn, String nameUz) {
        this.regionId = regionId;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.nameUz = nameUz;
    }

    public RegionResponseDTO(String regionId, String nameRu, String nameEn, String nameUz, Boolean visible, LocalDateTime createdDate) {
        this.regionId = regionId;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.nameUz = nameUz;
        this.visible = visible;
        this.createdDate = createdDate;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameUz() {
        return nameUz;
    }

    public void setNameUz(String nameUz) {
        this.nameUz = nameUz;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
