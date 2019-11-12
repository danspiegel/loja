<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:template>
    <jsp:attribute name="title">
        Detalhes de <c:out value="${categoria.nome}" />
    </jsp:attribute>
    <jsp:body>
    <c:set var="idcategoria" value="${categoria.idCategoria}" scope="session" />
    <article>
        <fieldset>
            <legend>Dados categoria</legend>
            <ol>
                <li>
                    <label>Id:</label>
                    <c:out value="${categoria.idCategoria}" />
                </li>
                <li>
                    <label>Nome:</label>
                    <c:out value="${categoria.nome}" />
                </li>
            </ol>
        </fieldset>
    </article>
    <aside>
    <h3>Opções</h3>
    <p>
        Para mais ações clique em::
    </p>
    <ul>
        <li><a href="Categoria?cmd=edit&id=${categoria.idCategoria}">Alterar</a></li>
        <li><a href="Categoria?cmd=delete" onclick="return confirm('Tem certeza que gostaria de excluir essa categoria?');">Excluir</a></li>
    </ul>
    </aside>
    </jsp:body>
</t:template>