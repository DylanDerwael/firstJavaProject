package Services;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by dylan on 25.12.14.
 */
public class ColorRenderer extends JLabel implements TableCellRenderer {
    private final boolean isBordered;
    private Border selectedBorder, unselectedBorder;

    public ColorRenderer(boolean isBordered) {
        this.isBordered = isBordered;
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(
            JTable table, Object obj,
            boolean isSelected, boolean hasFocus,
            int row, int column) {

        if (obj instanceof Color) {
            Color newColor = (Color) obj;
            this.setBackground(newColor);
        }
        if (obj instanceof FotoHandler) {
            FotoHandler newFoto = (FotoHandler) obj;
            this.setIcon(new ImageIcon(newFoto.getScaledFoto()));
        }


        if (isBordered) {
            if (isSelected) {
                setBorder(selectedBorder);
            } else {
                setBorder(unselectedBorder);
            }
        }

        setToolTipText("?");
        return this;
    }
}