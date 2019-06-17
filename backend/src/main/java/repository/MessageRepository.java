package repository;

import model.Message;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class MessageRepository implements Repository<Integer, Message> {
    private JdbcUtils dbUtils;

    public MessageRepository() {
        this.dbUtils = new JdbcUtils();
        initialize();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean save(Message entity) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public boolean update(Message entity) {
        return false;
    }

    @Override
    public Message findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Message> findAll() {
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
