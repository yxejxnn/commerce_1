import java.util.List;
import java.util.Scanner;

public class Admin {

    // 속성
    private String adminPassword = "admin123"; // 관리자 비밀번호
    private List<Category> categoryList; // 전체 카테고리 목록

    // 생성자
    public Admin(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    // 기능
    // 관리자 인증
    public boolean checkAdminPassword(Scanner scanner) {
        int passwordFailCount = 0;

        while (passwordFailCount < 3) {
            System.out.print("관리자 비밀번호를 입력해주세요: ");
            String inputPassword = scanner.next();
            System.out.println();

            if (inputPassword.equals(adminPassword)) {
                System.out.println("관리자 인증이 완료되었습니다.");
                System.out.println();
                return true;
            } else {
                passwordFailCount++;
                System.out.println("비밀번호가 올바르지 않습니다.");
                System.out.println();
            }
        }
        System.out.println("비밀번호를 3회 이상 틀렸습니다. 메인 메뉴로 돌아갑니다.");
        System.out.println();
        return false;
    }
    // 관리자 상품 추가 기능
    public void adminAddProduct(Scanner scanner) {
        System.out.println("어느 카테고리에 상품을 추가하시겠습니까?");
        System.out.println();

        int categoryIndex = 1;
        for (Category category : categoryList) {
            System.out.println(categoryIndex + ". " + category.getCategoryName());
            categoryIndex++;
        }
        System.out.println();
        try {
            // 추가할 상품의 카테고리 입력
            System.out.print("메뉴 입력: ");
            int categoryChoice = scanner.nextInt();
            System.out.println();

            if (categoryChoice < 1 || categoryChoice > categoryList.size()) {
                System.out.println("잘못된 카테고리 번호입니다.");
                System.out.println();
                return;
            }

            Category selectedCategory = categoryList.get(categoryIndex - 1);
            // 추가할 상품의 정보를 입력
            System.out.print("상품명을 입력해주세요: ");
            String productName = scanner.next();
            System.out.print("가격을 입력해주세요: ");
            int productPrice = scanner.nextInt();
            System.out.print("상품 설명을 입력해주세요: ");
            String productDescription = scanner.next();
            System.out.println("재고 수량을 입력해주세요: ");
            int productStock = scanner.nextInt();
            // 입력한 상품 추가
            Product newProduct = new Product(productName, productPrice, productDescription, productStock);
            // 입력한 상품을 추가할 것인지 묻고 입력받기
            System.out.println(newProduct.getProductDetailInfo());
            System.out.println("위 정보로 상품을 추가하시겠습니까?");
            System.out.println("1. 확인   2. 취소");
            System.out.print("메뉴 입력: ");
            int addChoice = scanner.nextInt();
            System.out.println();

            if (addChoice == 1) {
                selectedCategory.getCategoryProductList().add(newProduct);
                System.out.println("상품이 성공적으로 추가되었습니다!");
                System.out.println();
            } else if (addChoice == 2) {
                System.out.println("상품 추가를 취소했습니다.");
                System.out.println();
            } else {
                System.out.println("잘못된 번호입니다.");
            }
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다.");
            System.out.println();
        }
    }
}