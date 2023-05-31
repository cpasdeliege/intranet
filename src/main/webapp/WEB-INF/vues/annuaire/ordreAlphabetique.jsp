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
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.annuaire">annuaire interne</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">recherche par ordre alphabétique</a>
<hr>
</div>
<table align="center" cellpadding="0" cellspacing="0">
	<tr height="25">
		<td width="22" background="images/annuaire/cadre/area_top_left.gif"></td>
		<td background="images/annuaire/cadre/area_top.gif"></td>
		<td width="22" background="images/annuaire/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/annuaire/cadre/area_left.gif"></td>
		<td align="center"><a class="lien_navigation"  href="recherche.annuaire">recherche par mot-clé</a> - <a class="lien_navigation" href="services.annuaire">service</a> - <a class="lien_navigation_actif">ordre alphabétique</a></td>
		<td background="images/annuaire/cadre/area_right.gif"></td>
	</tr>
	<tr height="25">
		<td background="images/annuaire/cadre/area_left.gif"></td>
		<td background="images/annuaire/cadre/area_top.gif"></td>
		<td background="images/annuaire/cadre/area_right.gif"></td>
	</tr>
	<tr>
		<td background="images/annuaire/cadre/area_left.gif"></td>
		<td>
			<table width="100%">
				<tr>
					<td align="center">
						<a href="ordreAlphabetique.annuaire?lettre=a" class="lien_sous_navigation">A</a>
						<a href="ordreAlphabetique.annuaire?lettre=b" class="lien_sous_navigation">B</a>
						<a href="ordreAlphabetique.annuaire?lettre=c" class="lien_sous_navigation">C</a>
						<a href="ordreAlphabetique.annuaire?lettre=d" class="lien_sous_navigation">D</a>
						<a href="ordreAlphabetique.annuaire?lettre=e" class="lien_sous_navigation">E</a>
						<a href="ordreAlphabetique.annuaire?lettre=f" class="lien_sous_navigation">F</a>
						<a href="ordreAlphabetique.annuaire?lettre=g" class="lien_sous_navigation">G</a>
						<a href="ordreAlphabetique.annuaire?lettre=h" class="lien_sous_navigation">H</a>
						<a href="ordreAlphabetique.annuaire?lettre=i" class="lien_sous_navigation">I</a>
						<a href="ordreAlphabetique.annuaire?lettre=j" class="lien_sous_navigation">J</a>
						<a href="ordreAlphabetique.annuaire?lettre=k" class="lien_sous_navigation">K</a>
						<a href="ordreAlphabetique.annuaire?lettre=l" class="lien_sous_navigation">L</a>
						<a href="ordreAlphabetique.annuaire?lettre=m" class="lien_sous_navigation">M</a>
					</td>
				</tr>
				<tr>
					<td align="center">
						<a href="ordreAlphabetique.annuaire?lettre=n" class="lien_sous_navigation">N</a>
						<a href="ordreAlphabetique.annuaire?lettre=o" class="lien_sous_navigation">O</a>
						<a href="ordreAlphabetique.annuaire?lettre=p" class="lien_sous_navigation">P</a>
						<a href="ordreAlphabetique.annuaire?lettre=q" class="lien_sous_navigation">Q</a>
						<a href="ordreAlphabetique.annuaire?lettre=r" class="lien_sous_navigation">R</a>
						<a href="ordreAlphabetique.annuaire?lettre=s" class="lien_sous_navigation">S</a>
						<a href="ordreAlphabetique.annuaire?lettre=t" class="lien_sous_navigation">T</a>
						<a href="ordreAlphabetique.annuaire?lettre=u" class="lien_sous_navigation">U</a>
						<a href="ordreAlphabetique.annuaire?lettre=v" class="lien_sous_navigation">V</a>
						<a href="ordreAlphabetique.annuaire?lettre=w" class="lien_sous_navigation">W</a>
						<a href="ordreAlphabetique.annuaire?lettre=x" class="lien_sous_navigation">X</a>
						<a href="ordreAlphabetique.annuaire?lettre=y" class="lien_sous_navigation">Y</a>
						<a href="ordreAlphabetique.annuaire?lettre=z" class="lien_sous_navigation">Z</a>
					</td>
				</tr>
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
				<% 
					int i = 0; 
					List<String> listeEmail = (List<String>)request.getAttribute("listeEmail");	
					List<String> listePathPhoto = (List<String>)request.getAttribute("listePathPhoto");
					List<String> listeCodeAS = (List<String>)request.getAttribute("listeCodeAS");
				%>
				<c:forEach items="${listePersonnel}" var="employe">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" id="trPhoto" onmouseover="see_bubble('<%= listePathPhoto.get(i-1) %>')" onmouseout="kill_bubble('<%= listePathPhoto.get(i-1) %>')">
				<% } else { %>
					<tr class="ligne_impaire" id="trPhoto" onmouseover="see_bubble('<%= listePathPhoto.get(i-1) %>')" onmouseout="kill_bubble('<%= listePathPhoto.get(i-1) %>')">
				<% } %>
						<td>
							<% if(!listeEmail.get(i-1).equals("")) { %>
							<a title="<%= listeEmail.get(i-1) %>">
								<img width="15px" src="images/annuaire/email.gif" border="0"/>
							</a>
							<% } %>
						</td>
						<td>
							<img width="60px" src="http://10.210.117.21/photosAnnuaire/<%= listePathPhoto.get(i-1) %>" id="<%= listePathPhoto.get(i-1) %>" style="display: none;" >
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