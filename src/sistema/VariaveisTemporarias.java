package sistema;

/**
 *
 * @author Salomão Francisco da Silva - Fone: 62 99447-4551
 * @version 1.0
 */
public class VariaveisTemporarias {

    public static String id;

    // retorna o id e limpa
    public static String getID(boolean limpar) {

        // variável de retorno
        String retorno = id;

        // valida se vai limpar
        if (limpar == true) {

            // limpa variável
            id = null;

        }

        // retorno
        return retorno;

    }

    // seta o id
    public static void setID(String conteudo) {

        id = conteudo;

    }

}
