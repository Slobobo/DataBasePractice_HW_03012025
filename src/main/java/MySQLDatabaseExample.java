import java.sql.*;

public class MySQLDatabaseExample {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/my_database";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "Lvbnhbq&1025";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
            System.out.println("Connection to database successful");

            createTable(connection);
            insertData(connection);
            selectData(connection);
            deleteData(connection);
            selectData(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(50) NOT NULL, "
                + "age INT NOT NULL, "
                + "email VARCHAR(100) NOT NULL"
                + ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table 'users' created (if not yet created before)");
        }
    }

    public static void insertData(Connection connection) throws SQLException {
        String insertSQL = "INSERT INTO users (name, age, email) VALUES "
                + "('John', 30, 'john@example.com'), "
                + "('Alice', 25, 'alice@example.com'), "
                + "('Bob', 35, 'bob@example.com');";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertSQL);
            System.out.println("Data saved into table 'users'.");
        }
    }

    public static void selectData(Connection connection) throws SQLException {
        String selectSQL = "SELECT * FROM users";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            System.out.println("Data in table 'users':");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");

                System.out.println(id + " | " + name + " | " + age + " | " + email);
            }
        }
    }

    public static void deleteData(Connection connection) throws SQLException {
        String deleteSQL = "DELETE FROM users WHERE name = 'Bob'";

        try (Statement statement = connection.createStatement()) {
            int rowsAffected = statement.executeUpdate(deleteSQL);
            System.out.println("Number of deleted lines: " + rowsAffected);
        }
    }
}
