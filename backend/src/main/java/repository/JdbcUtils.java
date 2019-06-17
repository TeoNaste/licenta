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
        String driver ="org.sqlite.JDBC";
        String url = "jdbc:sqlite:D:\\an3\\licenta\\SpeedyTalk.db";

        Connection con=null;
        try {
            Class.forName(driver);
            con=DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver "+e);
        } catch (SQLException e) {
            System.out.println("Error getting connection "+e);
        }
        return con;
    }

    public Connection getConnection(){
        try {
            if (instance==null || instance.isClosed())
                instance=getNewConnection();

        } catch (SQLException e) {

            System.out.println("Error DB "+e);
        }
        return instance;
    }
}
