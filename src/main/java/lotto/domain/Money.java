package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {
    private static final BigDecimal ZERO = BigDecimal.ZERO;

    private final BigDecimal amount;

    public static Money of(String value) {
        return new Money(new BigDecimal(value));
    }

    public static Money of(long value) {
        return new Money(BigDecimal.valueOf(value));
    }

    public static Money ZERO() {
        return new Money(ZERO);
    }

    private Money(BigDecimal amount) {
        if (amount.compareTo(ZERO) < 0) {
            throw new IllegalArgumentException(String.format("Money는 음수(%s)일 수 없습니다.", amount.toString()));
        }
        this.amount = amount;
    }

    public Money sum(Money anotherMoney) {
        return new Money((amount.add(anotherMoney.getAmount())));
    }

    public Money minus(Money anotherMoney) {
        return new Money(amount.subtract(anotherMoney.getAmount()));
    }

    public Money multiply(long number) {
        return new Money(amount.multiply(BigDecimal.valueOf(number)));
    }

    public long divide(Money anotherMoney) {
        return amount.divide(anotherMoney.getAmount(), RoundingMode.DOWN)
                .longValue();
    }

    public BigDecimal calculateProfit(Money anotherMoney) {
        return amount.divide(anotherMoney.getAmount(), 2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
