package servlets;

import dbService.services.DBService;
import entity.UsersEntity;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       SessionFactory sessionFactory = DBService.getSessionFactory();

        sessionFactory.openSession().get(UsersEntity.class,37);

        PrintWriter writer = resp.getWriter();

        writer.write("HELLO");
        writer.flush();


    }

}
