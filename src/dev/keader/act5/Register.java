package dev.keader.act5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/cadastro")
public class Register extends HttpServlet {
    private HashMap<String, String> users = new HashMap();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        HttpSession session = req.getSession(true);
        boolean login = session.getAttribute("login") != null;

        if (email == null || senha == null) {
            req.getRequestDispatcher("cadastro.jsp").forward(req, resp);
            return;
        }

        if (!login) {

            if (users.containsKey(email)) {
                req.getRequestDispatcher("cadastro.jsp").forward(req, resp);
                return;
            }

            users.put(email, senha);
            session.setAttribute("login", true);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
        // Login
        else {

            if (!users.containsKey(email)) {
                session.setAttribute("login", null);
                req.getRequestDispatcher("cadastro.jsp").forward(req, resp);
                return;
            }

            if (users.get(email).equals(senha)) {
                User user = new User(email, senha);
                int time = (int) (System.currentTimeMillis() - session.getCreationTime())/1000;

                if (time > 30) {
                    session.invalidate(); // delete session
                    session = req.getSession(true); // make a new one
                    session.setAttribute("login", true); 
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                    return;
                }

                user.setTempo(time);
                req.setAttribute("usuario", user);
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
            else
                req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
