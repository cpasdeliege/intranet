<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="cpas.css" type="text/css">
<script type="text/javascript">
<!--

//-->
</script>
<title>Intranet CPAS de Liège - administration - gestion des imprimantes</title>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="gestionImprimantes.admin">gestion des imprimantes</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">ajout d'une imprimante</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container">
	<tr height="25">
		<td></td>
		<td>
			<table>
				<tr>
					<td class="titre_tableau">Ajout d'une imprimante</td>
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
					<td align="right">numero de série : </td>
					<td><input type="text" maxlength="200" name="numeroSerie" value="${ formulaireImprimanteAjouter.numeroSerie }"/></td>
				</tr>
				<tr>
					<td align="right">type : </td>
					<td>
						<select name="type" size="1" />
							<c:forEach items="${typeImprimante}" var="type">
								<c:choose>
									<c:when test="${formulaireImprimanteAjouter.type == type.type}">
										<option selected>${type.type}</option>
									</c:when>
									<c:otherwise>
										<option>${type.type}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">ordinateur local : </td>
					<td>
						<select name="ordinateurLocal" size="1" />
							<option></option>
							<c:forEach items="${listeOrdinateur}" var="ordinateur">
								<c:choose>
									<c:when test="${formulaireImprimanteAjouter.ordinateurLocal == ordinateur.nom}">
										<option selected>${ordinateur.nom}</option>
									</c:when>
									<c:otherwise>
										<option>${ordinateur.nom}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<!--  
				<tr>
					<td align="right">service : </td>
					<td>
						<select name="service" size="1" />
							<c:forEach items="${listeService}" var="service">
								<c:choose>
									<c:when test="${formulaireImprimanteAjouter.service == service.nom}">
										<option selected>${service.nom}</option>
									</c:when>
									<c:otherwise>
										<option>${service.nom}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				-->
				<tr>
					<td></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
						<a href="${retour}"><img alt="retour" src="images/administration/retour.jpg" border="0"></a>
					</td>
					<td align="right"><input type="submit" value="envoyer"/></td>
				</tr>
			</table>
			<input type="hidden" name="_numeroSerie"/>
			<input type="hidden" name="_type"/>
			<input type="hidden" name="_ordinateurLocal"/>
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