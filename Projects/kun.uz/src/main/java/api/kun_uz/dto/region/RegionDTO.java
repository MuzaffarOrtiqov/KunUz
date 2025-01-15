package api.kun_uz.dto.region;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;


import java.time.LocalDateTime;
@Getter
@Setter
@Validated
public class RegionDTO {
    //    id,order_number,name_uz, name_ru, name_en,visible,created_date
    private String id;
    private Integer orderNumber;
    private String nameUz;
    private String nameRu;
    private String nameEn;
    private Boolean visible;
    private LocalDateTime createdDate;
}
