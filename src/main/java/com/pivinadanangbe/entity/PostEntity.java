package com.pivinadanangbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "post")
public class PostEntity  extends AbstractEntity{

    @Column (name="title",columnDefinition = "TEXT")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "modified_date")
    private Date modifiedDate;


    @Column(name = "status")
    private PostStatus status;

    @Column(name = "slug", columnDefinition = "TEXT")
    private String slug;

    @Column (name = "excerpt", columnDefinition = "TEXT")
    private String excerpt;


    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_image_id")
    private PostImageEntity image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "post", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<CommentEntity> comments = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<TagEntity> tags = new HashSet<>();


    @PreUpdate
    public void preUpdate() {
        modifiedDate = new Date();
    }

    @PrePersist
    public void prePersist() {
        createdDate = new Date();
    }

    public void generatePostName() {
        if (StringUtils.isEmpty(this.title)) {
            this.slug = "";
        } else {
            // Loại bỏ các ký tự không mong muốn, thay thế khoảng trắng bằng dấu gạch ngang
            this.slug = this.title.trim()
                    .toLowerCase()
                    .replaceAll("[^a-z0-9\\s-]", "")
                    .replaceAll("\\s+", "-");
        }
    }

    public void generateExcerpt() {
        if (StringUtils.isNotEmpty(this.content)) {
            // Độ dài tối đa của excerpt bạn muốn lấy
            int maxLength = 200;

            // Chuỗi excerpt sẽ được lấy từ đoạn đầu của nội dung
            if (this.content.length() <= maxLength) {
                this.excerpt = this.content;
            } else {
                // Trích xuất phần nội dung đầu tiên đến maxLength ký tự và cắt bớt ký tự cuối cùng để tránh cắt giữa từ
                this.excerpt = this.content.substring(0, maxLength - 1);

                // Kiểm tra và cắt bớt ký tự cuối cùng nếu nó không phải là khoảng trắng
                if (!Character.isWhitespace(this.content.charAt(maxLength - 1))) {
                    int lastSpace = this.excerpt.lastIndexOf(" ");
                    if (lastSpace != -1) {
                        this.excerpt = this.excerpt.substring(0, lastSpace);
                    }
                }
            }
        } else {
            this.excerpt = "";
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PostEntity that = (PostEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
