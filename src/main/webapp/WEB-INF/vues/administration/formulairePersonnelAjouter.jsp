<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">ajout d'un employé</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container">
	<tr height="25">
		<td></td>
		<td>
			<table>
				<tr>
					<td class="titre_tableau">Ajout d'un employé</td>
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
			<form method="post" accept-charset="UTF-8">
				<table>
					<tr>
						<td align="right">nom : </td>
						<td><input type="text" accept="UTF-8" maxlength="200" name="nom" value="${ formulairePersonnelAjouter.nom }"/></td>
					</tr>
					<tr>
						<td align="right">prénom : </td>
						<td><input type="text" maxlength="200" name="prenom" value="${ formulairePersonnelAjouter.prenom }"/></td>
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
				<input type="hidden" name="pathPhoto" value=""/>
				<input type="hidden" name="_nom"/>
				<input type="hidden" name="_prenom"/>
				<input type="hidden" name="loginWindows" value=""/>
				<input type="hidden" name="loginAS400" value=""/>
				<input type="hidden" name="loginCPAS2000" value=""/>
				<input type="hidden" name="loginNCC" value=""/>
				<input type="hidden" name="loginGRH" value=""/>
				<input type="hidden" name="loginPublilink" value=""/>
				<input type="hidden" name="mdpPublilink" value=""/>
				<input type="hidden" name="email" value=""/>
				<input type="hidden" name="emailAlias" value=""/>
				<input type="hidden" name="mdpEmail" value=""/>
				<input type="hidden" name="_wifi"/>
				<input type="hidden" name="_pathPhoto"/>
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