<%
		String chrono = (String)request.getAttribute("chrono");
		if (chrono == null || chrono.equals("0,000")) {
			chrono = "en moins de 0,001";
		}
%>

<table width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="center"><hr width="80%">Intranet du CPAS de Li�ge - D�partement Informatique - 2007</td>
	</tr>
	<tr>
		<td align="center" style="font-size: 12px; font-style: italic">- page g�n�r�e en <%= chrono %> sec le ${date } -</td>
	</tr>
</table>

