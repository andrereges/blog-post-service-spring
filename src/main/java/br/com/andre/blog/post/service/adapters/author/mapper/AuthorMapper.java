package br.com.andre.blog.post.service.adapters.author.mapper;

import br.com.andre.blog.post.models.Author;
import br.com.andre.blog.post.service.adapters.post.mapper.PostMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Author")
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorMapper {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;

    private String name;

    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany(mappedBy = "authors")
    private Set<PostMapper> posts;

    public Author toAuthor() {
        return new Author(id, name);
    }

    public AuthorMapper toAuthorMapper() {
        return AuthorMapper.builder()
                .id(id)
                .name(name)
                .build();
    }
}
