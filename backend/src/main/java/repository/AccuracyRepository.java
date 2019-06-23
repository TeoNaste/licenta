package repository;

import model.Accuracy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccuracyRepository implements IRepository<Integer, Accuracy> {
    @Override
    public int size() {
        return Repository.getAll(Accuracy.class).size();
    }

    @Override
    public void save(Accuracy entity) {
        Repository.add(Accuracy.class,entity);
    }

    @Override
    public void delete(Integer integer) {
        Optional<Accuracy> optional = Repository.get(Accuracy.class,integer);
        optional.ifPresent(Accuracy -> Repository.delete(Accuracy.class, Accuracy));
    }

    @Override
    public void update(Integer id, Accuracy entity) {
        Optional<Accuracy> optional = Repository.get(Accuracy.class,id);
        optional.ifPresent(Accuracy -> Repository.update(Accuracy.class, Accuracy,entity));
    }

    @Override
    public Accuracy findOne(Integer id) {
        Optional<Accuracy> optional = Repository.get(Accuracy.class,id);
        return optional.orElse(null);
    }

    @Override
    public Iterable<Accuracy> findAll() {

        List<Accuracy> accuracys = Repository.getAll(Accuracy.class);

        return accuracys;
    }
}
