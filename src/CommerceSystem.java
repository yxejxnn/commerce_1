import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    // 속성
    private List<Category> categoryList; // 카테고리 목록
    private Cart cart; // 장바구니

    // 입력받기 위한 Scanner 객체 생성 (달릴 때만 입이 생기는 고양이는 되면 안 됨...)
    private Scanner scanner = new Scanner(System.in);

    // 생성자
    public CommerceSystem(List<Category> categoryList, Cart cart) {
        this.categoryList = categoryList;
        this.cart = cart;
    }

    // 기능
    // 메인메뉴 출력 메서드
    private void showMainMenu() {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");

        int categoryNumber = 1;

        for (Category category : categoryList) {
            System.out.println(categoryNumber + ". " + category.getCategoryName());
            categoryNumber++;
        }
        System.out.println("0. 종료 | 프로그램 종료");
        System.out.println(); // 줄바꿈

        if (!cart.isEmptyCart()) {
            showOrderManagementMenu();
        }
    }
    // 주문 관리 메뉴 출력 메서드
    private void showOrderManagementMenu() {
        System.out.println("[ 주문 관리 ]");
        System.out.println("4. 장바구니 확인 | 장바구니를 확인 후 주문합니다.");
        System.out.println("5. 주문 취소 | 진행중인 주문을 취소합니다.");
        System.out.println(); // 줄바꿈
    }
    // 선택한 카테고리 상품 메뉴 출력 메서드
    private void showCategoryMenu(Category selectedCategory) {
        System.out.println("[ " + selectedCategory.getCategoryName() + " 카테고리 ]");
        selectedCategory.showProductList();
        System.out.println("0. 뒤로가기");
        System.out.println();
    }
    // 선택한 상품을 장바구니에 추가하는 메서드
    private boolean addProductToCart(Product selectedProduct) {
        System.out.println("장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인   2. 취소");
        System.out.println();
        System.out.print("메뉴 입력: ");
        int cartChoice = scanner.nextInt();
        System.out.println();

        if (cartChoice == 1) {
            if (selectedProduct.hasEnoughStock()) {
                CartItem cartItem = new CartItem(selectedProduct, 1);
                cart.addCartItem(cartItem);

                System.out.println(selectedProduct.getProductName() + "가 장바구니에 추가되었습니다.");
                System.out.println();
                return true;
            } else {
                System.out.println("재조가 부족하여 장바구니에 추가할 수 없습니다.");
                System.out.println();
                return false;
            }
        } else if (cartChoice == 2) {
            System.out.println("장바구니 추가를 취소했습니다.");
            System.out.println();
            return false;
        } else {
            System.out.println("잘못된 번호입니다.");
            System.out.println();
            return false;
        }
    }
    // 장바구니 확인 화면을 출력하는 메서드
    private void showCartMenu() {
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println();

        System.out.println("[ 장바구니 내역 ]");
        cart.showCartItemList();
        System.out.println();

        System.out.println("[ 총 주문 금액 ]");
        System.out.println(String.format("%,d" , cart.calculateTotalPrice()) + "원");
        System.out.println();

        System.out.println("1. 주문 확정   2. 메인으로 돌아가기");
        System.out.println();
    }
    // 주문 확정 처리 메서드
    private void completeOrder() {
        System.out.println("주문이 완료되었습니다!");
        System.out.println("총 금액: " + String.format("%,d" , cart.calculateTotalPrice()) + "원");
        System.out.println(); // 줄바꿈

        for (CartItem cartItem : cart.getCartItemList()) {
            Product cartProduct = cartItem.getCartProduct();
            int orderQuantity = cartItem.getCartQuantity();

            int beforeStock = cartProduct.getProductStock();
            cartProduct.reduceProductStock(orderQuantity);
            int afterStock = cartProduct.getProductStock();

            System.out.println(cartProduct.getProductName() + " 재고가 " + beforeStock + "개 → " + afterStock + "개로 업데이트되었습니다.");
        }
        System.out.println();
        cart.clearCart();
    }
    // 진행 중인 주문을 취소하는 메서드
    private void cancleOrder() {
        cart.clearCart();
        System.out.println("진행 중인 주문이 취소되었습니다.");
        System.out.println();
    }
    // 입출력 반복문 로직을 start 메서드로 분리
    public void start() {
        while (true) {
            showMainMenu();

            System.out.print("메뉴 입력: ");
            int categoryChoice = scanner.nextInt(); // 카테고리 메뉴 선택
            System.out.println(); // 줄바꿈

            if (categoryChoice == 0) { // 0 입력 시 종료
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            } else if (categoryChoice >= 1 && categoryChoice <= categoryList.size()) { // 카테고리 번호를 입력한 경우

                while (true) {
                    // 입력한 카테고리 객체를 가져옴
                    Category selectedCategory = categoryList.get(categoryChoice - 1);

                    showCategoryMenu(selectedCategory);

                    System.out.print("메뉴 입력: ");
                    int productChoice = scanner.nextInt();
                    System.out.println(); // 줄바꿈

                    // 0 입력 시 메인 카테고리 목록으로 돌아가기
                    if (productChoice == 0) {
                        break;
                    }

                    // 상품 번호를 입력한 경우
                    else if (productChoice >= 1 && productChoice <= selectedCategory.getProductCount()) {
                        // 선택한 상품 객체를 가져옴
                        Product selectedProduct = selectedCategory.getProductList().get(productChoice - 1);
                        System.out.println(selectedProduct.getProductDetailInfo());
                        System.out.println(); // 줄바꿈

                        if (addProductToCart(selectedProduct)) {
                            break;
                        }
                    } else {
                        System.out.println("잘못된 상품 번호입니다.");
                        System.out.println(); // 줄바꿈
                    }
                }
            } else if (categoryChoice == 4 && !cart.isEmptyCart()) { // 4 입력 시 장바구니 확인
                showCartMenu();

                System.out.print("메뉴 입력: ");
                int orderChoice = scanner.nextInt();
                System.out.println(); // 출바꿈

                if (orderChoice == 1) { // 1 입력 시 주문 확정
                    completeOrder();
                } else if (orderChoice == 2) { // 2 입력 시 메인으로 돌아가기
                    continue;
                } else {
                    System.out.println("잘못된 번호입니다.");
                }
            } else if (categoryChoice == 5 && !cart.isEmptyCart()) { // 5 입력 시 주문 취소
                cancleOrder();
            } else{
                System.out.println("잘못된 카테고리 번호입니다.");
                System.out.println(); // 줄바꿈
            }
        }
        scanner.close();
    }
}