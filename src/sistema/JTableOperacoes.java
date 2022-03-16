package sistema;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Salomão Francisco da Silva - Fone: 62 99447-4551
 * @version 1.0
 */
public class JTableOperacoes {

    private JTable obj_tabela;
    private DefaultTableModel modelo;
    private JFrame janela;
    private List lista;

    // construtor
    public JTableOperacoes(JTable obj_tabela, JFrame janela) {

        // seta a janela
        this.janela = janela;

        // informa a JTable de trabalho
        this.obj_tabela = obj_tabela;

        // model para manipular linhas e colunas
        modelo = (DefaultTableModel) obj_tabela.getModel();

        // adciona evento
        addEvento();

    }

    // adciona evento
    private void addEvento() {

        // valida se passou a janela
        if (janela != null) {

            // novo evento
            Action delete;
            delete = new AbstractAction() {

                // sobrecarga
                @Override
                public void actionPerformed(ActionEvent e) {

                    // pega o index
                    int index = Integer.valueOf(e.getActionCommand());

                    // abre a janela
                    abrirJanela((String) lista.get(index));

                }
            };

            // adiciona o evento ao botão
            ButtonColumn buttonColumn = new ButtonColumn(obj_tabela, delete, obj_tabela.getColumnCount() - 1);

        }

    }

    // abre a janela
    private void abrirJanela(String index) {

        // valida se passou a janela
        if (janela != null) {

            // envia o id
            VariaveisTemporarias.setID(index);

            try {

                // nova instancia
                janela = janela.getClass().newInstance();

            } catch (InstantiationException | IllegalAccessException ex) {

            }

            // exibe a janela
            janela.setVisible(true);

        }

    }

    // add linhas
    public void addLinhas(Object objeto[], List lista) {

        // atualiza a lista
        this.lista = lista;

        // adiciona linha
        modelo.addRow(objeto);

    }

    // remove as linhas
    public void removeLinhas() {

        // removendo uma linha
        modelo.setRowCount(0);

    }

}
