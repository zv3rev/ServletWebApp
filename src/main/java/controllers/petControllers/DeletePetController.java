package controllers.petControllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.PetRepository;
import services.PetService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/pet/delete")
public class DeletePetController extends HttpServlet {
    private PetService petService = new PetService(new PetRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("pets", petService.getOwnerPets((Long)req.getSession().getAttribute("loggedId")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("/WEB-INF/user/pet/delete.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            petService.deletePet(Long.valueOf(req.getParameter("id")), (Long)req.getSession().getAttribute("loggedId"));
        } catch (SQLException e) {
            req.setAttribute("errorMessage", "can't delete pet ");
            resp.sendRedirect(req.getContextPath() + "/error");
            return;
        }
        resp.sendRedirect(req.getContextPath()+"/user/welcome");
    }
}
