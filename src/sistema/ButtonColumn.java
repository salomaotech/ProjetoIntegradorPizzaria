package sistema;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Salomão Francisco da Silva - Fone: 62 99447-4551
 * @version 1.0
 */
public final class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener {

    private JTable table;
    private Action action;
    private JButton renderButton;
    private JButton editButton;
    private Object editorValue;
    private boolean isButtonColumnEditor;

    public ButtonColumn(JTable table, Action action, int column) {

        this.table = table;
        this.action = action;

        renderButton = new JButton();
        editButton = new JButton();
        editButton.setFocusPainted(false);
        editButton.addActionListener(this);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer(this);
        columnModel.getColumn(column).setCellEditor(this);
        table.addMouseListener(this);

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        if (value == null) {

            editButton.setText("");
            editButton.setIcon(null);

        } else if (value instanceof Icon) {

            editButton.setText("");
            editButton.setIcon((Icon) value);

        } else {

            editButton.setText(value.toString());
            editButton.setIcon(null);

        }

        this.editorValue = value;
        return editButton;

    }

    @Override
    public Object getCellEditorValue() {

        return editorValue;

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        // título do botão
        renderButton.setText("Editar");

        // retorno
        return renderButton;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int row = table.convertRowIndexToModel(table.getEditingRow());
        fireEditingStopped();

        ActionEvent event = new ActionEvent(table, ActionEvent.ACTION_PERFORMED, "" + row);
        action.actionPerformed(event);

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (table.isEditing() && table.getCellEditor() == this) {

            isButtonColumnEditor = true;

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (isButtonColumnEditor && table.isEditing()) {

            table.getCellEditor().stopCellEditing();

        }

        isButtonColumnEditor = false;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
