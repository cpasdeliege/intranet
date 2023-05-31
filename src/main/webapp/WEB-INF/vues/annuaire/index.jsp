<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page import="java.net.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">annuaire interne</a>
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
					<td>Recherche par mot-clé :</td>
					<td background="images/annuaire/cadre/area_right.gif"></td>
				</tr>
				<tr>
					<td background="images/annuaire/cadre/area_left.gif"></td>
					<td><input size="50" type="text" maxlength="200" name="requete" value=""/></td>
					<td background="images/annuaire/cadre/area_right.gif"></td>
				</tr>
				<tr>
					<td background="images/annuaire/cadre/area_left.gif"></td>
					<td><input type="submit" value="rechercher"/></td>
					<td background="images/annuaire/cadre/area_right.gif"></td>
				</tr>
				<tr height="25">
					<td background="images/annuaire/cadre/area_left.gif"></td>
					<td background="images/annuaire/cadre/area_top.gif"></td>
					<td background="images/annuaire/cadre/area_right.gif"></td>
				</tr>
				<tr>
					<td background="images/annuaire/cadre/area_left.gif"></td>
					<td>Recherche par ordre alphabétique :</td>
					<td background="images/annuaire/cadre/area_right.gif"></td>
				</tr>
				<tr>
					<td background="images/annuaire/cadre/area_left.gif"></td>
					<td>
						<table width="100%">
							<tr>
								<td align="center">
									<a href="ordreAlphabetique.annuaire?lettre=a" class="lien_sous_navigation">A</a>
									<a href="ordreAlphabetique.annuaire?lettre=b" class="lien_sous_navigation">B</a>
									<a href="ordreAlphabetique.annuaire?lettre=c" class="lien_sous_navigation">C</a>
									<a href="ordreAlphabetique.annuaire?lettre=d" class="lien_sous_navigation">D</a>
									<a href="ordreAlphabetique.annuaire?lettre=e" class="lien_sous_navigation">E</a>
									<a href="ordreAlphabetique.annuaire?lettre=f" class="lien_sous_navigation">F</a>
									<a href="ordreAlphabetique.annuaire?lettre=g" class="lien_sous_navigation">G</a>
									<a href="ordreAlphabetique.annuaire?lettre=h" class="lien_sous_navigation">H</a>
									<a href="ordreAlphabetique.annuaire?lettre=i" class="lien_sous_navigation">I</a>
									<a href="ordreAlphabetique.annuaire?lettre=j" class="lien_sous_navigation">J</a>
									<a href="ordreAlphabetique.annuaire?lettre=k" class="lien_sous_navigation">K</a>
									<a href="ordreAlphabetique.annuaire?lettre=l" class="lien_sous_navigation">L</a>
									<a href="ordreAlphabetique.annuaire?lettre=m" class="lien_sous_navigation">M</a>
								</td>
							</tr>
							<tr>
								<td align="center">
									<a href="ordreAlphabetique.annuaire?lettre=n" class="lien_sous_navigation">N</a>
									<a href="ordreAlphabetique.annuaire?lettre=o" class="lien_sous_navigation">O</a>
									<a href="ordreAlphabetique.annuaire?lettre=p" class="lien_sous_navigation">P</a>
									<a href="ordreAlphabetique.annuaire?lettre=q" class="lien_sous_navigation">Q</a>
									<a href="ordreAlphabetique.annuaire?lettre=r" class="lien_sous_navigation">R</a>
									<a href="ordreAlphabetique.annuaire?lettre=s" class="lien_sous_navigation">S</a>
									<a href="ordreAlphabetique.annuaire?lettre=t" class="lien_sous_navigation">T</a>
									<a href="ordreAlphabetique.annuaire?lettre=u" class="lien_sous_navigation">U</a>
									<a href="ordreAlphabetique.annuaire?lettre=v" class="lien_sous_navigation">V</a>
									<a href="ordreAlphabetique.annuaire?lettre=w" class="lien_sous_navigation">W</a>
									<a href="ordreAlphabetique.annuaire?lettre=x" class="lien_sous_navigation">X</a>
									<a href="ordreAlphabetique.annuaire?lettre=y" class="lien_sous_navigation">Y</a>
									<a href="ordreAlphabetique.annuaire?lettre=z" class="lien_sous_navigation">Z</a>
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
												<td> 
													<!-- <a href="services.annuaire?service=${ fn:escapeXml(service.nom) }#debut" class="lien_sous_navigation">${service.nom}</a> -->
													<a href="services.annuaire?service=${ fn:escapeXml(service.nom) }#debut" class="lien_sous_navigation">${service.nom}</a>
													<c:if test = "${service.voip eq 1}">
													  (*)
													</c:if>
												</td>
											</tr>
										</c:forEach>
									</table>
								</td>
								<td>
									<table>
										<c:forEach items="${colonne2}" var="service">
											<tr>
												<td>
													<a href="services.annuaire?service=${fn:escapeXml(service.nom)}#debut" class="lien_sous_navigation">${service.nom}</a>
													<c:if test = "${service.voip eq 1}">
													  (*)
													</c:if>
												</td>
											</tr>
										</c:forEach>
									</table>
								</td>
								<td valign="top">
									<table>
										<c:forEach items="${colonne3}" var="service">
											<tr>
												<td>
													<a href="services.annuaire?service=${fn:escapeXml(service.nom)}#debut" class="lien_sous_navigation">${service.nom}</a>
													<c:if test = "${service.voip eq 1}">
													  (*)
													</c:if>
												</td>
											</tr>
										</c:forEach>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="3" align="right">(*) service en téléphonie VOIP </td>
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