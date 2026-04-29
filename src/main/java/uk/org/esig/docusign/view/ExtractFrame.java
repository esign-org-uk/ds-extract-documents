package uk.org.esig.docusign.view;

import uk.org.esig.docusign.control.ExtractDragDropListener;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.dnd.DropTarget;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class ExtractFrame extends JFrame {
    private static final String DS_Image = "ds.png";
    public ExtractFrame() {
        try {
            getContentPane().setPreferredSize(new Dimension(300, 300));
            setAlwaysOnTop(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setTitle("ds-extract-documents");
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        Image base64 = toolkit.getImage(getClass().getResource(DS_Image));
        setIconImage(base64);
        JPanel panel = new JPanel();
        JLabel label = new JLabel(new ImageIcon(base64));
        new DropTarget(label, new ExtractDragDropListener(this));
        panel.add(label);
        add(panel);
        pack();
        setLocation(screenSize.width-this.getWidth()-20, 20);
    }
}
