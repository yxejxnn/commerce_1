import java.util.List;

public class Category {

    // 속성
    private String categoryName;
    private List<Product> categoryProductList;

    // 생성자
    public Category(String categoryName, List<Product> categoryProductList) {
        this.categoryName = categoryName;
        this.categoryProductList = categoryProductList;
    }

    // 기능
    // 카테고리 이름 반환
    public String getCategoryName() {
        return categoryName;
    }
    // 카테고리 상품 목록 반환
    public List<Product> getCategoryProductList() {
        return categoryProductList;
    }
    // 인덱스를 받아서 해당 상품 반환
    public Product getProduct(int index) {
        if (index >= 0 && index < categoryProductList.size()) {
            return categoryProductList.get(index);
        }
        return null;
    }
}