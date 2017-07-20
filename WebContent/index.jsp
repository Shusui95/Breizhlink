<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="crt" uri="http://java.sun.com/jstl/core_rt" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<t:generic>
    <jsp:body>
        <div class="col-md-12">
            <div class="container">
                <form id="contact" action="http://localhost:8080/Breizhlink/index" method="post">
                    <h3>Raccourcir une URL</h3>
                    <fieldset>
                        <label for="url">URL à raccourcir</label>
                        <input name="url" id="url" placeholder="Url à raccourcir" type="text" tabindex="1" required autofocus>
                    </fieldset>
                    <fieldset>
                        <label for="passwordInput">Sécurisé avec mot de passe</label>
                        <input name="passwordInput" id="passwordInput" type="checkbox"
                               tabindex="2"
                               <c:if test="${ sessionScope.urlIndex != null && sessionScope.urlIndex.getPassword() != null }">
                        	checked
                        		</c:if>>
                    </fieldset>
                    <fieldset <c:if test="${ sessionScope.urlIndex == null || sessionScope.urlIndex.getPassword() == null }">
                        	class="inputPass hidden"
                        		</c:if>
                        		<c:if test="${ sessionScope.urlIndex != null && sessionScope.urlIndex.getPassword() != null }">
                        	class="inputPass"
                        		</c:if>
                        		>
                        <label for="password">Mot de passe</label>
                        <input name="password" id="password" placeholder="Mot de passe" type="text"
                               tabindex="3"
                               <c:if test="${ sessionScope.urlIndex != null && sessionScope.urlIndex.getPassword() != null }">
                        	value="${ sessionScope.urlIndex.getPassword() }"
                        		</c:if>
                               >
                    </fieldset>
                    <fieldset>
                        <label for="shortUrl">Votre URL raccourcie</label>
                        <input name="shortUrl" id="shortUrl" placeholder="Votre URL raccourcie" type="text"
                               tabindex="4" disabled="disabled"
                               <c:if test="${ sessionScope.urlIndex != null && sessionScope.urlIndex.getUrl_short() != null }">
                        	value="http://localhost:8080/Breizhlink/link/${ sessionScope.urlIndex.getUrl_short() }"
                        		</c:if>
                               >
                    </fieldset>
                    <crt:if test="${ sessionScope.user == null }">
                    <fieldset>
                        <a href="http://localhost:8080/Breizhlink/creer-compte">Créer un compte pour voir nos autres options possibles</a>
                    </fieldset>
                    </crt:if>
                    <fieldset>
                        <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Raccourcir
                        </button>
                    </fieldset>

                </form>
            </div>
        </div>
    </jsp:body>
</t:generic>
