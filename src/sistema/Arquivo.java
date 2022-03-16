/*
Manipula arquivos, como abrir, copiar, criar pasta etc.
 */
package sistema;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.util.Objects.isNull;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Salomão Francisco da Silva - Fone: 62 99447-4551
 * @version 1.0
 */
public class Arquivo {

    // retorna a extenção do arquivo
    public String getExtensaoArquivo(String origem) {

        // arquivo
        File arquivo = new File(origem);

        // pega o nome do arquivo
        String nomeArquivo = arquivo.getName();

        // valida ponto de extensão
        if (nomeArquivo.lastIndexOf(".") != -1 && nomeArquivo.lastIndexOf(".") != 0) {

            // retorno
            return "." + nomeArquivo.substring(nomeArquivo.lastIndexOf(".") + 1);

        } else {

            // retorno
            return null;

        }

    }

    // abre um arquivo e retorna uma string com sua localização
    public String abrirDialogoSelecaoArquivo(String extensao) {

        // dialogo para selecionar arquivo
        JFileChooser selecionaArquivo = new JFileChooser();

        // opção
        int opcao = selecionaArquivo.showOpenDialog(null);

        // valida opção
        if (opcao == JFileChooser.APPROVE_OPTION) {

            // extensão
            String extensaoArquivo = getExtensaoArquivo(selecionaArquivo.getSelectedFile().getAbsoluteFile().toString());

            // verifica a extensao
            if (extensao.equals(extensaoArquivo)) {

                // retorno
                return selecionaArquivo.getSelectedFile().getAbsolutePath();

            } else {

                // mensagem de erro
                JOptionPane.showMessageDialog(null, "Extensão de arquivo incompatível!");

                // retorno
                return null;
            }

        }

        // retorno padrão
        return null;

    }

    // cria uma pasta
    public void criarPasta(String pastaDestino) {

        File arquivo = new File(pastaDestino);
        arquivo.mkdir();

    }

    // copia arquivo de uma pasta para outra
    private void copiaBitsArquivo(String origem, String destino) throws IOException {

        // leitura de dados
        OutputStream out;

        // excessão
        try (InputStream in = new FileInputStream(origem)) {

            out = new FileOutputStream(destino);
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {

                out.write(buf, 0, len);

            }

            // fecha o arquivo
            out.close();

        } catch (java.lang.NullPointerException ex) {

        }

    }

    // copia arquivo
    public void copiarArquivo(String pastaDestino, String origem, String destino) {

        // cria uma pasta
        criarPasta(pastaDestino);

        // trata excessão
        try {

            // copia arquivo de uma pasta para outra
            copiaBitsArquivo(origem, destino);

        } catch (IOException ex) {

            // mensagem de erro
            Algoritmos.dialogo("Falha ao copiar os arquivos\nOrigem: " + origem + "\nDestino: " + destino);

        }

    }

    // exclui arquivo
    public boolean excluirArquivo(String origem) {

        // valida origem
        if (isNull(origem) == false) {

            // arquivo
            File arquivo = new File(origem);

            // apaga e retorna o booleano
            return arquivo.delete();

        } else {

            // retorno
            return false;

        }

    }

}
