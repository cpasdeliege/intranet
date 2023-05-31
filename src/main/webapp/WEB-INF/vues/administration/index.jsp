<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.security.*" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet CPAS de Liège - administration</title>
<link rel="stylesheet" href="cpas.css" type="text/css">
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
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
				<!-- <tr>
					<td width="60px" align="center"><img src="images/administration/ordinateur.png" border="0" width="50px"></td>
					<td><a class="texte_menu_principal" href="gestionOrdinateurs.admin?param=actifs">Gestion des Ordinateurs</a></td>
				</tr>-->
				<tr>
					<td width="60px" align="center"><img src="images/administration/scanner.png" border="0" width="60px"></td>
					<td><a class="texte_menu_principal" href="gestionScanners.admin">Gestion des Scanners</a></td>
				</tr>
				<tr>
					<!-- <td width="60px" align="center"></td>
					<td>&nbsp;</td> -->
					
					<td width="60px" align="center"><img src="images/LOUPE1.jpg" border="0" width="50px"></td>
				 <td><a class="texte_menu_principal" href="http://cpl-app-08/outils/intranetselect.php">Intranet select</a></td>
				</tr>
				
				
			</table>
		</td>
		<td valign="top">
			<table border="0">
				<tr>
					<td width="60px" align="center"><img src="images/administration/ordinateur.png" border="0" width="50px"></td>
					<td><a class="texte_menu_principal" href="http://10.210.117.21:8080/gpi/#/">GPI</a></td> 
					<!--  <td><a class="texte_menu_principal" href="http://cpl-app-27:8080/gpi/#/">GPI</a></td> -->
				</tr>
				<tr>
					<td width="60px" align="center"><img src="images/dsi.png" border="0" width="50px"></td>
					<td>
						<a class="texte_menu_principal" href="index.dsi">DSI</a>
						
					</td>
				<tr>
					<td width="60px" align="center"><img src="images/administration/planning1.png" border="0" width="50px"></td>
					<td><a class="texte_menu_principal" href="gestionPlanning.admin">GTI</a></td>
				</tr>
				</tr>
				<%
				
				String date = new SimpleDateFormat("yyyyMMdd").format(new GregorianCalendar().getTime());
				String tmp = date + "psswrd";
//				System.out.println(tmp);
				 byte[] hash;
			        try {
			            hash = MessageDigest.getInstance("MD5").digest(tmp.getBytes("UTF-8"));
			        } catch (NoSuchAlgorithmException e) {
			            throw new RuntimeException("Huh, MD5 should be supported?", e);
			        } catch (UnsupportedEncodingException e) {
			            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
			        }
			        StringBuilder hex = new StringBuilder(hash.length * 2);
			        for (byte b : hash) {
			            int i = (b & 0xFF);
			            if (i < 0x10) hex.append('0');
			            hex.append(Integer.toHexString(i));
			        }
				
				%>
				<tr>
					<td width="60px" align="center"><img src="images/administration/imprimante.png" border="0" width="50px"></td>
					<td><a class="texte_menu_principal" href="http://10.210.117.21/imprimantes/connect.php?pass=<%= hex.toString() %>">Gestion des Imprimantes</a></td>
				</tr>
				<tr>
					<td width="60px" align="center"></td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
				<td width="60px" align="center"><img src="images/logo_cerisier.png" border="0" width="50px"></td>
				 <td><a class="texte_menu_principal" href="http://10.210.117.21/cerisier/index.html">Cerisier</a></td>
				</tr>
				
				
				<!--  
				<tr>
					<td><a class="texte_menu_principal" href="gestionLogiciels.admin">Gestion des Logiciels</a></td>
				</tr>
				<tr>
					<td><a class="texte_menu_principal" href="gestionStocks.admin">Gestion des Stocks</a></td>
				</tr>
				
				-->
				
			</table>
		</td>
	</tr>
	
	
	<tr>	
		
		<!--   <td valign="top">
			<table  border="0">
				<tr>
					<td width="60px" align="center"><img src="images/administration/email.png" border="0" width="50px"></td>
					<td>
						<a class="texte_menu_principal" href="gestionEmails.admin">Liste des Accès Internet</a>
						
					</td>
				</tr>
				
				<tr>
					<td width="60px" align="center"></td>
					<td>&nbsp;</td>
				</tr>
				
			</table>
		</td>
		-->
	</tr>
	
	
	<tr>
		<td valign="top">
			<table  border="0">
				
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
		<td valign="top">
			<table border="0">
				
				<tr>
					<td width="60px" align="center"><img src="images/administration/statistiques.png" border="0" width="60px"></td>
					<td><a class="texte_menu_principal" href="rapports.admin?requete=accueil">Rapports &amp; Statistiques</a></td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<jsp:include page="../basDePage.jsp" ></jsp:include>
</body>
</html>
