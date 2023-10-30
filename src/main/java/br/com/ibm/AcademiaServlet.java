package br.com.ibm;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

class Aluno {
    private String nome;
    private int idade;

    public Aluno(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }
}

public class AcademiaServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String nome = request.getParameter("nome");
        String idadeStr = request.getParameter("idade");

        if (nome != null && idadeStr != null) {
            try {
                int idade = Integer.parseInt(idadeStr);
                Aluno aluno = new Aluno(nome, idade);

                out.println("<html>");
                out.println("<head><title>Informações do Aluno</title></head>");
                out.println("<body>");

                out.println("<h1>Informações do Aluno</h1>");
                exibirInformacoesAluno(out, aluno);

                out.println("</body>");
                out.println("</html>");
            } catch (NumberFormatException e) {
                out.println("A idade deve ser um número válido.");
            }
        } else {
            out.println("<html>");
            out.println("<head><title>Formulário do Aluno</title></head>");
            out.println("<body>");

            out.println("<h1>Insira as informações do aluno:</h1>");
            exibirFormularioAluno(out);

            out.println("</body>");
            out.println("</html>");
        }
    }

    private void exibirFormularioAluno(PrintWriter out) {
        out.println("<form method='get'>");
        out.println("Nome: <input type='text' name='nome'><br>");
        out.println("Idade: <input type='text' name='idade'><br>");
        out.println("<input type='submit' value='Enviar'>");
        out.println("</form>");
    }

    private void exibirInformacoesAluno(PrintWriter out, Aluno aluno) {
        out.println("<h2>Dados do Aluno:</h2>");
        out.println("<p>Nome: " + aluno.getNome() + "</p>");
        out.println("<p>Idade: " + aluno.getIdade() + " anos</p>");
    }
}
