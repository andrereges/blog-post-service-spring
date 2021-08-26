package br.com.andre.blog.post.service.adapters.post.dto;

import br.com.andre.blog.post.models.Author;
import br.com.andre.blog.post.models.Post;
import br.com.andre.blog.post.service.adapters.author.dto.AuthorResponseDto;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class PostRequestDto {
    private String title;
    private String content;

    public Post toPost() {
        return new Post(null, title, content, Set.of(new Author("1", "Andre")));
    }

}
