<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration - gestion des services</title>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="gestionServices.admin">gestion des services</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">ajout d'un service</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container">
	<tr height="25">
		<td></td>
		<td>
			<table>
				<tr>
					<td class="titre_tableau">Ajout d'un service</td>
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
			<form method="post">
				<table>
					<tr>
						<td align="right">Pole : </td>
						<td>
							<select>
								<option>-</option>
								<c:forEach items="${ formulaireServiceAjouter.poles }" var="pole">
									<option selected>${pole.nom}</option>
								</c:forEach>
							</select>
						</td>	
					</tr>
					<tr>
						<td align="right">département : </td>
						<td>
							<select>
								<option>-</option>
								<c:forEach items="${ formulaireServiceAjouter.departements }" var="departement">
									<option>${departement.nom}</option>
								</c:forEach>
							</select>
						</td>	
					</tr>
					<tr>
						<td align="right">nom : </td>
						<td><input type="text" maxlength="200" name="nom" value="${ formulaireServiceAjouter.nom }"/></td>
					</tr>
					<tr>
						<td align="right">adresse : </td>
						<td><input type="text" maxlength="200" name="adresse" value="${ formulaireServiceAjouter.adresse }"/></td>
					</tr>
					<tr>
						<td align="right">numéro : </td>
						<td><input type="text" maxlength="200" name="numero" value="${ formulaireServiceAjouter.numero }"/></td>
					</tr>
					<tr>
						<td align="right">code postal : </td>
						<td><input type="text" maxlength="200" name="codePostal" value="${ formulaireServiceAjouter.codePostal }"/></td>
					</tr>
					<tr>
						<td align="right">localité : </td>
						<td><input type="text" maxlength="200" name="localite" value="${ formulaireServiceAjouter.localite }"/></td>
					</tr>
					<tr>
						<td align="right">localisation : </td>
						<td><input type="text" maxlength="200" name="localisation" value="${ formulaireServiceAjouter.localisation }"/></td>
					</tr>
					<tr>
						<td align="right">fax : </td>
						<td><input type="text" maxlength="200" name="fax" value="${ formulaireServiceAjouter.fax }"/></td>
					</tr>
					<tr>
						<td align="right">email service : </td>
						<td><input type="text" maxlength="200" name="emailService" value="${ formulaireServiceAjouter.emailService }"/></td>
					</tr>
					<tr>
						<td align="right" valign="top">remarques : </td>
						<td><textarea  cols="30" rows="5" name="remarque"/>${ formulaireServiceAjouter.remarque }</textarea></td>
					</tr>
					<tr>
						<td></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td><a href="${retour}"><img alt="retour" src="images/administration/retour.jpg" border="0"></a></td>
						<td align="right"><input type="submit" value="envoyer"/></td>
					</tr>
				</table>
				<input type="hidden" name="_nom"/>
				<input type="hidden" name="_adresse"/>
				<input type="hidden" name="_numero"/>
				<input type="hidden" name="_localite"/>
				<input type="hidden" name="_codePostal"/>
				<input type="hidden" name="_fax"/>
				<input type="hidden" name="_remarque"/>
			</form>
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