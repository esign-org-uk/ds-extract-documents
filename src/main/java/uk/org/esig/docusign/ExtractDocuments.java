package uk.org.esig.docusign;

import uk.org.esig.docusign.view.ExtractFrame;
import javax.swing.UIManager;

class ExtractDocuments {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            ExtractFrame frame = new ExtractFrame();
            frame.setVisible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
