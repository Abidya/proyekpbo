import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Main extends JFrame {
    private JTextField quantityField;
    private JComboBox<String> serviceComboBox;
    private JTextArea receiptArea;
    private JTable dataTable;
    private DefaultTableModel tableModel;
    private JLabel priceInfoLabel;
    private JButton addButton;
    private JButton calculateButton;

    private static final double MINIMUM_TOTAL_FOR_DISCOUNT = 50_000;
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private static final Color ACCENT_COLOR = new Color(236, 240, 241);

    public Main() {
        setTitle("Laundry Service Premium");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main Panel with Gradient Background
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, PRIMARY_COLOR, getWidth(), getHeight(), SECONDARY_COLOR);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JPanel headerPanel = createHeaderPanel();
        JPanel inputPanel = createInputPanel();
        JPanel tablePanel = createTablePanel();
        JPanel receiptPanel = createReceiptPanel();

        // Content Panel with Layout
        JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel.setOpaque(false);
        contentPanel.add(inputPanel, BorderLayout.WEST);
        contentPanel.add(tablePanel, BorderLayout.CENTER);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(receiptPanel, BorderLayout.SOUTH);

        add(mainPanel);
        initializeEventListeners();
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        headerPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Laundry Service Premium");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        return headerPanel;
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setOpaque(false);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel serviceLabel = createStyledLabel("Pilih Jenis Servis");
        serviceComboBox = new JComboBox<>(new String[]{"Regular Laundry", "Cuci Bersih"});
        styleComboBox(serviceComboBox);

        JLabel quantityLabel = createStyledLabel("Jumlah (kg)");
        quantityField = new JTextField(10);
        styleTextField(quantityField);

        priceInfoLabel = new JLabel();
        priceInfoLabel.setForeground(Color.WHITE);
        priceInfoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        addButton = createStyledButton("Tambah");
        calculateButton = createStyledButton("Hitung Total");

        inputPanel.add(serviceLabel);
        inputPanel.add(Box.createVerticalStrut(5));
        inputPanel.add(serviceComboBox);
        inputPanel.add(Box.createVerticalStrut(15));
        inputPanel.add(quantityLabel);
        inputPanel.add(Box.createVerticalStrut(5));
        inputPanel.add(quantityField);
        inputPanel.add(Box.createVerticalStrut(15));
        inputPanel.add(priceInfoLabel);
        inputPanel.add(Box.createVerticalStrut(20));
        inputPanel.add(addButton);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(calculateButton);

        return inputPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setOpaque(false);

        tableModel = new DefaultTableModel(new String[]{"Service", "Jumlah (kg)", "Harga (Rp)"}, 0);
        dataTable = new JTable(tableModel);
        styleTable(dataTable);

        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        tablePanel.add(scrollPane, BorderLayout.CENTER);
        return tablePanel;
    }

    private JPanel createReceiptPanel() {
        JPanel receiptPanel = new JPanel(new BorderLayout());
        receiptPanel.setOpaque(false);

        receiptArea = new JTextArea(8, 30);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        receiptArea.setBackground(Color.WHITE);
        receiptArea.setForeground(Color.BLACK);
        receiptArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        receiptArea.setLineWrap(true);
        receiptArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(receiptArea);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setOpaque(true);
        scrollPane.setBorder(BorderFactory.createTitledBorder(null, "Resi",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 14), Color.BLACK));

        receiptPanel.add(scrollPane, BorderLayout.CENTER);
        return receiptPanel;
    }


    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(SECONDARY_COLOR);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        return button;
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(Color.BLACK);
    }

    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    private void styleTable(JTable table) {
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFillsViewportHeight(true);
    }

    private void styleReceiptArea(JTextArea area) {
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setBackground(new Color(255, 255, 255, 200));
        area.setForeground(Color.BLACK);
        area.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void initializeEventListeners() {
        addButton.addActionListener(e -> addService());
        calculateButton.addActionListener(e -> calculateTotal());
        serviceComboBox.addActionListener(e -> updatePriceInfo());
        updatePriceInfo();
    }

    private void addService() {
        try {
            String serviceType = (String) serviceComboBox.getSelectedItem();
            int jumlah = Integer.parseInt(quantityField.getText().trim());
            if (jumlah <= 0) {
                JOptionPane.showMessageDialog(this, "Jumlah harus lebih besar dari 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double harga = serviceType.equals("Regular Laundry") ? 5000 : 10000;
            double totalHarga = harga * jumlah;
            DecimalFormat formatter = new DecimalFormat("#,###.##");
            tableModel.addRow(new Object[]{serviceType, jumlah, "Rp " + formatter.format(totalHarga)});
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan jumlah yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateTotal() {
        try {
            double total = 0;
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String hargaStr = tableModel.getValueAt(i, 2).toString().replace("Rp", "").trim().replace(",", "");
                total += Double.parseDouble(hargaStr);
            }

            receiptArea.setText("Resi:\n");
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                receiptArea.append(tableModel.getValueAt(i, 0) + " - " +
                        tableModel.getValueAt(i, 1) + " kg - " + tableModel.getValueAt(i, 2) + "\n");
            }
            receiptArea.append("\nTotal: Rp" + String.format("%,.2f", total) + "\n");

            if (total >= MINIMUM_TOTAL_FOR_DISCOUNT) {
                double discountAmount = total * 0.15;
                double finalTotal = total - discountAmount;
                receiptArea.append("Diskon 15%: Rp" + String.format("%,.2f", discountAmount) + "\n");
                receiptArea.append("Total dengan Diskon: Rp" + String.format("%,.2f", finalTotal));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghitung total.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePriceInfo() {
        String serviceType = (String) serviceComboBox.getSelectedItem();
        priceInfoLabel.setText(serviceType.equals("Regular Laundry") ?
                "Harga per cuci (Regular Laundry): Rp5.000/kg" :
                "Harga per cuci (Cuci Bersih): Rp10.000/kg");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}
