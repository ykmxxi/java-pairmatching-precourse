package pairmatching.domain;

public enum Mission {

    RACING_CAR("자동차경주"), LOTTO("로또"), NUMBER_BASEBALL("숫자야구게임"),
    SHOPPING_CART("장바구니"), PAYMENT("결제"), SUBWAY_MAP("지하철노선도"),
    PERFORMANCE_IMPROVEMENTS("성능개선"), RELEASE("배포"),
    NONE("미션없음");

    private final String name;

    Mission(final String name) {
        this.name = name;
    }

    public boolean hasSameName(final String missionName) {
        return this.name.equals(missionName);
    }

}
