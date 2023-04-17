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

@WebServlet("/signup")
public class SignUpController extends HttpServlet {
    private final ProfileService profileService = new ProfileService(new ProfileRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/sign_up.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Byte age = Byte.valueOf(req.getParameter("age"));
        if(profileService.createProfile(new Profile(username,password,email,age))){
            resp.sendRedirect(req.getContextPath()+"/signin");
        }
        else{
            resp.sendRedirect(req.getContextPath()+"/signup");
        }
    }
}
