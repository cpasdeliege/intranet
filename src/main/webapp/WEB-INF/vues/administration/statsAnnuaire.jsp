<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration - rapports &amp; statistiques</title>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="rapports.admin?requete=accueil">rapports &amp; statistiques</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">statistiques annuaire</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="60px"><img src="images/administration/statistiques.png" border="0" width="50px"></td>
					<td class="titre_tableau">Statistiques annuaire</td>
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
			<table cellspacing="5">
				<tr>
					<th></th>
					<th align="right">pages</th>
					<th align="right">ordinateurs</th>
				</tr>
				<tr>
					<td width="120">Total</td>
					<td width="80" align="right">${statistiquesAnnuaire.pagesTotal }</td>
					<td width="120" align="right">${statistiquesAnnuaire.ordinateursTotal }</td>
				</tr>
				<tr>
					<td>Ajourd'hui</td>
					<td align="right">${statistiquesAnnuaire.pagesAujourdhui }</td>
					<td align="right">${statistiquesAnnuaire.ordinateursAujourdhui }</td>
				</tr>
				<tr>
					<td>Hier</td>
					<td align="right">${statistiquesAnnuaire.pagesHier }</td>
					<td align="right">${statistiquesAnnuaire.ordinateursHier }</td>
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