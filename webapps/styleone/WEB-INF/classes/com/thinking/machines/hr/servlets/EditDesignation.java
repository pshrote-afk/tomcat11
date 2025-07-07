// gives form which can be used to edit
package com.thinking.machines.hr.servlets;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import com.thinking.machines.hr.dl.*;
public class EditDesignation extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
int code=0;
PrintWriter pw=null;
try
{
code = Integer.parseInt(request.getParameter("code"));
}catch(NumberFormatException numberFormatException)
{
	try
	{
	response.sendRedirect("/styleone/designationsView");
	return;
	}catch(IOException ioException)
	{
	return;
	}
}

DesignationDAO designationDAO = new DesignationDAO();
DesignationDTO designationDTO;
try
{
designationDTO = designationDAO.getByCode(code);
}catch(DAOException daoException)
{
	try
	{
	response.sendRedirect("/styleone/designationsView");
	return;
	}catch(IOException ioException)
	{
	return;
	}
}
try
{
pw = response.getWriter();
response.setContentType("text/html");
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang = 'en'>");
pw.println("<head>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function validateForm(frm)");
pw.println("{");
pw.println("var title = frm.title.value.trim();");
pw.println("var titleErrorSection = document.getElementById('titleErrorSection');");
pw.println("titleErrorSection.innerHTMl='';");
pw.println("if(title.length==0)");
pw.println("{");
pw.println("titleErrorSection.innerHTML='required';");
pw.println("frm.title.focus();");
pw.println("return false;");
pw.println("}");
pw.println("return true;");
pw.println("}");
pw.println("function cancelEditing()");
pw.println("{");
pw.println("document.getElementById('cancelEditingForm').submit();");
pw.println("}");
pw.println("</script>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- Main container starts here -->");
pw.println("<div style='width:90hw;height:auto;border:1px solid black'>");
pw.println("<!-- Header container starts here -->");
pw.println("<div style='margin:5px;width:90hw;height:auto;border:1px solid black'>");
pw.println("<img src='/styleone/images/logo.png' style='float:left;width:35px;height:auto;padding:5px'><div style='margin-top:9px;margin-bottom:9px;font-size:20pt'>Thinking Machines</div>");
pw.println("</div><!-- Header container ends here -->");
pw.println("<!-- content-section starts here -->");
pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid white'>");
pw.println("<!-- left panel starts here -->");
pw.println("<div style='height:65vh;margin:5px;float:left;padding:5px;border:1px solid black'>");
pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
pw.println("<a href='/styleone/employeesView'>Employees</a>");
pw.println("</div><!-- left panel ends here -->");
pw.println("<!-- right panel starts here -->");
pw.println("<div style='height:65vh;margin-left:105px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;border:1px solid black'>");
pw.println("<h2>Designation (Edit Module)</h2>");
pw.println("<br>");
pw.println("<form method='post' action='/styleone/updateDesignation' onsubmit='return validateForm(this)'>");
pw.println("Designation");
pw.println("<input type='hidden' id='code' name='code' value='"+designationDTO.getCode()+"'>");
pw.println("<input type='text' id='title' name='title' maxlength='35' size='36' value='"+designationDTO.getTitle()+"'>");
pw.println("<span id='titleErrorSection' style='color:red'></span><br><br>");
pw.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
pw.println("<button type='submit'>Update</button>");
pw.println("<button type='button' onclick='cancelEditing()'>Cancel</button>");
pw.println("</form>");
pw.println("</div><!-- right panel ends here -->");
pw.println("</div><!-- content-section ends here -->");
pw.println("<!-- footer starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;text-align:center;border:1px solid footer'>");
pw.println("&copy; Thinking Machines 2025");
pw.println("</div> <!-- footer ends here -->");
pw.println("</div><!-- Main container ends here -->");
pw.println("<form id='cancelEditingForm' action='/styleone/designationsView'>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");
}catch(Exception exception)
{
System.out.println(exception.getMessage()); //remove after testing
}
}
}