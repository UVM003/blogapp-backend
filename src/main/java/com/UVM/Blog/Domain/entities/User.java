package com.UVM.Blog.Domain.entities;

import com.UVM.Blog.Domain.helpers.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "posts")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false,unique = true)
    private String gmail;
    @Column(nullable = false)
    private String password;
  private String name;
private LocalDateTime createdAt;

@OneToMany(mappedBy = "author",cascade = CascadeType.ALL,orphanRemoval = true)
private List<Post> posts=new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && gmail.equals(user.gmail) && password.equals(user.password) && name.equals(user.name) && createdAt.equals(user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gmail, password, name, createdAt);
    }

    @PrePersist
    protected void onCreate(){
        createdAt=LocalDateTime.now();
    }
}
