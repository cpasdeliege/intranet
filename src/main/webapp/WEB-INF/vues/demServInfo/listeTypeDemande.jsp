<%@page import="be.cpasdeliege.intranet.DemServInfo.model.DemServInf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="be.cpasdeliege.intranet.DemServInfo.model.*"%>
<%@page import="be.cpasdeliege.intranet.informatique.model.*"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="site.css" type="text/css">
</head>

<%
	PrivilegeInformatique privilege = (PrivilegeInformatique) session.getAttribute("privilegeInformatique");
%>

<body class="body">
	<jsp:include page="../entete.jsp"></jsp:include>
	<div>
		
		<hr>
		&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0"
			alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
		<%
			if (privilege.isAdministrateur()) {
		%>
		<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a
			class="menu_contextuel" href="index.admin">administration</a>
		<%
			}
		%>
		<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a
			class="menu_contextuel">demande service informatique</a>
		<hr>
	</div>

	<table cellpadding="0" cellspacing="0" class="tableau_container_centre">

		<tr height="25">
			<td></td>
			<td>
				<table width="100%">
					<tr>
						<td align="center"><a class="lien_tableau" href="index.dsi">Demandes
								en cours</a> - <a class="lien_tableau" href="index.dsi?action=fini">Demandes
								traitées</a> 
							<%
 							if (privilege.isDsiInfo()) {
 							%> 
 							- <a class="lien_tableau" href="index.dsi?action=refusees">Demandes refusées</a>
 							- <a class="lien_tableau" href="index.dsi?action=supprimees">Demandes supprimées</a>
 							- <a class="lien_tableau" href="gestionPlanning.admin">GTI</a>
						
						<%
							}
						%>
						</td>
						<td>
							<a class="lien_tableau" href="listeTypeDemande.dsi">Configuration</a>
						</td>
					</tr>
				</table></td>
			<td></td>
		</tr>

		<tr height="25">
			<td></td>
			<td>
				<hr></td>
			<td></td>
		</tr>
		
		<tr height="25">
			<td></td>
			<td>
			<p>Ce tableau permet de spécifier le comportement de l'application DSI quant à la validation des demandes par la direction.</p> 
			<p><li> Les types de demande signalés en <span style="color: red;font-weight: bold;">"auto"</span> seront validés automatiquement lors de leur passage au niveau de la direction (D).</p>
			<p><li> Les types de demande signalés en <span style="color: green;font-weight: bold;">"manuel"</span> devront être validés manuellement par la direction (D).</p>
			</td>
			<td></td>
		</tr>
		
		<tr height="25">
			<td></td>
			<td>
				<table width="100%">
					<tr>
						<td width="30px"><img src="images/attente.png" border="0"
							width="35px">
						</td>
						<td class="titre_tableau">Liste des types de demande
						</td>
						<td align="right">
							
						</td>
					</tr>
				</table></td>
			<td></td>
		</tr>

		<tr height="25">
			<td width="22"
				background="images/administration/cadre/area_top_left.gif"></td>
			<td background="images/administration/cadre/area_top.gif"></td>
			<td width="22" background="images/administration/cadre/area_top_right.gif"></td>
		</tr>
		<% 
		List<TypeDemandeDsi> liste = (List<TypeDemandeDsi>)request.getAttribute("listeTypeDemande");
		
		%>
		<tr>
			<td background="images/administration/cadre/area_left.gif"></td>
			<td>
				<table width="100%">
				<%
					int i = 0;
					for (TypeDemandeDsi tmp : liste) {
						i++;
						if (i % 2 == 0) {
					%>
					<tr class="ligne_paire"
						onmouseover="this.className='ligne_survol'"
						onmouseout="this.className='ligne_paire'">
					<%
						} else {
					%>
					
					<tr class="ligne_impaire"
						onmouseover="this.className='ligne_survol'"
						onmouseout="this.className='ligne_impaire'">
					<%
						}
					%>
						<td><%= tmp.getType() %></td>
						<td width="100px">
							<%
							if(tmp.isAccordDG()){
							%>
							<span style="color: green;font-weight: bold;">Manuel</span>
							<%
							} else {
							%>
							<span style="color: red;font-weight: bold;">Auto</span>
							<%
							}
							%>
						</td>
						<td width="2px">
							<form method="get" action="listeTypeDemande.dsi">
								<input type="submit" name="bouton" value="changer" />
								<input type="hidden" name="typeDemande" value="<%= tmp.getType() %>"/>
								<% if(tmp.isAccordDG()){%>
								<input type="hidden" name="valeur" value="0"/>
								<%} else {%>
								<input type="hidden" name="valeur" value="1"/>
								<%} %>
							</form>
						</td>
						
					</tr>
						
				<%		
					}
				%>
					
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

	<jsp:include page="../basDePage.jsp"></jsp:include>
</body>
</html>