package br.com.andre.blog.post.service.adapters.author.dto;

import br.com.andre.blog.post.models.Author;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class AuthorResponseDto {
    private String id;
    private String name;

    public static AuthorResponseDto of(Author author) {
        return AuthorResponseDto.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }

    public static Set<AuthorResponseDto> of(Set<Author> authors) {
        return authors.stream()
                .map(author -> AuthorResponseDto.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .build())
                .collect(Collectors.toSet());
    }
}
