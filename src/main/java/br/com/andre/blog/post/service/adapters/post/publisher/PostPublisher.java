package br.com.andre.blog.post.service.adapters.post.publisher;

import br.com.andre.blog.post.models.Post;
import br.com.andre.blog.post.ports.publisher.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PostPublisher implements Publisher<Post> {
    @Override
    public void publish(Post post) {

    }
}
