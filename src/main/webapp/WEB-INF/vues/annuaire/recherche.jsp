<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="be.cpasdeliege.intranet.informatique.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="annuaire.css" type="text/css">
<script type="text/javascript">
<!--

function setFocus()
{
 document.formulaire.requete.focus();
}

//-->
//-----------------------
function see_bubble(img_){
var Obj = document.getElementById(img_);
Obj.style.display = "block";
}
//--------------------
function kill_bubble(img_){
var Obj = document.getElementById(img_);
Obj.style.display = "none";
}
//<!--
document.oncontextmenu = new Function("return false");
//-->
</script>
</head>
<body class="body" onload="setFocus()">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.annuaire">annuaire interne</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">recherche par mot-clé</a>
<hr>
</div>
<form method="get" action="recherche.annuaire" name="formulaire">
<table align="center" cellpadding="0" cellspacing="0">
	<tr height="25">
		<td width="22" background="images/annuaire/cadre/area_top_left.gif"></td>
		<td background="images/annuaire/cadre/area_top.gif"></td>
		<td width="22" background="images/annuaire/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/annuaire/cadre/area_left.gif"></td>
		<td align="center"><a class="lien_navigation_actif">recherche par mot-clé</a> - <a class="lien_navigation" href="services.annuaire">service</a> - <a class="lien_navigation" href="ordreAlphabetique.annuaire">ordre alphabétique</a></td>
		<td background="images/annuaire/cadre/area_right.gif"></td>
	</tr>
	<tr height="25">
		<td background="images/annuaire/cadre/area_left.gif"></td>
		<td background="images/annuaire/cadre/area_top.gif"></td>
		<td background="images/annuaire/cadre/area_right.gif"></td>
	</tr>
	<tr>
		<td background="images/annuaire/cadre/area_left.gif"></td>
		<td><input size="50" type="text" maxlength="200" name="requete" value="${requete }"/></td>
		<td background="images/annuaire/cadre/area_right.gif"></td>
	</tr>
	<tr>
		<td background="images/annuaire/cadre/area_left.gif"></td>
		<td><input type="submit" value="rechercher"/></td>
		<td background="images/annuaire/cadre/area_right.gif"></td>
	</tr>
	<tr height="25">
		<td background="images/annuaire/cadre/area_bottom_left.gif"></td>
		<td background="images/annuaire/cadre/area_bottom.gif"></td>
		<td background="images/annuaire/cadre/area_bottom_right.gif"></td>
	</tr>
</table>
</form>
<br>

<%@ page import="be.cpasdeliege.intranet.informatique.model.dao.*" %>
<%@ page import="java.util.*" %>
<%
	List listEmploye = (List)request.getAttribute("listePersonnel");
	if(request.getAttribute("listePersonnel") != null) {
%>
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
	<tr height="25">
		<td width="22" background="images/annuaire/cadre/area_top_left.gif"></td>
		<td background="images/annuaire/cadre/area_top.gif"></td>
		<td width="22" background="images/annuaire/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/annuaire/cadre/area_left.gif"></td>
		<td>
			<table class="tableau_liste" cellspacing="0">
				<tr class="titre_colonne_tableau_liste">
					<th width="20px"></th>
					<th width="60px"></th>
					<th>Nom</th>
					<th>Prénom</th>
					<th>Code Agent</th>
					<th>Service</th>
					<th>Fonction</th>
					<th width="60px" align="center">Extension VOIP</th>
					<th align="right">Numéro Externe</th>
				</tr>
				<% 	int i = 0; 
					List<String> listeEmail = (List<String>)request.getAttribute("listeEmail");
					List<String> listePathPhoto = (List<String>)request.getAttribute("listePathPhoto");
					List<String> listeCodeAS = (List<String>)request.getAttribute("listeCodeAS");
				%>
				<c:forEach items="${listePersonnel}" var="employe">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr id="trPhoto" class="ligne_paire" onmouseover="see_bubble('<%= listePathPhoto.get(i-1) %>')" onmouseout="kill_bubble('<%= listePathPhoto.get(i-1) %>')">
				<% } else { %>
					<tr id="trPhoto" class="ligne_impaire" onmouseover="see_bubble('<%= listePathPhoto.get(i-1) %>')" onmouseout="kill_bubble('<%= listePathPhoto.get(i-1) %>')" >
				<% } %>
						<td>
							<% if(!listeEmail.get(i-1).equals("")) { %>
							<a  title="<%= listeEmail.get(i-1) %>">
								<img width="15px" src="images/annuaire/email.gif" border="0"/>
							</a>
							<% } %>
						</td>
						<td>
							<img width="60px" src="http://10.104.86.21/photosAnnuaire/<%= listePathPhoto.get(i-1) %>" id="<%= listePathPhoto.get(i-1) %>" style="display: none;" >
						</td>
						<td>${employe.nom}</td>
						<td>${employe.prenom}</td>
						<td><%= listeCodeAS.get(i-1) %></td>
						<td>${employe.service}</td>
						<td>${employe.fonction} ${employe.rem}</td>
						<%
						String tel = "";
						String prefix = "";
						/** if((((DaoPersonnelService)listEmploye.get(i-1)).getTelephone().startsWith("04/220.") 
								|| ((DaoPersonnelService)listEmploye.get(i-1)).getTelephone().startsWith("04/250.")
								) && !((DaoPersonnelService)listEmploye.get(i-1)).getTelephone().startsWith("04/220.23")) {
							tel = ((DaoPersonnelService)listEmploye.get(i-1)).getTelephone().substring(7);
							prefix = ((DaoPersonnelService)listEmploye.get(i-1)).getTelephone().substring(0,7);
						} else {
							tel = ((DaoPersonnelService)listEmploye.get(i-1)).getTelephone();
						} **/
						tel = ((DaoPersonnelService)listEmploye.get(i-1)).getTelephone();
						%>
						<td align="right">${employe.extension}</td>
						<td align="right"><span style="color: red"><%= prefix %></span><%= tel %></td>
						
						
					</tr>
				</c:forEach>
			</table>			
		</td>
		<td background="images/annuaire/cadre/area_right.gif"></td>
	</tr>
	<tr height="25">
		<td background="images/annuaire/cadre/area_bottom_left.gif"></td>
		<td background="images/annuaire/cadre/area_bottom.gif"></td>
		<td background="images/annuaire/cadre/area_bottom_right.gif"></td>
	</tr>
</table>
<%
	}
%>
<jsp:include page="../basDePage.jsp" ></jsp:include>
</body>
</html>