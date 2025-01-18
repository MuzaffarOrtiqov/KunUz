package api.kun_uz.dto.articletype;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class ArticleTypeCreateDTO {
    //(order_number,name_uz, name_ru, name_en)
    @NotNull(message = "Order number is required")
    private Integer orderNumber;
    @NotBlank(message = "NameUz is required")
    private String nameUz;
    @NotBlank(message = "NameRu is required")
    private String nameRu;
    @NotBlank(message = "NameEn is required")
    private String nameEn;

    public ArticleTypeCreateDTO(Integer orderNumber, String nameUz, String nameRu, String nameEn) {
        this.orderNumber = orderNumber;
        this.nameUz = nameUz;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
    }

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
