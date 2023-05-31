<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="annuaire.css" type="text/css">
<script type="text/javascript">
<!--

function setFocus()
{
 document.formulaire.requete.focus();
}

//-->
</script>
</head>
<body class="body" onload="setFocus()">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">zone formations</a>
<hr>
</div>
<form method="get" action="recherche.annuaire" name="formulaire">
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
	<tr>
		<td>
			<table align="center" cellpadding="0" cellspacing="0">
				<tr height="25">
					<td width="22" background="images/annuaire/cadre/area_top_left.gif"></td>
					<td background="images/annuaire/cadre/area_top.gif"></td>
					<td width="22" background="images/annuaire/cadre/area_top_right.gif"></td>
				</tr>
				<tr>
					<td background="images/annuaire/cadre/area_left.gif"></td>
					<td>Recherche par type de formation :</td>
					<td background="images/annuaire/cadre/area_right.gif"></td>
				</tr>
				<tr>
					<td background="images/annuaire/cadre/area_left.gif"></td>
					<td>
						<table width="100%">
							<tr>
								<td>
									<table>
										<c:forEach items="${colonneType1}" var="type">
											<tr>
												<td><a href="typeFormations.formations?typeFormation=${type.nom}#debut" class="lien_sous_navigation">${type.nom}</a></td>
											</tr>
										</c:forEach>
									</table>
								</td>
								<td>
									<table>
										<c:forEach items="${colonneType2}" var="type">
											<tr>
												<td><a href="typeFormations.formations?typeFormation=${type.nom}#debut" class="lien_sous_navigation">${type.nom}</a></td>
											</tr>
										</c:forEach>
									</table>
								</td>
								<td valign="top">
									<table>
										<c:forEach items="${colonneType3}" var="type">
											<tr>
												<td><a href="typeFormations.formations?typeFormation=${type.nom}#debut" class="lien_sous_navigation">${type.nom}</a></td>
											</tr>
										</c:forEach>
									</table>
								</td>
							</tr>
						</table>
					</td>
					<td background="images/annuaire/cadre/area_right.gif"></td>
				</tr>
				<tr height="25">
					<td background="images/annuaire/cadre/area_left.gif"></td>
					<td background="images/annuaire/cadre/area_top.gif"></td>
					<td background="images/annuaire/cadre/area_right.gif"></td>
				</tr>
				<tr>
					<td background="images/annuaire/cadre/area_left.gif"></td>
					<td>Recherche par service :</td>
					<td background="images/annuaire/cadre/area_right.gif"></td>
				</tr>
				<tr>
					<td background="images/annuaire/cadre/area_left.gif"></td>
					<td>
						<table width="100%">
							<tr>
								<td>
									<table>
										<c:forEach items="${colonne1}" var="service">
											<tr>
												<td><a href="services.formations?service=${service.nom}#debut" class="lien_sous_navigation">${service.nom}</a></td>
											</tr>
										</c:forEach>
									</table>
								</td>
								<td>
									<table>
										<c:forEach items="${colonne2}" var="service">
											<tr>
												<td><a href="services.formations?service=${service.nom}#debut" class="lien_sous_navigation">${service.nom}</a></td>
											</tr>
										</c:forEach>
									</table>
								</td>
								<td valign="top">
									<table>
										<c:forEach items="${colonne3}" var="service">
											<tr>
												<td><a href="services.formations?service=${service.nom}#debut" class="lien_sous_navigation">${service.nom}</a></td>
											</tr>
										</c:forEach>
									</table>
								</td>
							</tr>
						</table>
					</td>
					<td background="images/annuaire/cadre/area_right.gif"></td>
				</tr>
				<tr height="25">
					<td background="images/annuaire/cadre/area_bottom_left.gif"></td>
					<td background="images/annuaire/cadre/area_bottom.gif"></td>
					<td background="images/annuaire/cadre/area_bottom_right.gif"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
<jsp:include page="../basDePage.jsp" ></jsp:include>
</body>
</html>