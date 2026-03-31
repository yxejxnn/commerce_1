import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 현재 확장성이 좋은 코드
        List<Category> categoryList = Data.createCategoryList();

        // 빈 장바구니 생성
        Cart cart = new Cart();

        // CommerceSystem 객체 생성
        CommerceSystem commerceSystem = new CommerceSystem(categoryList, cart);

        // 커머스 플랫폼 실행
        commerceSystem.start();
    }
}