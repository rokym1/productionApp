package production.sort;

import production.model.Item;

import java.util.Comparator;

public class ProductionSorter implements Comparator<Item> {


    /**
     {@inheritDoc}
     */

    @Override
    public int compare(Item o1, Item o2) {
        return o1.getSellingPrice().compareTo(o2.getSellingPrice());
    }

    /**
     * Returns a comparator that imposes the reverse ordering of this
     * comparator.
     *
     * @return a comparator that imposes the reverse ordering of this
     * comparator.
     * @since 1.8
     */
    @Override
    public Comparator<Item> reversed() {
        return Comparator.super.reversed();
    }
}
