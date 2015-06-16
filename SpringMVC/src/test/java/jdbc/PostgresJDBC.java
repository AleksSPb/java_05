package jdbc;

import org.postgresql.Driver;

import java.security.MessageDigest;
import java.sql.*;

/**
 * Работа с PostgreSQL через JDBC
 */
public class PostgresJDBC {
    static Statement query;
    public static void main(String[] args) throws Exception {
        /*Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            System.out.println("driver = " + driver);
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } */

        Driver driver = new org.postgresql.Driver();
        Class.forName("org.postgresql.Driver");

        DriverManager.getDriver("jdbc:postgresql://localhost:5432/test");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/test",
                "postgres", "123");

        // Создаём запрос
        query = con.createStatement();

        // Набор результатов
        ResultSet resultSet =
                query.executeQuery("SELECT * FROM contact");
        // Пока есть результаты
        while (resultSet.next()){
            System.out.println(resultSet.getString("name"));
        }
        resultSet.close();

        // class Contact {
        //   int id;
        //   String name;
        //   String surname;
        // }
        ResultSet res2 = query.executeQuery("SELECT contact.id, contact.name, contact.surname, " +
                " phone.number FROM phone LEFT JOIN contact " +
                "ON contact.id = phone.contact_id");

        ResultSetMetaData rsmd = res2.getMetaData();
        while (res2.next()){
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.println(rsmd.getColumnName(i) + " = " + res2.getString(i));
            }
        }
        res2.close();
        int aleksId = insertUser("AleksSPb", "myPassword", "Aleksander", "Parkhomenko");
        int denisId = insertUser("DenisSPb", "denisPassword", "Denis", "Stepulenok");

        int t1 = insertTask("Insert task", "Make query to add task to tasklist", aleksId);
        int t2 = insertTask("Check execution", "Check how student realized add task to tasklist", denisId);

        query.executeUpdate("update tasks set processed = false where id=" + t1);
        printTaskList();


        query.executeUpdate("delete from tasks where id>0");
        query.executeUpdate("delete from users where login='DenisSPb'");




        //query.executeUpdate("UPDATE contact SET ... WHERE ...");
        //query.executeUpdate("DELETE FROM contact WHERE ...");
        //query.executeUpdate("INSERT INTO contact VALUES(...)")
        //query.execute("CREATE TABLE ...");
    }

    //добавить пользователя
    static int insertUser(String userName, String pw, String name, String surname) throws Exception {
        byte[] bytesOfMessage = pw.getBytes("UTF-8");

        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] thedigest = md.digest(bytesOfMessage);

        ResultSet resQ = query.executeQuery("SELECT id FROM users where login='" + userName + "'");

        if (resQ.next()) {
            System.out.println("Login " + userName + " present!!!");
            return resQ.getInt("id");
        }
        resQ.close();
        String insStr = "insert into users (login, hashpw, name, surname,is_active) VALUES('" + userName + "', '" + thedigest.toString() + "', '" + name + "', '" + surname + "', true)";
        query.executeUpdate(insStr, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = query.getGeneratedKeys();
        if (rs.next()) return rs.getInt("id");
        return 0;
    }

    //insert task
    static int insertTask(String title, String descr, int executant_id) throws Exception {
        ResultSet resQ = query.executeQuery("SELECT id FROM users where id=" + executant_id);

        if (!resQ.next()) {
            System.out.println("User id " + executant_id + " not present!!!");
            return 0;
        }
        resQ.close();
        String insStr = "insert into tasks (title, description, executant_id, processed) VALUES('" + title + "', '" + descr + "', " + executant_id + ", true)";
        query.executeUpdate(insStr, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = query.getGeneratedKeys();
        if (rs.next()) return rs.getInt(1);

        return 0;
    }

    //get alltask
    static void printTaskList() throws Exception {
        ResultSet res = query.executeQuery("SELECT tasks.id, users.login as Executant, title, description, processed from tasks LEFT JOIN users ON tasks.executant_id=users.id");
        System.out.println("Tasks list:");
        ResultSetMetaData rsmd = res.getMetaData();
        while (res.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.println(rsmd.getColumnName(i) + " = " + res.getString(i));
            }
        }
        res.close();

    }
}
