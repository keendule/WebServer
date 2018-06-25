package servlets.users;

import dbService.services.UserService;
import entity.UsersEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        RequestDispatcher dispatcher = req.getRequestDispatcher("users/user.jsp");

        if (id != null) {
          UsersEntity user = userService.getUser(Integer.parseInt(id));
          req.setAttribute("user",user);
        }

        dispatcher.forward(req,resp);

    }

    protected void doCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersEntity user = new UsersEntity(
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("password"));
        userService.createUser(user);

        resp.sendRedirect("/users");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersEntity user = new UsersEntity(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("password"));
        userService.updateUser(user);

        resp.sendRedirect("/users");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            userService.deleteUser((Integer.parseInt(id)));
        }

        resp.sendRedirect("/users");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("GET")) {
            doGet(req,resp);
        } else {
            switch (req.getParameter("action")) {
                case "CREATE":
                    doCreate(req,resp);
                    break;
                case "DELETE":
                    doDelete(req, resp);
                    break;
                case "UPDATE":
                    doPut(req,resp);
                    break;
                default:
                    resp.sendRedirect("/users");
                    break;
            }
        }
    }
}
