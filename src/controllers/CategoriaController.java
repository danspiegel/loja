package controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.CategoriaDAOImp;
import entities.Categoria;

@WebServlet("/Categoria")
public class CategoriaController extends HttpServlet {
  private static final long serialVersionUID = 1L;

    public CategoriaController() {
        super();
        // TODO Auto-generated constructor stub
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  String command = request.getParameter("cmd");
  CategoriaDAOImp dao = new CategoriaDAOImp();
  if(command != null){
    if(request.getParameter("id") != null){
      long id = Long.parseLong(request.getParameter("id").toString());
      Categoria categoria = dao.getById(Categoria.class, id);
      request.setAttribute("categoria", categoria);
    }

    if(command.equalsIgnoreCase("edit")){
      request.getRequestDispatcher("/categoria/edit.jsp").forward(request,response);
    }
    else if(command.equalsIgnoreCase("details")){
      request.getRequestDispatcher("/categoria/details.jsp").forward(request,response);
    }
    else if(command.equalsIgnoreCase("delete")){
      if(request.getSession().getAttribute("idcategoria") != null){
        long id = Long.parseLong(request.getSession().getAttribute("idcategoria").toString());
        Categoria categoria = dao.getById(Categoria.class, id);
        dao.remove(categoria);
        response.sendRedirect("/Loja/Categoria");
      }
    }
  }
  else{
    List<Categoria> categorias = dao.getAll(Categoria.class);
    request.setAttribute("categorias", categorias);
    request.getRequestDispatcher("/categoria/list.jsp").forward(request,response);
  }
}

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Categoria categoria = (Categoria) request.getAttribute("categoria");
    if(categoria == null){
      categoria = new Categoria();
      if(!request.getParameter("idCategoria").isEmpty())
        categoria.setIdCategoria(Long.parseLong(request.getParameter("idCategoria")));
      else
        categoria.setIdCategoria(0L);
      categoria.setNome(request.getParameter("nome"));
    }
    try{
      CategoriaDAOImp dao = new CategoriaDAOImp();
      dao.save(categoria);

      request.setAttribute("msg", "Gravado com sucesso!");
      if(categoria.getIdCategoria() > 0)
        response.sendRedirect("/Loja/Categoria");
      else
        request.getRequestDispatcher("/categoria/edit.jsp").forward(request, response);
    }catch (Exception e) {
      request.setAttribute("msg", "Erro: " + e.getMessage());
      request.getRequestDispatcher("/categoria/edit.jsp").forward(request, response);
    }
  }
}