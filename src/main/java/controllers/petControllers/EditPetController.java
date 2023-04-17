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

@WebServlet("/user/pet/edit")
public class EditPetController extends HttpServlet {
    private PetService petService = new PetService(new PetRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("pets", petService.getOwnerPets((Long)req.getSession().getAttribute("loggedId")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("/WEB-INF/user/pet/edit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pet editingPet = new Pet(
                Long.valueOf(req.getParameter("id")),
                req.getParameter("nickname"),
                Date.valueOf(req.getParameter("birthday")),
                req.getParameter("type"),
                (Long)req.getSession().getAttribute("loggedId")
        );

        try {
            petService.updatePet(editingPet);
        } catch (SQLException e) {
            req.setAttribute("errorMessage", "can't change pet data");
            resp.sendRedirect(req.getContextPath() + "/error");
            return;
        }
        resp.sendRedirect(req.getContextPath()+"/user/welcome");
    }
}
