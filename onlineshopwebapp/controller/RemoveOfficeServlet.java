package sit.int202.onlineshopwebapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sit.int202.onlineshopwebapp.repositories.OfficeRepository;
import sit.int202.onlineshopwebapp.utils.CheckParam;

import java.io.IOException;

@WebServlet(name = "RemoveOfficeServlet", value = "/remove-office")
public class RemoveOfficeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OfficeRepository officeRepository = new OfficeRepository();
        req.setAttribute("offices",officeRepository.findAll());
        req.getRequestDispatcher("/remove-office.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String removeParam = req.getParameter("removeId");
        OfficeRepository officeRepository = new OfficeRepository();
        req.setAttribute("offices", officeRepository.findAll());
        if (removeParam == null || removeParam.isEmpty() || removeParam.equals("0")) {
            req.setAttribute("errorRemove", "Invalid id");
            req.getRequestDispatcher("remove-office.jsp").forward(req, resp);
        } else {
            doRemove(removeParam, req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/office-list");

    }

    private void doRemove(String removeId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        OfficeRepository officeRepository = new OfficeRepository();
        if(officeRepository.delete(removeId)){
            System.out.println("Success");
            req.setAttribute("removeStatus", "remove success");
            officeRepository.close();
        } else {
            System.out.println("Unsuccessful");
            req.setAttribute("removeStatus", "remove unsuccessful because ID its doesn't exists.");
            req.getRequestDispatcher("remove-office.jsp").forward(req, resp);
            officeRepository.close();
        }
    }
}
