import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    // 속성
    private List<Category> categoryList;
    private Cart cart;
    private Scanner scanner = new Scanner(System.in);

    // 생성자
    public CommerceSystem(List<Category> categoryList, Cart cart) {
        this.categoryList = categoryList;
        this.cart = cart;
    }

    // 기능
    public void start() {
        while (true) {
            showCategoryMenu();
            System.out.print("메뉴 입력: ");
            int categoryMenuChoice = scanner.nextInt();
            System.out.println();

            if (categoryMenuChoice == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }

            if (categoryMenuChoice >= 1 && categoryMenuChoice <= categoryList.size()) {
                runProductMenu(categoryList.get(categoryMenuChoice - 1));
            } else if (categoryMenuChoice == 4 && !cart.isEmpty()) {
                runCartMenu();
            } else if (categoryMenuChoice == 5 && !cart.isEmpty()) {
                cancelOrder();
            } else {
                System.out.println("잘못된 번호입니다.");
                System.out.println();
            }
        }
        scanner.close();
    }
    // 카테고리 메뉴 출력
    private void showCategoryMenu() {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");

        int categoryIndex = 1;
        for (Category category : categoryList) {
            System.out.println(categoryIndex + ". " + category.getCategoryName());
            categoryIndex++;
        }
        System.out.println("0. 종료 | 프로그램 종료");
        System.out.println();

        if (!cart.isEmpty()) {
            System.out.println("[ 주문 관리 ]");
            System.out.println("4. 장바구니 확인 | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. 주문 취소 | 진행중인 주문을 취소합니다.");
            System.out.println();
        }

    }
    // 상품 선택 메뉴
    private void runProductMenu(Category selectedCategory) {
        System.out.println("[ " + selectedCategory.getCategoryName() + " 카테고리 ]");
        int productIndex = 1;
        for (Product product : selectedCategory.getCategoryProductList()) {
            System.out.println(productIndex + ". " + product.getProductInfo());
            productIndex++;
        }
        System.out.println("0. 뒤로가기");
        System.out.println();

        System.out.print("메뉴 입력: ");
        int productChoice = scanner.nextInt();
        System.out.println();

        if (productChoice == 0) {
            return;
        }
        Product selectedProduct = selectedCategory.getProduct(productChoice - 1);

        if (selectedProduct != null) {
            System.out.println(selectedProduct.getProductDetailInfo());
            System.out.println();

            if (!selectedProduct.hasStock()) {
                System.out.println("현재 재고가 없어 구매할 수 없습니다.");
                System.out.println();
                return;
            }

            System.out.println("장바구니에 추가하시겠습니까?");
            System.out.println("1. 확인   2. 취소");
            System.out.println();
            System.out.print("메뉴 입력: ");
            int cartChoice = scanner.nextInt();
            System.out.println();

            if (cartChoice == 1) {
                cart.addProductToCart(selectedProduct);
                System.out.println(selectedProduct.getProductName() + "가 장바구니에 추가되었습니다.");
                System.out.println();
            } else {
                System.out.println("장바구니 추가를 취소했습니다.");
                System.out.println();
            }
        } else {
            System.out.println("잘못된 상품 번호입니다.");
            System.out.println();
        }
    }
    // 장바구니 및 결제 메뉴
    private void runCartMenu() {
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println();
        System.out.println("[ 장바구니 내역 ]");
        for (Product product : cart.getCartProductList()) {
            System.out.println(product.getProductInfo());
        }
        System.out.println();
        System.out.println("[ 총 주문 금액 ]");
        System.out.println(String.format("%,d" , cart.calculateTotalPrice()) + "원");
        System.out.println();
        System.out.println("1. 주문 확정   2. 메뉴로 돌아가기");
        System.out.println();
        System.out.print("메뉴 입력: ");
        int orderChoice = scanner.nextInt();
        System.out.println();

        if (orderChoice == 1) {
            completeOrder();
        }
    }
    // 주문 확정 처리
    private void completeOrder() {
        System.out.println("주문이 완료되었습니다!");
        System.out.println();
        System.out.println("총 결제 금액: " + String.format("%,d" , cart.calculateTotalPrice()) + "원");
        System.out.println();

        for (Product product : cart.getCartProductList()) {
            int beforeStock = product.getProductStock();
            product.decreaseStock();
            System.out.println(product.getProductName() + " 재고가 " + beforeStock + "개 → " + product.getProductStock() + "개로 업데이트되었습니다.");
        }
        cart.clearCart();
        System.out.println();
    }
    // 주문 취소
    private void cancelOrder() {
        cart.clearCart();
        System.out.println("진행 중인 주문이 취소되었습니다.");
        System.out.println();
    }
}