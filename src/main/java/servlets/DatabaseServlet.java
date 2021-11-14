package servlets;

import Database.trailDatabase;
import Model.testing;
import Model.testingService;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DatabaseServlet", value = "/DatabaseServlet")
public class DatabaseServlet extends HttpServlet {
    private testingService service;

    public DatabaseServlet() throws SQLException, ClassNotFoundException {
        service = new testingService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        List<testing> users = new ArrayList<>();
        if(id != null){
            try {users = service.getUsers("select * from users where id=" + id);}
            catch (Exception e) {e.printStackTrace();}
        }else {
            try {users = service.getUsers("select * from users");}
            catch (Exception e) {e.printStackTrace();}
        }

        Gson gson = new Gson();
        String userJson = gson.toJson(users); // Feed ArrayList To Gson to get converted into json

        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        printWriter.write(userJson);
        printWriter.close();
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Name = request.getParameter("Name");
        int age = Integer.parseInt(request.getParameter("age"));
        try {
            service.setUsers(Name, age, response);
        } catch (Exception e) {
            System.out.println("Check Inputs Again");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String Name = req.getParameter("Name");
        try {
            service.putUser(id, Name, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            service.deleteUser(Integer.parseInt(id), resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}