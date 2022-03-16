/*
Realiza a conclusão da compra do pedido
 */
package modelo;

import daobanco.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static java.util.Objects.isNull;
import sistema.Algoritmos;

/**
 *
 * @author Salomão Francisco da Silva - Fone: 62 99447-4551
 * @version 1.0
 */
public class Pedido {

    private List listaProdutos = new ArrayList();
    private Produto produto;
    private Cliente cliente = null;
    private String pagamentoRealizado = "Nao";
    private String entrega = "Nao";
    private String comanda = "Nao";
    private String comandaMesa = null;
    private String comandaFuncionario = null;

    // adiciona um produto por seu id
    public void addProduto(String idProduto, int quantidade) {

        // banco de dados
        BancoDados banco = new BancoDados();

        // produto
        produto = new Produto();

        // recupera os dados do produto
        Map dadosProduto = banco.recuperaDadosChavePrimaria("id", idProduto, "tb_produto");

        // popula o produto
        produto.setCaracteristicas((String) dadosProduto.get("caracteristicas"));
        produto.setCodigo((String) dadosProduto.get("codigo"));
        produto.setNome((String) dadosProduto.get("nome"));
        produto.setPreco((String) dadosProduto.get("preco"));
        produto.setQuantidade(quantidade);

        // adiciona o produto
        listaProdutos.add(produto);

    }

    // retorna o total a pagar
    public double getTotalPagar() {

        double total = 0.0;

        // lista os produtos na lista
        for (Object a : listaProdutos) {

            // cast para produto
            Produto produto = (Produto) a;

            // atualiza o total
            total += (produto.getPreco() * produto.getQuantidade());

        }

        // retorno
        return total;

    }

    // seta o cliente
    public void setCliente(Cliente cliente) {

        this.cliente = cliente;

    }

    // se um cliente estiver nulo então inicialize
    private void inicializaClienteNulo() {

        // valida nulo
        if (isNull(cliente) == true) {

            cliente = new Cliente();
            cliente.setNome("");
            cliente.setCpf("");
            cliente.setTelefone("");
            cliente.setEndereco("");

        }

    }

    // finaliza a compra
    public void finalizaCompra() {

        // se um cliente estiver nulo então inicialize
        inicializaClienteNulo();

        // valida a quantidade de produtos
        if (listaProdutos.isEmpty() == false) {

            double total = 0.0;
            String listaItens = "";

            // lista os produtos na lista
            for (Object a : listaProdutos) {

                // cast para produto
                Produto produto = (Produto) a;

                // atualiza o total
                total += (produto.getPreco() * produto.getQuantidade());

                // atualiza a lista de itens
                listaItens = listaItens + produto.getCodigo() + " >> " + produto.getNome() + " >> " + produto.getPreco() + " >> " + produto.getQuantidade() + "\n";

            }

            // dados
            Map dados = new LinkedHashMap();

            // popula
            dados.put("cliente_nome", cliente.getNome());
            dados.put("cliente_cpf", cliente.getCpf());
            dados.put("cliente_telefone", cliente.getTelefone());
            dados.put("cliente_endereco", cliente.getEndereco());
            dados.put("lista_produtos", listaItens);
            dados.put("pagamento_realizado", pagamentoRealizado);
            dados.put("entrega", entrega);
            dados.put("comanda", comanda);
            dados.put("comanda_mesa", comandaMesa);
            dados.put("comanda_funcionario", comandaFuncionario);
            dados.put("total", Algoritmos.arredondaNumero(total));
            dados.put("data", Algoritmos.getDataAtual());

            // grava no banco
            BancoDados banco = new BancoDados();
            banco.inserirDados(dados, "tb_pedido");

        }

    }

    // retorna o codigo da compra
    public String getCodigoCompra() {

        // banco de dados
        BancoDados banco = new BancoDados();

        // query
        String query = "select id from tb_pedido order by id desc limit 1;";

        // dados
        Map dados = banco.getDadosQueryRecuperados(query, 0);

        // retorno
        return String.valueOf(dados.get("id"));

    }

    // seta pagamento realizado
    public void setPagamentoRealizado() {

        this.pagamentoRealizado = "Sim";

    }

    // seta entrega
    public void setEntrega() {

        this.entrega = "Sim";

    }

    // seta comanda
    public void setComanda() {

        this.comanda = "Sim";

    }

    // seta mesa da comanda
    public void setComandaMesa(String comandaMesa) {

        this.comandaMesa = comandaMesa;

    }

    // seta funcionario que atendeu a comanda
    public void setComandaFuncionario(String comandaFuncionario) {

        this.comandaFuncionario = comandaFuncionario;

    }

    // retorna a quantidade de itens adiconados
    public int getQuantidadeItens() {

        return this.listaProdutos.size();

    }

}
