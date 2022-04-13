package production.model;

import java.math.BigDecimal;

/**
 * Marks items that are edible
 */
public interface Edible {

    /**
     * Calculates calories of an edible item
     * @return integer representing the number of calories of the edible item
     */
    int calculateKilocalories();

    /**
     * Calculates the selling price of the item
     * @return BigDecimal representing the selling price of the edible item
     */
    BigDecimal calculatePrice();

}