package repository;

import model.MessageStatus;
import model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


@Component
public class UserRepository implements IRepository<Integer, User> {

    public UserRepository() {
    }

    @Override
    public int size() {
        return Repository.getAll(User.class).size();
    }

    @Override
    public void save(User entity) {
        Repository.add(User.class,entity);
    }

    @Override
    public void delete(Integer integer) {
        Optional<User> optional = Repository.get(User.class,integer);
        optional.ifPresent(User -> Repository.delete(User.class, User));
    }

    @Override
    public void update(Integer id, User entity) {
        Optional<User> optional = Repository.get(User.class,id);
        optional.ifPresent(User -> Repository.update(User.class, User,entity));
    }

    @Override
    public User findOne(Integer id) {
        Optional<User> optional = Repository.get(User.class,id);
        return optional.orElse(null);
    }

    @Override
    public Iterable<User> findAll() {

        List<User> users = Repository.getAll(User.class);

        return users;
    }

    public User getUserByName(String username){
        Predicate<User> query = (user -> user.getUsername().equals(username));

        Optional<User> messages = Repository.findObj(User.class,query);

        if(messages.isPresent())
            return messages.get();
        return null;
    }

}
