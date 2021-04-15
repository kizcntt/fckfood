import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class Lox {

    public static void main(String[] args) {
        new Lox();
    }

    public Lox() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        public TestPane() {
            setBorder(new EmptyBorder(10, 10, 10, 10));
            JButton fancyPB = new JButton("Restart Now");
            MetroLookAndFeel ui = new MetroLookAndFeel();
            ui.Mau("do");
            fancyPB.setUI(ui);
            
            JButton normalPB = new JButton("Restart Now");
            
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.insets = new Insets(4, 4, 4, 4);

            add(fancyPB, gbc);
            add(normalPB, gbc);
        }

       

    }

    public class MetroLookAndFeel extends BasicButtonUI {

        // This could be computed properties, where the border color
        // is determined based on other properties
        private String mau;
        private Border focusBorder = new CompoundBorder(new LineBorder(Color.DARK_GRAY, 3), new EmptyBorder(7, 13, 7, 14));
        private Border unfocusedBorder = new EmptyBorder(10, 14, 10, 14);
        
        public String Mau(String c){
            this.mau = c;
            return this.mau;
        } 
        //@Override
        public void installDefaults(AbstractButton b) {
            String m = this.mau;
            super.installDefaults(b);
            Font f = new Font("Segoe UI", Font.PLAIN, 20);
            Color co = new Color(0x00000);
            switch(m){
                case "do":
                    co = new Color(255, 51, 51);
                    break;
                case "cam":
                    co = new Color(255,153,102);
                    break;
                case "xanh":
                    co = new Color(51,153,255);
                    break;
                case "dodam":
                    co = new Color(204,0,51);
                    break;
                case "":
                    co = new Color(0xff0000);
            }
            b.setBackground(co);
            b.setFont(f);
            b.setContentAreaFilled(false);
            b.setFocusPainted(false);
            // This seems like an oddity...
            b.setFocusable(false);
            b.setForeground(Color.BLACK);
//            b.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
            b.setBorder(unfocusedBorder);
        }

        @Override
        public void installListeners(AbstractButton b) {
            super.installListeners(b);
            b.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    ((JButton)e.getSource()).setBorder(focusBorder);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    ((JButton)e.getSource()).setBorder(unfocusedBorder);
                }                
            });
        }

    }

}