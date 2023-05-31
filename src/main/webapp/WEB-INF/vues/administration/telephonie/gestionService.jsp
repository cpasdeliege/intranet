<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration - gestion des services</title>
</head>
<body class="body">
<jsp:include page="../../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="gestionServices.admin">gestion des services</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" >${service.nom }</a>
<hr>
</div>
<br>
<%@ page import="be.cpasdeliege.intranet.informatique.model.*" %>
<%
List liste = (List)request.getAttribute("listePersonnel");
Service service = (Service)request.getAttribute("service");
Departement departement = (Departement)request.getAttribute("departement");
Pole pole = (Pole)request.getAttribute("pole"); 

%>
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="45px"><img src="images/administration/service.png" border="0" width="35px"></td>
					<td class="titre_tableau"><u style="font-weight: bold;">${service.nom}</u> <c:if test="${not empty departement}"> / ${departement.nom} </c:if> <c:if test="${not empty pole}"> / ${pole.nom} </c:if></td>
					<td align="right">
						<form method="get" action="formulaireServiceModifier.admin">
							<input type="submit" value="modifier le service"/>
							<input type="hidden" name="service" value="${ fn:escapeXml(service.nom) }"/>
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
				<tr>
					<td>
						<table>
							<tr>
								<td>adresse : </td>
								<td class="texte_cadre_gestion">${service.adresse}, ${service.numero}</td>
							</tr>
							<tr>
								<td></td>
								<td class="texte_cadre_gestion">${service.codePostal} ${service.localite}</td>
							</tr>
							<tr>
								<td>localisation : </td>
								<td class="texte_cadre_gestion">${service.localisation}</td>
							</tr>
							<tr>
								<td>fax : </td>
								<td class="texte_cadre_gestion">${service.fax}</td>
							</tr>
							<tr>
								<td>email service : </td>
								<td class="texte_cadre_gestion">${service.emailService}</td>
							</tr>
							<tr>
								<td valign="top">remarques : </td>
								<td class="texte_cadre_gestion"><%= service.getRemarque().replaceAll("\r\n", "<br>") %></td>
							</tr>
						</table>
					</td>
					<td align="right">
						<table>
							<tr>
								<td>
									<%	if(liste.size() > 1) { %>														
											<%= liste.size() %> employés
									<%	} else { %>
											<%= liste.size() %> employé
									<%	} %>
								</td>
							</tr>
						</table>
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
	<tr height="25">
		<td></td>
		<td>
			
		</td>
		<td></td>
	</tr>
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td class="titre_tableau"><a name="listePersonnel">Liste du personnel</a></td>
					<td align="right">
						<form method="get" action="formulaireServicePersonnelAssigner.admin">
							<input type="submit" value="assigner un employé"/>
							<input type="hidden" name="service" value="${ fn:escapeXml(service.nom) }"/>
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
					<th>Nom</th>
					<th>Prénom</th>
					<th>Fonction</th>
					<th></th>
					<th>Téléphone</th>
				</tr>
				<% int i = 0; %>
				<c:forEach items="${listePersonnel}" var="employe">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td><a class="lien_tableau" href="gestionPersonnel.admin?nom=${employe.nom}&amp;prenom=${employe.prenom}">${employe.nom}</a></td>
						<td>${employe.prenom}</td>
						<td>${employe.fonction}</td>
						<td>${employe.rem}</td>
						<td>${employe.telephone}</td>
						<td width="30px" align="right"><a href="formulaireServicePersonnelModifier.admin?service=${ fn:escapeXml(employe.service)}&amp;nom=${employe.nom}&amp;prenom=${employe.prenom}&amp;ancre=listePersonnel"><img src="images/administration/modifier.png" border="0" alt="modifier"></a></td>
						<td width="30px" align="right" onclick=""><a href="desassignerServicePersonnel.admin?service=${ fn:escapeXml(employe.service) }&amp;nom=${employe.nom}&amp;prenom=${employe.prenom}&amp;ancre=listePersonnel"><img src="images/administration/supprimer.gif" border="0"></a></td>
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
<jsp:include page="../../basDePage.jsp" ></jsp:include>
</body>
</html>