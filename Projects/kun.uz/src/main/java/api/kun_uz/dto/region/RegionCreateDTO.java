package api.kun_uz.dto.region;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class RegionCreateDTO {
    //order_number,name_uz, name_ru, name_en
    @NotNull(message = "Order number can't be empty")
    private Integer orderNumber;
    @NotBlank(message = "nameUz can't be blank")
    private String nameUz;
    @NotBlank(message = "nameRu can't be blank")
    private String nameRu;
    @NotBlank(message = "nameEn can't be blank")
    private String nameEn;

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getNameUz() {
        return nameUz;
    }

    public void setNameUz(String nameUz) {
        this.nameUz = nameUz;
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
}
