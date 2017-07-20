<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="crt" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<t:generic>
    <jsp:body>
        <div class="col-md-12">
            <div class="container">
                <form id="contact" action="" method="post">
                    <h3>Mes urls</h3>
                    <fieldset>
                        <table id="table">
                            <thead>
                            <tr>
                                <td>Url Breizhlink</td>
                                <td>Votre url</td>
                                <td>Date de création</td>
                                <td>Disponibilité</td>
                                <td>Stats</td>
                            </tr>
                            </thead>
                            <crt:if test="${ sessionScope.urls != null }">
                                <tbody>
                                <crt:forEach items="${sessionScope.urls }" var="url">
                                    <tr>
                                        <td><a href="http://localhost:8080/Breizhlink/link/${url.getUrl_short()}">Breizhlink/link/${url.getUrl_short()}</a>
                                        </td>
                                        <td>${url.getUrl_long()}</td>
                                        <td>${url.getDate_created()}</td>
                                        <td>
                                            <crt:if test="${ url.getDisponible() == 0 }">
                                                Non disponible
                                            </crt:if>
                                            <crt:if test="${ url.getDisponible() == 1 }">
                                                Valide
                                            </crt:if>
                                        </td>
                                        <td><a href="http://localhost:8080/Breizhlink/stats/${url.getUrl_short()}">Voir
                                            Stats</a></td>

                                    </tr>
                                </crt:forEach>
                                </tbody>

                            </crt:if>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
    </jsp:body>
</t:generic>
