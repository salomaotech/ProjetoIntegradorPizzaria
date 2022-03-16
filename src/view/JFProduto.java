/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import daobanco.BancoDados;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.Objects.isNull;
import javax.swing.JOptionPane;
import sistema.*;

/**
 *
 * @author Salomão Francisco da Silva - Fone: 62 99447-4551
 * @version 1.0
 */
public class JFProduto extends javax.swing.JFrame {

    private String id;

    public JFProduto() {

        // inicializa componentes
        initComponents();

        // cria a tabela do produto
        criaTabela();

        // carrega os dados pelo id
        carregaDados();

        // carrega a imagem do produto
        carregaImagem();

    }

    // cria a tabela do produto
    private void criaTabela() {

        BancoDados banco = new BancoDados();
        Map colunas = new LinkedHashMap();
        colunas.put("codigo", "varchar(16)");
        colunas.put("nome", "varchar(256)");
        colunas.put("preco", "varchar(16)");
        colunas.put("caracteristicas", "varchar(300)");
        banco.criarTabela(colunas, "tb_produto");

    }

    // carrega os dados pelo id
    private void carregaDados() {

        // id
        id = VariaveisTemporarias.getID(true);

        // valida se o id não é nulo
        if (isNull(id) == false) {

            // recupera os dados do banco
            BancoDados banco = new BancoDados();
            Map dados = banco.recuperaDadosChavePrimaria("id", id, "tb_produto");

            // seta os dados
            JTCodigo.setText((String) dados.get("codigo"));
            JTNome.setText((String) dados.get("nome"));
            JTPreco.setText((String) dados.get("preco"));
            JTCaracteristicas.setText((String) dados.get("caracteristicas"));

            // seta campos
            jButton3.setVisible(false);
            jButton2.setEnabled(true);
            jButton1.setText("Atualizar");
            JTCodigo.setEnabled(false);

        } else {

            // desabilita
            jButton4.setEnabled(false);

        }

    }

    // carrega a imagem do produto
    public void carregaImagem() {

        // valida id
        if (isNull(id) == false) {

            // banco de dados
            BancoDados banco = new BancoDados();

            // carrega a imagem
            CarregaImagem imagem = new CarregaImagem(banco.getURLSERVIDOR() + "/imagens/" + id + ".jpg", this.JLImagem.getHeight(), this.JLImagem.getWidth());

            // seta a imagem
            this.JLImagem.setText("");
            this.JLImagem.setIcon(imagem.getImagem());

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        JTCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        JTNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        JTPreco = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTCaracteristicas = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        JLImagem = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de produto");
        setResizable(false);

        jLabel1.setText("Código:");

        jLabel2.setText("Nome:");

        jLabel3.setText("Preço:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Características"));

        JTCaracteristicas.setColumns(20);
        JTCaracteristicas.setRows(5);
        jScrollPane1.setViewportView(JTCaracteristicas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setText("Gravar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Excluir");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Pesquisar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Foto do produto"));

        JLImagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLImagem.setText("...");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton4.setText("Carregar imagem...");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JTNome)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JTPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(JTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(JTNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(JTPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // valida os campos
    private boolean validarCampos() {

        boolean validado = true;

        // banco
        BancoDados banco = new BancoDados();

        // valida campo
        if (this.JTCodigo.getText().length() == 0) {

            // mensagem
            JOptionPane.showMessageDialog(null, "O código não pode ficar em branco.");
            JTCodigo.requestFocus();

            // não validado
            validado = false;

        }

        // valida campo
        if (this.JTNome.getText().length() == 0) {

            // mensagem
            JOptionPane.showMessageDialog(null, "O nome não pode ficar em branco.");
            JTNome.requestFocus();

            // não validado
            validado = false;

        }

        // valida campo
        if (this.JTPreco.getText().length() == 0) {

            // mensagem
            JOptionPane.showMessageDialog(null, "O preço não pode ficar em branco.");
            JTPreco.requestFocus();

            // não validado
            validado = false;

        } else {

            // valida se o preço é um número
            if (banco.isNumero(this.JTPreco.getText()) == false) {

                // mensagem
                JOptionPane.showMessageDialog(null, "O preço não está na forma numérica correta!\nExemplo correto 22.99");
                JTPreco.requestFocus();

                // não validado
                validado = false;

            }

        }

        // valida campo
        if (this.JTCaracteristicas.getText().length() == 0) {

            // mensagem
            JOptionPane.showMessageDialog(null, "As características não podem ficar em branco.");
            JTCaracteristicas.requestFocus();

            // não validado
            validado = false;

        }

        // valida o id
        if (isNull(id) == true) {

            // valida se o código do produto já está cadastrado
            if (banco.isRegistroExiste("tb_produto", "codigo", this.JTCodigo.getText()) == true) {

                // mensagem
                JOptionPane.showMessageDialog(null, "Código de produto já cadastrado.");

                // não validado
                validado = false;

            }

        }

        // retorno
        return validado;

    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // valida os campos
        if (validarCampos() == true) {

            // insere dados na tabela
            BancoDados banco = new BancoDados();
            Map dados = new LinkedHashMap();
            dados.put("codigo", this.JTCodigo.getText());
            dados.put("nome", this.JTNome.getText());
            dados.put("preco", banco.formataDouble(this.JTPreco.getText()));
            dados.put("caracteristicas", this.JTCaracteristicas.getText());

            // se não houver id cadastra, caso contrário atualiza
            if (isNull(this.id) == true) {

                banco.inserirDados(dados, "tb_produto");

            } else {

                dados.put("id", this.id);
                banco.AtualizarDados(dados, "tb_produto");

            }

            // mensagem de sucesso
            Algoritmos.dialogo("Gravado com sucesso!");
            this.dispose();

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        // abre a janela
        JFProdutoView janela = new JFProdutoView();
        janela.setVisible(true);

        // fecha a janela atual
        this.setVisible(false);
        this.dispose();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        // banco de dados
        BancoDados banco = new BancoDados();

        // valida se removeu o registro
        if (banco.removeRegistroChavePrimaria("id", id, "tb_produto", "Remover produto?") == true) {

            // arquivo
            Arquivo arquivo = new Arquivo();

            // url da imagem
            String urlImagem = banco.getLOCALINSTALACAOSERVIDOR() + "/imagens/" + id + ".jpg";

            // exclui imagem
            arquivo.excluirArquivo(urlImagem);

            // fecha a janela atual
            this.setVisible(false);
            this.dispose();

        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        // banco de dados
        BancoDados banco = new BancoDados();

        // classe para copiar arquivos
        Arquivo arquivo = new Arquivo();

        // origem e destino
        String pastaDestino = banco.getLOCALINSTALACAOSERVIDOR() + "/imagens/";
        String origem = arquivo.abrirDialogoSelecaoArquivo(".jpg");
        String destino = pastaDestino + id + ".jpg";

        // copia
        arquivo.copiarArquivo(pastaDestino, origem, destino);

        // carrega a imagem do produto
        carregaImagem();

    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFProduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLImagem;
    private javax.swing.JTextArea JTCaracteristicas;
    private javax.swing.JTextField JTCodigo;
    private javax.swing.JTextField JTNome;
    private javax.swing.JTextField JTPreco;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
