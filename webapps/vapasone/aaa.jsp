<!DOCTYPE HTML>
<html lang='en'>
<head>
<meta charset='utf-8'>
<title>Form page 1</title>
</head>
<body>
<%
String nn = request.getParameter("name");
String cc = request.getParameter("city");
System.out.println("Data arrived");
System.out.println("Name: "+nn);
System.out.println("City: "+cc);
%>

Data saved <br>
Name: <%=nn%><br>
<%
out.println("City: "+cc);
%>
</body>
</html>