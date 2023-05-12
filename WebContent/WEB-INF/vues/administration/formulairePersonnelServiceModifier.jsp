<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
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
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="gestionPersonnel.admin?nom=${nom }&amp;prenom=${prenom }">${nom } ${prenom }</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" >modifier un employé</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container">
	<tr height="25">
		<td></td>
		<td>
			<table>
				<tr>
					<td class="titre_tableau">Modifier un employé</td>
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
					<td align="right">service : </td>
					<td>
						<select disabled="disabled" name="" size="1" />
							<option >${formulairePersonnelServiceModifier.service}</option>
						</select>
						<input type="hidden" name="" value="${formulairePersonnelServiceModifier.service}"/>
					</td>
				</tr>
				<tr>
					<td align="right">fonction : </td>
					<td>
						<select name="fonction" size="1" />
							<c:forEach items="${typeFonction}" var="fonction">
								<c:choose>
									<c:when test="${formulairePersonnelServiceModifier.fonction == fonction.fonction}">
										<option selected>${fonction.fonction}</option>
									</c:when>
									<c:otherwise>
										<option >${fonction.fonction}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">rem : </td>
					<td><input type="text" maxlength="200" name="rem" value="${ formulairePersonnelServiceModifier.rem }"/></td>
				</tr>
				<tr>
					<td align="right">téléphone : </td>
					<td><input type="text" maxlength="200" name="telephone" value="${ formulairePersonnelServiceModifier.telephone }"/></td>
				</tr>
				<tr>
					<td align="right">extension voip : </td>
					<td><input type="text" maxlength="200" name="extension" value="${ formulairePersonnelServiceModifier.extension }"/></td>
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
			<input type="hidden" name="_service"/>
			<input type="hidden" name="_fonction"/>
			<input type="hidden" name="_telephone"/>
			<input type="hidden" name="_extension"/>
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