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
                    <h3>Mes informations</h3>
                    <fieldset>
                    	<table>
                            <crt:if test="${ sessionScope.userInfos != null }">
                                <tr>
                                <td> Mon Login : </td>
                                <td>${ sessionScope.userInfos.getLogin() }</td>
                                </tr>
                                <tr>
                                <td> Mon Email : </td>
                                <td>${ sessionScope.userInfos.getEmail() }</td>
                                </tr>
                                <tr>
                                <td> Mon Statut : </td>
                                <td>${ sessionScope.userInfos.getStatut() }</td>
                                </tr>
                                <tr>
                                    <td> Crée le : </td>
                                    <td>${ sessionScope.userInfos.getCreated_date() }</td>
                                </tr>
                                <td> Statut du compte : </td>
                                
                            <crt:if test="${ sessionScope.userInfos.getActivated() == 1 }">
                                <td>Activé</td>
                            </crt:if>
                                <crt:if test="${ sessionScope.userInfos.getActivated() == 0 }">
                                    <td>En cours de validation</td>
                                    <a href="http://localhost:8080/Breizhlink/confirmAccount/">Renvoyer un email de confirmation à l'adresse : 
                        ${ sessionScope.user.getEmail() }
                        
                        </a>
                                </crt:if>

                                </tr>
                                
                            </crt:if>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
    </jsp:body>
</t:generic>
