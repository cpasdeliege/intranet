<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration</title>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="gestionEmails.admin">liste des accès internet</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">ajouter un email générique</a>
<hr>
</div>
<br>
<p align="center" style="color: red">
${erreurFormulaireTacheAjouter}
</p>
<table cellpadding="0" cellspacing="0"  class="tableau_container">
	<tr height="25">
		<td></td>
		<td>
			<table>
				<tr>
					<td class="titre_tableau">Ajout d'un email générique</td>
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
					<td align="right">email : </td>
					<td><input type="text" maxlength="200" name="email" value="${ formulaireEmailGeneriqueAjouter.email }"/></td>
				</tr>
				<tr>
					<td align="right">mot de passe : </td>
					<td><input type="text" maxlength="200" name="mdp" value="${ formulaireEmailGeneriqueAjouter.mdp }"/></td>
				</tr>
				<tr>
					<td align="right">alias : </td>
					<td><input type="text" maxlength="200" name="alias" value="${ formulaireEmailGeneriqueAjouter.alias }"/></td>
				</tr>
				<tr>
					<td align="right">login publilink : </td>
					<td><input type="text" maxlength="200" name="loginPublilink" value="${ formulaireEmailGeneriqueAjouter.loginPublilink }"/></td>
				</tr>
				<tr>
					<td align="right">mot de passe publilink : </td>
					<td><input type="text" maxlength="200" name="mdpPublilink" value="${ formulaireEmailGeneriqueAjouter.mdpPublilink }"/></td>
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
			<input type="hidden" name="_email"/>
			<input type="hidden" name="_mdp"/>
			<input type="hidden" name="_alias"/>
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