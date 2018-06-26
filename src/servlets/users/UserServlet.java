package servlets.users;

import dbService.services.UserService;
import entity.UserEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        RequestDispatcher dispatcher = req.getRequestDispatcher("users/user.jsp");

        if (id != null) {
          UserEntity user = userService.getUser(Integer.parseInt(id));
          req.setAttribute("user",user);
        }

        dispatcher.forward(req,resp);

    }

    protected void doCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity user = new UserEntity.Builder()
                .setName(req.getParameter("name"))
                .setLogin(req.getParameter("login"))
                .setPassword(req.getParameter("password")).build();
        userService.createUser(user);

        resp.sendRedirect("/users");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity user = new UserEntity.Builder()
                .setId(Integer.parseInt(req.getParameter("id")))
                .setName(req.getParameter("name"))
                .setLogin(req.getParameter("login"))
                .setPassword(req.getParameter("password")).build();
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

    @Override
    public void init() {
        userService = new UserService(getServletContext());
    }
}
