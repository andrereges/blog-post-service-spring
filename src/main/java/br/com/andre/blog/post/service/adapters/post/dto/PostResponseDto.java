package br.com.andre.blog.post.service.adapters.post.dto;

import br.com.andre.blog.post.models.Post;
import br.com.andre.blog.post.models.PostStatus;
import br.com.andre.blog.post.service.adapters.author.dto.AuthorResponseDto;
import br.com.andre.blog.post.utils.PageInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class PostResponseDto {
    private String id;
    private String title;
    private String content;
    private PostStatus status;
    private Set<AuthorResponseDto> authors;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public static PostResponseDto of(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .authors(AuthorResponseDto.of(post.getAuthors()))
                .status(post.getStatus())
                .createdAt(post.getCreatedAt())
                .build();
    }

    public static Set<PostResponseDto> of(Set<Post> posts) {
        return posts.stream()
                .map(post -> PostResponseDto.of(post))
                .collect(Collectors.toSet());
    }

    public static Set<PostResponseDto> of(PageInfo<Post> pageInfo) {
        return pageInfo.content().stream()
                .map(post -> PostResponseDto.of(post))
                .collect(Collectors.toSet());
    }
}
