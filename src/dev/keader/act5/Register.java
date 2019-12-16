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

    private HashMap<String, String> usuarios = new HashMap();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String ehCadastro = req.getParameter("ehCadastro");

        if (email == null || senha == null) {
            req.getRequestDispatcher("cadastro.jsp").forward(req, resp);
            return;
        }

        if (ehCadastro.equals("true")) {

            if (usuarios.containsKey(email)) {
                req.getRequestDispatcher("cadastro.jsp").forward(req, resp);
                return;
            }

            usuarios.put(email, senha);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
        // Login
        else {

            if (!usuarios.containsKey(email)){
                req.getRequestDispatcher("cadastro.jsp").forward(req, resp);
                return;
            }

            if (usuarios.get(email).equals(senha)) {

                Usuario user = new Usuario(email, senha);
                HttpSession session = req.getSession(true);
                int tempo = (int) (System.currentTimeMillis() - session.getCreationTime())/1000;

                if (tempo > 30) {
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                    session.invalidate(); // delete session
                    return;
                }

                user.setTempo(tempo);
                req.setAttribute("usuario", user);
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }

        }
    }
}
