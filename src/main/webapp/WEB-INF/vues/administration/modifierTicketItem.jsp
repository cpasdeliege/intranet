<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration - gestion des services</title>
</head>
<%@ page import="be.cpasdeliege.intranet.informatique.model.*" %>
<%
TicketItem ticketItem = (TicketItem)request.getAttribute("ticket");
%>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">modification ticket</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container">
	<tr height="25">
		<td></td>
		<td>
			<table>
				<tr>
					<td class="titre_tableau">Modification d'un ticket</td>
					<td>
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
			<form method="post">
				<table>
					<tr>
						<td align="right" valign="top">texte : </td>
						<td>
							<textarea id="zonedetexte" cols="60" rows="10" name="texte"/>${ ticketItem.texte }</textarea>
							<br>
							<input onclick="ajoutLien()" type="button" value="ajouter lien"/>
							<input onclick="ajoutLienDSI()" type="button" value="ajouter lien DSI"/>
							<input onclick="ajoutLienTHI()" type="button" value="ajouter lien TI"/>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td><a href="${retour}"><img alt="retour" src="images/administration/retour.jpg" border="0"></a></td>
						<td align="right"><input type="submit" value="envoyer"/></td>
					</tr>
				</table>
			</form>
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

<script type="text/javascript">
function ajoutLien() {
	document.getElementById('zonedetexte').value = document.getElementById('zonedetexte').value+' <a href=""></a>';
}
function ajoutLienDSI() {
	document.getElementById('zonedetexte').value = document.getElementById('zonedetexte').value+' <a href="afficherDemande.dsi?idDemandes=">DSI</a>';
}
function ajoutLienTHI() {
	document.getElementById('zonedetexte').value = document.getElementById('zonedetexte').value+' <a href="gestionTache.admin?idPlanning=">TI</a>';
}
</script>

</body>
</html>