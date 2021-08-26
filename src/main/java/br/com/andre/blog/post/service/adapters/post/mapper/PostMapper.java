package br.com.andre.blog.post.service.adapters.post.mapper;

import br.com.andre.blog.post.models.Author;
import br.com.andre.blog.post.models.Post;
import br.com.andre.blog.post.models.PostStatus;
import br.com.andre.blog.post.service.adapters.author.mapper.AuthorMapper;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "Post")
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostMapper {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private PostStatus status;

    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "post_authors", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<AuthorMapper> authors;

    public Post toPost() {
        Set<Author> domainAuthors = authors.stream()
                .map(pm -> pm.toAuthor())
                .collect(Collectors.toSet());

        return new Post(id, title, content, domainAuthors, status, createdAt);
    }

    public static PostMapper toPostMapper(Post post) {
        return PostMapper.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .status(post.getStatus())
                .createdAt(post.getCreatedAt())
                .authors(post.getAuthors().stream()
                        .map(p -> AuthorMapper.builder()
                                .id(p.getId()).name(p.getName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }

    public static Set<PostMapper> toPostMapper(Set<Post> posts) {
        return posts.stream()
                .map(post -> PostMapper.toPostMapper(post))
                .collect(Collectors.toSet());
    }

}
