package repository;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;

import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection  getConnection() {
        try {
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/MyLocalDB");

            if (dataSource == null) {
                throw new RuntimeException("Data source not found");
            }

            return dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
