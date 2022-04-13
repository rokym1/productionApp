package production.model;


import java.util.Objects;

/**
 * Represents categories with specified description, inherits the variable name of parent class NamedEntity
 */
public class Category extends NamedEntity {
    private String description;

    /**
     * Creates a category with name and description
     * @param id Category id
     * @param name Name of category
     * @param description Description of category
     */
    public Category(Long id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Overrides method from base class Object
     *
     * @return Name and description of category
     */
    @Override
    public String toString() {
        return " Name: " + getName(). toUpperCase()
                + " (ID: " + getId() + ")"
                + " (description: " + getDescription() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return Objects.equals(description, category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description);
    }
}

