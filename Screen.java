import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JFrame {

    public Screen() {
        setTitle("Welcome to Laundry Service Univeristas Ahmad Dahlan");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, Color.CYAN, getWidth(), getHeight(), Color.BLUE);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        ImageIcon imageIcon = new ImageIcon("src/laundry.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("Laundry Service Univeritas Ahmad Dahlan");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton nextButton = new JButton("Selanjutnya");
        nextButton.setFont(new Font("Arial", Font.BOLD, 18));
        nextButton.setBackground(new Color(34, 167, 240));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);
        nextButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Main().setVisible(true);
            }
        });

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(imageLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(nextButton);

        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Screen splashScreen = new Screen();
            splashScreen.setVisible(true);
        });
    }
}
