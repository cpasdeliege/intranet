<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="annuaire.css" type="text/css">
<script type="text/javascript">
<!--

function setFocus()
{
 document.formulaire.requete.focus();
}

//-->
</script>
</head>
<body class="body" onload="setFocus()">
<jsp:include page="../entete.jsp"></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0"
	alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a
	class="menu_contextuel" href="index.formations">zone formations</a> <img
	height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a
	class="menu_contextuel">recherche par service</a>
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
		<td align="center"><a class="lien_navigation"
			href="typeFormations.formations">recherche par type de formation</a>
		- <a class="lien_navigation_actif">service</a></td>
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
				<td>
				<table>
					<c:forEach items="${colonne1}" var="service">
						<tr>
							<td><a
								href="services.formations?service=${service.nom}#debut"
								class="lien_sous_navigation">${service.nom}</a></td>
						</tr>
					</c:forEach>
				</table>
				</td>
				<td>
				<table>
					<c:forEach items="${colonne2}" var="service">
						<tr>
							<td><a
								href="services.formations?service=${service.nom}#debut"
								class="lien_sous_navigation">${service.nom}</a></td>
						</tr>
					</c:forEach>
				</table>
				</td>
				<td valign="top">
				<table>
					<c:forEach items="${colonne3}" var="service">
						<tr>
							<td><a
								href="services.formations?service=${service.nom}#debut"
								class="lien_sous_navigation">${service.nom}</a></td>
						</tr>
					</c:forEach>
				</table>
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
<%@ page import="be.cpasdeliege.intranet.informatique.model.*"%>
<%@ page import="be.cpasdeliege.intranet.informatique.model.dao.*"%>
<%@ page import="java.util.*"%>
<%
	Service service = (Service) request.getAttribute("service");
	List listFormations = (List) request
			.getAttribute("listeFormations");
	if (service != null) {
		if (listFormations != null) {
%>


<table cellpadding="0" cellspacing="0" class="tableau_container_centre">
	<tr>
		<td></td>
		<td>
		<table width="100%">
			<tr class="titre_tableau" valign="bottom">
				<td width="50px"><img src="images/formation.png" border="0"
					width="40px"></td>
				<td><a name="debut">${service.nom }</a></td>

			</tr>
		</table>
		</td>
		<td></td>
	</tr>
	<tr height="25">
		<td width="22"
			background="images/administration/cadre/area_top_left.gif"></td>
		<td background="images/administration/cadre/area_top.gif"></td>
		<td width="22"
			background="images/administration/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/administration/cadre/area_left.gif"></td>
		<td>
		<table class="tableau_liste" cellspacing="0">
			<tr class="titre_colonne_tableau_liste">
				<th>Intitulé</th>
				<th>Typologie</th>
			</tr>
			<%
				int i = 0;
			%>
			<c:forEach items="${listeFormations}" var="formation">
				<%
					i++;
				%>
				<%
					if (i % 2 == 0) {
				%>
				<tr class="ligne_paire" >
					<%
						} else {
					%>
				
				<tr class="ligne_impaire">
					<%
						}
					%>
					<td valign="top"><a class="lien_tableau"
						href="formation.formations?idFormation=${formation.idFormation}">${formation.intitule}</a></td>
					<td valign="top">${formation.typologie}</td>
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


<%
	} else {
%>
<table cellpadding="0" cellspacing="0" class="tableau_container_centre">
	<tr>
		<td></td>
		<td>
		<table width="100%">
			<tr class="titre_tableau" valign="bottom">
				<td width="50px"><img src="images/formation.png" border="0"
					width="40px"></td>
				<td><a name="debut">${service.nom }</a></td>

			</tr>
		</table>
		</td>
		<td></td>
	</tr>
	<tr height="25">
		<td width="22"
			background="images/administration/cadre/area_top_left.gif"></td>
		<td background="images/administration/cadre/area_top.gif"></td>
		<td width="22"
			background="images/administration/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/administration/cadre/area_left.gif"></td>
		<td>
		<table class="tableau_liste" cellspacing="0">
			
			<tr>
				<td colspan="2" align="center"
					style="font-weight: bold; font-style: italic">&nbsp;</td>
			</tr>
			<tr>
				<td class="ligne_impaire" colspan="2" align="center"
					style="font-weight: bold; font-style: italic">pas de formation
				pour ce service</td>
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



<%
	}
	}
%>
<jsp:include page="../basDePage.jsp"></jsp:include>
</body>
</html>