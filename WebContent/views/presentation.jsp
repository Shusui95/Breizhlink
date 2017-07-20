<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<t:generic>
    <jsp:body>
        <div class="col-md-12">
            <div class="container">
                <form id="contact">
                    <div class="hero-unit" style="text-align: left;color:#47536B;">
                        <h3 style="color: #EFBB07;width:100%;text-align:center;">Presentation : Comment utiliser
                            Breizhlink</h3>
                        <br>
                        <h5 style="color: #777;">Comment faire un lien court ?</h5>
                        Il n'y a rien de plus simple. Pour faire un lien court en quelques secondes, renseignez l'url de
                        déstination puis validez en cliquant sur "Raccourcir". Vous recevrez ensuite une URL court du
                        type http://localhost:8080/Breizhlink/link/6fJoU avec une rediréction vers votre adresse web.
                        <br> <br>
                        <h5 style="color: #777;">Pourquoi utiliser un réducteur d'URL ?</h5>
                        Vous pouvez utiliser le service de reducteur d'URL pour les reseaux sociaux comme Twitter en
                        partagant immédiatement sur le réseau social son lien court accompagné d'un message. Vous pouvez
                        également utiliser un réducteur d'URL pour masquer des liens d'affiliations ou d'autres types
                        d'URL éxotiques.
                        Il est également possible de récuperer son URL court sur son mobile pour l'envoyer par SMS par
                        exemple.
                        <br> <br>
                        <h5 style="color: #777;">Comment faire un lien personnalisé ?</h5>
                        Pour faire un lien personnalisé, l'URL doit respecter certaines régles:
                        <br> <br>
                        <ul>
                            <li> Il faut pour cela se créer un compte.
                            </li>
                            <li>L'URL peut être soumise à plusieurs conditions. (Captcha, mot de passes, max
                                d'utilisations, etc ...)
                            </li>
                        </ul>
                        <br>
                        <h5 style="color: #777;">Comment afficher les statistiques d'une URL ?</h5>
                        Pour obtenir les statistiques d'un lien, rendez-vous sur l'onglet "Stats" une fois connecté,
                        puis affichez les statistiques à partir de l'url court
                        "http://localhost:8080/Breizhlink/stats/6fJoU" ou du code "6fJoU".
                        <br>
                        <br> <br>
                    </div>
                </form>
            </div>
        </div>
        </div>
    </jsp:body>
</t:generic>
