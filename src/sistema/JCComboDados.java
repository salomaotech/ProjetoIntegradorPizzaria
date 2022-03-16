/*
carrega os dados do banco de dados e exibe em um jcombo
 */
package sistema;

import daobanco.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

/**
 *
 * @author Salomão Francisco da Silva - Fone: 62 99447-4551
 * @version 1.0
 */
public class JCComboDados {

    private String tabela;
    private String colunaExibir;

    // construtor
    public JCComboDados(String tabela, String colunaExibir) {

        // informa a tabela
        this.tabela = tabela;

        // informa a coluna
        this.colunaExibir = colunaExibir;

    }

    // recupera os dados do banco de dados
    public List recuperaDados(JComboBox objLista) {

        // limpa os itens antigos
        objLista.removeAllItems();

        // retorno
        List retorno = new ArrayList();

        // banco de dados
        BancoDados banco = new BancoDados();

        // conexão com o mysql
        Mysql mysql = new Mysql(banco.getServidor(), banco.getUsuario(), banco.getSenha(), banco.getNomeBanco());

        // query
        String query = "select id, " + colunaExibir + " from " + tabela + ";";

        // result set
        ResultSet resultados = mysql.getResultado(query);

        // contador
        int contador = 0;

        // recupera os dados da tabela
        try {

            while (resultados.next()) {

                // adiciona
                objLista.addItem(resultados.getString((String) colunaExibir));

                // atualiza o retorno
                retorno.add(contador, resultados.getString("id"));

                // atualiza o contador
                contador++;

            }

        } catch (SQLException ex) {

        }

        // fecha a conexão
        mysql.desconecta();

        // retorno
        return retorno;

    }

    // recupera os dados do banco de dados em forma de pesquisa
    public List pesquisaDados(JComboBox objLista, Map colunasPesquisa) {

        // limpa os itens antigos
        objLista.removeAllItems();

        // retorno
        List retorno = new ArrayList();

        // banco de dados
        BancoDados banco = new BancoDados();

        // conexão com o mysql
        Mysql mysql = new Mysql(banco.getServidor(), banco.getUsuario(), banco.getSenha(), banco.getNomeBanco());

        // termos de pesquisa
        String termosPesquisa = "";

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

        // query
        String query = "select id, " + colunaExibir + " from " + tabela + termosPesquisa + ";";

        // result set
        ResultSet resultados = mysql.getResultado(query);

        // list model
        DefaultListModel listModel = new DefaultListModel();

        // contador
        int contador = 0;

        // recupera os dados da tabela
        try {

            while (resultados.next()) {

                // adiciona
                objLista.addItem(resultados.getString((String) colunaExibir));

                // atualiza o retorno
                retorno.add(contador, resultados.getString("id"));

                // atualiza o contador
                contador++;

            }

        } catch (SQLException ex) {

        }

        // fecha a conexão
        mysql.desconecta();

        // retorno
        return retorno;

    }

}
