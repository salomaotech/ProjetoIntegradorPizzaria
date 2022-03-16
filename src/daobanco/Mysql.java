/*
conecta-se ao servidor Mysql
executa todos os comandos query necessários
 */
package daobanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Objects.isNull;
import javax.swing.JOptionPane;

/**
 *
 * @author Salomão Francisco da Silva - Fone: 62 99447-4551
 * @version 1.0
 */
public class Mysql {

    private String servidor, usuario, senha, bancoDados;
    private Connection conexao;
    private PreparedStatement executa;
    private ResultSet resultado;
    private boolean conectado;

    // construtor
    public Mysql(String servidor, String usuario, String senha, String bancoDados) {

        // dados de conexão
        this.servidor = servidor;
        this.usuario = usuario;
        this.senha = senha;
        this.bancoDados = bancoDados;

        // conecta-se ao servidor mysql
        conectado = conecta();

    }

    // conecta-se ao servidor e retorna um booleano informando se a conexão foi bem sucessida ou não
    private boolean conecta() {

        // url de conexão
        String urlConexao;

        // valida se o banco de dados foi informado
        if (isNull(bancoDados) == false) {

            // url de conexão
            urlConexao = "jdbc:mysql://" + servidor + "/" + bancoDados;

        } else {

            // url de conexão
            urlConexao = "jdbc:mysql://" + servidor;

        }

        // tenta uma conexão
        try {

            // conexão
            conexao = DriverManager.getConnection(urlConexao, usuario, senha);

            // retorno
            return true;

        } catch (SQLException ex) {

            // string com mensagem de erro
            String mensagem = null;

            // seleciona o código de erro
            switch (ex.getErrorCode()) {

                case 0:
                    mensagem = "O servidor Mysql não está on-line.";
                    break;

                case 1045:
                    mensagem = "Usuário Mysql não encontrado.";
                    break;

                case 1049:
                    mensagem = "Banco de dados '" + bancoDados + "' não existe. \nReinicie o sistema para que ele possa ser instalado automaticamente.";
                    break;

            }

            // valida mensagem
            if (isNull(mensagem) == true) {

                mensagem = ex.getMessage();

            }

            // informa ao usuário o que houve
            JOptionPane.showMessageDialog(null, "Mysql diz: " + mensagem);

            // retorno
            return false;

        }

    }

    // desconecta do servidor
    public void desconecta() {

        // verifica se está conectado
        if (conectado == true) {

            try {

                // fecha a conexão
                conexao.close();

            } catch (SQLException ex) {

                // informa ao usuário o que houve
                JOptionPane.showMessageDialog(null, "Falha ao desconectar o servidor Mysql: " + ex.getMessage());

            }

        }
    }

    // executa um comando query
    public void executaQuery(String query) {

        // valida se está conectado
        if (isConectado() == true) {

            // trata execução de query
            try {

                // executa
                executa = conexao.prepareStatement(query);
                executa.execute();

            } catch (SQLException ex) {

                // mensagem de erro
                JOptionPane.showMessageDialog(null, "Falha ao executar query: " + ex.getMessage() + "\n" + query);

            }

        }

    }

    // retorna a conexão
    public Connection getConexao() {

        return conexao;

    }

    // retorna instrução
    public PreparedStatement getExecuta() {

        return executa;

    }

    // retorna resultado
    public ResultSet getResultado(String query) {

        // trata execução de query
        try {

            // executa
            executa = conexao.prepareStatement(query);
            resultado = executa.executeQuery();

            // retorno
            return resultado;

        } catch (SQLException ex) {

            // mensagem de erro
            JOptionPane.showMessageDialog(null, "Falha ao recuperar o ResultSet: " + ex.getMessage() + "\n" + query);

            // retorno
            return null;

        }

    }

    // retorna se está conectado
    public boolean isConectado() {

        return conectado;

    }

}
