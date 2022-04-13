package production.generics;

import production.model.Edible;
import production.model.Item;
import production.model.Store;

import java.util.*;

public class FoodStore <T extends Edible> extends Store {
    private List<T> foodStoreItems;

    /**
     * Creates a store with name, web address and a List of items in store
     *
     * @param name       Name of store
     * @param webAddress Web address of store
     */
    public FoodStore(Long id, String name, String webAddress) {
        super(id, name, webAddress);
        this.foodStoreItems = new ArrayList<T>();
    }

    /**
     * Adds object of type T in items list
     *
     * @param item Object of type T
     */
    public void addFoodStoreItem(T item) {
        foodStoreItems.add(item);
    }

    public List<T> getListOfFoodStoreItems() {
        return foodStoreItems;
    }

    @Override
    public void displayItems() {
        if (!foodStoreItems.isEmpty()) {
            List<Item> list = new ArrayList<>();
            for (T item : foodStoreItems) {
                list.add((Item) item);
            }
            list.forEach(item -> System.out.println(item.getName().toUpperCase()));
        }
    }

    @Override
    public Set<Item> getItems() {
        return new HashSet<>((Collection<? extends Item>) foodStoreItems);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FoodStore<?> foodStore = (FoodStore<?>) o;
        return Objects.equals(foodStoreItems, foodStore.foodStoreItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), foodStoreItems);
    }
}
