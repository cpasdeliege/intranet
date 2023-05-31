<%@page import="be.cpasdeliege.intranet.DemServInfo.model.DemServInf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="be.cpasdeliege.intranet.DemServInfo.*" %>
<%@page import="be.cpasdeliege.intranet.informatique.model.*"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="site.css" type="text/css">

</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<%

//List<DemServInf> listeDsi = (List<DemServInf>)request.getAttribute("listeDsi");
PrivilegeInformatique privilege = (PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique");
List<DemServInf> supprimees = (List<DemServInf>) request.getAttribute("listeDsiSupprimees");
%>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<% if(privilege.isAdministrateur()) { %>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<% } %>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">demande service informatique</a>
<hr>
</div>

<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">

	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td align="center"><a class="lien_tableau" href="index.dsi">Demandes en cours</a> - <a class="lien_tableau" href="index.dsi?action=fini">Demandes traitées</a>
					<% if(privilege.isDsiInfo()) { %>
					- <a class="lien_tableau" href="index.dsi?action=refusees">Demandes refusées</a>
 							- <a class="lien_tableau" href="index.dsi?action=supprimees">Demandes supprimées</a>
 							- <a class="lien_tableau" href="gestionPlanning.admin">GTI</a></td>
					<% } %>
					</td>
					<td>
							<a class="lien_tableau" href="listeTypeDemande.dsi">Configuration</a>
						</td>
				</tr>
			</table>
		</td>
		<td></td>
	</tr>

<tr height="25">
		<td></td>
		<td>
			<hr>
		</td>
		<td></td>
	</tr>



	
	<% if(supprimees.size() != 0) {%>
	<tr height="25">
		<td colspan="3"><br></td>
		
	</tr>
	
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="30px"><img src="images/refuse.png" border="0" width="35px"></td>
					<td class="titre_tableau"><a name="refusees">Liste des demandes supprimées (<%= supprimees.size() %>)</a></td>
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
			<table class="tableau_liste" cellspacing="0">
				<tr class="titre_colonne_tableau_liste">
					<th><a href="index.dsi?action=fini&orderby=idDemandes#refusees">n°</a></th>
					<th><a href="index.dsi?action=fini&orderby=typeDemande#refusees">Type</a></th>
					<th>Titre</th>
					<th><a href="index.dsi?action=fini&orderby=nom#refusees">Auteur Demande</a></th>
					<th><a href="index.dsi?action=fini&orderby=service#refusees">Service</a></th>
					<th><a href="index.dsi?action=fini&orderby=nomChef#refusees">Responsable</a></th>
					<th><a href="index.dsi?action=fini&orderby=dateDemande#refusees">Date Demande</a></th>
				</tr>
				<% int j = 0; %>
				<c:forEach items="${listeDsiSupprimees}" var="dsi">
				<% j++; %>
				<% if(j%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td>${dsi.idDemandes}</td>
						<td>${dsi.typeDemandeAffichage}</td>
						<td><a class="lien_tableau" href="afficherDemande.dsi?idDemandes=${dsi.idDemandes}">${dsi.titre}</a></td>
						<td>${dsi.nom} ${dsi.prenom}</td>
						<td>${dsi.service}</td>
						<td>${dsi.nomChef} ${dsi.prenomChef}</td>
						<td>${dsi.dateDemande}</td>
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
	<% } %>
</table>

<jsp:include page="../basDePage.jsp" ></jsp:include>
</body>
</html>