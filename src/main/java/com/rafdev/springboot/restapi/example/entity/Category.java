package com.rafdev.springboot.restapi.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.slugify.Slugify;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "tab_categories")
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    @JsonManagedReference
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Post> posts = new HashSet<>();

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
