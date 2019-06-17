package repository;

import org.springframework.stereotype.Repository;


@Repository
public class PredictionRepository {
    private JdbcUtils dbUtils;

    public PredictionRepository() {
        this.dbUtils = new JdbcUtils();
    }


}
