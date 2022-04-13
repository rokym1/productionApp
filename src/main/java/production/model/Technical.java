package production.model;

/**
 * Marks technical items, sealed for Laptops
 */

public sealed interface Technical permits Laptop {

    /**
     * Calculates the months of warranty
     * @return Integer that represents the number of months of warranty
     */
    int monthsOfWarranty();
}
