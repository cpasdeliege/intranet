<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="be.cpasdeliege.intranet.informatique.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="cpas.css" type="text/css">
<script type="text/javascript">
<!--

//-->
</script>
<title>Intranet CPAS de Liège - administration - gestion des ordinateurs</title>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="gestionOrdinateurs.admin">gestion des ordinateurs</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">ajout d'un ordinateur</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container">
	<tr height="25">
		<td></td>
		<td>
			<table>
				<tr>
					<td class="titre_tableau">Ajout d'un ordinateur</td>
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
					<td align="right">nom : </td>
					<td><input type="text" maxlength="200" name="nom" value="${ formulaireOrdinateurAjouter.nom }"/> <input checked="checked" type="checkbox" name="actif" /> actif </td> 
				</tr>
				<tr>
					<td align="right">service : </td>
					<td>
						<select name="service" size="1" />
							<option></option>
							<c:forEach items="${listeService}" var="service">
								<c:choose>
									<c:when test="${formulaireOrdinateurAjouter.service == service.nom}">
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
				<tr>
					<td align="right">numéro prise : </td>
					<td><input type="text" maxlength="200" name="prise" value="${ formulaireOrdinateurAjouter.prise }"/></td>
				</tr>
				<tr>
					<td align="right">numéro CPAS2000 : </td>
					<td><input type="text" maxlength="200" name="numCpas2000" value="${ formulaireOrdinateurAjouter.numCpas2000 }"/></td>
				</tr>
				<tr>
					<td align="right">système d'exlpoitation : </td>
					<td>
						<select name="systemeExploitation" size="1" />
							<c:forEach items="${typeOS}" var="type">
								<c:choose>
									<c:when test="${formulaireOrdinateurAjouter.systemeExploitation == type.nom}">
										<option selected>${type.nom}</option>
									</c:when>
									<c:otherwise>
										<option>${type.nom}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">carte mère : </td>
					<td><input type="text" maxlength="200" name="carteMere" value="${ formulaireOrdinateurAjouter.carteMere }"/></td>
				</tr>
				<tr>
					<td align="right">processeur : </td>
					<td><input type="text" maxlength="200" name="processeur" value="${ formulaireOrdinateurAjouter.processeur }"/></td>
				</tr>
				<tr>
					<td align="right">mémoire ram : </td>
					<td><input type="text" maxlength="200" name="memoireRam" value="${ formulaireOrdinateurAjouter.memoireRam }"/></td>
				</tr>
				<tr>
					<td align="right">carte graphique : </td>
					<td><input type="text" maxlength="200" name="carteGraphique" value="${ formulaireOrdinateurAjouter.carteGraphique }"/></td>
				</tr>
				<tr>
					<td align="right">disque dur : </td>
					<td><input type="text" maxlength="200" name="disqueDur" value="${ formulaireOrdinateurAjouter.disqueDur }"/></td>
				</tr>
				<tr>
					<td align="right">graveur : </td>
					<td>
						<select name="graveur" size="1" />
							<c:forEach items="${typeGraveur}" var="graveur">
								<c:choose>
									<c:when test="${formulaireOrdinateurAjouter.graveur == graveur.graveur}">
										<option selected>${graveur.graveur}</option>
									</c:when>
									<c:otherwise>
										<option>${graveur.graveur}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">carte réseau : </td>
					<td><input type="text" maxlength="200" name="carteReseau" value="${ formulaireOrdinateurAjouter.carteReseau }"/></td>
				</tr>
				<tr>
					<td align="right">écran : </td>
					<td>
						<select name="ecran" size="1" />
							<c:forEach items="${typeEcran}" var="ecran">
								<c:choose>
									<c:when test="${formulaireOrdinateurAjouter.ecran == ecran.ecran}">
										<option selected>${ecran.ecran}</option>
									</c:when>
									<c:otherwise>
										<option>${ecran.ecran}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
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
			<input type="hidden" name="_nom"/>
			<input type="hidden" name="_service"/>
			<input type="hidden" name="_prise"/>
			<input type="hidden" name="_systemeExploitation"/>
			<input type="hidden" name="_carteMere"/>
			<input type="hidden" name="_processeur"/>
			<input type="hidden" name="_memoireRam"/>
			<input type="hidden" name="_carteGraphique"/>
			<input type="hidden" name="_disqueDur"/>
			<input type="hidden" name="_carteReseau"/>
			<input type="hidden" name="_ecran"/>
			<input type="hidden" name="_graveur"/>
			<input type="hidden" name="_actif"/>
			</form>
		</td>
		<td background="images/administration/cadre/area_right.gif"></td>
	</tr>
	<tr height="25">
		<td background="images/administration/cadre/area_bottom_left.gif"></td>
		<td background="images/administration/cadre/area_bottom.gif"></td>
		<td background="images/administration/cadre/area_bottom_right.gif"></td>
	</tr>
	<tr height="25">
		<td></td>
		<td>
			<table>
				<tr>
					<td class="menu_contextuel">Configuration rapide</td>
					<td>
					</td>
				</tr>
			</table>
		</td>
		<td></td>
	</tr>
	<tr height="25">
		<td></td>
		<td>
			<table>
	<%
	List liste = (List)request.getAttribute("typeOrdinateur");
	for(int i = 0; i < liste.size(); i = i + 4) {
		TypeOrdinateur colonne1 = null;
		TypeOrdinateur colonne2 = null;
		TypeOrdinateur colonne3 = null;
		TypeOrdinateur colonne4 = null;
		try {
			colonne1 = (TypeOrdinateur)liste.get(i);
		} catch(Exception e) {
			colonne1 = null; 
		} 
		
		try {
			colonne2 = (TypeOrdinateur)liste.get(i + 1);
		} catch(Exception e) {
			colonne2 = null; 
		}
		
		try {
			colonne3 = (TypeOrdinateur)liste.get(i + 2);
		} catch(Exception e) {
			colonne3 = null; 
		}
		
		try {
			colonne4 = (TypeOrdinateur)liste.get(i + 3);
		} catch(Exception e) {
			colonne4 = null; 
		}
	
	%>
				<tr>
					<td valign="top" align="center">
						<%
						if(colonne1 != null) {
						%>
						<table border="0">
							<tr>
								<td>
									<input type="button" value="<%= colonne1.getCarteMere() %>" onclick=' 
										document.forms["formulaire"].elements["carteMere"].value="<%= colonne1.getCarteMere() %>" 
										document.forms["formulaire"].elements["processeur"].value="<%= colonne1.getProcesseur() %>" 
										document.forms["formulaire"].elements["memoireRam"].value="<%= colonne1.getMemoireRam() %>" 
										document.forms["formulaire"].elements["carteGraphique"].value="<%= colonne1.getCarteGraphique() %>" 
										document.forms["formulaire"].elements["disqueDur"].value="<%= colonne1.getDisqueDur() %>" 
										document.forms["formulaire"].elements["carteReseau"].value="<%= colonne1.getCarteReseau() %>" '/>
								</td>
							</tr>
							<tr>
								<td align="center">
									<img width="90px" alt="pas de photo" src="images/administration/ordi/<%= colonne1.getCarteMere() %>.jpg" border="0">
								</td>
							</tr>
						</table>
						<%
						}
						%>
					</td>
					<td valign="top" align="center">
						<%
						if(colonne2 != null) {
						%>
						<table border="0">
							<tr>
								<td>
									<input type="button" value="<%= colonne2.getCarteMere() %>" onclick=' 
										document.forms["formulaire"].elements["carteMere"].value="<%= colonne2.getCarteMere() %>" 
										document.forms["formulaire"].elements["processeur"].value="<%= colonne2.getProcesseur() %>" 
										document.forms["formulaire"].elements["memoireRam"].value="<%= colonne2.getMemoireRam() %>" 
										document.forms["formulaire"].elements["carteGraphique"].value="<%= colonne2.getCarteGraphique() %>" 
										document.forms["formulaire"].elements["disqueDur"].value="<%= colonne2.getDisqueDur() %>" 
										document.forms["formulaire"].elements["carteReseau"].value="<%= colonne2.getCarteReseau() %>" '/>
								</td>
							</tr>
							<tr>
								<td align="center">
									<img width="90px" alt="pas de photo" src="images/administration/ordi/<%= colonne2.getCarteMere() %>.jpg" border="0">
								</td>
							</tr>
						</table>
						<%
						}
						%>
					</td>
					<td valign="top" align="center">
						<%
						if(colonne3 != null) {
						%>
						<table border="0">
							<tr>
								<td>
									<input type="button" value="<%= colonne3.getCarteMere() %>" onclick=' 
										document.forms["formulaire"].elements["carteMere"].value="<%= colonne3.getCarteMere() %>" 
										document.forms["formulaire"].elements["processeur"].value="<%= colonne3.getProcesseur() %>" 
										document.forms["formulaire"].elements["memoireRam"].value="<%= colonne3.getMemoireRam() %>" 
										document.forms["formulaire"].elements["carteGraphique"].value="<%= colonne3.getCarteGraphique() %>" 
										document.forms["formulaire"].elements["disqueDur"].value="<%= colonne3.getDisqueDur() %>" 
										document.forms["formulaire"].elements["carteReseau"].value="<%= colonne3.getCarteReseau() %>" '/>
								</td>
							</tr>
							<tr>
								<td align="center">
									<img width="90px" alt="pas de photo" src="images/administration/ordi/<%= colonne3.getCarteMere() %>.jpg" border="0">
								</td>
							</tr>
						</table>
						<%
						}
						%>
					</td>
					<td valign="top" align="center">
						<%
						if(colonne4 != null) {
						%>
						<table border="0">
							<tr>
								<td>
									<input type="button" value="<%= colonne4.getCarteMere() %>" onclick=' 
										document.forms["formulaire"].elements["carteMere"].value="<%= colonne4.getCarteMere() %>" 
										document.forms["formulaire"].elements["processeur"].value="<%= colonne4.getProcesseur() %>" 
										document.forms["formulaire"].elements["memoireRam"].value="<%= colonne4.getMemoireRam() %>" 
										document.forms["formulaire"].elements["carteGraphique"].value="<%= colonne4.getCarteGraphique() %>" 
										document.forms["formulaire"].elements["disqueDur"].value="<%= colonne4.getDisqueDur() %>" 
										document.forms["formulaire"].elements["carteReseau"].value="<%= colonne4.getCarteReseau() %>" '/>
								</td>
							</tr>
							<tr>
								<td align="center">
									<img width="90px" alt="pas de photo" src="images/administration/ordi/<%= colonne4.getCarteMere() %>.jpg" border="0">
								</td>
							</tr>
						</table>
						<%
						}
						%>
					</td>				
				</tr>
	<%
	}
	%>
	
	
			</table>
		</td>
		<td></td>
	</tr>
</table>
	
<jsp:include page="../basDePage.jsp" ></jsp:include>
</body>
</html>