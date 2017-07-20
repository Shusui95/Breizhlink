<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="crt" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<t:generic>
    <jsp:body>
        <div class="col-md-12">
            <div class="container">
                <form id="contact" action="http://localhost:8080/Breizhlink/raccourcir" method="post">
                    <h3>Raccourcir une URL</h3>
                    <fieldset>
                        <label for="url">URL à raccourcir</label>
                        <input name="url" id="url" placeholder="Url à raccourcir" type="text" tabindex="1" required
                               autofocus
                        <c:if test="${ sessionScope.urlSave != null }">
                               value="${ sessionScope.urlSave.getUrl_long() }"
                        </c:if>
                        >
                    </fieldset>
                    <fieldset>
                        <label>Vos options possibles :</label>

                    </fieldset>
                    <fieldset>
                        <input name="captchaInput" id="captchaInput" type="checkbox"
                               tabindex="2"
                        <c:if test="${ sessionScope.urlSave != null && sessionScope.urlSave.getCaptcha() == 1 }">
                               checked
                        </c:if>
                        >
                        <label for="jcaptcha">Captcha</label>
                        <!-- <img src="http://localhost:8080/Breizhlink/jcaptcha.jpg" /> <input type="text" id="jcaptcha" name="jcaptcha" value="" /> -->
                    </fieldset>
                    <fieldset>
                        <input name="email" id="email" type="checkbox"
                               tabindex="3"
                        <c:if test="${ sessionScope.urlSave != null && sessionScope.urlSave.getEmail() != null }">
                               checked
                        </c:if>
                        >
                        <label for="email">Sécurisé avec votre email</label>
                    </fieldset>
                    <fieldset style="display: inline-block;
    						min-width: 50%;
    						width: 50%;">
                        <input name="checkboxPassword" id="checkboxPassword" type="checkbox" tabindex="4"
                        <c:if test="${ sessionScope.urlSave != null && sessionScope.urlSave.getPassword() != null }">
                               checked
                        </c:if>
                        >
                        <label for="checkboxPassword">Sécurisé avec mot de passe</label>
                    </fieldset>
                    <fieldset style="display: inline-block;
    min-width: 49%;
    width: 49%;"
                              class="inputPass">
                        <input
                        <c:if test="${ sessionScope.urlSave == null || sessionScope.urlSave.getEmail() == null }">
                                class="hidden"
                        </c:if>
                                name="password" id="password" placeholder="Mot de passe" type="text"
                                tabindex="4"
                        <c:if test="${ sessionScope.urlSave != null && sessionScope.urlSave.getEmail() != null }">
                                value="${ sessionScope.urlSave.getPassword() }"
                        </c:if>
                        >
                    </fieldset>
                    <fieldset style="display: inline-block;
    						min-width: 50%;
    						width: 50%;">
                        <input name="checkboxMaxUse" id="radioClics" type="checkbox" tabindex="5"
                        <c:if test="${ sessionScope.urlSave != null && sessionScope.urlSave.getMax_use() > 0 }">
                               checked
                        </c:if>
                        >
                        <label for="numberInput">Max Clics </label>
                    </fieldset>
                    <fieldset style="display: inline-block;
    min-width: 49%;
    width: 49%;"
                              class="inputPass">
                        <input
                        <c:if test="${ sessionScope.urlSave == null || sessionScope.urlSave.getMax_use() == 0 }">
                                class="hidden"
                        </c:if>
                                name="numberInput" id="numberInput" type="number"
                                tabindex="6"
                        <c:if test="${ sessionScope.urlSave != null && sessionScope.urlSave.getMax_use() > 0 }">
                                value="${ sessionScope.urlSave.getMax_use() }"
                        </c:if>
                        >
                    </fieldset>
                    <fieldset>
                        <input name="radioChoice" id="radioPeriod" type="radio" tabindex="4" value="period"
                        <c:if test="${ sessionScope.urlSave != null && sessionScope.urlSave.getPeriod_start() != null}">
                               checked
                        </c:if>
                        >
                        <label for="startInput">Valable du </label>
                        <input name="startInput" id="startInput" type="date"
                               tabindex="5"
                        <c:if test="${ sessionScope.urlSave != null && sessionScope.urlSave.getPeriod_start() != null}">
                               value="${ sessionScope.urlSave.getPeriod_start() }"
                        </c:if>
                        >
                        <label for="endInput"> au </label>
                        <input name="endInput" id="endInput" type="date"
                               tabindex="5"
                        <c:if test="${ sessionScope.urlSave != null && sessionScope.urlSave.getDate_expiration() != null && sessionScope.urlSave.getPeriod_start() != null}">
                               value="${ sessionScope.urlSave.getDate_expiration() }"
                        </c:if>
                        >
                    </fieldset>

                    <fieldset>
                        <input name="radioChoice" id="radioLimitDate" type="radio" tabindex="6" value="dateLimit"
                        <c:if test="${ sessionScope.urlSave != null && sessionScope.urlSave.getDate_expiration() != null && sessionScope.urlSave.getPeriod_start() == null}">
                               checked
                        </c:if>
                        >
                        <label for="validityInput">Valable jusqu'au </label>
                        <input name="validityInput" id="validityInput" type="date"
                               tabindex="7"
                        <c:if test="${ sessionScope.urlSave != null && sessionScope.urlSave.getDate_expiration() != null && sessionScope.urlSave.getPeriod_start() == null}">
                               value="${ sessionScope.urlSave.getDate_expiration() }"
                        </c:if>
                        >
                    </fieldset>
                    <fieldset>
                        <label for="shortUrl">Votre URL raccourcie</label>
                        <input name="shortUrl" id="shortUrl" placeholder="Votre URL raccourcie" type="text"
                               tabindex="8" disabled="disabled"
                        <c:if test="${ sessionScope.urlSave != null && sessionScope.urlSave.getUrl_short() != null }">
                               value="http://localhost:8080/Breizhlink/link/${ sessionScope.urlSave.getUrl_short() }"
                        </c:if>
                        >
                    </fieldset>
                    <fieldset>
                        <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Raccourcir
                        </button>
                    </fieldset>
                </form>
            </div>
        </div>
    </jsp:body>
</t:generic>
