package ru.andrei.tsystemsverificationwork.database.models;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "goods")
@SQLDelete(sql="UPDATE goods SET IsGoodDeleted = '1' WHERE GoodId = ?")
public class Good {
    private long goodId;
    private String title;
    private BigDecimal price;
    private String brand;
    private String colour;
    private BigDecimal weight;
    private String size;
    private String filePath;
    private int count;
    private Category category;
    private char isGoodDeleted;

    @Basic
    @Column(name = "IsGoodDeleted")
    public char getIsGoodDeleted() {
        return isGoodDeleted;
    }

    public void setIsGoodDeleted(char isDeleted) {
        this.isGoodDeleted = isDeleted;
    }

    @Basic
    @Column(name = "FilePath")
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CategoryId")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

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

    @Column(name = "Price")
    @NotNull
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Good good = (Good) o;

        if (goodId != good.goodId) return false;
        return title.equals(good.title);
    }

    @Override
    public int hashCode() {
        int result = (int) (goodId ^ (goodId >>> 32));
        result = 31 * result + title.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Good{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", colour='" + colour + '\'' +
                ", weight=" + weight +
                ", size='" + size + '\'' +
                ", count=" + count +
                ", category=" + category +
                '}';
    }
}
