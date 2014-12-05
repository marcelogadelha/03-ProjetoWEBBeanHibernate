package br.com.projeto.web.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projeto.web.dao.DAOClasse;
import br.com.projeto.web.dao.factory.DAOFactoryHibernate;
import br.com.projeto.web.model.Classe;

@WebServlet(description = "ServletControle", urlPatterns = { "/ServletControle" })
public class ServletControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletControle() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Classe classe=new Classe();
		DAOClasse dao=DAOFactoryHibernate.getDAOClasse();
		String escolha=new String();
		
		//TRATANDO OS DADOS RECEBIDOS
		escolha=(String)request.getParameter("escolha");
		if(!escolha.equals("listar")){
			classe.setNumero(Long.valueOf(request.getParameter("numero")));
			classe.setNome((String)request.getParameter("nome"));
			DateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			try{
				classe.setData(sdf.parse(request.getParameter("data")));
			}catch(ParseException pe){
				classe.setData(new Date());
				pe.printStackTrace();
			}
		}
		
		HttpSession sessao=request.getSession(true);
		switch(escolha){
		case "alterar":
			dao.alterar(classe);
			break;
		case "consultar":
			sessao.setAttribute("objeto", dao.consultar(classe));
			break;
		case "excluir":
			dao.excluir(classe);
			break;
		case "inserir":
			dao.inserir(classe);
			break;
		case "listar":
			sessao.setAttribute("lista", dao.listar());
			break;
		default:
		}
		
		RequestDispatcher expediente=request.getRequestDispatcher("/paginas/sucesso/sucessoJSTLEL.jsp");
		expediente.forward(request, response);
	}
}