<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%--  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
    <%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
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
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="gestionPersonnel.admin?nom=${formulairePersonnelModifier.nom}&amp;prenom=${formulairePersonnelModifier.prenom}">${formulairePersonnelModifier.nom } ${formulairePersonnelModifier.prenom }</a>
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
					<td class="titre_tableau">Modification d'un employé</td>
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
					<td align="right">nom : </td>
					<td><input disabled="disabled" type="text" maxlength="200" name="nomx" value="${ formulairePersonnelModifier.nom }"/></td>
				</tr>
				<tr>
					<td align="right">prénom : </td>
					<td><input disabled="disabled" type="text" maxlength="200" name="prenomx" value="${ formulairePersonnelModifier.prenom }"/></td>
				</tr>
				<tr>
					<td align="right">code Agent : </td>
					<td><input type="text" maxlength="200" name="codeAS" value="${ formulairePersonnelModifier.codeAS }"/></td>
				</tr>
				<tr>
					<td align="right">login Windows : </td>
					<td><input type="text" maxlength="200" name="loginWindows" value="${ formulairePersonnelModifier.loginWindows }"/></td>
				</tr>
				<tr>
					<td align="right">login AS400 : </td>
					<td><input type="text" maxlength="200" name="loginAS400" value="${ formulairePersonnelModifier.loginAS400 }"/></td>
				</tr>
				<tr>
					<td align="right">login CPAS2000 : </td>
					<td><input type="text" maxlength="200" name="loginCPAS2000" value="${ formulairePersonnelModifier.loginCPAS2000 }"/></td>
				</tr>
				<tr>
					<td align="right">login NCC : </td>
					<td><input type="text" maxlength="200" name="loginNCC" value="${ formulairePersonnelModifier.loginNCC }"/></td>
				</tr>
				<tr>
					<td align="right">login GRH : </td>
					<td><input type="text" maxlength="200" name="loginGRH" value="${ formulairePersonnelModifier.loginGRH }"/></td>
				</tr>
				<tr>
					<td align="right">login publilink : </td>
					<td><input type="text" maxlength="200" name="loginPublilink" value="${ formulairePersonnelModifier.loginPublilink }"/></td>
				</tr>
				<tr>
					<td align="right">mdp publilink : </td>
					<td><input type="text" maxlength="200" name="mdpPublilink" value="${ formulairePersonnelModifier.mdpPublilink }"/></td>
				</tr>
				<tr>
					<td align="right">email : </td>
					<td><input type="text" maxlength="200" name="email" value="${ formulairePersonnelModifier.email }"/></td>
				</tr>
				<tr>
					<td align="right">mdp email : </td>
					<td><input type="text" maxlength="200" name="mdpEmail" value="${ formulairePersonnelModifier.mdpEmail }"/></td>
				</tr>
				<tr>
					<td align="right">alias email : </td>
					<td><input type="text" maxlength="200" name="emailAlias" value="${ formulairePersonnelModifier.emailAlias }"/></td>
				</tr>
				<tr>
					<td align="right">accès wifi : </td>
					<td>
						<select name="wifi" size="1" />
						<c:choose>
                            <c:when test="${formulairePersonnelModifier.wifi == 0}">
                                <option selected value="0">non</option>
                                <option value="1">oui</option>
                            </c:when>
                            <c:otherwise>
                                <option selected value="1">oui</option>
                                <option value="0">non</option>
                            </c:otherwise>
                        </c:choose>
					</td>
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
			<input type="hidden" name="nom" value="${ formulairePersonnelModifier.nom }"/>
			<input type="hidden" name="prenom" value="${ formulairePersonnelModifier.prenom }"/>
			<input type="hidden" name="pathPhoto" value="${ formulairePersonnelModifier.pathPhoto }"/>
			<input type="hidden" name="_nom"/>
			<input type="hidden" name="_prenom"/>
			<input type="hidden" name="_loginWindows"/>
			<input type="hidden" name="_loginAS400"/>
			<input type="hidden" name="_loginCPAS2000"/>
			<input type="hidden" name="_loginNCC"/>
			<input type="hidden" name="_loginGRH"/>
			<input type="hidden" name="_loginPublilink"/>
			<input type="hidden" name="_mdpPublilink"/>
			<input type="hidden" name="_email"/>
			<input type="hidden" name="_emailAlias"/>
			<input type="hidden" name="_wifi"/>
			<input type="hidden" name="_pathPhoto"/>
			<input type="hidden" name="_codeAS"/>
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