package br.com.andre.blog.post.service.adapters.post.repository;

import br.com.andre.blog.post.service.adapters.post.mapper.PostMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostJpaRepository extends JpaRepository<PostMapper, String> {
    Page<PostMapper> findAll(Pageable pageable);
}
