<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="gestionService.admin?service=${ fn:escapeXml(formulaireServiceModifier.nom) }">${formulaireServiceModifier.nom }</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">modification</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container">
	<tr height="25">
		<td></td>
		<td>
			<table>
				<tr>
					<td class="titre_tableau">Modification d'un service</td>
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
					<%-- 
					<tr>
						<td align="right">Pole : </td>
						<td>
							<select>
								<option>-</option>
								<c:forEach items="${listePole}" var="pole">
									<c:choose>
										<c:when test="${formulaireServiceModifier.pole == pole.nom}">
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
										<c:when test="${formulaireServiceModifier.departement == departement.nom}">
											<option selected>${departement.nom}</option>
										</c:when>
										<c:otherwise>
											<option>${departement.nom}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</td>	
					</tr>--%>
					<tr>
						<td align="right">nom : </td>
						<td><input disabled="disabled" maxlength="200" type="text" name="nompx" value="${ fn:escapeXml(formulaireServiceModifier.nom) }"/></td>
					</tr>
					<tr>
						<td align="right">adresse : </td>
						<td><input type="text" maxlength="200" name="adresse" value="${ formulaireServiceModifier.adresse }"/></td>
					</tr>
					<tr>
						<td align="right">numéro : </td>
						<td><input type="text" maxlength="200" name="numero" value="${ formulaireServiceModifier.numero }"/></td>
					</tr>
					<tr>
						<td align="right">code postal : </td>
						<td><input type="text" maxlength="200" name="codePostal" value="${ formulaireServiceModifier.codePostal }"/></td>
					</tr>
					<tr>
						<td align="right">localité : </td>
						<td><input type="text" maxlength="200" name="localite" value="${ formulaireServiceModifier.localite }"/></td>
					</tr>
					<tr>
						<td align="right">localisation : </td>
						<td><input type="text" maxlength="200" name="localisation" value="${ formulaireServiceModifier.localisation }"/></td>
					</tr>
					<tr>
						<td align="right">fax : </td>
						<td><input type="text" maxlength="200" name="fax" value="${ formulaireServiceModifier.fax }"/></td>
					</tr>
					<tr>
						<td align="right">email service : </td>
						<td><input type="text" maxlength="200" name="emailService" value="${ formulaireServiceModifier.emailService }"/></td>
					</tr>
					<tr>
						<td align="right" valign="top">remarques : </td>
						<td><textarea  cols="30" rows="5" name="remarque"/>${ formulaireServiceModifier.remarque }</textarea></td>
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
				<!-- <input type="hidden" name="nom" value="encodeURI(${ fn:escapeXml(formulaireServiceModifier.nom)})"/> -->
				<input type="hidden" name="nom" value="${ fn:escapeXml(formulaireServiceModifier.nom)}"/>
				<input type="hidden" name="_nom"/>
				<input type="hidden" name="_adresse"/>
				<input type="hidden" name="_numero"=/>
				<input type="hidden" name="_localite"=/>
				<input type="hidden" name="_codePostal"=/>
				<input type="hidden" name="_fax"/>
				<input type="hidden" name="_remarque"=/>
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