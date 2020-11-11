package javacore.presentations;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servletクラス
 *
 * @author kenminami
 */
@WebServlet("/helloworld")
public class DispatcherServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String param = request.getParameter("param");
    response.getWriter().write("Hello "+ param);
  }
}
