package production.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents stores with specified web address and a List of items. Inherits the
 * variable name of parent class NamedEntity
 */
public class Store extends NamedEntity {
    private String webAddress;
    private Set<Item> items;

    /**
     * Creates a store with name, web address and a List of items in store
     * @param name Name of store
     * @param webAddress Web address of store
     */
    public Store(Long id, String name, String webAddress) {
        super(id, name);
        this.webAddress = webAddress;
        this.items = new HashSet<>();
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public void displayItems() {
        if (!items.isEmpty()) {
            items.forEach(item -> System.out.println(item.getName().toUpperCase()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Store store = (Store) o;
        return Objects.equals(webAddress, store.webAddress) && Objects.equals(items, store.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), webAddress, items);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public String toString() {
        return " " + getName().toUpperCase()
                + " (ID: " + getId() + ")"
                + " - web address:  " + getWebAddress();
    }
}
