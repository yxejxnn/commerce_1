import java.util.ArrayList;
import java.util.List;

public class Cart {

    // 속성
    private List<CartItem> cartItemList = new ArrayList<>(); // 장바구니에 담긴 상품 목록

    // 기능
    // 장바구니 추가 기능
    public void addCartItem(CartItem cartItem) {
        cartItemList.add(cartItem);
    }
    // 장바구니에 담긴 상품 목록을 반환
    public List<CartItem> getCartItemList() {
        return cartItemList;
    }
    // 장바구니가 비어있는지 확인하는 메서드
    public boolean isEmptyCart() {
        return cartItemList.isEmpty();
    }
    // 장바구니 내역을 출력하는 메서드
    public void showCartItemList() {
        for (CartItem cartItem : cartItemList) {
            System.out.println(cartItem.getCartItemInfo());
        }
    }
    // 장바구니에 담긴 상품들의 총 금액을 계산하는 메서드
    public int calculateTotalPrice() {
        int cartTotalPrice = 0; // 합계를 담을 변수

        for (CartItem cartItem : cartItemList) {
            int productPrice = cartItem.getCartProduct().getProductPrice();
            int productQuantity = cartItem.getCartQuantity();
            cartTotalPrice += productPrice * productQuantity;
        }
        return cartTotalPrice; // 계산된 총 금액 반환
    }
    // 주문 완료 또는 주문 취소 시 장바구니 비우는 메서드
    public void clearCart() {
        cartItemList.clear();
    }
}