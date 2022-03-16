/*
trata dados
 */
package daobanco;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.text.NumberFormat;

/**
 *
 * @author Salomão Francisco da Silva - Fone: 62 99447-4551
 * @version 1.0
 */
public class TrataDados {

    // trata os dados
    public String trataDados(Object conteudo) {

        // trata excessão
        try {

            // remove espaços em branco no fim e no inicio
            conteudo = removeEspacoBrancoInicioFinal(conteudo);

            // remove acentos
            conteudo = removeAcentos(conteudo);
            conteudo = converteMinusculo(conteudo);
            conteudo = escapaDados(conteudo);

            // converte em string e retorna
            return (String) conteudo;

        } catch (java.lang.StringIndexOutOfBoundsException ex) {

            // retorno
            return "";

        }

    }

    // remove espaços em branco no fim e no inicio
    public String removeEspacoBrancoInicioFinal(Object conteudo) {

        // conteúdo de retorno
        String retorno = String.valueOf(conteudo);

        // remove espaços em banco
        retorno = retorno.trim().replaceAll("\\s+", " ");

        // retorno
        return retorno;

    }

    // scape de dados
    public String escapaDados(Object conteudo) {

        String retorno = (String) conteudo;

        // remove aspas
        retorno = retorno.replaceAll("^\"+|\"+$", "");
        retorno = retorno.replaceAll("^[\"']+|[\"']+$", "");
        retorno = retorno.replace("'", "");
        retorno = retorno.replace("\\", "");
        retorno = retorno.replace("/", "");
        retorno = retorno.replace("\"", "");

        // retorno
        return retorno;

    }

    // converte para minusculo
    public String converteMinusculo(Object conteudo) {

        String retorno = (String) conteudo;

        // converte a primeira letra para maisculo e o restante para minusculo
        retorno = retorno.substring(0, 1).toUpperCase().concat(retorno.substring(1).toLowerCase());

        // retorno
        return retorno;

    }

    // trata dados para remover acentos
    public String removeAcentos(Object conteudo) {

        // retorno
        String retorno = String.valueOf(conteudo);

        // retorno
        return Normalizer.normalize(retorno, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

    }

    // converte uma string para numero double
    public String formataDouble(String numero) {

        // retonro
        double retorno;

        // tratamento de erro
        try {

            // calcula
            retorno = Double.parseDouble(numero.replace(",", "."));

        } catch (java.lang.NumberFormatException ex) {

            // zero padrão
            retorno = 0;

        }

        // retorno
        return String.valueOf(retorno);

    }

    // valida se uma string é um número
    public boolean isNumero(String conteudo) {

        // valida conteúdo
        if (conteudo == null) {

            return false;

        }

        // tenta converter para double
        try {

            // converte para double
            double d = Double.parseDouble(conteudo);

        } catch (NumberFormatException nfe) {

            // retorno
            return false;

        }

        // retorno
        return true;

    }

    // converte string em moeda
    public String formataMoeda(Object numero) {

        // bigdecimal
        BigDecimal valor = new BigDecimal(String.valueOf(numero));

        // formata o numero
        NumberFormat nf = NumberFormat.getCurrencyInstance();

        // retorno
        return nf.format(valor);

    }

}
