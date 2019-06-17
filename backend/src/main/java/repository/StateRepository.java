package repository;

import model.State;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StateRepository implements IRepository<Integer, State> {
    public StateRepository() { }

    @Override
    public int size() {
        return Repository.getAll(State.class).size();
    }

    @Override
    public void save(State entity) {
        Repository.add(State.class,entity);
    }

    @Override
    public void delete(Integer integer) {
        Optional<State> optional = Repository.get(State.class,integer);
        optional.ifPresent(State -> Repository.delete(State.class, State));
    }

    @Override
    public void update(Integer id, State entity) {
        Optional<State> optional = Repository.get(State.class,id);
        optional.ifPresent(State -> Repository.update(State.class, State,entity));
    }

    @Override
    public State findOne(Integer id) {
        Optional<State> optional = Repository.get(State.class,id);
        return optional.orElse(null);
    }

    @Override
    public Iterable<State> findAll() {

        List<State> states = Repository.getAll(State.class);

        return states;
    }
}
