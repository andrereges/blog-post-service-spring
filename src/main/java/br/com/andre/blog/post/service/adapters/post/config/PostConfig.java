package br.com.andre.blog.post.service.adapters.post.config;

import br.com.andre.blog.post.PostPublisher;
import br.com.andre.blog.post.models.Author;
import br.com.andre.blog.post.models.Post;
import br.com.andre.blog.post.ports.notifier.Notifier;
import br.com.andre.blog.post.ports.publisher.Publisher;
import br.com.andre.blog.post.ports.repository.AuthorRepository;
import br.com.andre.blog.post.ports.repository.PostRepository;
import br.com.andre.blog.post.ports.sender.MessageSender;
import br.com.andre.blog.post.services.PostService;
import br.com.andre.blog.post.services.impl.PostServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PostConfig {
    @Bean
    public PostPublisher postEventPublisher(
            final MessageSender<Post> eventPublisher,
            final List<Publisher<Post>> publishers,
            final List<Notifier<Post>> notifiers) {

        return new PostPublisher(eventPublisher, publishers, notifiers);
    }

    @Bean
    public PostService postService(
            final PostRepository<Post, String> postRepository,
            final AuthorRepository<Author, String> authorRepository,
            final Publisher<Post> eventPublisher
    ) {

        return new PostServiceImpl(postRepository, authorRepository, eventPublisher);
    }

}
