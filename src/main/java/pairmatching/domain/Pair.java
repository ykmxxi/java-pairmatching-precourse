package pairmatching.domain;

import java.util.List;
import java.util.Objects;

public class Pair {

    private final List<Crew> pair;

    public Pair(final List<Crew> pair) {
        this.pair = pair;
    }

    public boolean isThree() {
        return pair.size() == 3;
    }

    public List<Pair> getSubPairs() {
        if (isThree()) {
            return List.of(
                    new Pair(List.of(pair.get(0), pair.get(1))),
                    new Pair(List.of(pair.get(0), pair.get(2))),
                    new Pair(List.of(pair.get(1), pair.get(2)))
            );
        }
        throw new IllegalStateException("세 명의 크루로 이루어진 페어만 호출할 수 있습니다.");
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pair pair1)) {
            return false;
        }
        return Objects.equals(pair, pair1.pair);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pair);
    }

}
