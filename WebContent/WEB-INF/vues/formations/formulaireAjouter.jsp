<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="cpas.css" type="text/css">
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="indexAdmin.formations">administration des formations</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">ajouter une formation</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container">
	<tr height="25">
		<td></td>
		<td>
			<table>
				<tr>
					<td class="titre_tableau">Ajout d'une formation</td>
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
			<form method="post" name="formulaire">
			<table>
				<tr>
					<td align="right" valign="top">intitulé : </td>
					<td><textarea cols="80" rows="3" name="intitule">${ formulaireFormationAjouter.intitule }</textarea></td>
				</tr>
				<tr>
					<td align="right">typologie : </td>
					<td>
						<select name="typologie" size="1" />
							<c:forEach items="${listeTypeFormation}" var="typeFormation">
								<option>${typeFormation.nom}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right" valign="top">objectif : </td>
					<td><textarea cols="80" rows="3" name="objectif">${ formulaireFormationAjouter.objectif }</textarea></td>
				</tr>
				<tr>
					<td align="right" valign="top">synthèse : </td>
					<td><textarea cols="80" rows="3" name="synthese">${ formulaireFormationAjouter.synthese }</textarea></td>
				</tr>
				<tr>
					<td align="right" valign="top">méthodologie : </td>
					<td><textarea cols="80" rows="3" name="methodologie">${ formulaireFormationAjouter.methodologie }</textarea></td>
				</tr>
				<tr>
					<td align="right" valign="top">opérateur : </td>
					<td><textarea cols="80" rows="3" name="operateur">${ formulaireFormationAjouter.operateur }</textarea></td>
				</tr>
				<tr>
					<td align="right" valign="top">personne visée : </td>
					<td><textarea cols="80" rows="3" name="personneVisee">${ formulaireFormationAjouter.personneVisee }</textarea></td>
				</tr>
				<tr>
					<td align="right" valign="top">rapport formation : </td>
					<td><textarea cols="80" rows="3" name="rapportFormation">${ formulaireFormationAjouter.rapportFormation }</textarea></td>
				</tr>
				<tr>
					<td align="right" valign="top">département : </td>
					<td><textarea cols="80" rows="3" name="departement">${ formulaireFormationAjouter.departement }</textarea></td>
				</tr>
				<tr>
					<td align="right" valign="top">service : </td>
					<td><textarea cols="80" rows="3" name="service">${ formulaireFormationAjouter.service }</textarea></td>
				</tr>
				<tr>
					<td align="right" valign="top">durée : </td>
					<td><textarea cols="80" rows="3" name="duree">${ formulaireFormationAjouter.duree }</textarea></td>
				</tr>
				<tr>
					<td align="right" valign="top">lieu : </td>
					<td><textarea cols="80" rows="3" name="lieu">${ formulaireFormationAjouter.lieu }</textarea></td>
				</tr>
				<tr>
					<td align="right" valign="top">année : </td>
					<td><input type="text" maxlength="200" name="annee" value="${ formulaireFormationAjouter.annee }"/></td>
				</tr>
				<tr>
					<td align="right" valign="top">rapport : </td>
					<td><textarea cols="80" rows="20" name="rapport"/>${ formulaireFormationAjouter.rapport }</textarea></td>
				</tr>
				<tr>
					<td></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
						<a href="${retour}"><img alt="retour" src="images/administration/retour.jpg" border="0"></a>
					</td>
					<td align="right"><input type="submit" value="ajouter"/></td>
				</tr>
			</table>
			<input type="hidden" name="_intitule"/>
			<input type="hidden" name="_objectif"/>
			<input type="hidden" name="_synthese"/>
			<input type="hidden" name="_methodologie"/>
			<input type="hidden" name="_operateur"/>
			<input type="hidden" name="_personneVisee"/>
			<input type="hidden" name="_rapportFormation"/>
			<input type="hidden" name="_typologie"/>
			<input type="hidden" name="_departement"/>
			<input type="hidden" name="_service"/>
			<input type="hidden" name="_duree"/>
			<input type="hidden" name="_lieu"/>
			<input type="hidden" name="_annee"/>
			<input type="hidden" name="_rapport"/>
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
