package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoriaDAOImp;
import dao.ProdutoDAOImp;
import entities.Categoria;
import entities.Produto;


@WebServlet("/Produto")
public class ProdutoController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String command = request.getParameter("cmd");
    ProdutoDAOImp dao = new ProdutoDAOImp();
    if(command != null){
      if(request.getParameter("id") != null){
        long id = Long.parseLong(request.getParameter("id").toString());
        Produto produto = dao.getById(Produto.class, id);
        request.setAttribute("prod", produto);
      }

      if(command.equalsIgnoreCase("edit")){
        CategoriaDAOImp catDao = new CategoriaDAOImp();
        List<Categoria> categorias = catDao.getAll(Categoria.class);
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher("/produto/edit.jsp").forward(request,response);
      }
      else if(command.equalsIgnoreCase("details")){
        request.getRequestDispatcher("/produto/details.jsp").forward(request,response);
      }
      else if(command.equalsIgnoreCase("delete")){
        if(request.getSession().getAttribute("idproduto") != null){
          long id = Long.parseLong(request.getSession().getAttribute("idproduto").toString());
          Produto produto = dao.getById(Produto.class, id);
          dao.remove(produto);
          response.sendRedirect("/Loja/Produto");
        }
      }
    }
    else{
      List<Produto> produtos = dao.getAll(Produto.class);
      request.setAttribute("produtos", produtos);
      request.getRequestDispatcher("/produto/list.jsp").forward(request,response);
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Produto produto = (Produto) request.getAttribute("produto");
    if(produto == null){
      produto = new Produto();
      if(!request.getParameter("idProduto").isEmpty())
        produto.setIdProduto(Long.parseLong(request.getParameter("idProduto")));
      else
        produto.setIdProduto(0L);
      produto.setProduto(request.getParameter("produto"));
      produto.setCodigo(request.getParameter("codigo"));
      produto.setPreco(Double.parseDouble(request.getParameter("preco")));
      produto.setEstoque(Integer.parseInt(request.getParameter("estoque")));
      long idCategoria = Long.parseLong(request.getParameter("idcategoria"));
      CategoriaDAOImp catDao = new CategoriaDAOImp();
      Categoria categoria = catDao.getById(Categoria.class, idCategoria);
      produto.setCategoria(categoria);
    }
    try{
      ProdutoDAOImp dao = new ProdutoDAOImp();
      dao.save(produto);

      request.setAttribute("msg", "Gravado com sucesso!");
      if(produto.getIdProduto() > 0)
        response.sendRedirect("/Loja/Produto");
      else{
        CategoriaDAOImp catDao = new CategoriaDAOImp();
        List<Categoria> categorias = catDao.getAll(Categoria.class);
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher("/produto/edit.jsp").forward(request, response);
      }
    }catch (Exception e) {
      request.setAttribute("msg", "Erro: " + e.getMessage());
      request.getRequestDispatcher("/produto/edit.jsp").forward(request, response);
    }
  }
}