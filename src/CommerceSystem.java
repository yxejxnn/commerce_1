import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    // 속성
    List<Category> categoryList; // 카테고리 목록

    // 입력받기 위한 Scanner 객체 생성 (달릴 때만 입이 생기는 고양이는 되면 안 됨...)
    Scanner scanner = new Scanner(System.in);

    // 생성자
    public CommerceSystem(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    // 기능
    // 입출력 반복문 로직을 start 메서드로 분리
    public void start() {

        while (true) {

            // 프로그램 실행 시 상품 목록 출력
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");

            // 향상된 for문으로 List 안에 카테고리 목록을 하나씩 꺼내서 출력
            // 향상된 for문은 인덱스 번호가 없기 때문에 번호를 저장할 변수를 따로 만듦
            int categoryNumber = 1;

            // 카테고리 목록 출력
            for (Category category : categoryList) {
                System.out.println(categoryNumber + ". " + category.getCategoryName());
                categoryNumber++;
            }

            System.out.println("0. 종료 | 프로그램 종료"); // 종료 메뉴 출력
            System.out.println(); // 줄바꿈
            System.out.print("메뉴 입력: ");
            int categoryChoice = scanner.nextInt(); // 카테고리 메뉴 선택
            System.out.println(); // 줄바꿈

            // 0 입력 시 종료
            if (categoryChoice == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }

            while (true) {

                // 카테고리 번호를 입력한 경우
                if (categoryChoice >= 1 && categoryChoice <= categoryList.size()) {
                    // 입력한 카테고리 객체를 가져옴
                    Category selectedCategory = categoryList.get(categoryChoice - 1); // 리스트 인덱스는 0부터 시작하기 때문에 1을 빼줌
                    // 선택한 카테고리 출력
                    System.out.println("[ " + selectedCategory.categoryName + " 카테고리 ]");

                    // 향산된 for문으로 List 안에 상품 목록을 하나씩 꺼내서 출력
                    // 향산된 for문은 인덱스 번호가 없기 때문에 번호를 저장할 변수를 따로 만듦
                    int productNumber = 1;

                    // 선택한 카테고리에 들어있는 상품 목록 출력
                    for (Product product : selectedCategory.productList) {
                        // String.foramt("%,c")를 사용해서 금액 출력 시 천단위 구분
                        System.out.println(productNumber + ". " + product.productName + " | " + String.format("%,d", product.productPrice) + "원 | " + product.productDescription);
                        productNumber++;
                    }

                    // 뒤로가기 메뉴 및 상품 선택 메뉴 출력
                    System.out.println("0. 뒤로가기");
                    System.out.println(); // 줄바꿈
                    System.out.print("메뉴 입력: ");
                    int productChoice = scanner.nextInt();
                    System.out.println(); // 줄바꿈

                    // 0 입력 시 메인 카테고리 목록으로 돌아가기
                    if (productChoice == 0) {
                        break;
                    }

                    // 상품 번호를 입력한 경우
                    if (productChoice >= 1 && productChoice <= selectedCategory.getProductList().size()) {
                        // 선택한 상품 객체를 가져옴
                        Product selectedProduct = selectedCategory.productList.get(productChoice - 1);
                        System.out.println(selectedProduct.productName + " | " + String.format("%,d", selectedProduct.productPrice) + "원 | " + selectedProduct.productDescription + " | 재고: " + selectedProduct.productStock + "개");
                        System.out.println(); // 줄바꿈
                        break;
                    }
                    else {
                        System.out.println("잘못된 상품 번호입니다.");
                        System.out.println(); // 줄바꿈
                    }
                }
                else {
                    System.out.println("잘못된 카테고리 번호입니다.");
                    System.out.println(); // 줄바꿈
                }
            }
        }
        scanner.close();
    }
}