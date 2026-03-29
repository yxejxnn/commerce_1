import java.util.List;

public class Category {

    // 속성
    String categoryName; // 카테고리 이름
    List<Product> productList; // 해당 카테고리에 속한 상품 목록

    // 생성자
    public Category(String categoryName, List<Product> productList) {
        this.categoryName = categoryName;
        this.productList = productList;
    }

    // 기능
    // 카테고리 이름 반환
    public String getCategoryName() {
        return categoryName;
    }
    // 상품 목록 반환
    public List<Product> getProductList() {
        return productList;
    }
}