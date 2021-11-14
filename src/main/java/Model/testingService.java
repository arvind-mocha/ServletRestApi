package Model;

import Database.trailDatabase;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testingService {
    PreparedStatement stmt;

    public testingService() throws SQLException, ClassNotFoundException {
    }

    public List<testing> getUsers(String query) throws SQLException, ClassNotFoundException {
        Connection con = trailDatabase.initializeDatabase();
        stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        List<testing> users = new ArrayList<>();
        while(rs.next()){
            users.add(new testing(rs.getString("Name"),rs.getInt("id"),rs.getInt("age")));
        }
        stmt.close();
        con.close();
        return users;
    }

    public void setUsers(String Name, int age, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        Connection con = trailDatabase.initializeDatabase();
        stmt = con.prepareStatement("insert into users(Name, age) values(?, ?)");
        stmt.setString(1, Name);
        stmt.setInt(2, age);

        stmt.executeUpdate();

        stmt.close();
        con.close();

        PrintWriter out = response.getWriter();
        out.println(Name + " has been Successfully Inserted");
    }

    public void deleteUser(int id, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        Connection con = trailDatabase.initializeDatabase();
        stmt = con.prepareStatement("delete from users where id=" + id);
        stmt.executeUpdate();
        stmt.close();
        con.close();

        PrintWriter out = response.getWriter();
        out.println("user has been Successfully deleted");
    }

    public void putUser(String id, String name, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException{
        Connection con = trailDatabase.initializeDatabase();
        stmt = con.prepareStatement("UPDATE users SET name =? where id=?");
        stmt.setString(1, name);
        stmt.setString(2, id);
        stmt.executeUpdate();
        stmt.close();
        con.close();

        PrintWriter out = response.getWriter();
        out.println("user " + id + " has been Successfully updated");
    }
}
