<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="cpas.css" type="text/css">
<script type="text/javascript">
<!--
	
//-->
</script>
<script language="JavaScript" src="javascript/calendar_db.js"></script>
<link rel="stylesheet" href="calendar.css">
<title>Intranet CPAS de Liège - administration - gestion du
	planning</title>
</head>
<body class="body">
	<jsp:include page="../entete.jsp"></jsp:include>
	<div>
		<hr>
		&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0"
			alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
		<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a
			class="menu_contextuel" href="index.admin">administration</a> <img
			height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a
			class="menu_contextuel" href="gestionPlanning.admin">gestion du
			planning</a> <img height="12px" src="images/fleche_droite.jpg" border="0"
			alt=" - "><a class="menu_contextuel">ajouter un tâche</a>
		<hr>
	</div>
	<br>
	<p align="center" style="color: red">
		${erreurFormulaireTacheAjouter}</p>
	<table cellpadding="0" cellspacing="0" class="tableau_container">
		<tr height="25">
			<td></td>
			<td>
				<table>
					<tr>
						<td class="titre_tableau">Ajouter une tâche</td>
						<td></td>
					</tr>
				</table>
			</td>
			<td></td>
		</tr>
		<tr height="25">
			<td width="22"
				background="images/administration/cadre/area_top_left.gif"></td>
			<td background="images/administration/cadre/area_top.gif"></td>
			<td width="22"
				background="images/administration/cadre/area_top_right.gif"></td>
		</tr>
		<tr>
			<td background="images/administration/cadre/area_left.gif"></td>
			<td>
				<form method="post" name="formulaire">
					<table>
						<%
							List<String> listeHeure = (List<String>) request.getAttribute("listeHeure");
							List<String> listeMinute = (List<String>) request.getAttribute("listeMinute");
							String incident = (String) request.getSession().getAttribute("incident");
							String heureIncident = (String) request.getSession().getAttribute("heureIncident");
							String minuteIncident = (String) request.getSession().getAttribute("minuteIncident");
						%>
						<tr>
							<td colspan="2">
								<p>Si cette tâche est ouverte suite à un incident, veuillez
									cocher la case had hoc et indiquer l'heure effective de
									l'incident.</p>
							</td>
						</tr>
						<tr>
							<td align="right">incident :</td>
							<td><INPUT type="checkbox" name="incident"
								<%if (incident != null && incident.equals("on")) {%> checked
								<%}%>> à &nbsp; <select name="heureIncident" size="1">
									<option>-</option>
									<%
										for (int i = 0; i < listeHeure.size(); i++) {
											if (heureIncident != null && listeHeure.get(i).equals(heureIncident)) {
									%>
									<option selected><%=listeHeure.get(i)%></option>
									<%
										} else {
									%>
									<option><%=listeHeure.get(i)%></option>
									<%
										}
										}
									%>
							</select> H <select name="minuteIncident" size="1">
									<option>-</option>
									<%
										for (int i = 0; i < listeMinute.size(); i++) {
											if (minuteIncident != null && listeMinute.get(i).equals(minuteIncident)) {
									%>
									<option selected><%=listeMinute.get(i)%></option>
									<%
										} else {
									%>
									<option><%=listeMinute.get(i)%></option>
									<%
										}
										}
									%>
							</select> M</td>
						</tr>
						<tr>
							<td colspan="2">
								<hr>
							</td>
						</tr>





						<tr>
							<td align="right">service :</td>
							<td><select name="service" size="1" />
								<option>-</option> <c:forEach items="${listeService}"
									var="service">
									<c:choose>
										<c:when
											test="${formulaireTacheAjouter.service == service.nom}">
											<option selected>${service.nom}</option>
										</c:when>
										<c:otherwise>
											<option>${service.nom}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach> </select></td>
						</tr>
						<tr>
							<td align="right">employé :</td>
							<td><select name="personnel" size="1" />
								<option>-</option> <c:forEach items="${listeEmploye}"
									var="employe">
									<c:choose>
										<c:when test="${formulaireTacheAjouter.personnel == employe}">
											<option selected>${employe}</option>
										</c:when>
										<c:otherwise>
											<option>${employe}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach> </select></td>
						</tr>
						<tr>
							<td align="right">ordinateur :</td>
							<td><select name="ordinateur" size="1" />
								<option>-</option> <c:forEach items="${listeOrdinateur}"
									var="ordi">
									<c:choose>
										<c:when
											test="${formulaireTacheAjouter.ordinateur == ordi.nom}">
											<option selected>${ordi.nom}</option>
										</c:when>
										<c:otherwise>
											<option>${ordi.nom}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach> </select></td>
						</tr>
						<tr>
							<td align="right">échéance :</td>
							<td><input type="text" name="echeance"
								value="${ formulaireTacheAjouter.echeance }" /> <script
									language="JavaScript">
									new tcal({
										'formname' : 'formulaire',
										'controlname' : 'echeance'
									});
								</script></td>
						</tr>
						<tr>
							<td align="right">type :</td>
							<td><select name="type" size="1" />
								<option>-</option> <c:forEach items="${listeType}" var="type">
									<c:choose>
										<c:when test="${formulaireTacheAjouter.type == type}">
											<option selected>${type}</option>
										</c:when>
										<c:otherwise>
											<option>${type}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach> </select></td>
						</tr>
						<tr>
							<td align="right">assignée à :</td>
							<td><select name="personnelInfo" size="1" />
								<option>-</option> <c:forEach items="${listePersonnelInfo}"
									var="employe">
									<option>${employe}</option>
								</c:forEach> </select></td>
						</tr>
						<tr>
							<td align="right">titre :</td>
							<td><input type="text" size="60" name="titre"
								value="${ formulaireTacheAjouter.titre }" /></td>
						</tr>
						<tr>
							<td align="right" valign="top">tâche :</td>
							<td><textarea cols="60" rows="10" name="travail" />${description}</textarea></td>
						</tr>
						<tr>
							<td></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><a href="${retour}"><img alt="retour"
									src="images/administration/retour.jpg" border="0"></a></td>
							<td align="right"><input type="submit" value="ajouter" /></td>
						</tr>
					</table>
					<input type="hidden" name="numeroSerie"
						value="${ imprimante.numeroSerie }" /> <input type="hidden"
						name="_ordinateur" /> <input type="hidden" name="_employe" /> <input
						type="hidden" name="_service" /> <input type="hidden"
						name="_personnelInfo" /> <input type="hidden" name="_remarque" />
					<input type="hidden" name="_type" />
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

	<jsp:include page="../basDePage.jsp"></jsp:include>
</body>
</html>