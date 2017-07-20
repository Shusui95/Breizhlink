<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="crt" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page language="java" import="beans.Url" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<t:generic>
    <jsp:body>
        <div class="col-md-12">
            <div class="container">
                <form id="contact" action="http://localhost:8080/Breizhlink/stats" method="post">
                    <h3>Stats de l'url</h3>

                    <c:if test="${ sessionScope.urlRequest != null }">
                        <fieldset>
                            <label for="shortUrl">Url demandée :</label>
                            <label name="shortUrl">
                                /stats/${ sessionScope.urlRequest }
                            </label>
                        </fieldset>
                    </c:if>
                    <c:if test="${ sessionScope.errorUrl != null }">
                        ${ sessionScope.errorUrl }
                    </c:if>
                    <c:if test="${ sessionScope.url == null }">
                        <label for="shortUrl">L'url demandée n'est pas disponible</label>
                    </c:if>
                    <c:if test="${ sessionScope.url != null }">
                        <fieldset>
                            <c:if test="${ sessionScope.url.getPeriod_start() != null }">
                                <label for="shortUrl">Début de validité :</label>
                                <label name="shortUrl">
                                        ${ sessionScope.url.getPeriod_start() }
                                </label>
                            </c:if>
                            <c:if test="${ sessionScope.url.getDate_expiration() != null }">
                                <label for="shortUrl">Fin de validité :</label>
                                <label name="shortUrl">
                                        ${ sessionScope.url.getDate_expiration() }
                                </label>
                            </c:if>
                        </fieldset>
                        <fieldset>
                            <label for="shortUrl">Utilisation :</label>
                            <label name="shortUrl">
                                <c:if test="${ sessionScope.url.getUtilsation() != null }">
                                    ${ sessionScope.url.getUtilsation() }
                                </c:if>
                            </label>
                            <c:if test="${ sessionScope.url.getMax_use() != null && sessionScope.url.getMax_use() > 0 }">
                                <label for="shortUrl">Max utilisation :</label>
                                <label name="shortUrl">
                                        ${ sessionScope.url.getMax_use() }
                                </label>
                            </c:if>
                        </fieldset>
                        <c:if test="${ sessionScope.url.getCaptcha() > 0 }">
                            <fieldset>
                                <label for="shortUrl">Captcha requis</label>
                                <label name="shortUrl">


                                </label>
                            </fieldset>
                        </c:if>
                        <c:if test="${ sessionScope.url.getPassword() != null }">
                            <fieldset>

                                <label for="password">Mot de passe requis : ${ sessionScope.url.getPassword() }</label>

                            </fieldset>
                        </c:if>
                        <c:if test="${ sessionScope.url.getEmail() != null }">
                            <fieldset>

                                <label for="email">Email requis : ${ sessionScope.url.getEmail() }</label>

                            </fieldset>
                        </c:if>
                        <c:if test="${ sessionScope.url != null }">
                            <fieldset>
                                <div id="chart"></div>
                            </fieldset>
                        </c:if>
                        <fieldset>

                            <!-- <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Valider
                            </button> -->
                        </fieldset>
                    </c:if>
                    <fieldset>
                        <img src="http://localhost:8080/Breizhlink/helloworld/${ sessionScope.urlRequest }">
                    </fieldset>
                    <fieldset>
                        <a href="http://localhost:8080/Breizhlink/url"><label name="shortUrl">
                            Retour
                        </label></a>
                    </fieldset>

                    <c:remove var='url'/>
                    <c:remove var='sessionScope.url'/>
                </form>
            </div>
        </div>
    </jsp:body>
</t:generic>
