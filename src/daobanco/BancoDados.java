
/*
cria banco de dados
cria tabela
insere dados
verifica dados
 */
package daobanco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.Objects.isNull;
import javax.swing.JOptionPane;

/**
 *
 * @author Salomão Francisco da Silva - Fone: 62 99447-4551
 * @version 1.0
 */
public class BancoDados extends TrataDados {

    // nome do banco de dados
    private final String SERVIDOR = "localhost";
    private final String USUARIO = "root";
    private final String SENHA = "12345678";
    private final String NOMEBANCO = "bd_pizzaria";
    private final String URLSERVIDOR = "http://" + SERVIDOR;
    private final String LOCALINSTALACAOSERVIDOR = "/opt/lampp/htdocs/";

    // cria o banco de dados
    public void criaBancoDados() {

        // conexão com o mysql
        Mysql mysql = new Mysql(getServidor(), getUsuario(), getSenha(), null);

        // query para criar o banco de dados
        String query = "create database if not exists " + NOMEBANCO + ";";

        // executa sem retorno
        mysql.executaQuery(query);

        // desconecta o banco de dados
        mysql.desconecta();

    }

    // cria uma tabela no banco de dados
    public void criarTabela(Map campos, String tabela) {

        // conexão com o mysql
        Mysql mysql = new Mysql(getServidor(), getUsuario(), getSenha(), getNomeBanco());

        // campos de tabela
        String query = "create table if not exists " + tabela + "(id int not null auto_increment primary key, ";

        // colunas
        String colunas = "";

        // listando colunas
        for (Object chave : campos.keySet()) {

            // atualiza as colunas
            colunas = colunas + chave + " " + campos.get(chave) + ", ";

        }

        // campleta
        colunas = colunas + ";";
        colunas = colunas.replaceAll(", ;", ");");
        query = query + colunas;

        // executa sem retorno
        mysql.executaQuery(query);

        // desconecta o banco de dados
        mysql.desconecta();

    }

    // insere dados na tabela
    public void inserirDados(Map campos, String tabela) {

        // conexão com o mysql
        Mysql mysql = new Mysql(getServidor(), getUsuario(), getSenha(), getNomeBanco());

        // query
        String query = "insert into " + tabela + "(";

        // colunas e linhas
        String colunas = "";
        String linhas = "";

        // listando colunas
        for (Object chave : campos.keySet()) {

            // colunas
            colunas = chave + ", " + colunas;

            // linhas
            linhas = "'" + trataDados(campos.get(chave)) + "', " + linhas;

        }

        // completa
        colunas = colunas + ")";
        linhas = linhas + ")";

        // completa
        colunas = colunas.replace(", )", ")");
        linhas = linhas.replace(", )", ")");

        // completa query
        query = query + colunas + " values (" + linhas + ";";

        // executa sem retorno
        mysql.executaQuery(query);

        // desconecta o banco de dados
        mysql.desconecta();

    }

    // atualiza dados na tabela
    public void AtualizarDados(Map campos, String tabela) {

        // conexão com o mysql
        Mysql mysql = new Mysql(getServidor(), getUsuario(), getSenha(), getNomeBanco());

        // query
        String query = "update " + tabela + " set ";

        // id de chave primaria
        Object idChavePrimaria = campos.get("id");

        // remove chave primária
        campos.remove("id");

        // colunas e linhas
        String linhas = "";

        // listando colunas
        for (Object chave : campos.keySet()) {

            // linhas
            linhas = chave + "='" + trataDados(campos.get(chave)) + "', " + linhas;

        }

        // completa
        linhas = linhas + ")";

        // completa
        linhas = linhas.replace(", )", "");

        // completa query
        query = query + linhas + " where id='" + idChavePrimaria + "';";

        // executa sem retorno
        mysql.executaQuery(query);

        // desconecta o banco de dados
        mysql.desconecta();

    }

    // retorna se um registro existe em uma tabela do banco de dados
    public boolean isRegistroExiste(String tabela, String coluna, String valor) {

        // conexão com o mysql
        Mysql mysql = new Mysql(getServidor(), getUsuario(), getSenha(), getNomeBanco());

        // prepara instrução e resultados
        PreparedStatement executa;
        ResultSet resultado;

        // query
        String query = "select " + coluna + " from " + tabela + " where " + coluna + "='" + valor + "';";

        // trata execução de query
        try {

            // executa
            executa = mysql.getConexao().prepareStatement(query);
            resultado = executa.executeQuery();

            // retorno
            return resultado.next();

        } catch (SQLException ex) {

            // retorno
            return false;

        }

    }

