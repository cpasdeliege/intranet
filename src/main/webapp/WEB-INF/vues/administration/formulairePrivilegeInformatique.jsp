<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
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
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="gestionPersonnel.admin?nom=${personnel.nom }&amp;prenom=${personnel.prenom }">${personnel.nom } ${personnel.prenom }</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" >modifier privilèges informatique</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container">
	<tr height="25">
		<td></td>
		<td>
			<table>
				<tr>
					<td class="titre_tableau">Modifier les privilèges informatique</td>
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
					<td align="right">administrateur : </td>
					<td>
						<c:choose>
							<c:when test='${formulairePrivilegeInformatique.administrateur == true }'>
								<input name="administrateur" value="true" type="checkbox" checked="checked"/>
							</c:when>
							<c:otherwise>
								<input name="administrateur" value="true" type="checkbox"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td align="right">téléphonie : </td>
					<td>
						<c:choose>
							<c:when test='${formulairePrivilegeInformatique.telephonie == true }'>
								<input name="telephonie" value="true" type="checkbox" checked="checked"/>
							</c:when>
							<c:otherwise>
								<input name="telephonie"  value="true" type="checkbox"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td align="right">formations : </td>
					<td>
						<c:choose>
							<c:when test='${formulairePrivilegeInformatique.formations == true }'>
								<input name="formations" value="true" type="checkbox" checked="checked"/>
							</c:when>
							<c:otherwise>
								<input name="formations"  value="true" type="checkbox"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				
				<tr>
					<td align="right">dsi utilisateur : </td>
					<td>
						<c:choose>
							<c:when test='${formulairePrivilegeInformatique.dsiUser == true }'>
								<input name="dsiUser" value="true" type="checkbox" checked="checked"/>
							</c:when>
							<c:otherwise>
								<input name="dsiUser"  value="true" type="checkbox"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td align="right">dsi chef d�p. : </td>
					<td>
						<c:choose>
							<c:when test='${formulairePrivilegeInformatique.dsiChef == true }'>
								<input name="dsiChef" value="true" type="checkbox" checked="checked"/>
							</c:when>
							<c:otherwise>
								<input name="dsiChef"  value="true" type="checkbox"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td align="right">dsi informatique : </td>
					<td>
						<c:choose>
							<c:when test='${formulairePrivilegeInformatique.dsiInfo == true }'>
								<input name="dsiInfo" value="true" type="checkbox" checked="checked"/>
							</c:when>
							<c:otherwise>
								<input name="dsiInfo"  value="true" type="checkbox"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td align="right">dsi direction : </td>
					<td>
						<c:choose>
							<c:when test='${formulairePrivilegeInformatique.dsiDirection == true }'>
								<input name="dsiDirection" value="true" type="checkbox" checked="checked"/>
							</c:when>
							<c:otherwise>
								<input name="dsiDirection"  value="true" type="checkbox"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				<tr>
					<td align="right">cadastre MP : </td>
					<td>
						<c:choose>
							<c:when test='${formulairePrivilegeInformatique.cadastreMP == true }'>
								<input name="cadastreMP" value="true" type="checkbox" checked="checked"/>
							</c:when>
							<c:otherwise>
								<input name="cadastreMP"  value="true" type="checkbox"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				<tr>
					<td align="right">administrateur vade-mecum : </td>
					<td>
						<c:choose>
							<c:when test='${formulairePrivilegeInformatique.admVM == true }'>
								<input name="admVM" value="true" type="checkbox" checked="checked"/>
							</c:when>
							<c:otherwise>
								<input name="admVM"  value="true" type="checkbox"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				<tr>
					<td align="right">utilisateur wifi : </td>
					<td>
						<c:choose>
							<c:when test='${formulairePrivilegeInformatique.wifiUser == true }'>
								<input name="wifiUser" value="true" type="checkbox" checked="checked"/>
							</c:when>
							<c:otherwise>
								<input name="wifiUser"  value="true" type="checkbox"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td align="right">administrateur wifi : </td>
					<td>
						<c:choose>
							<c:when test='${formulairePrivilegeInformatique.wifiAdmin == true }'>
								<input name="wifiAdmin" value="true" type="checkbox" checked="checked"/>
							</c:when>
							<c:otherwise>
								<input name="wifiAdmin"  value="true" type="checkbox"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td colspan="2"><hr></td>
				</tr>
				<tr>
					<td align="right">accès au GPI : </td>
					<td>
						<c:choose>
							<c:when test='${formulairePrivilegeInformatique.gpi == true }'>
								<input name="gpi" value="true" type="checkbox" checked="checked"/>
							</c:when>
							<c:otherwise>
								<input name="gpi"  value="true" type="checkbox"/>
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
			<input type="hidden" name="_administrateur"/>
			<input type="hidden" name="_telephonie"/>
			<input type="hidden" name="_formations"/>
			<input type="hidden" name="_dsiUser"/>
			<input type="hidden" name="_dsiChef"/>
			<input type="hidden" name="_dsiInfo"/>
			<input type="hidden" name="_dsiDirection"/>
			<input type="hidden" name="_cadastreMP"/>
			<input type="hidden" name="_admVM"/>
			<input type="hidden" name="_wifiUser"/>
			<input type="hidden" name="_wifiAdmin"/>
			<input type="hidden" name="_gpi"/>
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