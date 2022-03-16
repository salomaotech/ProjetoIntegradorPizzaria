package modelo;

/**
 *
 * @author Salomão Francisco da Silva - Fone: 62 99447-4551
 * @version 1.0
 */
public class Produto {

    private String codigo;
    private String nome;
    private double preco;
    private String caracteristicas;
    private int quantidade;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(String preco) {

        // trata conversão para double
        try {

            this.preco = Double.parseDouble(preco);

        } catch (java.lang.NumberFormatException ex) {

            this.preco = 0.0;

        }

    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
