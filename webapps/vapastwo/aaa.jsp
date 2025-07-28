<!DOCTYPE HTML>
<html lang='en'>
<head>
<script>
function goBack()
{
document.getElementById('goBackForm').submit();
}
</script>
</head>
<body>
<%
String name=request.getParameter("name");
String city=request.getParameter("city");
System.out.println("Data pohochla bara ka!");
System.out.println("Name: "+name);
System.out.println("City: "+city);
%>
<center>
Name: <%=name%><br>
City:
<%=city%><br>
<button type='button' onclick='goBack()'>OK</button>
</center>
<form action='/vapastwo/index.html' id='goBackForm'>
</form>
</body>
</html>