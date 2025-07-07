// code to actually make update in database
package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
public class UpdateDesignation extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
doPost(request,response);
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
int code = Integer.parseInt(request.getParameter("code"));
String title = request.getParameter("title");
DesignationDTO designationDTO = new DesignationDTO();
designationDTO.setCode(code);
designationDTO.setTitle(title);
PrintWriter pw = null;
try
{
pw = response.getWriter();
response.setContentType("text/html");
DesignationDAO designationDAO = new DesignationDAO();
String oldTitle = designationDAO.getByCode(code).getTitle();
designationDAO.update(designationDTO);

//Notification - updated title this to that. OK button

pw.println("<!DOCTYPE HTML>");
pw.println("<html lang = 'en'>");
pw.println("<head>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function validateForm(frm)");
pw.println("{");
pw.println("var title = frm.title.value.trim();");
pw.println("var titleErrorSection = document.getElementById('titleErrorSection');");
pw.println("var designationErrorSection = document.getElementById('designationErrorSection');");
pw.println("titleErrorSection.innerHTMl='';");
pw.println("if(title.length==0)");
pw.println("{");
pw.println("designationErrorSection.innerHTML='';");
pw.println("titleErrorSection.innerHTML='required';");
pw.println("frm.title.focus();");
pw.println("return false;");
pw.println("}");
pw.println("return true;");
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
pw.println("<h3>Notification</h3>");
pw.println("<br>");
pw.println("<form action='/styleone/designationsView' onsubmit='return validateForm(this)'>");
pw.println("<div id='designationErrorSection' style='color:red'> </div>");
pw.println("Designation updated from ("+oldTitle+") to ("+designationDTO.getTitle()+").<br><br>");
pw.println("<button type='submit'>OK</button>");
pw.println("</form>");
pw.println("</div><!-- right panel ends here -->");
pw.println("</div><!-- content-section ends here -->");
pw.println("<!-- footer starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;text-align:center;border:1px solid footer'>");
pw.println("&copy; Thinking Machines 2025");
pw.println("</div> <!-- footer ends here -->");
pw.println("</div><!-- Main container ends here -->");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");

}catch(DAOException daoException)
{
// invalid title
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang = 'en'>");
pw.println("<head>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function validateForm(frm)");
pw.println("{");
pw.println("var title = frm.title.value.trim();");
pw.println("var titleErrorSection = document.getElementById('titleErrorSection');");
pw.println("var designationErrorSection = document.getElementById('designationErrorSection');");
pw.println("titleErrorSection.innerHTMl='';");
pw.println("if(title.length==0)");
pw.println("{");
pw.println("designationErrorSection.innerHTML='';");
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
pw.println("<div id='designationErrorSection' style='color:red'>"+daoException.getMessage()+"</div>");
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
}
catch(Exception exception) //if PrintWriter throws IOException
{
System.out.println(exception.getMessage()); //remove after testing and setup 500 (internal page error)
}



}
}