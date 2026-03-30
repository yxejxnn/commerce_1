import java.util.ArrayList;
import java.util.List;

public class Cart {

    // 속성
    List<CartItem> cartItemList = new ArrayList<>(); // 장바구니에 담긴 상품 목록

    // 기능
    // 장바구니 추가 기능
    public void addCartItem(CartItem cartItem) {
        cartItemList.add(cartItem);
    }
    // 장바구니에 담긴 상품 목록을 반환
    public List<CartItem> getCartItemList() {
        return cartItemList;
    }
    // 장바구니에 담긴 상품들의 총 금액을 계산하는 메서드
    public int calculateTotalPrice() {
        int cartTotalPrice = 0; // 합계를 담을 변수

        for (CartItem cartItem : cartItemList) {
            // cartItem - 카트에 담긴, .getCartProduct() - 상품에서, .getProductPrice() - 상품의 가격을 가져옴
            int productPrice = cartItem.getCartProduct().getProductPrice();
            // cartItem - 카트에 담긴, .getCartQuantity() - 상품의 수량을 가져옴
            int productQuantity = cartItem.getCartQuantity();
            // 장바구니에 담기는 상품의 가격과 수량을 곱해서 누적 시킴
            cartTotalPrice += productPrice * productQuantity;
        }
        return cartTotalPrice; // 계산된 총 금액 반환
    }
    // 주문 완료 또는 주문 취소 시 장바구니 비우는 메서드
    public void clearCart() {
        cartItemList.clear();
    }
}