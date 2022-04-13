package production.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a Laptop with specified months of warranty.
 * Inherits variables of parent class Item.
 * Method monthsOfWarranty from Technical interface must be implemented.
 */
public final class Laptop extends Item implements Technical{
    private int months;

    /**
     * Creates a Laptop with name, category, width, length, productionCost, height,
     * selling price, discount amount and months of warranty
     * @param name Name of Laptop
     * @param category Object of class Category in which Laptop belongs
     * @param width Width of Laptop
     * @param height Height of Laptop
     * @param length Length of Laptop
     * @param productionCost Production cost of Laptop
     * @param sellingPrice Selling price of Laptop
     * @param discount Discount amount of Laptop
     * @param months Months of warranty of Laptop
     */
    public Laptop(Long id, String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length,
                  BigDecimal productionCost, BigDecimal sellingPrice, Discount discount, int months) {
        super(id, name, category, width, height, length, productionCost, sellingPrice, discount);
        this.months = months;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    /**
     * Overrides method from Technical interface, this method must be implemented.
     * @return Integer that represents the number of months of warranty
     */
    @Override
    public int monthsOfWarranty() {
        return months;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Laptop laptop = (Laptop) o;
        return months == laptop.months;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), months);
    }
}
