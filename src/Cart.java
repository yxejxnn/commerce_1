import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart {

    // 속성
    private List<Product> cartProductList = new ArrayList<>(); // 장바구니에 담긴 상품 목록

    // 기능
    public List<Product> getCartProductList() {
        return cartProductList;
    }
    // 장바구니가 비어있는지 확인
    public boolean isEmpty() {
        return cartProductList.isEmpty();
    }
    // 장바구니에 상품 추가
    public void addProductToCart(Product product) {
        cartProductList.add(product);
    }
    // 총 주문 금액 계산
    public int calculateTotalPrice() {
        int totalPrice = 0;

        for (Product product : cartProductList) {
            totalPrice += product.getProductPrice();
        }
        return totalPrice;
    }
    // 장바구니 비우기
    public void clearCart() {
        cartProductList.clear();
    }
}