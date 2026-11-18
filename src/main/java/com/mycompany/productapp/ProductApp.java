package com.mycompany.productapp;

import javax.swing.*;
import java.io.FileWriter;

public class ProductApp extends JFrame {

    private JTextField txtCustomer;
    private JComboBox<String> cmbProduct;
    private JTextField txtPrice;
    private JButton btnProcess;

    public ProductApp() {
        setTitle("Product App");
        setSize(450, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new java.awt.GridLayout(4, 2, 10, 15));

        // MENU - Q2.4
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");
        fileMenu.add(exitItem);
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        // FORM - Q2.2
        add(new JLabel("Customer Name:"));
        txtCustomer = new JTextField();
        add(txtCustomer);

        add(new JLabel("Select Product:"));
        cmbProduct = new JComboBox<>(new String[]{"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"});
        add(cmbProduct);

        add(new JLabel("Enter Price:"));
        txtPrice = new JTextField();
        add(txtPrice);

        add(new JLabel(""));
        btnProcess = new JButton("PROCESS");
        add(btnProcess);

        // BUTTONS
        btnProcess.addActionListener(e -> processSale());
        processItem.addActionListener(e -> processSale());
        exitItem.addActionListener(e -> System.exit(0));
        clearItem.addActionListener(e -> {
            txtCustomer.setText("");
            txtPrice.setText("");
            cmbProduct.setSelectedIndex(0);
        });
    }

    private void processSale() {
        try {
            String customer = txtCustomer.getText();
            String product = (String) cmbProduct.getSelectedItem();
            double price = Double.parseDouble(txtPrice.getText());

            ProductCalculations calc = new ProductCalculations(price);

            String message = "Customer Name: " + customer
                    + "\nProduct: " + product
                    + "\nVAT: R " + calc.GetVat()
                    + "\nCOMMISSION: R " + calc.GetCommission()
                    + "\nTOTAL: R " + calc.GetTotal();
            JOptionPane.showMessageDialog(this, message);

            // Save to data.txt - Q2.3
            FileWriter writer = new FileWriter("data.txt");
            writer.write("DATA LOG\n");
            writer.write("********************************************\n");
            writer.write("Customer Name: " + customer + "\n");
            writer.write("Product: " + product + "\n");
            writer.write("VAT: R " + calc.GetVat() + "\n");
            writer.write("COMMISSION: R " + calc.GetCommission() + "\n");
            writer.write("TOTAL: R " + calc.GetTotal() + "\n");
            writer.write("********************************************\n");
            writer.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
    }
}