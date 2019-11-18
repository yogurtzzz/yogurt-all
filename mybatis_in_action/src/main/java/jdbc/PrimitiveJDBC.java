package jdbc;

import java.sql.*;

public class PrimitiveJDBC {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/yogurt?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "yuehun_8023";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(url,user,password);

        String sql = "SELECT * FROM user ;";
        preparedStatement = connection.prepareStatement(sql);

        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            System.out.println("id="+resultSet.getString("id")+","
                    + "name="+resultSet.getString("name")+","
                +"age="+resultSet.getString("age")+","+
                    "gender="+resultSet.getString("gender"));
        }

    }
}
