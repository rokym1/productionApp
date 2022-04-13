package production.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Represents meat with constant calories per kilogram and variable weight.
 * Inherits variables from parent class Item.
 * Methods calculateKilocalories and calculatePrice must be implemented from interface Edible.
 */
public class Meat extends Item implements Edible{
    private final int KCAL_PER_KG = 2500;
    private BigDecimal weight;

    /**
     * Creates a meat item with name, category, width, length, productionCost, height,
     * selling price, discount amount and weight
     * @param name Name of meat
     * @param category Object of class Category in which meat belongs
     * @param width Width of meat
     * @param height Height of meat
     * @param length Length of meat
     * @param productionCost Production cost of meat
     * @param sellingPrice Selling price of meat
     * @param discount Discount amount of meat
     * @param weight Weight of meat
     */
    public Meat(Long id, String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length,
                BigDecimal productionCost, BigDecimal sellingPrice, Discount discount ,BigDecimal weight) {
        super(id, name, category, width, height, length, productionCost, sellingPrice, discount);
        this.weight = weight;
    }

    /**
     * Overrides method from Edible interface, this method must be implemented.
     * @return Integer that represents the number of calories of meat item.
     */
    @Override
    public int calculateKilocalories() {
        return (weight.multiply(BigDecimal.valueOf(100))).intValue() * KCAL_PER_KG/100;
    }

    /**
     * Overrides method from Edible interface, this method must be implemented.
     * @return BigDecimal that represents calculated final selling price.
     */
    @Override
    public BigDecimal calculatePrice() {
        BigDecimal calcDiscount = new BigDecimal(100 - getDiscount().discountAmount());
        calcDiscount = calcDiscount.divide(new BigDecimal(100),3, RoundingMode.DOWN);
        return calcDiscount.multiply(getSellingPrice().multiply(weight));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Meat meat = (Meat) o;
        return Objects.equals(weight, meat.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), KCAL_PER_KG, weight);
    }
}