    // retorna um data com os nomes de colunas
    private Map getColunas(ResultSet resultados) {

        // dados
        Map dados = new HashMap();

        // dados de retorno
        ResultSetMetaData dadosColuna;

        // trata erros
        try {

            // pega os dados
            dadosColuna = resultados.getMetaData();

            // listando os dados
            for (int a = 1; a <= dadosColuna.getColumnCount(); a++) {

                // coluna
                String coluna = Integer.toString(a);

                // popula os dados
                dados.put(coluna, dadosColuna.getColumnName(a));

            }

        } catch (SQLException ex) {

        }

        // retorno
        return dados;

    }

    // recupera os dados do banco de dados pela chave primária
    private List recuperaDadosQuery(String query) {

        // map
        List dados = new ArrayList();

        // conexão com o mysql
        Mysql mysql = new Mysql(getServidor(), getUsuario(), getSenha(), getNomeBanco());

        // result set
        ResultSet resultados = mysql.getResultado(query);

        // map de colunas
        Map colunas = getColunas(resultados);

        // recupera os dados da tabela
        try {

            // proxima linha
            while (resultados.next()) {

                // dados locais
                Map dadosLocais = new HashMap();

                // lista colunas
                colunas.forEach((key, value) -> {

                    // trata erro
                    try {

                        // popula dados locais
                        dadosLocais.put(value, resultados.getString((String) value));

                    } catch (SQLException ex) {

                    }

                });

                // adiciona os dados locais
                dados.add(dadosLocais);

            }

        } catch (SQLException ex) {

        }

        // fecha a conexão
        mysql.desconecta();

        // retorno
        return dados;

    }

    // retorna os dados da query recuperados e tratados
    public Map getDadosQueryRecuperados(String query, int index) {

        // trata erro
        try {

            // raiz de dados
            List raiz = recuperaDadosQuery(query);

            // map de dados
            Map nos = (Map) raiz.get(index);

            // retorno
            return nos;

        } catch (java.lang.IndexOutOfBoundsException ex) {

            // retorno
            return null;

        }

    }

    // recupera os dados do banco de dados pela chave primária
    public Map recuperaDadosChavePrimaria(String chavePrimaria, String valorChavePrimaria, String tabela) {

        // query
        String query = "select *from " + tabela + " where " + chavePrimaria + "='" + valorChavePrimaria + "' limit 1;";

        // map
        Map dados = new HashMap();

        // conexão com o mysql
        Mysql mysql = new Mysql(getServidor(), getUsuario(), getSenha(), getNomeBanco());

        // result set
        ResultSet resultados = mysql.getResultado(query);

        // map de colunas
        Map colunas = getColunas(resultados);

        // recupera os dados da tabela
        try {

            while (resultados.next()) {

                // lista colunas
                colunas.forEach((key, value) -> {

                    // trata erro
                    try {

                        // valor
                        String valor = resultados.getString((String) value);

                        // popula
                        dados.put(value, valor);

                    } catch (SQLException ex) {

                    }

                });

            }

        } catch (SQLException ex) {

        }

        // fecha a conexão
        mysql.desconecta();

        // retorno
        return dados;

    }

    // remove um registro pela chave primária
    public boolean removeRegistroChavePrimaria(String chavePrimaria, String valorChavePrimaria, String tabela, String pergunta) {

        int respostaPergunta = 0;

        // verifica se há uma pergunta confirmando a exclusão
        if (isNull(pergunta) == false) {

            respostaPergunta = JOptionPane.showConfirmDialog(null, pergunta);

        }
        // pergunta
        if (respostaPergunta == 0) {

            // query
            String query = "delete from " + tabela + " where " + chavePrimaria + "='" + valorChavePrimaria + "';";

            // conexão com o mysql
            Mysql mysql = new Mysql(getServidor(), getUsuario(), getSenha(), getNomeBanco());

            // remove
            mysql.executaQuery(query);

            // fecha a conexão
            mysql.desconecta();

        }

        // retorno
        return respostaPergunta == 0;

    }

    public String getServidor() {
        return SERVIDOR;
    }

    public String getUsuario() {
        return USUARIO;
    }

    public String getSenha() {
        return SENHA;
    }

    public String getNomeBanco() {
        return NOMEBANCO;
    }

    public String getURLSERVIDOR() {
        return URLSERVIDOR;
    }

    public String getLOCALINSTALACAOSERVIDOR() {
        return LOCALINSTALACAOSERVIDOR;
    }

}
