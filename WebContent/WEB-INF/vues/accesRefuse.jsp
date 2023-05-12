<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="site.css" type="text/css">

</head>
<body class="body">
<jsp:include page="entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<hr>
</div>
<table align="center" width="80%" cellpadding="0" cellspacing="0">
	<tr height="25">
		<td></td>
		<td class="titre_tableau">Accès refusé !</td>
		<td></td>
	</tr>
	<tr height="25">
		<td width="22" background="images/cadre/area_top_left.gif"></td>
		<td background="images/cadre/area_top.gif"></td>
		<td width="22" background="images/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/cadre/area_left.gif"></td>
		<td>
		
		<%@ page import="be.cpasdeliege.intranet.informatique.model.*" %>
		<%
		Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur"); 
			if(utilisateur == null) {
		%>
			Le service que vous tentez de joindre est à accès limité.<br><br>
			Vous devez vous enregistrer en introduisant votre nom d'utilisateur 
			et votre mot de passe afin d'accéder à cette ressource.<br><br>
			Pour toute question, veuillez vous adresser au service informatique.
			
			<br><br><a href="index.htm"><img alt="retour" src="images/retour.jpg" border="0"></a>
			
		<%} else { %>
			
			Le service que vous tentez de joindre est à accès limité.<br><br>
			Vous n'avez pas accès à la ressource demandée.<br><br>
			Pour toute question, veuillez vous adresser au service informatique.
			
			<br><br><a href="${retour}"><img alt="retour" src="images/retour.jpg" border="0"></a>
		
		<%} %>
			
		</td>
		<td background="images/cadre/area_right.gif"></td>
	</tr>
	
	<tr height="25">
		<td background="images/cadre/area_bottom_left.gif"></td>
		<td background="images/cadre/area_bottom.gif"></td>
		<td background="images/cadre/area_bottom_right.gif"></td>
	</tr>
</table>
<jsp:include page="basDePage.jsp" ></jsp:include>
</body>
</html>