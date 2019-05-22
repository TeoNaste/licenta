package repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private Properties jdbcProps;
    private  Connection instance=null;

    public JdbcUtils(Properties props){
        jdbcProps=props;
    }

    public JdbcUtils(){}

    private Connection getNewConnection(){
        String driver=jdbcProps.getProperty("autocomplete.jdbc.driver");
        String url=jdbcProps.getProperty("autocomplete.jdbc.url");
//        String driver ="org.sqlite.JDBC";
//        String url = "jdbc:sqlite:D:\\an3\\licenta\\SpeedyTalk";
       // logger.info("trying to connect to database ... {}",url);
        Connection con=null;
        try {
            Class.forName(driver);
          //  logger.info("Loaded driver ...{}",driver);
            con=DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
         //   logger.error(e);
            System.out.println("Error loading driver "+e);
        } catch (SQLException e) {
        //    logger.error(e);
            System.out.println("Error getting connection "+e);
        }
        return con;
    }

    public Connection getConnection(){
       // logger.traceEntry();
        try {
            if (instance==null || instance.isClosed())
                instance=getNewConnection();

        } catch (SQLException e) {
           // logger.error(e);
            System.out.println("Error DB "+e);
        }
       // logger.traceExit(instance);
        return instance;
    }
}
