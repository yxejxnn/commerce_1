import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    // 속성
    private List<Category> categoryList;
    private Cart cart;
    private Admin admin;
    private Scanner scanner = new Scanner(System.in);

    // 생성자
    public CommerceSystem(List<Category> categoryList, Cart cart) {
        this.categoryList = categoryList;
        this.cart = cart;
        this.admin = new Admin(categoryList, scanner);
    }

    // 기능
    public void start() {
        while (true) {
            Category selectedCategory = categoryMenu();
            if (selectedCategory == null) {
                continue;
            }

            Product selectedProduct = productMenu(selectedCategory);
            if (selectedProduct == null) {
                continue;
            }

            cartMenu(selectedProduct);
        }
    }
    // 카테고리 목록 출력 + 입력 + 카테고리 선택
    private Category categoryMenu() {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        // 카테고리 목록 출력
        int categoryIndex = 1;
        for (Category category : categoryList) {
            System.out.println(categoryIndex + ". " + category.getCategoryName());
            categoryIndex++;
        }
        System.out.println("0. 종료 | 프로그램 종료");
        System.out.println("6. 관리자 모드");
        System.out.println();
        // 장바구니에 상품이 담겨 있을 경우 출력
        if (!cart.isEmpty()) {
            System.out.println("[ 주문 관리 ]");
            System.out.println("4. 장바구니 확인 | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. 주문 취소 | 진행중인 주문을 취소합니다.");
            System.out.println();
        }
        // 카테고리 메뉴 입력
        System.out.print("메뉴 입력: ");
        int categoryChoice = scanner.nextInt();
        System.out.println();
        // 카테고리 선택
        if (categoryChoice == 0) {
            System.out.println("커머스 플랫폼을 종료합니다.");
            scanner.close();
            System.exit(0);
        } else if (categoryChoice == 6) {
            if (admin.checkAdminPassword()) {
                adminMenu();
            }
            return null;
        } else if (categoryChoice == 4 && !cart.isEmpty()) {
            orderMenu();
            return null;
        } else if (categoryChoice == 5 && !cart.isEmpty()) {
            cart.clearCart();
            System.out.println("진행 중인 주문이 취소되었습니다.");
            System.out.println();
            return null;
        } else if (categoryChoice >= 1 && categoryChoice <= categoryList.size()) {
            return categoryList.get(categoryChoice - 1);
        } else {
            System.out.println("잘못된 카테고리 번호입니다.");
            System.out.println();
            return null;
        }
        return null;
    }
    // 상품 목록 출력 + 입력 + 상품 선택
    private Product productMenu(Category selectedCategory) {
        // 상품 목록 출력
        System.out.println("[ " + selectedCategory.getCategoryName() + " 카테고리 ]");
        int productIndex = 1;
        for (Product product : selectedCategory.getCategoryProductList()) {
            System.out.println(productIndex + ". " + product.getProductInfo());
            productIndex++;
        }
        System.out.println("0. 뒤로가기");
        System.out.println();
        // 상품 메뉴 입력
        System.out.print("메뉴 입력: ");
        int productChoice = scanner.nextInt();
        System.out.println();
        // 상품 선택
        if (productChoice == 0) {
            return null;
        }

        Product selectedProduct = selectedCategory.getProduct(productChoice - 1);

        if (selectedProduct == null) {
            System.out.println("잘못된 상품 번호입니다.");
            System.out.println();
            return null;
        }
        System.out.println(selectedProduct.getProductDetailInfo());
        System.out.println();

        return selectedProduct;
    }
    // 장바구니 선택 메뉴 출력 + 입력 + 장바구니에 상품 추가
    private void cartMenu(Product selectedProduct) {
        // 장바구니 메뉴 출력
        if (!selectedProduct.hasStock()) {
            System.out.println("현재 재고가 없어 구매할 수 없습니다.");
            System.out.println();
            return;
        }
        System.out.println("장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인   2. 취소");
        System.out.println();
        // 장바구니 메뉴 입력
        System.out.print("메뉴 입력: ");
        int cartChoice = scanner.nextInt();
        System.out.println();
        // 장바구니 선택
        if (cartChoice == 1) {
            cart.addProductToCart(selectedProduct);
            System.out.println(selectedProduct.getProductName() + "가 장바구니에 추가되었습니다.");
            System.out.println();
        } else if (cartChoice == 2) {
            System.out.println("장바구니 추가를 취소했습니다.");
            System.out.println();
        } else {
            System.out.println("잘못된 번호입니다.");
            System.out.println();
        }
    }
    // 주문 메뉴 출력 + 입력 + 주문
    private void orderMenu() {
        if (cart.isEmpty()) {
            return;
        }
        // 주문 메뉴 출력
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
        // 주문 메뉴 입력
        System.out.print("메뉴 입력: ");
        int orderChoice = scanner.nextInt();
        System.out.println();
        // 주문
        if (orderChoice == 1) {
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
        } else if (orderChoice == 2) {
            // 메뉴로 돌아가기
        } else {
            System.out.println("잘못된 번호입니다.");
            System.out.println();
        }
    }
    // 관리자 목록 출력 + 입력 + 관리자 모드 선택
    private void adminMenu() {
        while (true) {
            // 관리자 목록 출력
            System.out.println("[ 관리자 모드 ]");
            System.out.println("1. 상품 추가");
            System.out.println("2. 상품 수정");
            System.out.println("3. 상품 삭제");
            System.out.println("4. 전체 상품 현황");
            System.out.println("0. 메인으로 돌아가기");
            System.out.println();
            // 관리자 메뉴 입력
            System.out.print("메뉴 입력: ");
            int adminChoice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            // 관리자 선택
            if (adminChoice == 1) {
                admin.addAdminProduct();
            } else if (adminChoice == 2) {
                admin.editAdminProduct();
            } else if (adminChoice == 3) {
                admin.deleteAdminProduct();
            } else if (adminChoice == 4) {
                admin.showAllProduct();
            } else if (adminChoice == 0) {
                return;
            } else {
                System.out.println("잘못된 번호입니다.");
                System.out.println();
            }
        }
    }
}