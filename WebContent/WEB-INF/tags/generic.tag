<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="crt" uri="http://java.sun.com/jstl/core_rt" %> 
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->


    <!-- Basic Page Needs
  ================================================== -->
	<meta charset="utf-8">
	<title>Breizh Link</title>
	<meta name="description" content="Free Responsive Html5 Css3 Templates | zerotheme.com">
	
    <!-- Mobile Specific Metas
	================================================== -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    
    <!-- CSS
	================================================== -->
	<style>
	
	<jsp:include page="/ressources/css/lightbox.css"/>
	<jsp:include page="/ressources/css/menu.css"/>
	<jsp:include page="/ressources/css/style.css"/>
	<jsp:include page="/ressources/css/zerogrid.css"/>
	html, body, #background, #content{
		min-height: 100vh;
	}
	#background{
		background: url(http://localhost:8080/Breizhlink/ressources/images/background6.jpeg) no-repeat center center fixed; 
  		-webkit-background-size: cover;
  		-moz-background-size: cover;
  		-o-background-size: cover;
  		background-size: cover;
  		filter: blur(2px);
  		position:relative;
  		z-index:1;
	}
	#content{
		background: rgba(51, 51, 51, 0.70);
    	z-index: 3;
    	position: absolute;
    	top: 0;
    	width: 100%;
	}
	#cssfooter ul li span{
		color: #eee;
	}]
	</style>
	<!-- <style src="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css"></style> -->
	<script type="application/javascript">
		<%@ include file="/ressources/js/html5.js"%>
		<%@ include file="/ressources/js/css3-mediaqueries.js"%>
		
		<%@ include file="/ressources/js/jquery1111.min.js"%>
		<%@ include file="/ressources/js/lightbox-plus-jquery.min.js"%>
		<%@ include file="/ressources/js/script.js"%>
	</script>
	<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="http://static.fusioncharts.com/code/latest/fusioncharts.js"></script>
</head>
<body>
<div id="background"></div>
<div id="content">
<div id="pageheader">
    <header class="zerogrid">
		<div class="logo"><img src="<c:url value='/ressources/images/breizh.png'/>" ></div>
		<div id='cssmenu' class="align-center">
			<ul>

			   <li class="accueil active"><a href='http://localhost:8080/Breizhlink/'><span>Accueil</span></a></li>
			   <li class="presentation"><a href='http://localhost:8080/Breizhlink/presentation'><span>Presentation</span></a></li>
			   
				<crt:if test="${ sessionScope.user != null }">
					
					<!-- ${ sessionScope.user.getEmail() } -->
					<crt:if test="${ sessionScope.user.getActivated() == 1 || sessionScope.userInfos.getActivated() == 1}">

                        <li class="raccourcir hidden"><a href='http://localhost:8080/Breizhlink/raccourcir'><span>Raccourcir</span></a></li>
                        
                    </crt:if>
					
					<li class="compte hidden"><a href='http://localhost:8080/Breizhlink/compte'><span>Mon compte</span></a></li>
					<li class='connexion last'><a href='http://localhost:8080/Breizhlink/deconnexion'><span>Deconnexion</span></a></li>
				</crt:if>
				<crt:if test="${ sessionScope.user == null }">
				<li class="creer-compte"><a href='http://localhost:8080/Breizhlink/creer-compte'><span>Créer un compte</span></a></li>
					<li class='connexion last'><a href='http://localhost:8080/Breizhlink/connexion'><span>Connexion</span></a></li>
				</crt:if>

			</ul>
		</div>
	</header>
</div>
<div id="body">
    <jsp:doBody/>
</div>
<div id="pagefooter">
    <footer>
		<div id='cssfooter' class="align-center">
			<ul>
				
				<li>-</li>
				<li class='last'><a href='http://localhost:8080/Breizhlink/mentions'><span>Mentions légales</span></a></li>
				<li>-</li>
			</ul>
		</div>
	</footer>
</div>
</div>

</body>
</html>