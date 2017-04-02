package ru.tsystemsverificationwork.database.models;

        import org.hibernate.validator.constraints.NotBlank;

        import javax.persistence.*;
        import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    private long categoryId;
    private String name;
    private List<Good> good;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @OneToMany(mappedBy = "category")
    public List<Good> getGood() {
        return good;
    }

    public void setGood(List<Good> good) {
        this.good = good;
    }

    @Basic
    @Column(name = "Name")
    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (categoryId != category.categoryId) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (categoryId ^ (categoryId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
