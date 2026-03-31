import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart {

    // 속성
    private List<Product> cartProductList = new ArrayList<>(); // 장바구니에 담긴 상품 목록

    // 기능
    // 장바구니가 비어있는지 확인
    public boolean isEmpty() {
        return cartProductList.isEmpty();
    }
    // 주문 관리 메뉴 출력
    public void showOrderMenu() {
        System.out.println("[ 주문 관리 ]");
        System.out.println("4. 장바구니 확인 | 장바구니를 확인 후 주문합니다.");
        System.out.println("5. 주문 취소 | 진행중인 주문을 취소합니다.");
        System.out.println();
    }
    // 장바구니 추가 메뉴 출력
    public void showAddCartMenu() {
        System.out.println("장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인   2. 취소");
        System.out.println();
    }
    // 장바구니 추가 처리
    public void addProduct(Product selectedProduct, int cartChoice) {
        if (cartChoice == 1) {
            if (selectedProduct.updateStock(false)) {
                cartProductList.add(selectedProduct);
                System.out.println(selectedProduct.getProductName() + "가 장바구니에 추가되었습니다.");
                System.out.println();
            } else {
                System.out.println("재고가 부족하여 장바구니에 추가할 수 없습니다.");
                System.out.println();
            }
        } else if (cartChoice == 2) {
            System.out.println("장바구니 추가를 취소했습니다.");
            System.out.println();
        } else {
            System.out.println("잘못된 번호입니다.");
            System.out.println();
        }
    }
    // 총 주문 금액 반환
    public int getTotalPrice() {
        int totalPrice = 0;

        for (Product product : cartProductList) {
            totalPrice += product.getProductPrice();
        }
        return totalPrice;
    }
    // 장바구니 화면 출력
    public void showCartMenu() {
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println();

        System.out.println("[ 장바구니 내역 ]");
        for (Product product : cartProductList) {
            System.out.println(product.getProductInfo());
        }
        System.out.println();

        System.out.println("[ 총 주문 금액 ]");
        System.out.println(String.format("%,d" , getTotalPrice()) + "원");
        System.out.println();

        System.out.println("1. 주문 확정   2. 메인으로 돌아가기");
        System.out.println();
    }
    // 주문 확정 처리
    public void completeOrder() {
        System.out.println("주문이 완료되었습니다!");
        System.out.println("총 금액: " + String.format("%,d" , getTotalPrice()) + "원");
        System.out.println();

        for (Product product : cartProductList) {
            int beforeStock = product.getProductStock();
            product.updateStock(true);
            int afterStock = product.getProductStock();

            System.out.println(product.getProductName() + " 재고가 " + beforeStock + "개 → " + afterStock + "개로 업데이트되었습니다.");
        }
        System.out.println();
        clear();
    }
    // 주문 취소 처리
    public void cancelOrder() {
        clear();
        System.out.println("진행 중인 주문이 취소되었습니다.");
        System.out.println();
    }
    // 장바구니 메뉴 실행
    public void runCartMenu(Scanner scanner) {
        showCartMenu();

        System.out.print("메뉴 입력: ");
        int orderChoice = scanner.nextInt();
        System.out.println();

        if (orderChoice == 1) {
            completeOrder();
        } else if (orderChoice == 2) {
            // 메인으로 돌아가기
        } else {
            System.out.println("잘못된 번호입니다.");
            System.out.println();
        }
    }
    // 장바구니 비우기
    private void clear() {
        cartProductList.clear();
    }
}