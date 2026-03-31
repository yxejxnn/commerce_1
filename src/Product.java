public class Product {

    // 속성
    private String productName;
    private int productPrice;
    private String productDescription;
    private int productStock;

    // 생성자
    public Product(String productName, int productPrice, String productDescription, int productStock) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productStock = productStock;
    }

    // 기능
    // 상품명 반환
    public String getProductName() {
        return productName;
    }
    // 상품 가격 반환
    public int getProductPrice() {
        return productPrice;
    }
    // 상품 재고 반환
    public int getProductStock() {
        return productStock;
    }
    // 재고 있는지 확인
    public boolean hasStock() {
        return productStock > 0;
    }
    // 재고 1개 차감
    public void decreaseStock() {
        if (!hasStock()) {
            productStock--;
        }
    }
    // 상품 목록 출력 정보 반환
    public String getProductInfo() {
        return productName + " | " + String.format("%,d" , productPrice) + "원 | " + productDescription;
    }
    // 상품 상세 정보 반환
    public String getProductDetailInfo() {
        return productName + " | " + String.format("%,d" , productPrice) + "원 | " + productDescription + " | 재고: " + productStock + "개";
    }
}