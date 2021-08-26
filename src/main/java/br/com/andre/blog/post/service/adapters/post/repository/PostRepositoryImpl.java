package br.com.andre.blog.post.service.adapters.post.repository;

import br.com.andre.blog.post.exceptions.BadRequestException;
import br.com.andre.blog.post.models.Post;
import br.com.andre.blog.post.ports.repository.PostRepository;
import br.com.andre.blog.post.service.adapters.post.mapper.PostMapper;
import br.com.andre.blog.post.service.shared.utils.PageInfoImpl;
import br.com.andre.blog.post.service.shared.utils.PaginationImpl;
import br.com.andre.blog.post.utils.PageInfo;
import br.com.andre.blog.post.utils.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository<Post, String> {

    private final PostJpaRepository jpaRepository;

    @Override
    public Set<Post> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(pm -> pm.toPost())
                .collect(Collectors.toSet());
    }

    @Override
    public PageInfo<Post> findAll(Pagination pagination) {
        Page<PostMapper> result = jpaRepository.findAll(PageRequest.of(
                pagination.page(),
                pagination.size()
        ));

        Set<Post> postList = result.getContent()
                .stream()
                .map(pm -> pm.toPost())
                .collect(Collectors.toSet());

        Pagination paginationBuilder = PaginationImpl.builder()
                .page(pagination.page())
                .size(pagination.size())
                .sort(result.getSort().toString())
                .build();

        PageInfoImpl<Post> pageInfo = new PageInfoImpl<>(postList, paginationBuilder);
        pageInfo.setContent(postList);
        pageInfo.setPagination(paginationBuilder);
        pageInfo.setFirst(result.isFirst());
        pageInfo.setLast(result.isLast());
        pageInfo.setEmpty(result.isEmpty());
        pageInfo.setTotalElements(result.getTotalElements());
        pageInfo.setTotalPages(result.getTotalPages());

        return pageInfo;
    }

    @Override
    public Set<Post> findByTitleAndContentWithLike(String title, String content) {
        return null;
    }

    @Override
    public Set<Post> findByAuthorNameWithLike(String authorName) {
        return null;
    }

    @Override
    public Optional<Post> find(String id) {
        PostMapper result = jpaRepository.findById(id).orElseThrow(
                () -> new BadRequestException("Post", id)
        );

        return Optional.of(result.toPost());
    }

    @Override
    public void save(Post post) {
        jpaRepository.save(PostMapper.toPostMapper(post));
    }

    @Override
    public void update(Post post) {

    }

    @Override
    public void delete(String id) {

    }

}
