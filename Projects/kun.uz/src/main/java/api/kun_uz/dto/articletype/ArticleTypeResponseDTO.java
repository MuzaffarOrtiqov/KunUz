package api.kun_uz.dto.articletype;

public class ArticleTypeResponseDTO {
    //order_number,name_uz, name_ru, name_en
    private Integer orderNumber;
    private String nameUz;
    private String nameRu;
    private String nameEn;

    public ArticleTypeResponseDTO(Integer orderNumber, String nameUz, String nameRu, String nameEn) {
        this.orderNumber = orderNumber;
        this.nameUz = nameUz;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
    }

    public ArticleTypeResponseDTO() {
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
