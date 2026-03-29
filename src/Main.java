import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 전자제품 카테고리의 상품들 생성 (진열할 상품들을 만듦)
        Product galaxyS25 = new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 30);
        Product iphone16 = new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 20);
        Product macBookPro = new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 10);
        Product airPodsPro = new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 50);

        // 전자제품 카테고리 생성 (진열할 상품들을 전자제품이라는 카테고리로 분류할 것임)
        List<Product> electronicsProductList = new ArrayList<>();

        // 전자제품을 전자제품 카테고리 추가 (진열할 상품들에게 "너는 전자제품이다" 라고 태그를 붙임)
        electronicsProductList.add(galaxyS25);
        electronicsProductList.add(iphone16);
        electronicsProductList.add(macBookPro);
        electronicsProductList.add(airPodsPro);

        // 전자제품 카테고리 객체 생성 (진열대에 전자제품코너 라고 붙이고 전자제품을 진열함)
        Category electronicsCategory = new Category("전자제품", electronicsProductList);

        // 의류 카테고리의 상품들 생성 (진열할 상품들을 만듦)
        Product homebodyHoodie = new Product("집순이 후드티", 49000, "밖에 나가기 싫을 때 입는 후드티", 40);
        Product tightJeans = new Product("개미허리 청바지", 30000, "앉으면 숨 참고 입어야 하는 청바지", 40);
        Product mondayPadding = new Product("월요병 방치 패딩", 87000, "출근하기 싫은 아침에 입는 패딩", 40);

        // 의류 카테고리 생성 (진열할 상품들을 의류라는 카테고리로 분류할 것임)
        List<Product> clothesProductList = new ArrayList<>();

        // 의류를 의류 카테고리 추가 (진열할 상품들에게 "너는 의류다" 라고 태그를 붙임)
        clothesProductList.add(homebodyHoodie);
        clothesProductList.add(tightJeans);
        clothesProductList.add(mondayPadding);

        // 의류 카테고리 객체 생성 (진열대에 의류코너 라고 붙이고 의류를 진열함)
        Category clothesCategory = new Category("의류", clothesProductList);

        // 식품 카테고리의 상품들 생성 (진열할 상품들을 만듦)
        Product lateNightRamen = new Product("야식용 라면", 4500, "밤 11시 이후에 먹으면 더 맛있는 라면", 80);
        Product realityChocolate = new Product("현타방지 초콜릿", 3000, "인생이 산으로 갈 때 하나씩만 먹는 초콜릿", 80);
        Product noBitePudding = new Product("한입만 금지 푸딩", 4500, "한입만 허락하면 절반이 사라지는 푸딩", 80);

        // 식품 카테고리 생성 (진열할 식품들을 식품이라는 카테고리로 분류할 것임)
        List<Product> foodProductList = new ArrayList<>();

        // 식품을 식품 카테고리에 추가 (진열할 상품들에게 "너는 식품이다" 라고 태그를 붙임)
        foodProductList.add(lateNightRamen);
        foodProductList.add(realityChocolate);
        foodProductList.add(noBitePudding);

        // 식품 카테고리 객체 생성 (진열대에 식품코너 라고 붙이고 식품을 진열함)
        Category foodCategory = new Category("식품", foodProductList);

        // 생성한 카테고리 객체들을 담을 리스트 생성
        List<Category> categoryList = new ArrayList<>();

        // 카테고리를 카테고리 리스트에 담기
        categoryList.add(electronicsCategory);
        categoryList.add(clothesCategory);
        categoryList.add(foodCategory);

        // 빈 장바구니 생성
        Cart cart = new Cart();

        // CommerceSystem 객체 생성
        CommerceSystem commerceSystem = new CommerceSystem(categoryList, cart);

        // 커머스 플랫폼 실행
        commerceSystem.start();
    }
}