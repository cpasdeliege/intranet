<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="cpas.css" type="text/css">
<script type="text/javascript">
<!-- 

function supprimerFormation(idFormation) {
   var prout = confirm('Confirmer la suppression de la formation ?');
   if(prout) {
   	document.location.href='supprimerFormation.formations?idFormation=' + idFormation;
   }
}

-->
</script>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">administration des formations</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="60px"><img src="images/formation.png" border="0" width="50px"></td>
					<td class="titre_tableau">Liste des formations</td>
					<td align="right">
						<form method="get" action="formulaireAjouter.formations">
							<input type="submit" value="ajouter une formation"/>
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
					<th>Intitulé</th>
					<th>Typologie</th>
					<th>service</th>
				</tr>
				<% int i = 0; %>
				<c:forEach items="${listeFormations}" var="formation">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td valign="top"><a class="lien_tableau" href="gestionFormation.formations?idFormation=${formation.idFormation}">${formation.intitule}</a></td>
						<td valign="top">${formation.typologie}</td>
						<td>
							<%= ((List<String>)request.getAttribute("listeServices")).get(i-1).replaceAll("\n", "<br>") %>
						</td>
						<td width="30px" align="right"><a style="cursor: pointer" onclick="supprimerFormation(${formation.idFormation})"><img alt="supprimer" src="images/administration/supprimer.gif" border="0"></a></td>
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
