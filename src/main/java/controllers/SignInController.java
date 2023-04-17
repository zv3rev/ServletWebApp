package controllers;

import entity.Profile;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.ProfileRepository;
import services.ProfileService;

import java.io.IOException;

@WebServlet("/signin")
public class SignInController extends HttpServlet {
    ProfileService service = new ProfileService(new ProfileRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/sign_in.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Profile user = service.validateProfile(username,password);
        RequestDispatcher dispatcher;
        if (user != null) {
            req.getSession().setAttribute("loggedUsername", user.getUsername());
            req.getSession().setAttribute("loggedId", user.getId());
            resp.sendRedirect(req.getContextPath()+"/user/welcome");
        }
        else {
            req.setAttribute("wrongPassword",true);
            resp.sendRedirect(req.getContextPath()+"/signin");
        }
    }
}
