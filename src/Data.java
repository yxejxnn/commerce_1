import java.util.ArrayList;
import java.util.List;

public class Data {

    public static List<Category> createCategoryList() {
        List<Category> categoryList = new ArrayList<>();

        List<Product> electronicsProductList = new ArrayList<>();
        electronicsProductList.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 30));
        electronicsProductList.add(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 20));
        electronicsProductList.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 10));
        electronicsProductList.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 50));

        List<Product> clothesProductList = new ArrayList<>();
        clothesProductList.add(new Product("집순이 후드티", 49000, "밖에 나가기 싫을 때 입는 후드티", 40));
        clothesProductList.add(new Product("개미허리 청바지", 30000, "앉으면 숨 참고 입어야 하는 청바지", 40));
        clothesProductList.add(new Product("월요병 방치 패딩", 87000, "출근하기 싫은 아침에 입는 패딩", 40));

        List<Product> foodProductList = new ArrayList<>();
        foodProductList.add(new Product("야식용 라면", 4500, "밤 11시 이후에 먹으면 더 맛있는 라면", 80));
        foodProductList.add(new Product("현타방지 초콜릿", 3000, "인생이 산으로 갈 때 하나씩만 먹는 초콜릿", 80));
        foodProductList.add(new Product("한입만 금지 푸딩", 4500, "한입만 허락하면 절반이 사라지는 푸딩", 80));

        categoryList.add(new Category("전자제품", electronicsProductList));
        categoryList.add(new Category("의류", clothesProductList));
        categoryList.add(new Category("식품", foodProductList));

        return categoryList;
    }
}
