package br.com.andre.blog.post.service.adapters.author.repository;

import br.com.andre.blog.post.service.adapters.author.mapper.AuthorMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorJpaRepository extends JpaRepository<AuthorMapper, String> {
}
