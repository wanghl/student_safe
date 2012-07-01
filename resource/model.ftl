<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"><title>RFID卡记录追踪报表</title>
<style type="text/css" <!--
.normal {  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-weight: normal; color: #000000}
.medium {  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 15px; font-weight: bold; color: #000000; text-decoration: none}
--></style>
</head>
<center><h2> 事件: ${event}&nbsp;&nbsp; 生成时间: ${date} </h2></center>
<center><h3> 共计 ${rowcount} 条 </h3></center>
<body>
<table id="result" border=1 align='center'>
	<tr>
		<th bgcolor=silver class='medium'>卡号</th>
		<th bgcolor=silver class='medium'>最终结果</th>
		<th bgcolor=silver class='medium'>详细信息</th>
	</tr>
	<#list datamodel as data >
		<tr>
			<td  class='normal' valign='top'>${data.rfid}</td>
			<td  class='normal' valign='top'>${data.result}</td>
			<td  class='normal' valign='top'><a href="#${data.rfid}" >查看</a></td>
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
			<th bgcolor=silver class='medium'>"${data.rfid}"详细信息</th>
		</tr>
		  <#list data.detail as x >
		<tr>
			<td  class='normal' valign='top'>${x}</td>
		</tr>
		  </#list>

</#list>

</body>

</html>