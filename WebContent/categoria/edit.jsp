<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:template>
    <jsp:attribute name="title">
        Edição de Categoria
    </jsp:attribute>
    <jsp:body>
       <p class="validation-summary-errors">
           <c:out value="${msg}" />
      </p>
        <jsp:useBean id="categoria" class="entities.Categoria" scope="request" />
    <jsp:setProperty name="categoria" property="*" />
    <form action="Categoria" method="POST">
      <input type="hidden" name="idCategoria" value="${categoria.idCategoria }" />
      <fieldset>
        <legend>Formulário de cadastro</legend>
        <ol>
          <li>
            <label>Nome:</label>
            <input type="text" name="nome" value="${categoria.nome }" />
          </li>
        </ol>
        <input type="submit" value="Salvar">
      </fieldset>
    </form>
    </jsp:body>
</t:template>