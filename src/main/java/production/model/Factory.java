package production.model;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents factories with specified address as an object of class Address and a List of items. Inherits the
 * variable name of parent class NamedEntity
 */
public class Factory extends NamedEntity {
    private Address address;
    private Set<Item> items;

    /**
     * Creates a factory with name, address and a Set of items in factory
     * @param name Name of factory
     * @param address Address of factory
     */
    public Factory(Long id, String name, Address address) {
        super(id, name);
        this.address = address;
        this.items = new HashSet<>();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Factory factory = (Factory) o;
        return Objects.equals(address, factory.address) && Objects.equals(items, factory.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, items);
    }

    /**
     {@inheritDoc}
     */
    @Override
    public String toString() {
        return " " + getName() + " - "
                + getAddress().toString();
    }
}
