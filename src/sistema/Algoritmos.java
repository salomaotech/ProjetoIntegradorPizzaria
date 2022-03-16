/*
Tem alguns algoritmos para otimizar
 */
package sistema;

import com.toedter.calendar.JDateChooser;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Salomão Francisco da Silva - Fone: 62 99447-4551
 * @version 1.0
 */
public class Algoritmos {

    // converte uma string para numero double
    public static double ConverteStringDouble(String numero) {

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
        return retorno;

    }

    // retorna numeros de string
    public static List retronaNumerosString(String conteudo) {

        // lista de retorno
        List retorno = new ArrayList();

        // dados
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(conteudo);

        // procura ocorrencias
        while (m.find()) {

            // popula o array
            retorno.add(m.group());

        }

        // retorno
        return retorno;

    }

    // remove linhas em branco de string
    public static String removeLinhasEmBranco(String conteudo) {

        // remove
        conteudo = conteudo.trim();
        conteudo = conteudo.replace("\n", "").replace("\r", "");

        // retorno
        return conteudo;

    }

    // retorna uma string convertida em data
    public static Date converteStringData(String data) {

        // em caso da data estar no formato 00-00-0000
        data = data.replace("-", "/");

        // array com elementos da data
        String data_array[] = data.split("/");

        // valida
        if (data_array.length != 3) {

            return null;

        }

        // meses do ano
        Map meses = new HashMap();
        meses.put("jan", "01");
        meses.put("fev", "02");
        meses.put("mar", "03");
        meses.put("abr", "04");
        meses.put("maio", "05");
        meses.put("jun", "06");
        meses.put("jul", "07");
        meses.put("ago", "08");
        meses.put("set", "09");
        meses.put("out", "10");
        meses.put("nov", "11");
        meses.put("dez", "12");

        // informa se há uma mes abreviado
        String mes_abreviado = null;

        // lista meses
        for (Object key : meses.keySet()) {

            // mes
            String mes = (String) key;

            // verifica igualdade
            if (mes.equals(data_array[1]) == true) {

                mes_abreviado = (String) meses.get(mes);
                break;

            }

        }

        // valida se há um mes abreviado
        if (mes_abreviado != null) {

            data = data_array[0] + "/" + mes_abreviado + "/" + data_array[2];

        }

        // try
        try {

            // formata a data
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = (Date) formatter.parse(data);

            // retorno
            return date;

        } catch (ParseException ex) {

            return null;

        }

    }

    // retorna se é um número
    public static boolean getStringNumerica(String str) {

        // trata excessão
        try {

            // converte para double
            Double.parseDouble(str);

            // retorno
            return true;

        } catch (NumberFormatException e) {

            // retorno
            return false;

        }

    }

    // exibe um dialogo
    public static void dialogo(String conteudo) {

        JOptionPane.showMessageDialog(null, conteudo);

    }

    // retorna se a data inicial é maior do que a final
    public static boolean retornaDataInicialMaiorFinal(String data_inicial, String data_final) {

        // formatador
        SimpleDateFormat formatador = new SimpleDateFormat("dd/mm/yyyy");

        // trata excessão
        try {

            // formata as datas
            Date data_primaria = formatador.parse((String) data_inicial);
            Date data_secundaria = formatador.parse((String) data_final);

            // retorno
            return data_primaria.after(data_secundaria);

        } catch (ParseException ex) {

            return false;

        }

    }

    // retorna se a data inicial é igual do que a final
    public static boolean retornaDataInicialIgualFinal(String data_inicial, String data_final) {

        // formatador
        SimpleDateFormat formatador = new SimpleDateFormat("dd/mm/yyyy");

        // trata excessão
        try {

            // formata as datas
            Date data_primaria = formatador.parse((String) data_inicial);
            Date data_secundaria = formatador.parse((String) data_final);

            // retorno
            return data_primaria.equals(data_secundaria);

        } catch (ParseException ex) {

            return false;

        }

    }

    // retorna se a data inicial é menor ou igual a data final
    public static boolean retornaDataInicialMenorIgualFinal(String data_inicial, String data_final) {

        // formatador
        SimpleDateFormat formatador = new SimpleDateFormat("dd/mm/yyyy");

        // trata excessão
        try {

            // formata as datas
            Date data_primaria = formatador.parse((String) data_inicial);
            Date data_secundaria = formatador.parse((String) data_final);

            // valida se é menor, ou se é igual
            return data_primaria.before(data_secundaria) == true || data_primaria.equals(data_secundaria) == true;

        } catch (ParseException ex) {

            return false;

        }

    }

    // retorna lista sem duplicatas
    public static List retornaListaSemDuplicatas(List lista) {

        // novo e remove
        Set<String> set = new HashSet<>(lista);
        lista.clear();
        lista.addAll(set);

        // retorno
        return lista;

    }

    // converte string em moeda
    public static String formataMoeda(Object numero) {

        // bigdecimal
        BigDecimal valor = new BigDecimal(String.valueOf(numero));

        // formata o numero
        NumberFormat nf = NumberFormat.getCurrencyInstance();

        // retorno
        return nf.format(valor);

    }

    // arredonda um numero
    public static double arredondaNumero(Object numero) {

        // trata erro
        try {

            // novo numero
            double novoNumero = ConverteStringDouble((String) numero);

            // big decimal
            BigDecimal bd = new BigDecimal(novoNumero).setScale(3, RoundingMode.HALF_EVEN);

            // retorno
            return bd.doubleValue();

        } catch (java.lang.ClassCastException ex) {

            // big decimal
            BigDecimal bd = new BigDecimal((double) numero).setScale(3, RoundingMode.HALF_EVEN);

            // retorno
            return bd.doubleValue();

        }

    }

    // retorna o numero em string de forma monetária
    public static String retornaNumeroArredondadoMonetario(Object numero) {

        // novo numero
        double novoNumero = arredondaNumero(numero);

        // retorno
        return formataMoeda(novoNumero);

    }

    // retorna a confirmação de um diálogo
    public static boolean confirmaDialogo(String mensagem) {

        // pega o código de retorno
        int retorno = JOptionPane.showConfirmDialog(null, mensagem);

        // retorno
        return retorno == 0;

    }

    // retorna a data atual
    public static String getDataAtual() {

        // formato
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();

        // retorno
        return dtf.format(now);

    }

    // retorna uma data formatada de jcalendar
    public static String retornaDataFormatadaJCalendar(JDateChooser JDData) {

        // valida se a data foi informada
        if (JDData.getDate() == null) {

            return "";

        }

        // data
        Date data = JDData.getDate();

        // formata data
        SimpleDateFormat dataf = new SimpleDateFormat("dd-MM-yyyy");

        // retorno
        return dataf.format(data);

    }

}
