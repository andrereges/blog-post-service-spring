package br.com.andre.blog.post.service.adapters.post.broker;

import br.com.andre.blog.post.models.Post;
import br.com.andre.blog.post.ports.sender.MessageSender;
import org.springframework.stereotype.Component;

@Component
public class PostBroker implements MessageSender<Post> {
    @Override
    public void sendMessageForCreated(Post post) {

    }

    @Override
    public void sendMessageForRetrieved(Post post) {

    }
}
