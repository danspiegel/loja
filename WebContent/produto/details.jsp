<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:template>
    <jsp:attribute name="title">
        Detalhes do produto <c:out value="${prod.produto}" />
    </jsp:attribute>
    <jsp:body>
    <c:set var="idproduto" value="${prod.idProduto}" scope="session" />
    <article>
        <fieldset>
            <legend>Dados produtos</legend>
            <ol>
                <li>
                    <label>Id:</label>
                    <c:out value="${prod.idProduto}" />
                </li>
                <li>
                    <label>Produto:</label>
                    <c:out value="${prod.produto}" />
                </li>
                <li>
                    <label>Código:</label>
                    <c:out value="${prod.codigo}" />
                </li>
                <li>
                    <label>Preço:</label>
                    <c:out value="${prod.preco}" />
                </li>
                <li>
                    <label>Estoque:</label>
                    <c:out value="${prod.estoque}" />
                </li>
                <li>
                    <label>Categoria:</label>
                    <c:out value="${prod.categoria.nome}" />
                </li>
            </ol>
        </fieldset>
    </article>
    <aside>
    <h3>Opções</h3>
    <p>
        Para mais ações clique em:
    </p>
    <ul>
        <li><a href="Produto?cmd=edit&id=${prod.idProduto}">Alterar</a></li>
        <li><a href="Produto?cmd=delete" onclick="return confirm('Tem certeza que gostaria de excluir esse produto?');">Excluir</a></li>
    </ul>
    </aside>
    </jsp:body>
</t:template>