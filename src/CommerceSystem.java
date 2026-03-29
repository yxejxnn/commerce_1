import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    // 속성
    List<Product> productList; // 상품 목록

    // 입력받기 위한 Scanner 객체 생성 (달릴 때만 입이 생기는 고양이는 되면 안 됨...)
    Scanner scanner = new Scanner(System.in);

    // 생성자
    public CommerceSystem(List<Product> productList) {
        this.productList = productList;
    }

    // 기능
    // 입출력 반복문 로직을 start 메서드로 분리
    public void start() {

        while (true) {

            // 프로그램 실행 시 상품 목록 출력
            System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");

            // 향상된 for문으로 List 안에 상품(객체)을 하나씩 꺼내서 출력
            // 향상된 for문은 인덱스 번호가 없기 때문에 번호를 저장할 변수를 따로 만듦
            int number = 1;

            for (Product product : productList) {
                System.out.println(number + ". " + product.productName + " | " + String.format("%,d" , product.productPrice) + "원 | " + product.productDescription);
                number++;
            }

            System.out.println("0. 종료 | 프로그램 종료"); // 종료 메뉴 출력
            System.out.println(); // 줄바꿈
            System.out.print("메뉴 입력: ");
            int productChoice = scanner.nextInt(); // 상품 메뉴 선택
            System.out.println(); // 줄바꿈

            // 0 입력 시 종료
            if (productChoice == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }
            else if (productChoice >= 1 && productChoice <= productList.size()) {
                // 입력한 번호에 맞는 상품을 가져옴
                Product selectedProduct = productList.get(productChoice - 1);
                // 선택한 상품 정보 출력
                System.out.println("선택한 상품: " + selectedProduct.productName + " | " + String.format("%,d" , selectedProduct.productPrice) + "원 | " + selectedProduct.productDescription);
                System.out.println();
            }
            else {
                System.out.println("잘못된 입력입니다.");
                System.out.println(); // 줄바꿈
            }
        }
        scanner.close();
    }
}