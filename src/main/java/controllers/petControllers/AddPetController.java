package controllers.petControllers;


import entity.Pet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.PetRepository;
import services.PetService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/user/pet/add")
public class AddPetController extends HttpServlet {
    private PetService petService = new PetService(new PetRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/user/pet/add.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Pet pet = new Pet(
                0L,
                req.getParameter("nickname"),
                Date.valueOf(req.getParameter("birthday")),
                req.getParameter("type"),
                (Long)req.getSession().getAttribute("loggedId")
                );

        try {
            petService.addPet(pet);
        } catch (SQLException e) {
            req.setAttribute("errorMessage", "can't add new pet ");
            resp.sendRedirect(req.getContextPath() + "/error");
            return;
        }
        resp.sendRedirect(req.getContextPath()+"/user/welcome");
    }
}
