<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="java.util.*" %>
<%@ page import="be.cpasdeliege.intranet.DemServInfo.*" %>
<%@page import="be.cpasdeliege.intranet.informatique.model.*"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="site.css" type="text/css">
</head>
<body>
<% 	
	DemServInf dsi = (DemServInf)request.getAttribute("dsi"); 
	PrivilegeInformatique privilege = (PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique");
	List<TypeDemandeDsi> listeType = (List<TypeDemandeDsi>)request.getAttribute("listeType");
	String typeDemande = (String)request.getAttribute("typeDemande");
%>
<%@page import="be.cpasdeliege.intranet.DemServInfo.model.*"%>
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<% if(privilege.isAdministrateur()) { %>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<% } %>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.dsi">demande service informatique</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">spliter une demande</a>
<hr>
</div>
<table cellpadding="0" cellspacing="0"  class="tableau_container" width="1120px">

	<tr height="25">
		<td width="25px"></td>
		<td>
			<table width="100%">
				<tr>
					<td class="titre_tableau">Spliter une demande</td>
					<td align="right">
					</td>
					<td width="65px" align="right">
					</td>
				</tr>
			</table>
		</td>
		<td></td>
	</tr>
</table>
<form method="post" action="splitDemande.dsi" >
<table cellpadding="0" cellspacing="0"  class="tableau_container" >
	<tr height="25">
		<td width="22" background="images/administration/cadre/area_top_left.gif"></td>
		<td background="images/administration/cadre/area_top.gif"></td>
		<td width="22" background="images/administration/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/administration/cadre/area_left.gif"></td>
		<td>
			<table>
				
				<tr>
					<td align="right">date demande : </td>
					<td>
						<input disabled="disabled" type="text" name="dateDemande" value="${dsi.dateDemande}"/>
					</td>
				</tr>
				<tr>
					<td align="right">auteur demande : </td>
					<td>
						<input disabled="disabled" type="text" size="30" maxlength="200" name="titre" value="${dsi.nom} ${dsi.prenom}"/>
					</td>
					<%-- <td>${dsi.typeDemande}</td> --%>
				</tr>
				<tr>
					<td align="right">type de demande : </td>
					<td>
						<select name="typeDemande" size="1" />
						<%
							for(int i = 0; i < listeType.size(); i++) {
								if(listeType.get(i).getType().equals(typeDemande)) {
						%>		
								<option selected><%= listeType.get(i).getType() %></option>
						<%
								} else {
						%>
								<option ><%= listeType.get(i).getType() %></option>
						<%
								}
							}
						%>
						</select>
					</td>
					<%-- <td>${dsi.typeDemande}</td> --%>
				</tr>
				<tr>
					<td align="right">responsable : </td>
					<td>
						<input disabled="disabled" type="text" size="30" maxlength="200" name="titre" value="${dsi.nomChef} ${dsi.prenomChef}"/>
					</td>
					<%-- <td>${dsi.typeDemande}</td> --%>
				</tr>
				<tr>
					<td align="right">service concerné : </td>
					<td>
						<select disabled="disabled" name="service" size="1" />
								<option >${dsi.service}</option>
						</select>
					</td>
					<%-- <td>${dsi.typeDemande}</td> --%>
				</tr>
				<tr>
					<td align="right">échéance souhaitée: </td>
					<td>
						<input disabled="disabled" type="text" name="echeanceSouhaitee" value="${dsi.dateExecSouhaitee}"/>
						<!-- <script language="JavaScript">
						new tcal ({
							'formname': 'formulaire',
							'controlname': 'echeanceSouhaitee'
						});
						</script> -->

					</td>
				</tr>
				<tr>
					<td align="right">Titre : </td>
					<td><input type="text" size="79" maxlength="200" name="titre" value="${dsi.titre}"/></td>
					<%-- <td>${dsi.titre}</td> --%>
				</tr>
				<tr>
					<td align="right">description : </td>
					<td><textarea cols="80" rows="20" name="description"/>${dsi.description}</textarea></td>
				</tr>
				<tr>
					<td><%-- <a href="${retour}"><img alt="retour" src="images/administration/retour.jpg" border="0"></a> --%></td>
					<td align="right"></td>
				</tr>
			</table>
			<input type="hidden" name="idDemandes" value="${dsi.idDemandes}"/>
			</form>
		</td>
		<td background="
		images/administration/cadre/area_right.gif"></td>
	</tr>
	
	
	
	<tr>
		<td background="images/administration/cadre/area_left.gif"></td>
		<td>
			
				
					
					<table width="100%">
					
					
					<tr>
						<td><a href="${retour}"><img alt="retour" src="images/administration/retour.jpg" border="0"></a></td>
						<td align="right">
						<input type="submit"  name="action" value="Spliter"/></td>
						<input type="hidden" name="idDemande" value="${dsi.idDemandes}"/>
					</tr>
					
				</table>
			
		</td>
		<td background="images/administration/cadre/area_right.gif"></td>
	</tr>
	
	<tr height="25">
		<td background="images/administration/cadre/area_bottom_left.gif"></td>
		<td background="images/administration/cadre/area_bottom.gif"></td>
		<td background="images/administration/cadre/area_bottom_right.gif"></td>
	</tr>
	<tr>
		<td colspan="2"><br></td>
	</tr>
	
	
	</table>
	</form>




	<jsp:include page="../basDePage.jsp" ></jsp:include>
</body>
</html>