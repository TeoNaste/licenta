package repository;

import model.Prefix;
import model.PrefixKey;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class PrefixRepository implements IRepository<PrefixKey, Prefix> {
    @Override
    public int size() {
        return Repository.getAll(Prefix.class).size();
    }

    @Override
    public void save(Prefix entity) {
        Repository.add(Prefix.class,entity);
    }

    @Override
    public void delete(PrefixKey id) {
        Optional<Prefix> optional = Repository.get(Prefix.class,id);
        optional.ifPresent(Prefix -> Repository.delete(Prefix.class, Prefix));
    }

    @Override
    public void update(PrefixKey id, Prefix entity) {
        Optional<Prefix> optional = Repository.get(Prefix.class,id);
        optional.ifPresent(Prefix -> Repository.update(Prefix.class, Prefix,entity));
    }

    @Override
    @Transactional
    public Prefix findOne(PrefixKey id) {
        Optional<Prefix> optional = Repository.get(Prefix.class,id);
        return optional.orElse(null);
    }

    @Override
    public Iterable<Prefix> findAll() {

        List<Prefix> prefixs = Repository.getAll(Prefix.class);

        return prefixs;
    }
}

