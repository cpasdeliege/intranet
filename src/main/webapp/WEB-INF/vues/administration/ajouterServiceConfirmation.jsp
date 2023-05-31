<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration - gestion des
	services</title>
</head>
<%@ page
	import="be.cpasdeliege.intranet.informatique.controler.formulaire.*"%>
<%
	FormulaireService service = (FormulaireService) request.getAttribute("formulaireServiceAjouter");
%>
<body class="body">
	<jsp:include page="../entete.jsp"></jsp:include>
	<div>
		<hr>
		&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0"
			alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
		<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a
			class="menu_contextuel" href="index.admin">administration</a> <img
			height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a
			class="menu_contextuel" href="gestionServices.admin">gestion des
			services</a> <img height="12px" src="images/fleche_droite.jpg" border="0"
			alt=" - "><a class="menu_contextuel">ajout d'un service</a>
		<hr>
	</div>
	<br>
	<table cellpadding="0" cellspacing="0" class="tableau_container">
		<tr height="25">
			<td></td>
			<td>
				<table>
					<tr>
						<td class="titre_tableau">Confirmation - Ajout d'un service</td>
						<td></td>
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
				<table class="tableau_liste">
					<%--  <tr>
					<td align="right">Pole : </td>
					<td>
						<select>
							<option>-</option>
							<c:forEach items="${listePole}" var="pole">
								<c:choose>
									<c:when test="${formulaireServiceAjouter.pole == pole.nom}">
										<option selected>${pole.nom}</option>
									</c:when>
									<c:otherwise>
										<option>${pole.nom}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>	
				</tr>
				<tr>
					<td align="right">département : </td>
					<td>
						<select>
							<option>-</option>
							<c:forEach items="${listeDepartement}" var="departement">
								<c:choose>
									<c:when test="${formulaireServiceAjouter.departement == departement.nom}">
										<option selected>${departement.nom}</option>
									</c:when>
									<c:otherwise>
										<option>${departement.nom}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>	
				</tr>
				--%>
					<tr>
						<td align="right">nom :</td>
						<td class="texte_tableau_confirmation">${ formulaireServiceAjouter.nom }</td>
					</tr>
					<tr>
						<td align="right">adresse :</td>
						<td class="texte_tableau_confirmation">${ formulaireServiceAjouter.adresse }</td>
					</tr>
					<tr>
						<td align="right">numéro :</td>
						<td class="texte_tableau_confirmation">${ formulaireServiceAjouter.numero }</td>
					</tr>
					<tr>
						<td align="right">code postal :</td>
						<td class="texte_tableau_confirmation">${ formulaireServiceAjouter.codePostal }</td>
					</tr>
					<tr>
						<td align="right">localité :</td>
						<td class="texte_tableau_confirmation">${ formulaireServiceAjouter.localite }</td>
					</tr>
					<tr>
						<td align="right">localisation :</td>
						<td class="texte_tableau_confirmation">${ formulaireServiceAjouter.localisation }</td>
					</tr>
					<tr>
						<td align="right">fax :</td>
						<td class="texte_tableau_confirmation">${ formulaireServiceAjouter.fax }</td>
					</tr>
					<tr>
						<td align="right">email service :</td>
						<td class="texte_tableau_confirmation">${ formulaireServiceAjouter.emailService }</td>
					</tr>
					<tr>
						<td align="right" valign="top">remarques :</td>
						<td class="texte_tableau_confirmation"><%=service.getRemarque().replaceAll("\r\n", "<br>")%></td>
					</tr>
					<tr>
						<td></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td><a href="formulaireServiceAjouter.admin"><img
								alt="retour" src="images/administration/retour.jpg" border="0"></a>
						</td>
						<td align="right">
							<form method="get" action="ajouterService.admin">
								<input type="submit" value="ajouter" />
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
	<jsp:include page="../basDePage.jsp"></jsp:include>
</body>
</html>