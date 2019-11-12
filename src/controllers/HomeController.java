package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Home")
public class HomeController extends HttpServlet {
  private static final long serialVersionUID = 1L;

    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
  }
}