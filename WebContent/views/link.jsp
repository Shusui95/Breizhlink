<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="crt" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page language="java" import="beans.Url" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<t:generic>
    <jsp:body>
        <div class="col-md-12">
            <div class="container">
                <form id="contact" action="http://localhost:8080/Breizhlink/link/${ sessionScope.urlRequest }"
                      method="post">
                    <h3>Générer l'url</h3>
                    <c:if test="${ sessionScope.urlRequest != null }">
                        <fieldset>

                            <label for="shortUrl">Url demandée :</label>
                            <label name="shortUrl">
                                /link/${ sessionScope.urlRequest }
                            </label>
                        </fieldset>
                    </c:if>
                    <c:if test="${ sessionScope.showUrl != null }">
                        <fieldset>
                            <label for="longUrl">Votre lien :</label>
                            <label name="longUrl">
                                    ${ sessionScope.showUrl }
                            </label>
                        </fieldset>
                    </c:if>
                    <c:if test="${ sessionScope.errorUrl != null }">
                        Erreur : ${ sessionScope.errorUrl }
                    </c:if>
                    <c:if test="${ sessionScope.url == null }">
                        <label for="shortUrl">L'url demandée n'est pas disponible</label>
                    </c:if>
                    <c:if test="${ sessionScope.url != null }">
                        <c:choose>
                            <c:when test="${ sessionScope.url.getDisponible() == 1 }">
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
                                    <label for="shortUrl">Captcha :</label>
                                    <label name="shortUrl">

                                    <img src="http://localhost:8080/Breizhlink/jcaptcha.jpg"/> <input type="text"
                                                                                                      id="jcaptcha"
                                                                                                      name="jcaptcha"
                                                                                                      value=""/>
                                </c:if>
                                </label>
                                </fieldset>

                                <c:if test="${ sessionScope.url.getPassword() != null }">
                                    <fieldset>

                                        <label for="password">Mot de passe</label>
                                        <input name="password" id="password" placeholder="Votre mot de passe"
                                               type="text"
                                               tabindex="1"
                                               required autofocus>
                                    </fieldset>
                                </c:if>
                                <c:if test="${ sessionScope.url.getEmail() != null }">
                                    <fieldset>

                                        <label for="email">Email</label>
                                        <input name="email" id="email" placeholder="Email" type="text"
                                               tabindex="1"
                                               required autofocus>
                                    </fieldset>
                                </c:if>

                                <fieldset>

                                    <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">
                                        Valider
                                    </button>
                                </fieldset>
                            </c:when>

                            <c:otherwise>
                                <label for="shortUrl">L'url demandée n'est pas disponible</label>
                            </c:otherwise>
                        </c:choose>

                    </c:if>
                    <c:remove var='url'/>
                    <c:remove var='sessionScope.url'/>
                </form>
            </div>
        </div>
    </jsp:body>
</t:generic>
