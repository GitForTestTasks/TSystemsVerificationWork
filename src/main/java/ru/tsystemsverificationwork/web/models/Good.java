package ru.tsystemsverificationwork.web.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "goods")
public class Good {
    private long goodId;
    private String title;
    private BigDecimal price;
    private String category;
    private String brand;
    private String colour;

    private BigDecimal weight;
    private String size;
    private int count;

    @Basic
    @Column(name = "Brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "Colour")
    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Good(String title, BigDecimal price, String brand, String colour) {
        this.title = title;
        this.price = price;
        this.brand = brand;
        this.colour = colour;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GoodId")
    public long getGoodId() {
        return goodId;
    }

    public void setGoodId(long goodId) {
        this.goodId = goodId;
    }

    @Basic
    @Column(name = "Title")
    @NotBlank
    @Size(min = 2, max = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "Price")
    @NotNull
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "Category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "Weight")
    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "Size")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Basic
    @Column(name = "Count")
    @NotNull
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Good() {
    }


    @Override
    public String toString() {
        return "Good{" +
                 goodId + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Good good = (Good) o;

        if (goodId != good.goodId) return false;
        if (count != good.count) return false;
        if (!title.equals(good.title)) return false;
        if (!price.equals(good.price)) return false;
        if (category != null ? !category.equals(good.category) : good.category != null) return false;
        if (brand != null ? !brand.equals(good.brand) : good.brand != null) return false;
        if (colour != null ? !colour.equals(good.colour) : good.colour != null) return false;
        if (weight != null ? !weight.equals(good.weight) : good.weight != null) return false;
        return size != null ? size.equals(good.size) : good.size == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (goodId ^ (goodId >>> 32));
        result = 31 * result + title.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (colour != null ? colour.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + count;
        return result;
    }
}
