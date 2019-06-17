package repository;

import model.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MessageRepository implements IRepository<Integer, Message> {

    public MessageRepository() { }

    @Override
    public int size() {
        return Repository.getAll(Message.class).size();
    }

    @Override
    public void save(Message entity) {
        Repository.add(Message.class,entity);
    }

    @Override
    public void delete(Integer integer) {
        Optional<Message> optional = Repository.get(Message.class,integer);
        optional.ifPresent(Message -> Repository.delete(Message.class, Message));
    }

    @Override
    public void update(Integer id, Message entity) {
        Optional<Message> optional = Repository.get(Message.class,id);
        optional.ifPresent(Message -> Repository.update(Message.class, Message,entity));
    }

    @Override
    public Message findOne(Integer id) {
        Optional<Message> optional = Repository.get(Message.class,id);
        return optional.orElse(null);
    }

    @Override
    public Iterable<Message> findAll() {

        List<Message> messages = Repository.getAll(Message.class);

        return messages;
    }

}
