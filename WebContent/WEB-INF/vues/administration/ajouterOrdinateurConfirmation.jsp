<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration - gestion des ordinateurs</title>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="gestionOrdinateurs.admin">gestion des ordinateurs</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">ajout d'un ordinateur</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container">
	<tr height="25">
		<td></td>
		<td>
			<table>
				<tr>
					<td class="titre_tableau">Confirmation - Ajout d'un ordinateur</td>
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
					<td class="texte_tableau_confirmation">${ formulaireOrdinateurAjouter.nom }</td>
				</tr>
				<tr>
					<td align="right">service : </td>
					<td class="texte_tableau_confirmation">${ formulaireOrdinateurAjouter.service }</td>
				</tr>
				<tr>
					<td align="right">numéro prise : </td>
					<td class="texte_tableau_confirmation">${ formulaireOrdinateurAjouter.prise }</td>
				</tr>
				<tr>
					<td align="right">système d'exlpoitation : </td>
					<td class="texte_tableau_confirmation">${ formulaireOrdinateurAjouter.systemeExploitation }</td>
				</tr>
				<tr>
					<td align="right">carte mère : </td>
					<td class="texte_tableau_confirmation">${ formulaireOrdinateurAjouter.carteMere }</td>
				</tr>
				<tr>
					<td align="right">processeur : </td>
					<td class="texte_tableau_confirmation">${ formulaireOrdinateurAjouter.processeur }</td>
				</tr>
				<tr>
					<td align="right">mémoire ram : </td>
					<td class="texte_tableau_confirmation">${ formulaireOrdinateurAjouter.memoireRam }</td>
				</tr>
				<tr>
					<td align="right">carte graphique : </td>
					<td class="texte_tableau_confirmation">${ formulaireOrdinateurAjouter.carteGraphique }</td>
				</tr>
				<tr>
					<td align="right">disque dur : </td>
					<td class="texte_tableau_confirmation">${ formulaireOrdinateurAjouter.disqueDur }</td>
				</tr>
				<tr>
					<td align="right">graveur : </td>
					<td class="texte_tableau_confirmation">${ formulaireOrdinateurAjouter.graveur }</td>
				</tr>
				<tr>
					<td align="right">carte réseau : </td>
					<td class="texte_tableau_confirmation">${ formulaireOrdinateurAjouter.carteReseau }</td>
				</tr>
				<tr>
					<td align="right">écran : </td>
					<td class="texte_tableau_confirmation">${ formulaireOrdinateurAjouter.ecran }</td>
				</tr>
				<tr>
					<td></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
						<a href="formulaireOrdinateurAjouter.admin"><img alt="retour" src="images/administration/retour.jpg" border="0"></a>
					</td>
					<td align="right">
						<form method="get" action="ajouterOrdinateur.admin">
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