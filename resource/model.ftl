<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"><title>RFID����¼׷�ٱ���</title>
<style type="text/css" <!--
.normal {  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-weight: normal; color: #000000}
.medium {  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 15px; font-weight: bold; color: #000000; text-decoration: none}
--></style>
</head>
<center><h2> �¼�: ${event}&nbsp;&nbsp; ����ʱ��: ${date} </h2></center>
<center><h3> ���� ${rowcount} �� </h3></center>
<body>
<table id="result" border=1 align='center'>
	<tr>
		<th bgcolor=silver class='medium'>����</th>
		<th bgcolor=silver class='medium'>���ս��</th>
		<th bgcolor=silver class='medium'>��ϸ��Ϣ</th>
	</tr>
	<#list datamodel as data >
		<tr>
			<td  class='normal' valign='top'>${data.rfid}</td>
			<td  class='normal' valign='top'>${data.result}</td>
			<td  class='normal' valign='top'><a href="#${data.rfid}" >�鿴</a></td>
		</tr>
	</#list>
			
	
</table>

<#list datamodel as data>
<p>
<p>
<p>
<p>
	<table id="${data.rfid}" border=1 align='center'>
		<tr>
			<th bgcolor=silver class='medium'>"${data.rfid}"��ϸ��Ϣ</th>
		</tr>
		  <#list data.detail as x >
		<tr>
			<td  class='normal' valign='top'>${x}</td>
		</tr>
		  </#list>

</#list>

</body>

</html>