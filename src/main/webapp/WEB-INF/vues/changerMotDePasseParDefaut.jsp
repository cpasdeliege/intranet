<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="site.css" type="text/css">

</head>
<body class="body">
<jsp:include page="entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<hr>
</div>
<table align="center" width="80%" cellpadding="0" cellspacing="0">
	<tr height="25">
		<td></td>
		<td class="titre_tableau">Bienvenue sur l'intranet du CPAS de Liège </td>
		<td></td>
	</tr>
	<tr height="25">
		<td width="22" background="images/cadre/area_top_left.gif"></td>
		<td background="images/cadre/area_top.gif"></td>
		<td width="22" background="images/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/cadre/area_left.gif"></td>
		<td>
		
		<p>Ceci est surement votre première connexion.</p>
		<p> Vous vous êtes connecté avec le mot de passe "cpas" qui est le mot de passe par défaut. Pour des raisons de sécurité, nous vous demandons de bien vouloir modifier votre mot de passe. </p>
		
		<p>Pour ce faire veuillez utiliser le formulaire ci-dessous : <p>
		<%@ page import="be.cpasdeliege.intranet.informatique.model.*" %>
		<%
		Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur");
		if(utilisateur == null) {
			utilisateur = new Utilisateur();
		}
		%>
		<form action="changerMotDePasse.htm">
			<input type="hidden" name="login" value="<%= utilisateur.getLogin() %>"/>
			<table>
				<tr>
					<td align="right" style="font-style: italic">
						taper votre mot de passe : 
					</td>
					<td>
						<input type="text" maxlength="20" name="mdp" value=""/>
					</td>
					<td>
						<input type="submit" value="changer mot de passe"/>
					</td>
				</tr>
			</table>
		</form>
		<br>
		<p>Vous pouvez à tout moment changer votre mot de passe via l'interface qui si trouve en haut à droite sur la page lorsque vous êtes connecté.</p>

		</td>
		<td background="images/cadre/area_right.gif"></td>
	</tr>
	
	<tr height="25">
		<td background="images/cadre/area_bottom_left.gif"></td>
		<td background="images/cadre/area_bottom.gif"></td>
		<td background="images/cadre/area_bottom_right.gif"></td>
	</tr>
</table>
<jsp:include page="basDePage.jsp" ></jsp:include>
</body>
</html>