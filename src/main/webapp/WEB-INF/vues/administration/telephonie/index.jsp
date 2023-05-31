<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Intranet CPAS de Liège - administration</title>
<link rel="stylesheet" href="cpas.css" type="text/css">
</head>
<body class="body">
<jsp:include page="../../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">administration</a>
<hr>
</div>
<br>
<table align="center" width="80%">
	<tr>
		<td valign="top">
			<table  border="0">
				<tr>
					<td width="60px" align="center"><img src="images/administration/service.png" border="0" width="50px"></td>
					<td><a class="texte_menu_principal" href="gestionServices.admin">Gestion des Services</a></td>
				</tr>
				<tr>
					<td width="60px" align="center"><img src="images/administration/personnel.png" border="0" width="50px"></td>
					<td><a class="texte_menu_principal" href="gestionPersonnels.admin">Gestion du Personnel</a></td>
				</tr>
				<tr>
					<td width="60px" align="center"><img src="images/administration/fonction.png" border="0" width="50px"></td>
					<td><a class="texte_menu_principal" href="gestionFonctions.admin">Gestion des Fonctions</a></td>
				</tr>
				
			</table>
		</td>
		<td valign="top">
			<table border="0" align="right">
				<tr>
					<td width="60px" align="center"><img src="images/administration/pdf.png" border="0" width="50px"></td>
					<td>
						<span class="texte_menu_principal">Générer PDF Annuaire Interne</span>
						<br><a target="_blank" class="texte_menu_principal" href="genererPDF.admin?type=service&saut=false">service</a>
						<a style="font-size: small" target="_blank" class="texte_menu_principal" href="genererPDF.admin?type=service&saut=true">(avec saut de page)</a> / 
						<a target="_blank" class="texte_menu_principal" href="genererPDF.admin?type=alpha&saut=false">alphabétique</a>
						<a style="font-size: small" target="_blank" class="texte_menu_principal" href="genererPDF.admin?type=alpha&saut=true">(avec saut de page)</a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>



<jsp:include page="../../basDePage.jsp" ></jsp:include>
</body>
</html>
