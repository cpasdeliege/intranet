<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="be.cpasdeliege.intranet.informatique.model.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration - gestion des ordinateurs</title>
</head>
<%
List listeOrd = (List)request.getAttribute("listeOrdinateurs");  
%>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">gestion des ordinateurs</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="60px"><img src="images/administration/ordinateur.png" border="0" width="50px"></td>
					<td class="titre_tableau">Liste des ordinateurs (<%= listeOrd.size() %>) </td>
					<td><a href="gestionOrdinateurs.admin?param=enService">En service</a> - <a href="gestionOrdinateurs.admin?param=stock">Stock</a> - <a href="gestionOrdinateurs.admin?param=tous">Tous</a></td>
					<td align="right">
						<form method="get" action="formulaireOrdinateurAjouter.admin">
							<input type="submit" value="ajouter un ordinateur"/>
						</form>
					</td>
				</tr>
			</table>
		</td>
		<td></td>
	</tr>
	<tr height="25">
		<td width="22" background="images/administration/cadre/area_top_left.gif"></td>
		<td background="images/administration/cadre/area_top.gif"></td>
		<td width="22" background="images/administration/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/administration/cadre/area_left.gif"></td>
		<td>
			<table class="tableau_liste" cellspacing="0">
				<tr class="titre_colonne_tableau_liste">
					<th>Nom</th>
					<!-- <th>Service</th> -->
					<th>O.S.</th>
					<th>Carte mère</th>
					<th>Ecran</th>
				</tr>
				<% 
					int i = 0; 
					String couleur = "#000000";
				%>
				<c:forEach items="${listeOrdinateurs}" var="ordinateur">
				
					
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
				
						<c:choose>
							<c:when test="${ordinateur.actif == 0}">
								<td><a class="lien_tableau" href="gestionOrdinateur.admin?ordinateur=${ordinateur.nom}" style="background-color: red;">${ordinateur.nom}</a></td>
							</c:when>
							<c:otherwise>
								<td><a class="lien_tableau" href="gestionOrdinateur.admin?ordinateur=${ordinateur.nom}">${ordinateur.nom}</a></td>
							</c:otherwise>
						</c:choose>
						
						
						<!-- <td>${ordinateur.service}</td> -->
						<td>${ordinateur.systemeExploitation}</td>
						<td>${ordinateur.carteMere}</td>
						<td>${ordinateur.ecran}</td>
						
						
						<td width="30px" align="right"><a href="supprimerOrdinateurConfirmation.admin?ordinateur=${ordinateur.nom}"><img alt="surpprimer" src="images/administration/supprimer.gif" border="0"></a></td>
					</tr>
				</c:forEach>
			</table>
		</td>
		<td background="images/administration/cadre/area_right.gif"></td>
	</tr>
	<tr height="25">
		<td background="images/administration/cadre/area_bottom_left.gif"></td>
		<td background="images/administration/cadre/area_bottom.gif"></td>
		<td background="images/administration/cadre/area_bottom_right.gif"></td>
	</tr>
</table>
<jsp:include page="../basDePage.jsp" ></jsp:include>
</body>
</html>