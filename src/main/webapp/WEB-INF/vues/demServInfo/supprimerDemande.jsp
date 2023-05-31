<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<%@ page import="be.cpasdeliege.intranet.DemServInfo.*" %>
<%@page import="be.cpasdeliege.intranet.DemServInfo.model.DemServInf"%>
<%@page import="be.cpasdeliege.intranet.DemServInfo.model.Remarque"%>
<%@page import="be.cpasdeliege.intranet.informatique.model.*"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="site.css" type="text/css">

<script language="JavaScript" src="javascript/calendar_db.js"></script>
<link rel="stylesheet" href="calendar.css">
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<% 
DemServInf dsi = (DemServInf)request.getAttribute("dsi");
Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
PrivilegeInformatique privilege = (PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique");
%>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<% if(privilege.isAdministrateur()) { %>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<% } %>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.dsi">demande service informatique</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">supprimer une demande</a>
<hr>
</div>
<p align="center" style="color: red">
${erreur}
</p>

<form method="post" name="formu">
<table cellpadding="0" cellspacing="0"  class="tableau_container" width="800px">

	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td class="titre_tableau">Supprimer la demande n°<%= dsi.getIdDemandes() %> (<%= dsi.getTitre() %>) de <%= dsi.getPrenom() %> <%= dsi.getNom() %></td>
					<td align="right">
						
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
			<p align="center">Pour supprimer une demande, il est nécessaire de le motiver via l'ajout d'une remarque.</p>
			<!--  <p align="center">Une demande supprimée passe en statut "Demande exécutée"</p> -->
		</td>
		<td background="
		images/administration/cadre/area_right.gif"></td>
	</tr>
	
	<tr>
		<td background="images/administration/cadre/area_left.gif"></td>
		<td align="center">
			<form method="get" action="afficherDemande.dsi">
				<textarea  cols="80" rows="10" name="justification"/></textarea><br><br>
				<input type="submit" value="Supprimer Demande"/>
				<input type="hidden" name="idDemandes" value="${dsi.idDemandes}">
				<input type="hidden" name="action" value="supprimer">
			</form>
		</td>
		<td background="
		images/administration/cadre/area_right.gif"></td>
	</tr>
			
	
	<tr height="25">
		<td background="images/administration/cadre/area_bottom_left.gif"></td>
		<td background="images/administration/cadre/area_bottom.gif"></td>
		<td background="images/administration/cadre/area_bottom_right.gif"></td>
	</tr>
	<tr>
		<td colspan="2"><br></td>
	</tr>
	
	
	</table>
	</form>
	
		
						 
		
	


</body>
</html>