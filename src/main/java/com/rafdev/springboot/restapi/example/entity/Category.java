package com.rafdev.springboot.restapi.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.slugify.Slugify;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
public class Category extends BaseEntity
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
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Post> posts = new HashSet<>();

    public void computeSlug() {
        Slugify slugify = new Slugify();
        this.slug = slugify.slugify(name);
    }

    @PrePersist
    public void prePersist() {
        computeSlug();
    }

    @PreUpdate
    public void preUpdate() {
        computeSlug();
    }
}
