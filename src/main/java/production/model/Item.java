package production.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents items with specified category as an object of class Category.
 * Inherits the variable name of parent class NamedEntity.
 */
public class Item extends NamedEntity {
    private Category category;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal length;
    private BigDecimal productionCost;
    private BigDecimal sellingPrice;
    private Discount discount;

    /**
     * Creates an item with name, category, width, length, productionCost, height, selling price and discount amount.
     *
     * @param name           Name of item
     * @param category       Category of item
     * @param width          Width of item
     * @param height         Height of item
     * @param length         Length of item
     * @param productionCost Production cost of item
     * @param sellingPrice   Selling price of item
     * @param discount       Discount amount of item
     */
    public Item(Long id, String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length,
                BigDecimal productionCost, BigDecimal sellingPrice, Discount discount) {
        super(id, name);
        this.category = category;
        this.width = width;
        this.height = height;
        this.length = length;
        this.productionCost = productionCost;
        this.sellingPrice = sellingPrice;
        this.discount = discount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(BigDecimal productionCost) {
        this.productionCost = productionCost;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    /**
     * Overrides method from base class Object
     *
     * @return String name, id, category, price, discount amount and class name
     */
    @Override
    public String toString() {
        return getName().toUpperCase()
                + " (ID: " + getId() + ")"
                + " (category: " + getCategory().getName()
                + ", price: " + getSellingPrice()
                + "$, discount: " + getDiscount().discountAmount() + "%)"
                + " - '" + getClass().getSimpleName() + "'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Item item = (Item) o;
        return Objects.equals(category, item.category) && Objects.equals(width, item.width)
                && Objects.equals(height, item.height) && Objects.equals(length, item.length)
                && Objects.equals(productionCost, item.productionCost)
                && Objects.equals(sellingPrice, item.sellingPrice) && Objects.equals(discount, item.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), category, width, height, length, productionCost, sellingPrice, discount);
    }
}

