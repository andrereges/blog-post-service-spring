package br.com.andre.blog.post.service.adapters.post.controller;

import br.com.andre.blog.post.models.Post;
import br.com.andre.blog.post.service.adapters.post.dto.PostRequestDto;
import br.com.andre.blog.post.service.adapters.post.dto.PostResponseDto;
import br.com.andre.blog.post.service.shared.dtos.ApiStandardResponseDto;
import br.com.andre.blog.post.service.shared.utils.PaginationImpl;
import br.com.andre.blog.post.services.PostService;
import br.com.andre.blog.post.utils.PageInfo;
import br.com.andre.blog.post.utils.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/no-pageable")
    ResponseEntity<ApiStandardResponseDto> getAll(HttpServletRequest httpRequest) {
        ApiStandardResponseDto response = ApiStandardResponseDto.builder()
                .message("Get all posts")
                .data(postService.findAll())
                .path(httpRequest.getRequestURI())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    ResponseEntity<ApiStandardResponseDto> getAll(Pageable pageable, HttpServletRequest httpRequest) {
        ApiStandardResponseDto response = ApiStandardResponseDto.builder()
                .message("Get all posts")
                .data(postService.findAll(
                    new PaginationImpl(pageable.getPageNumber(), pageable.getPageSize()))
                )
                .path(httpRequest.getRequestURI())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiStandardResponseDto> get(@PathVariable("id") final String id, HttpServletRequest httpRequest) {
        ApiStandardResponseDto response = ApiStandardResponseDto.builder()
                .message("Get one post")
                .data(PostResponseDto.of(postService.find(id)))
                .path(httpRequest.getRequestURI())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ResponseStatus
    ResponseEntity<ApiStandardResponseDto> create(@RequestBody @Valid final PostRequestDto request) {
        postService.create(request.toPost());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
