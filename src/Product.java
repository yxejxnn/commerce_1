public class Product {

    // 속성
    String productName; // 상품명
    int productPrice; // 상품 가격
    String productDescription; // 상품 설명
    int productStock; // 상품 재고수량

    // 생성자
    public Product(String productName, int productPrice, String productDescription, int productStock) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productStock = productStock;
    }
}