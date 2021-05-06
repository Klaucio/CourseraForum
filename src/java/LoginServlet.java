import controller.DatabaseConnection;
import controller.UsuariosDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("login");
            String password = request.getParameter("password");
            
            UsuariosDAO usuariosDAO= new UsuariosDAO();
            boolean found = usuariosDAO.login(username, password);
            
            if(found){
                response.sendRedirect(request.getContextPath() + "/welcome.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/signup.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
