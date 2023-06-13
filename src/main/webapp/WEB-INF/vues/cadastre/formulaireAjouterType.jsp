<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="be.cpasdeliege.intranet.cadastre.model.*"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="site.css" type="text/css">

<script type="text/javascript">
<!--

//-->
</script>
<script language="JavaScript" src="javascript/calendar_db.js"></script>
<link rel="stylesheet" href="calendar.css">

<style type="text/css">
#gauche {
	float: left; /* Le cadre sort du flux */
	/* border: 2px solid #DAA520; */ 
	width: 595px;
}

#droite {
	float: right; /* ou margin-left:202px;  */
	/* border: 2px solid gray; */
	width: 595px;
}

#principal {
	width: 1200px;
	/* border: 2px solid gray; */
	margin-left: auto;
	margin-right: auto;
}

.tabIndex {
	width: 95%;
}
</style>

</head>



<body class="bodyCadastre" >
	<jsp:include page="../entete.jsp"></jsp:include>
	<div>
		<hr>
		&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.cad">Cadastre des Marchés Publics</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">Ajouter un type de Marché Public</a>
		<hr>
	</div>
<div id="principal">
	

<p align="center" style="color: red">
${erreur}
</p>
<table cellpadding="0" cellspacing="0"  class="tableau_container">
	<tr height="25">
		<td></td>
		<td>
			<table>
				<tr>
					<td class="titre_tableau">Ajouter un type de marché public</td>
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
			<!-- <form method="post" action="index.cad?action=ajouterType" name="formulaire" >  -->
			<form method="post" enctype="multipart/form-data" action="utilsTache.admin">
			<table>
				<tr>
					<td align="right">Intitulé : </td>
					<td><input type="text" size="100" maxlength="100" name="type_marche" value="${typeMarche.type_marche}"/></td>
				</tr>
				<tr>
					<td align="right">Image : </td>
					<td>
						<input type="hidden" name="action" value="ajouterTypeCadastre">
						<input type="file" name="image" />
					</td>
				</tr>
				<tr>
					<td align="right"></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="right"></td>
					<td>&nbsp;</td>
				</tr>

				
				
				
				<tr>
					<td><a href="${retour}"><img alt="retour" src="images/administration/retour.jpg" border="0"></a></td>
					<td align="right"><input type="submit" value="Ajouter"/></td>
				</tr>
			</table>
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
	
	
</div>

<jsp:include page="../basDePage.jsp"></jsp:include>
</body>
</html>