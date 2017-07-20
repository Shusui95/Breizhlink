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
                    <h3>Confirmation de compte</h3>
                    <fieldset>
                    	<crt:if test="${ sessionScope.userInfos == null || sessionScope.confirmAccount == null }">
                          		Mauvais lien de confirmation !
                            </crt:if>
                            
                            <crt:if test="${ sessionScope.userInfos != null }">
                                
                            <crt:if test="${ sessionScope.confirmAccount == null && sessionScope.userInfos.getActivated() == 1 }">
                                Votre compte est déja activé !
                            </crt:if>
                            <crt:if test="${ sessionScope.userInfos.getActivated() == 1 }">
                                Bonjour ${ sessionScope.userInfos.getLogin() }, votre compte est désormais actif !
                            </crt:if>
                                <crt:if test="${ sessionScope.userInfos.getActivated() == 0 }">
                                    En cours de validation
                                    <a href="http://localhost:8080/Breizhlink/sendConfirmEmail">Renvoyer un email de confirmation à l'adresse : 
                        ${ sessionScope.user.getEmail() }
                        
                        </a>
                                </crt:if>
                            </crt:if>
                            
                            
                        
                    </fieldset>
                </form>
            </div>
        </div>
    </jsp:body>
</t:generic>
