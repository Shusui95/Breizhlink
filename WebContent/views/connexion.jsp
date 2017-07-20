<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="crt" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page language="java" import="beans.User" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<t:generic>
    <jsp:body>
        <div class="col-md-12">
            <div class="container">
                <form id="contact" action="http://localhost:8080/Breizhlink/connexion" method="post">
                    <h3>Créer un compte</h3>
                    <crt:if test="${ sessionScope.errorConnect != null }">
                        ${ sessionScope.errorConnect }<br>
                    </crt:if>
                    <crt:if test="${ sessionScope.confirm != null }">
                    	Votre compte n'a pas été confirmé. Verifiez votre boite email
                        <a href="http://localhost:8080/Breizhlink/sendConfirmEmail">Renvoyer un email de confirmation à l'adresse : 
                        
                        <crt:if test="${ sessionScope.user != null }">
                        ${ sessionScope.user.getEmail() }
                        
                    </crt:if>
                        </a>
                    </crt:if>
                    <fieldset>
                        <label for="login">Login</label>
                        <input name="login" id="login" placeholder="Votre login" type="text" tabindex="1" required autofocus>
                    </fieldset>
                    <fieldset>
                        <label for="password">Mot de passe</label>
                        <input name="password" id="password" placeholder="Votre mot de passe" type="password" tabindex="1" required autofocus>
                    </fieldset>
                    
                    <fieldset>
                        <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Connexion
                        </button>
                    </fieldset>
                </form>
            </div>
        </div>
    </jsp:body>
</t:generic>
