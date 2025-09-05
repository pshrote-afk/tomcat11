import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet 
{
protected void doGet(HttpServletRequest request, HttpServletResponse response)
{
response.setContentType("text/html");
try
{
PrintWriter out = response.getWriter();
out.println("<html><body>");
out.println("<h1>Hello World!</h1>");
out.println("</body></html>");
}catch(IOException ioe)
{
System.out.println(ioe.getMessage());
}

}
}