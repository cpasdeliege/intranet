<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration - gestion du planning</title>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="gestionPlanning.admin">gestion du planning</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">liste des incidents</a>
<hr>
</div>
<br>


	
	
	
	
	
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">	
	<%
		int i = 0;
		List tmp = (List)request.getAttribute("incidentsOuverts");
		int nbreTache = tmp.size();
	%>
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="60px"><img src="images/administration/tache.png" border="0" width="50px"></td>
					<td class="titre_tableau">Liste des incidents ouverts (<%= nbreTache %>)</td>
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
					<th align="center"><a href="gestionPlanning.admin?action=incidents&amp;triOuvert=idPlanning">num</a></th>
					<th align="center">titre</th>
					<th align="center"><a href="gestionPlanning.admin?action=incidents&amp;triOuvert=service">service</a></th>
					<th align="center">contact</th>
					<th align="center"><a href="gestionPlanning.admin?action=incidents&amp;triOuvert=echeance">echéance</a></th>
					<th align="center"><a href="gestionPlanning.admin?action=incidents&amp;triOuvert=nomInfo">assigné à</a></th>
				</tr>
				<% i = 0; %>
				<c:forEach items="${incidentsOuverts}" var="tache">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td><a class="lien_tableau" href="gestionTache.admin?idPlanning=${tache.idPlanning}" name="${tache.idPlanning}">${tache.idPlanning}</a></td>
						<td><a class="lien_tableau" href="gestionTache.admin?idPlanning=${tache.idPlanning}">${tache.titre}</a></td>
						<td><span>${tache.service}</span></td>
						<td>${tache.nom}</td>
						<td>${tache.echeance}</td>
						<c:choose>
                            <c:when test="${tache.dsi == true}">
                                <td align="right" style="color: red;">${tache.nomInfo}</td>
                            </c:when>
                            <c:otherwise>
                                <td align="right">${tache.nomInfo}</td>
                            </c:otherwise>
                        </c:choose>
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
	
	
	<tr height="25">
		<td></td>
		<td></td>
		<td></td>
	</tr>
</table>	
	
	
	
	
	
	
	
	
	
	
	
	<%
	List tmp3 = (List)request.getAttribute("incidentsFermes");
	int nbreTache3 = tmp3.size();
%>
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="60px"><img src="images/kthememgr.png" border="0" width="50px"></td>
					<td class="titre_tableau"><a name="enAttente">Liste des incidents fermés (<%= nbreTache3 %>)</a></td>
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
					<th align="center"><a href="gestionPlanning.admin?action=incidents&amp;triFerme=idPlanning#enAttente">num</a></th>
					<th align="center">titre</th>
					<th align="center"><a href="gestionPlanning.admin?action=incidents&amp;triFerme=service#enAttente">service</a></th>
					<th align="center">contact</th>
					<th align="center"><a href="gestionPlanning.admin?action=incidents&amp;triFerme=dateFin#enAttente">date fin</a></th>
				</tr>
				<% int i3 = 0; %>
				<c:forEach items="${incidentsFermes}" var="tache">
				<% i3++; %>
				<% if(i3%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td><a class="lien_tableau" href="gestionTache.admin?idPlanning=${tache.idPlanning}" name="${tache.idPlanning}">${tache.idPlanning}</a></td>
						<td><a class="lien_tableau" href="gestionTache.admin?idPlanning=${tache.idPlanning}">${tache.titre}</a></td>
						<td>${tache.service}</td>
						<td>${tache.nom}</td>
						<td>${tache.dateFin}</td>
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
	
	
	<tr height="25">
		<td></td>
		<td></td>
		<td></td>
	</tr>
	

</table>
<jsp:include page="../basDePage.jsp" ></jsp:include>
</body>
</html>