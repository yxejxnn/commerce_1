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
    public void checkAdminPassword(Scanner scanner) {
        int passwordFailCount = 0;

        while (passwordFailCount < 3) {
            System.out.print("관리자 비밀번호를 입력해주세요: ");
            String inputPassword = scanner.next();
            System.out.println();

            if (inputPassword.equals(adminPassword)) {
                System.out.println("관리자 인증이 완료되었습니다.");
                System.out.println();
                return;
            } else {
                passwordFailCount++;
                System.out.println("비밀번호가 올바르지 않습니다.");
                System.out.println();
            }
        }
        System.out.println("비밀번호를 3회 이상 틀렸습니다. 메인 메뉴로 돌아갑니다.");
        System.out.println();
    }
}