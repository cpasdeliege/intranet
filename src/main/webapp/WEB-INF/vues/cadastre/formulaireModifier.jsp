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

<%

List<TypeMarche> listeTypeMarche = (List<TypeMarche>)request.getAttribute("listeTypeMarche");
Marche marche = (Marche)request.getAttribute("marche");

%>


<body class="bodyCadastre" >
	<jsp:include page="../entete.jsp"></jsp:include>
	<div>
		<hr>
		&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.cad">Cadastre des Marchés Publics</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">Modifier Marché Public</a>
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
					<td class="titre_tableau">Modifier un marché public</td>
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
			<form method="post" action="index.cad?action=modifierMP" name="formulaire" >
			<table>
				<tr>
					<td align="right">type : </td>
					<td>
						<select name="type" size="1" />
								<option>-----> choisir un type</option>
								
								<%
									for(int i = 0; i < listeTypeMarche.size(); i++) {
										if(listeTypeMarche.get(i).getId_type_marche().equals(marche.getId_type_marche())) {
								%>		
										<option selected value="<%= listeTypeMarche.get(i).getId_type_marche() %>"><%= listeTypeMarche.get(i).getType_marche() %></option>
								<%
										} else {
								%>
										<option value="<%= listeTypeMarche.get(i).getId_type_marche() %>"><%= listeTypeMarche.get(i).getType_marche() %></option>
								<%
										}
									}
								%>
								
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">Intitulé : </td>
					<td><input type="text" size="100" maxlength="100" name="intitule" value="${marche.intitule}"/></td>
				</tr>
				<tr>
					<td align="right">Référence : </td>
					<td><input disabled="disabled" type="text" size="100" maxlength="100" name="" value="${marche.reference}"/></td>
				</tr>
				<tr>
					<td align="right">Adjudicataire : </td>
					<td><input type="text" size="100" maxlength="100" name="adjudicataire" value="${marche.adjudicataire}"/></td>
				</tr>
				<tr>
					<td align="right">Date BP : </td>
					<td>
						<input type="text" name="date_bp" value="${marche.date_BP}"/>
						<script language="JavaScript">
						new tcal ({
							'formname': 'formulaire',
							'controlname': 'date_bp'
						});
						</script>

					</td>
				</tr>
				<tr>
					<td align="right">Pouvoir Adjudicateur : </td>
					<td><input type="text" size="100" maxlength="100" name="adjudicateur" value="${marche.adjudicateur}"/></td>
				</tr>
				<tr>
					<td align="right">Adresse : </td>
					<td><input type="text" size="100" maxlength="100" name="adresse" value="${marche.adresse}"/></td>
				</tr>
				<tr>
					<td align="right">Description : </td>
					<td><textarea cols="75" rows="5" name="description"/>${marche.description}</textarea></td>
				</tr>
				<tr>
					<td align="right">Date de début : </td>
					<td>
						<input type="text" name="date_debut" value="${marche.date_debut}"/>
						<script language="JavaScript">
						new tcal ({
							'formname': 'formulaire',
							'controlname': 'date_debut'
						});
						</script>

					</td>
				</tr>
				<tr>
					<td align="right">Date de fin : </td>
					<td>
						<input type="text" name="date_fin" value="${marche.date_fin}"/>
						<script language="JavaScript">
						new tcal ({
							'formname': 'formulaire',
							'controlname': 'date_fin'
						});
						</script>

					</td>
				</tr>
				<tr>
					<td align="right"></td>
					<td>&nbsp;</td>
				</tr><tr>
					<td align="right"></td>
					<td>&nbsp;</td>
				</tr>

				
				
				
				<tr>
					<td><a href="${retour}"><img alt="retour" src="images/administration/retour.jpg" border="0"></a></td>
					<td align="right"><input type="submit" value="envoyer"/></td>
				</tr>
			</table>
			<input type="hidden" name="id_marche_public" value="${marche.id_marche_public}">
			<input type="hidden" name="reference" value="${marche.reference}">
			<input type="hidden" name="flag" value="${marche.flag}">
			<input type="hidden" name="retour" value="${retour}">
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