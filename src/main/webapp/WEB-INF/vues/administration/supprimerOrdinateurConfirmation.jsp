<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">suppression d'un ordinateur</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container">
	<tr height="25">
		<td></td>
		<td>
			<table>
				<tr>
					<td class="titre_tableau">Confirmation - Suppression d'un ordinateur</td>
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
					<td class="texte_tableau_confirmation">${ ordinateur.nom }</td>
				</tr>
				<tr>
					<td align="right">service : </td>
					<td class="texte_tableau_confirmation">${ ordinateur.service }</td>
				</tr>
				<tr>
					<td align="right">numéro prise : </td>
					<td class="texte_tableau_confirmation">${ ordinateur.numeroPrise }</td>
				</tr>
				<tr>
					<td align="right">système d'exlpoitation : </td>
					<td class="texte_tableau_confirmation">${ ordinateur.systemeExploitation }</td>
				</tr>
				<tr>
					<td align="right">carte mère : </td>
					<td class="texte_tableau_confirmation">${ ordinateur.carteMere }</td>
				</tr>
				<tr>
					<td align="right">processeur : </td>
					<td class="texte_tableau_confirmation">${ ordinateur.processeur }</td>
				</tr>
				<tr>
					<td align="right">mémoire ram : </td>
					<td class="texte_tableau_confirmation">${ ordinateur.memoireRam }</td>
				</tr>
				<tr>
					<td align="right">carte graphique : </td>
					<td class="texte_tableau_confirmation">${ ordinateur.carteGraphique }</td>
				</tr>
				<tr>
					<td align="right">disque dur : </td>
					<td class="texte_tableau_confirmation">${ ordinateur.disqueDur }</td>
				</tr>
				<tr>
					<td align="right">graveur : </td>
					<td class="texte_tableau_confirmation">${ ordinateur.graveur }</td>
				</tr>
				<tr>
					<td align="right">carte réseau : </td>
					<td class="texte_tableau_confirmation">${ ordinateur.carteReseau }</td>
				</tr>
				<tr>
					<td align="right">écran : </td>
					<td class="texte_tableau_confirmation">${ ordinateur.ecran }</td>
				</tr>
				<tr>
					<td></td>
					<td>&nbsp;</td>
				</tr>
				<tr>			
					<td>
						<a href="${retour}"><img alt="retour" src="images/administration/retour.jpg" border="0"></a>
					</td>
					<td align="right">
						<form method="get" action="supprimerOrdinateur.admin">
							<input type="hidden" name="ordinateur" value="${ordinateur.nom}"/>
							<input type="submit" value="supprimer"/>
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