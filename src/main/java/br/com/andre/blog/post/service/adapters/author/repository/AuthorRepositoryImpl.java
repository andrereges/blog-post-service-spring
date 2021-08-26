package br.com.andre.blog.post.service.adapters.author.repository;

import br.com.andre.blog.post.models.Author;
import br.com.andre.blog.post.ports.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository<Author, String> {

    private final AuthorJpaRepository jpaRepository;

    @Override
    public Set<Author> findAll() {
        return null;
    }

    @Override
    public Set<Author> findIn(Set<Author> authors) {
        return jpaRepository.findAllById(authors
            .stream()
            .map(a -> a.getId())
            .collect(Collectors.toList())
        ).stream().map(r -> r.toAuthor()).collect(Collectors.toSet());
    }

    @Override
    public Set<Author> findByNameWithLike(String name) {
        return null;
    }

    @Override
    public Optional<Author> find(String id) {
        return Optional.empty();
    }

    @Override
    public void save(Author author) {
    }

    @Override
    public void update(Author author) {

    }

    @Override
    public void delete(String id) {

    }
}
