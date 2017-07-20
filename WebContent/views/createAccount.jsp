<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="crt" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<t:generic>
    <jsp:body>
        <div class="col-md-12">
            <div class="container">
                <form id="contact" action="http://localhost:8080/Breizhlink/creer-compte" method="post">
                    <h3>Créer un compte</h3>
                    <crt:if test="${ sessionScope.emailCreate != null }">
						Un email de confirmation pour activer votre compte vous a été envoyé à l'adresse ${sessionScope.emailCreate}.
					<br>
					</crt:if>
                    <crt:if test="${ sessionScope.emailNotValid != null }">
                        ${ sessionScope.emailNotValid }
                        <br>
                    </crt:if>
                    <fieldset>
                        <label for="login">Login</label>
                        <input name="login" id="login" placeholder="Votre login" type="text" tabindex="1" required autofocus>
                    </fieldset>
                    <fieldset>
                        <label for="email">Email</label>
                        <input name="email" id="email" placeholder="Votre email" type="text" tabindex="1" required autofocus>
                    </fieldset>
                    <fieldset>
                        <label for="password">Mot de passe</label>
                        <input name="password" id="password" placeholder="Votre mot de passe" type="password" tabindex="1" required autofocus>
                    </fieldset>
                    <fieldset>
                        <label for="status">Votre statut</label>
                       	<select name="status" id="status">
                       		<option>Particulier</option>
                       		<option>Entreprise</option>
                       		<option>Association</option>
                       	</select>
                    </fieldset>
                   
                    <fieldset>
                        <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Creer</button>
                    </fieldset>
                </form>
            </div>
        </div>
    </jsp:body>
</t:generic>
