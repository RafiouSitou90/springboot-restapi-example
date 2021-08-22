package com.rafdev.springboot.restapi.example.entity;

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
@Table(name = "tab_categories")
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public void computeSlug() {
        Slugify slugify = new Slugify();
        this.slug = slugify.slugify(name);
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
