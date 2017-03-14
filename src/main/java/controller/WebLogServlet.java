package controller;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
//import service.WebLogService;

@WebServlet(name = "kwetter", urlPatterns = {"/kwetter"})
public class WebLogServlet extends HttpServlet {

    @Inject
    //WebLogService webLogService;
    //User user = new User("JEA");

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("get");
        /*List<Posting> postings = webLogService.getPostings();
        request.setAttribute("user", user);
        request.setAttribute("postings", postings);*/
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
        view.forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*webLogService.addPosting(new Posting("title 1", "content 1"), user);
        webLogService.addPosting(new Posting("title 2", "content 2"), user);
        webLogService.addPosting(new Posting("title 3", "content 3"), user);
        List<Posting> postings = webLogService.getPostings();
        request.setAttribute("user", user);
        request.setAttribute("postings", postings);*/
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
        view.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short WebLogServlet";
    }

}
