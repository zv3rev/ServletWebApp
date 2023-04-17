package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.PetRepository;
import repository.ProfileRepository;
import services.PetService;
import services.ProfileService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/welcome")
public class WelcomeController extends HttpServlet {
    PetService petService = new PetService(new PetRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var pets = petService.getOwnerPets((Long)req.getSession().getAttribute("loggedId"));
            req.setAttribute("pets", pets);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user/welcome.jsp");
        dispatcher.forward(req,resp);
    }
}
