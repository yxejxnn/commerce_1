public class CartItem {

    // 속성
    private Product cartProduct; // 장바구니에 담긴 상품
    private int cartQuantity; // 장바구니에 담긴 상품 수량

    // 생성자
    public CartItem(Product cartProduct, int cartQuantity) {
        this.cartProduct = cartProduct;
        this.cartQuantity = cartQuantity;
    }

    // 기능
    // 장바구니에 담긴 상품 반환
    public Product getCartProduct() {
        return cartProduct;
    }
    // 장바구니에 담긴 상품 수량 반환
    public int getCartQuantity() {
        return cartQuantity;
    }
    // 장바구니에 담긴 상품 목록 반환
    public String getCartItemInfo() {
        return cartProduct.getProductName() + " | "
                + String.format("%,d" , cartProduct.getProductPrice()) + "원 | "
                + cartProduct.getProductDetailInfo() + " | 수량: "
                + cartQuantity + "개";
    }
}