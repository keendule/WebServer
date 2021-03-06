package servlets.users;

import dbService.services.UserService;
import entity.UserEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<UserEntity> users = userService.getAllUsers();

        req.setAttribute("users",users);

        RequestDispatcher dispatcher = req.getRequestDispatcher("users/users.jsp");

        dispatcher.forward(req,resp);

    }

    @Override
    public void init() {
        userService = new UserService(getServletContext());
    }
}
