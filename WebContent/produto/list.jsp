<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:template>
    <jsp:attribute name="title">
        Produtos
    </jsp:attribute>
    <jsp:body>
  <table>
        <thead>
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Categoria</th>
                <th>Detalhes</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${produtos}" var="prod">
                <tr>
                    <td><c:out value="${prod.codigo}" /></td>
                    <td><c:out value="${prod.produto}" /></td>
                    <td><c:out value="${prod.categoria.nome}" /></td>
                    <td><a href="Produto?cmd=details&id=${prod.idProduto}">detalhes</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <aside>
    <h3>Novo produto</h3>
    <p>
        Para adicionar um novo produto clique em:
    </p>
    <ul>
        <li><a href="Produto?cmd=edit">Cadastrar</a></li>
    </ul>
    </aside>
    </jsp:body>
</t:template>