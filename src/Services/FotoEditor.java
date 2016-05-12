package Services;

/**
 * Created by dylan on 25.12.14.
 */

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author 1135195
 */


public class FotoEditor extends AbstractCellEditor
        implements TableCellEditor,
        ActionListener{

    private JFileChooser fc;
    private FotoHandler currentFoto;
    private JButton button;
    private JDialog dialog;
    protected static final String EDIT = "edit";

    public FotoEditor() {
        fc = new JFileChooser();
        button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);
    }

    @Override
    public Object getCellEditorValue() {
        return currentFoto;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        currentFoto=(FotoHandler)value;
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            int returnVal = fc.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                currentFoto.read(file.getPath());
            }
            fireEditingStopped();
        }
    }
}

