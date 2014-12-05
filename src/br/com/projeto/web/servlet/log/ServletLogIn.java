package br.com.projeto.web.servlet.log;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projeto.web.dao.DAOClasse;
import br.com.projeto.web.dao.factory.DAOFactoryHibernate;
import br.com.projeto.web.model.Classe;

@WebServlet(description = "/ServletLogIn", urlPatterns = { "/ServletLogIn" })
public class ServletLogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ServletLogIn() {
        super();
    }
	public void init(ServletConfig config) throws ServletException {
		super.init();
	}
	public void destroy() {
		super.destroy();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Classe classe=new Classe();
		//DAOClasse dao=new DAOJDBCClasse();
		DAOClasse dao=DAOFactoryHibernate.getDAOClasse();
		
		classe.setNumero(Long.valueOf(request.getParameter("numero")));
		classe.setNome((String)request.getParameter("nome"));
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");//sdf= new SimpleDateFormat("EEEE, dd de MMMM de yyyy");
		try {
			classe.setData(sdf.parse(request.getParameter("data")));
		} catch (ParseException pe) {
			classe.setData(new Date());
			pe.printStackTrace();
		}
		
		RequestDispatcher expediente=request.getRequestDispatcher("/index.jsp");
		if(classe!=null){
			if(!(dao.existe(classe))){
				dao.inserir(classe);
				
				HttpSession sessao=request.getSession(true);
				sessao.setAttribute("objeto", classe);
				sessao.setAttribute("nomear", classe.getNome());
				sessao.setAttribute("lista", dao.listar());
				
				expediente=request.getRequestDispatcher("/paginas/sucesso/sucessoJSTLEL.jsp");
			}
		}
		expediente.forward(request, response);
	}
}