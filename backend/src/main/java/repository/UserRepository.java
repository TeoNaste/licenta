package repository;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;


@Component
public class UserRepository implements Repository<Integer, User> {
    private JdbcUtils dbUtils;

    public UserRepository() {
        this.dbUtils = new JdbcUtils();
        initialize();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean save(User entity) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public User findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    static SessionFactory sessionFactory;
    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    static void close(){
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }

    }
}
