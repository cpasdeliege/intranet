<%@ page language="java" contentType="text/html;charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration - gestion du personnel</title>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="gestionPersonnels.admin">gestion du personnel</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">ajout d'un employé</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container">
	<tr height="25">
		<td></td>
		<td>
			<table>
				<tr>
					<td class="titre_tableau">Confirmation - Ajout d'un employé</td>
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
					<td align="right">nom : </td>
					<td class="texte_tableau_confirmation">${ formulairePersonnelAjouter.nom }</td>
				</tr>
				<tr>
					<td align="right">prénom : </td>
					<td class="texte_tableau_confirmation">${ formulairePersonnelAjouter.prenom }</td>
				</tr>
				<tr>
					<td></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
						<a href="formulairePersonnelAjouter.admin"><img alt="retour" src="images/administration/retour.jpg" border="0"></a>
						<!-- 
						<form method="get" action="formulairePersonnelAjouter.admin">
							<input type="hidden" name="nomPersonnel" value="${formulairePersonnelAjouter.nom}"/>
							<input type="hidden" name="prenomPersonnel" value="${formulairePersonnelAjouter.prenom}"/>
							<input type="submit" value="retour"/>
						</form>
						 -->
					</td>
					<td align="right">
						<form method="get" action="ajouterPersonnel.admin">
							<input type="submit" value="ajouter"/>
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