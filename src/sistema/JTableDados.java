/*
carrega os dados do banco de dados e exibe em um jtable
 */
package sistema;

import daobanco.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import static java.util.Objects.isNull;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author Salomão Francisco da Silva - Fone: 62 99447-4551
 * @version 1.0
 */
public class JTableDados {

    private String tabela;
    private List colunas;

    // construtor
    public JTableDados(String tabela, List colunas) {

        // informa a tabela
        this.tabela = tabela;

        // informa o map de colunas
        this.colunas = colunas;

        // inverte a ordem da lista
        Collections.reverse(colunas);

    }

    // recupera os dados do banco de dados
    public void recuperaDados(JTable objTabela, JFrame janela) {

        // banco de dados
        BancoDados banco = new BancoDados();

        // conexão com o mysql
        Mysql mysql = new Mysql(banco.getServidor(), banco.getUsuario(), banco.getSenha(), banco.getNomeBanco());

        // número de colunas
        int numColunas = colunas.size() + 1;

        // query
        String query = "";

        // lista as colunas
        for (Object coluna : colunas) {

            query = coluna + ", " + query;

        }

        // completa query
        query = query + ")";
        query = query.replace(", )", "");
        query = "select id, " + query + " from " + tabela + ";";

        // result set
        ResultSet resultados = mysql.getResultado(query);

        // nova lista
        List lista = new ArrayList();

        // inverte a ordem da lista
        Collections.reverse(colunas);

        // recupera os dados da tabela
        try {

            while (resultados.next()) {

                lista.add(resultados.getString("id"));

                // operacao
                JTableOperacoes operacao = new JTableOperacoes(objTabela, janela);

                // array de objetos
                Object linhasTabela[] = new Object[numColunas];

                // contador de linha
                int contador = 0;

                // lista as colunas
                for (Object coluna : colunas) {

                    linhasTabela[contador] = resultados.getString((String) coluna);

                    // atualiza o contador
                    contador++;

                }

                // adiciona
                operacao.addLinhas(linhasTabela, lista);

            }

        } catch (SQLException | java.lang.NullPointerException ex) {

        }

        // fecha a conexão
        mysql.desconecta();

    }

    // recupera os dados do banco de dados em forma de pesquisa
    public void pesquisaDados(JTable objTabela, JFrame janela, Map colunasPesquisa, boolean limparJtable, String completaCondicao) {

        // banco de dados
        BancoDados banco = new BancoDados();

        // conexão com o mysql
        Mysql mysql = new Mysql(banco.getServidor(), banco.getUsuario(), banco.getSenha(), banco.getNomeBanco());

        // número de colunas
        int numColunas = colunas.size() + 1;

        // query
        String query = "";

        // termos de pesquisa
        String termosPesquisa = "";

        // lista as colunas
        for (Object coluna : colunas) {

            query = coluna + ", " + query;

        }

        // lista colunas
        for (Object chave : colunasPesquisa.keySet()) {

            // valor da chave
            String valorChave = (String) colunasPesquisa.get(chave);

            // não permite chaves vazias
            if (valorChave.length() > 0) {

                // completa termos de pesquisa
                termosPesquisa = termosPesquisa + chave + " like '%" + colunasPesquisa.get(chave) + "%' and ";

            }

        }

        // valida termo de pesquisa
        if (termosPesquisa.length() > 0) {

            // completa termos de pesquisa
            termosPesquisa = termosPesquisa + ")";
            termosPesquisa = termosPesquisa.replace(" and )", "");
            termosPesquisa = " where " + termosPesquisa;

        }

        // valida condição
        if (isNull(completaCondicao) == false) {

            // valida termo de pesquisa
            if (termosPesquisa.length() > 0) {

                termosPesquisa = termosPesquisa + " and " + completaCondicao;

            } else {

                termosPesquisa = " where " + completaCondicao;

            }

        }

        // completa query
        query = query + ")";
        query = query.replace(", )", "");
        query = "select id, " + query + " from " + tabela + termosPesquisa + ";";

        // result set
        ResultSet resultados = mysql.getResultado(query);

        // nova lista
        List lista = new ArrayList();

        // inverte a ordem da lista
        Collections.reverse(colunas);

        // valida se remove as linhas antigas
        if (limparJtable == true) {

            // operacao
            JTableOperacoes operacao = new JTableOperacoes(objTabela, janela);

            // limpa linhas antigas
            operacao.removeLinhas();

        }

        // recupera os dados da tabela
        try {

            while (resultados.next()) {

                lista.add(resultados.getString("id"));

                // operacao
                JTableOperacoes operacao = new JTableOperacoes(objTabela, janela);

                // array de objetos
                Object linhasTabela[] = new Object[numColunas];

                // contador de linha
                int contador = 0;

                // lista as colunas
                for (Object coluna : colunas) {

                    linhasTabela[contador] = resultados.getString((String) coluna);

                    // atualiza o contador
                    contador++;

                }

                // adiciona
                operacao.addLinhas(linhasTabela, lista);

            }

        } catch (SQLException ex) {

        }

        // fecha a conexão
        mysql.desconecta();

    }

}
