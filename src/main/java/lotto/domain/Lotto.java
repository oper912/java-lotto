package lotto.domain;

import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private final Set<LottoNumber> lotto;

    private Lotto(Set<LottoNumber> lotto) {
        this.lotto = lotto;
    }

    public static Lotto of(LottoGenerator lottoGenerator) {
        return new Lotto(lottoGenerator.create());
    }

    public Set<Integer> parseInteger() {
        return lotto.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toSet());
    }

    public boolean contains(LottoNumber num) {
        for (LottoNumber lottoNumber : this.lotto) {
            if (lottoNumber.getNumber() == num.getNumber()) return true;
        }
        return false;
    }
}
