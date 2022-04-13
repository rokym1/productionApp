package production.model;

import java.util.Objects;

/**
 * records discount amount data
 */
public record Discount(int discountAmount) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return discountAmount == discount.discountAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountAmount);
    }
}
