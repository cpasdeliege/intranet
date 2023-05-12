<%@page import="be.cpasdeliege.intranet.DemServInfo.model.DemServInf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="be.cpasdeliege.intranet.DemServInfo.*" %>
<%@page import="be.cpasdeliege.intranet.informatique.model.*"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Intranet CPAS de Li�ge</title>
<link rel="stylesheet" href="site.css" type="text/css">

</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">demande service informatique</a>
<hr>
</div>
<%

//List<DemServInf> listeDsi = (List<DemServInf>)request.getAttribute("listeDsi");
PrivilegeInformatique privilege = (PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique");
List<DemServInf> nouvelles = (List<DemServInf> ) request.getAttribute("listeDsiNouvelles");
List<DemServInf> validUser = (List<DemServInf>) request.getAttribute("listeDsiValidUser");
List<DemServInf> validInfo = (List<DemServInf>) request.getAttribute("listeDsiValidInfo");
List<DemServInf> conf = (List<DemServInf>) request.getAttribute("listeDsiConf");
List<DemServInf> acceptees = (List<DemServInf>) request.getAttribute("listeDsiAcceptees");
List<DemServInf> executees = (List<DemServInf>) request.getAttribute("listeDsiExecutees");
List<DemServInf> attente = (List<DemServInf>) request.getAttribute("listeDsiAttente");
List<DemServInf> refusees = (List<DemServInf>) request.getAttribute("listeDsiRefusees");
%>
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">

	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td align="center"><a class="lien_tableau" href="index.dsi">Demandes en cours</a> - <a class="lien_tableau" href="index.dsi?action=fini">Demandes trait�es</a></td>
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

	<% if(executees.size() != 0) {%>
	<tr height="25">
		<td colspan="3"><br></td>
		
	</tr>
	
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="30px"><img src="images/termine.png" border="0" width="35px"></td>
					<td class="titre_tableau"><a name="executees">Liste des demandes ex�cut�es (<%= executees.size() %>)</a></td>
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
					<th><a href="index.dsi?action=fini&orderby=idDemandes#executees">n�</a></th>
					<th><a href="index.dsi?action=fini&orderby=typeDemande#executees">Type</a></th>
					<th>Titre</th>
					<th><a href="index.dsi?action=fini&orderby=nom#executees">Auteur Demande</a></th>
					<th><a href="index.dsi?action=fini&orderby=service#executees">Service</a></th>
					<th><a href="index.dsi?action=fini&orderby=nomChef#executees">Responsable</a></th>
					<th><a href="index.dsi?action=fini&orderby=dateDemande#executees">Date Demande</a></th>
				</tr>
				<% int j = 0; %>
				<c:forEach items="${listeDsiExecutees}" var="dsi">
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
	
	<% if(refusees.size() != 0) {%>
	<tr height="25">
		<td colspan="3"><br></td>
		
	</tr>
	
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="30px"><img src="images/refuse.png" border="0" width="35px"></td>
					<td class="titre_tableau"><a name="refusees">Liste des demandes refus�es (<%= refusees.size() %>)</a></td>
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
					<th><a href="index.dsi?action=fini&orderby=idDemandes#refusees">n�</a></th>
					<th><a href="index.dsi?action=fini&orderby=typeDemande#refusees">Type</a></th>
					<th>Titre</th>
					<th><a href="index.dsi?action=fini&orderby=nom#refusees">Auteur Demande</a></th>
					<th><a href="index.dsi?action=fini&orderby=service#refusees">Service</a></th>
					<th><a href="index.dsi?action=fini&orderby=nomChef#refusees">Responsable</a></th>
					<th><a href="index.dsi?action=fini&orderby=dateDemande#refusees">Date Demande</a></th>
				</tr>
				<% int j = 0; %>
				<c:forEach items="${listeDsiRefusees}" var="dsi">
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