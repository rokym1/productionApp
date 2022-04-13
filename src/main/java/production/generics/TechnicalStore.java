package production.generics;

import production.model.Item;
import production.model.Store;
import production.model.Technical;

import java.util.*;

public class TechnicalStore <T extends Technical> extends Store {
    private List<T> technicalStoreItems;

    /**
     * Creates a store with name, web address and a List of items in store
     *
     * @param name       Name of store
     * @param webAddress Web address of store
     */
    public TechnicalStore(Long id, String name, String webAddress) {
        super(id, name, webAddress);
        this.technicalStoreItems = new ArrayList<T>();
    }

    /**
     * Adds object of type T in items list
     * @param item Object of type T
     */
    public void addTechnicalStoreItem(T item) {
        technicalStoreItems.add(item);
    }

    public List<T> getListOfTechnicalStoreItems() {
        return technicalStoreItems;
    }

    @Override
    public void displayItems() {
        if (!technicalStoreItems.isEmpty()) {
            List<Item> list = new ArrayList<>();
            for (T item : technicalStoreItems) {
                list.add((Item) item);
            }
            list.forEach(item -> System.out.println(item.getName().toUpperCase()));
        }
    }

    @Override
    public Set<Item> getItems() {
        return new HashSet<>((Collection<? extends Item>) technicalStoreItems);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Optional<Object> opt = Optional.ofNullable(o);
        if (opt.isEmpty() || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TechnicalStore<?> that = (TechnicalStore<?>) o;
        return Objects.equals(technicalStoreItems, that.technicalStoreItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), technicalStoreItems);
    }
}

