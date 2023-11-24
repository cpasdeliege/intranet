<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration - gestion des tickets</title>
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
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">suppression d'un ticket</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container">
	<tr height="25">
		<td></td>
		<td>
			<table>
				<tr>
					<td class="titre_tableau">Confirmation - Suppression d'un ticket</td>
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
			<table class="tableau_liste">
				<tr>
					<td align="right">texte : </td>
					<td class="texte_tableau_confirmation"><%= ticketItem.getTexte() %></td>
				</tr>
				<tr>
					<td></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
						<a href="${retour}"><img src="images/administration/retour.jpg" border="0" alt="retour"></a>
					</td>
					<td align="right">
						<form method="get" action="supprimerTicketItem.admin">
							<input type="hidden" name="ticket" value="<%= ticketItem.getIdTicketItem() %>"/>
							<input type="submit" value="supprimer"/>
						</form>
					</td>
				</tr>
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