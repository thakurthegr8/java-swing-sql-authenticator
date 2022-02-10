import java.sql.*;

public class Database {
    static final String URL = "jdbc:mysql://localhost:3306/company";
    static final String USER = "root";
    static final String PWD = "abc123456#";
    Connection connection = null;
    void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Successfully Connected to Database");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    ResultSet runQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
        ResultSet rs = statement.getResultSet();
        return rs;
    }
}
