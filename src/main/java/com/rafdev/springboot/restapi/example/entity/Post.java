package com.rafdev.springboot.restapi.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.slugify.Slugify;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "tab_posts")
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    private String title;

    @Column(nullable = false)
    @NotNull
    private String summary;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    @NotNull
    private String content;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    )
    @ToString.Exclude
    private Category category;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public boolean isPublished() {
        return null != publishedAt;
    }

    public void computeSlug() {
        Slugify slugify = new Slugify();
        this.slug = slugify.slugify(title);
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        computeSlug();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
        computeSlug();
    }
}
