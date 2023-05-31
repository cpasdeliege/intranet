<script type="text/javascript">
<!-- 

function changerMotDePasse(login) {
   var mdp = prompt('Nouveau mot de passe :');
   if(mdp != null) {
   	document.location.href='changerMotDePasse.htm?login=' + login + '&mdp=' + mdp;
   }
}

//-->
</script>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td align="left" style="padding-left: 30px"><img src="images/banniere_gauche.png" border="0" height="90px" alt="cpas de liège" onclick=""></td>
		<td valign="bottom" align="right" >
		<%@ page import="be.cpasdeliege.intranet.informatique.model.*" %>
		<%
		Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur");
		if(utilisateur == null) {
		%>
			<form method="post" action="authentification.htm">
				<table border="0">
					<tr>
						<td style="font-size: 12px" align="right">utilisateur : </td>
						<td><input style="font-size: 12px" type="text" maxlength="200" name="login" value=""/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td style="font-size: 12px" align="right">mot de passe : </td>
						<td><input style="font-size: 12px" type="password" maxlength="200" name="mdp" value=""/></td>
					</tr>
					<tr>
						<td></td>
						<td align="right"><input style="font-size: 12px" type="submit" value="connexion"/></td>
					</tr>
				</table>
			</form>
		<%} else { %>
			<table>
				<tr>
					<td style="font-size: 12px">
						<%= utilisateur.getNom() %>, <%= utilisateur.getPrenom() %> - 
						<a href="" onclick="changerMotDePasse('<%= utilisateur.getLogin() %>')">changer mot de passe</a> - <a href="deconnexion.htm">déconnexion</a>
					</td>
				</tr>
			</table>
		
		<%} %>
		</td>
	</tr>
</table>