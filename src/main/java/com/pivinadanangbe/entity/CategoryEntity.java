package com.pivinadanangbe.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table (name = "category")
public class CategoryEntity extends AbstractEntity {

    @Column (name = "name", nullable = false, length = 100)
    private String name;

    @Column (name = "slug", nullable = false, length = 100)
    private String slug;


    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "modified_date")
    private Date modifiedDate;





    @OneToMany(mappedBy = "category")
    private List<PostEntity> posts = new ArrayList<>();

    @PreUpdate
    public void preUpdate() {
        modifiedDate = new Date();
    }

    @PrePersist
    public void prePersist() {
        createdDate = new Date();
    }


    public void generateCategorySlug() {
        if (this.name == null || this.name.isEmpty()) {
            this.slug = "";
        } else {
            // Chuẩn hóa văn bản tiếng Việt và loại bỏ dấu thanh
            String normalized = Normalizer.normalize(this.name, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            String withoutAccents = pattern.matcher(normalized).replaceAll("");

            // Tạo slug từ văn bản đã được chuẩn hóa
            this.slug = withoutAccents
                    .toLowerCase() // Chuyển thành chữ thường
                    .replaceAll("[^a-z0-9\\s-]", "") // Loại bỏ các ký tự không mong muốn, giữ lại chữ, số, khoảng trắng, dấu gạch ngang
                    .replaceAll("\\s+", "-"); // Thay thế khoảng trắng bằng dấu gạch ngang
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CategoryEntity category = (CategoryEntity) o;
        return getId() != null && Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), getId());
    }
}
