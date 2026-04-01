import java.util.List;
import java.util.Scanner;

public class Admin {

    // 속성
    private String adminPassword = "admin123"; // 관리자 비밀번호
    private List<Category> categoryList; // 전체 카테고리 목록
    private Scanner scanner;

    // 생성자
    public Admin(List<Category> categoryList, Scanner scanner) {
        this.categoryList = categoryList;
        this.scanner = scanner;
    }

    // 기능
    // 관리자 인증
    public boolean checkAdminPassword() {
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
    public void addAdminProduct() {
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
            scanner.nextLine();
            System.out.println();

            if (categoryChoice < 1 || categoryChoice > categoryList.size()) {
                System.out.println("잘못된 카테고리 번호입니다.");
                System.out.println();
                return;
            }

            Category selectedCategory = categoryList.get(categoryChoice - 1);
            // 추가할 상품의 정보를 입력
            System.out.print("상품명을 입력해주세요: ");
            String productName = scanner.nextLine();

            System.out.print("가격을 입력해주세요: ");
            int productPrice = scanner.nextInt();
            scanner.nextLine();

            System.out.print("상품 설명을 입력해주세요: ");
            String productDescription = scanner.nextLine();

            System.out.print("재고 수량을 입력해주세요: ");
            int productStock = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

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
            scanner.nextLine();
        }
    }
    // 관리자 상품 수정 기능
    public void editAdminProduct() {
        try {
            System.out.print("수정할 상품명을 입력해주세요: ");
            String targetProductName = scanner.nextLine();
            System.out.println();

            Product selectedProduct = null;

            for (Category category : categoryList) {
                for (Product product : category.getCategoryProductList()) {
                    if (product.getProductName().equals(targetProductName)) {
                        selectedProduct = product;
                        break;
                    }
                }
                if (selectedProduct != null) {
                    break;
                }
            }
            if (selectedProduct == null) {
                System.out.println("해당 상품을 찾을 수 없습니다.");
                System.out.println();
                return;
            }

            System.out.println("현재 상품 정보: " + selectedProduct.getProductDetailInfo());
            System.out.println();

            System.out.println("수정할 항목을 선택해주세요.");
            System.out.println("1. 가격");
            System.out.println("2. 설명");
            System.out.println("3. 재고수량");
            System.out.print("메뉴 입력: ");
            int editChoice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            if (editChoice == 1) {
                editProductPrice(selectedProduct);
            } else if (editChoice == 2) {
                editProductDescription(selectedProduct);
            } else if (editChoice == 3) {
                editProductStock(selectedProduct);
            } else {
                System.out.println("잘못된 번호입니다.");
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다.");
            System.out.println();
            scanner.nextLine();
        }
    }
    // 상품 가격 수정 기능
    public void editProductPrice(Product selectedProduct) {
        try {
            int beforePrice = selectedProduct.getProductPrice();

            System.out.println("현재 가격: " + String.format("%,d", beforePrice) + "원");
            System.out.print("새로운 가격을 입력해주세요: ");
            int afterPrice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            selectedProduct.setProductPrice(afterPrice);

            System.out.println(selectedProduct.getProductName() + "의 가격이 "
                    + String.format("%,d", beforePrice) + "원 → "
                    + String.format("%,d", afterPrice) + "원으로 수정되었습니다.");
            System.out.println();
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다.");
            System.out.println();
        }
    }
    // 상품 설명 수정 기능
    public void editProductDescription(Product selectedProduct) {
        try {
            String beforeDescription = selectedProduct.getProductDescription();

            System.out.println("현재 설명: " + beforeDescription);
            System.out.print("새로운 설명을 입력해주세요: ");
            String afterDescription = scanner.nextLine();
            System.out.println();

            selectedProduct.setProductDescription(afterDescription);

            System.out.println(selectedProduct.getProductName() + "의 설명이 "
                    + beforeDescription + " → "
                    + afterDescription + "로 수정되었습니다.");
            System.out.println();
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다.");
            System.out.println();
            scanner.nextLine();
        }
    }
    // 상품 재고 수정 기능
    public void editProductStock(Product selectedProduct) {
        try {
            int beforeStock = selectedProduct.getProductStock();

            System.out.println("현재 재고 수량: " + beforeStock);
            System.out.print("새로운 재고 수량을 입력해주세요: ");
            int afterStock = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            selectedProduct.setProductStock(afterStock);

            System.out.println(selectedProduct.getProductName() + "의 재고가 "
                    + beforeStock + "개 → "
                    + afterStock + "개로 수정되었습니다.");
            System.out.println();
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다.");
            System.out.println();
            scanner.nextLine();
        }
    }
    // 관리자 상품 삭제 기능
    public void deleteAdminProduct() {
        try {
            System.out.print("삭제할 상품명을 입력해주세요: ");
            String targetProductName = scanner.nextLine();
            System.out.println();

            Category selectedCategory = null;
            Product selectedProduct = null;

            for (Category category : categoryList) {
                for (Product product : category.getCategoryProductList()) {
                    if (product.equals(targetProductName)) {
                        selectedCategory = category;
                        selectedProduct = product;
                        break;
                    }
                }
                if (selectedProduct != null) {
                    break;
                }
            }
            if (selectedProduct == null) {
                System.out.println("해당 상품을 찾을 수 없습니다.");
                System.out.println();
                return;
            }

            System.out.println("현재 상품 정보: " + selectedProduct.getProductDetailInfo());
            System.out.println();
            System.out.println("위 상품을 삭제하시겠습니까?");
            System.out.println("1. 삭제   2. 취소");
            System.out.print("메뉴 입력: ");
            int deleteChoice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            if (deleteChoice == 1) {
                selectedCategory.getCategoryProductList().remove(selectedProduct);
                System.out.println("상품이 성공적으로 삭제되었습니다!");
                System.out.println();
            } else if (deleteChoice == 2) {
                System.out.println("상품 삭제를 취소했습니다.");
                System.out.println();
            } else {
                System.out.println("잘못된 번호입니다.");
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다.");
            System.out.println();
            scanner.nextLine();
        }
    }
    // 관리자 전체 상품 현황 출력 기능
    public void showAllProduct() {
        System.out.println("[ 전체 상품 현황 ]");
        System.out.println();

        for (Category category : categoryList) {
            System.out.println("[ " + category.getCategoryName() + " ]");

            for (Product product : category.getCategoryProductList()) {
                System.out.println(product.getProductDetailInfo());
            }
            System.out.println();
        }
    }
}