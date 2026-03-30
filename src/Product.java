public class Product {

    // 속성
    private String productName; // 상품명
    private int productPrice; // 상품 가격
    private String productDescription; // 상품 설명
    private int productStock; // 상품 재고수량

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
    // 상품 설명 반환
    public String getProductDescription() {
        return productDescription;
    }
    // 상품 재고수량 반환
    public int getProductStock() {
        return productStock;
    }
    // 상품 목록 출력 시 상품 정보 반환 메서드
    public String getProductInfo() {
        return productName + " | " + String.format("$,d" , productPrice) + "원 | " + productDescription;
    }
    // 상품 상세 조회 시 상품 상세 정보 반환 메서드
    public String getProductDetailInfo() {
        return productName + " | " + String.format("$,d" , productPrice) + "원 | " + productDescription + " | 재고: " + productStock + "개";
    }
    // 장바구니에 추가 시 현재 상품 재고가 1개 이상 있는지 확인하는 메서드
    public boolean hasEnoughStock() {
        return productStock > 0;
    }
    // 주문 확정 시 주문한 수량만큼 상품 재고를 차감하는 메서드
    public void reduceProductStock(int orderQuantity) {
        productStock -= orderQuantity;
    }
}