package Frame;

import Logic.*;
import Entity.*;

import java.lang.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class FrameDesign extends JFrame {
    private JLabel label1, label2, label3, label4, label5, label6, label7;
    private JTextField tf1, tf2, tf3;
    private Font BOLD_Font = new Font("Arial", Font.BOLD, 30);
    private Font Label_Font = new Font("Arial", Font.PLAIN, 19);
    private Font Tf_Font = new Font("Arial", Font.PLAIN, 18);
    private JButton button1 = new JButton("+");
    private JButton button2 = new JButton("-");
    private JButton addButton, editButton, clearButton, deleteButton, confirmButton;
    private Container c = getContentPane();
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scroll;
    private JComboBox<String> cb1, cb2;
    private Shop s;
    private Sales sales;
    private Customer customer;

    public JTextField getTF1() {
        return tf1;
    }

    public JTextField getTF2() {
        return tf2;
    }

    public void work() {
        s = new Shop();
        customer = new Customer();

        // Top Label
        label1 = new JLabel("Billing product");
        label1.setFont(BOLD_Font);
        add(label1);

        // Customer name
        label2 = new JLabel("Customer name");
        label2.setFont(Label_Font);
        add(label2);

        tf1 = new JTextField();
        tf1.setFont(Tf_Font);
        add(tf1);
        customer.setName(tf1.getText());

        // Contact number
        label4 = new JLabel("Contact number");
        label4.setFont(Label_Font);
        add(label4);

        tf2 = new JTextField();
        tf2.setFont(Tf_Font);
        add(tf2);
        customer.setContactInfo(tf2.getText());

        // Category
        label3 = new JLabel("Category");
        label3.setFont(Label_Font);
        add(label3);

        String category[] = new String[s.getCategories().length];
        for (int i = 0; i < s.getCategories().length; i++) {
            category[i] = s.getCategories()[i].getType();
        }
        cb1 = new JComboBox<>(category);
        cb1.setFont(Tf_Font);
        add(cb1);

        // Products
        label7 = new JLabel("Products");
        label7.setFont(Label_Font);
        add(label7);

        cb2 = new JComboBox<>(new String[]{"Select category first..."});
        cb2.setFont(Tf_Font);
        add(cb2);

        // Quantity
        label5 = new JLabel("Quantity");
        label5.setFont(Label_Font);
        add(label5);

        tf3 = new JTextField("1");
        tf3.setFont(Tf_Font);
        add(tf3);

        // Buttons
        Font fb1 = new Font("Arial", Font.BOLD, 15);
        button1.setFont(fb1);
        button1.setBackground(Color.GREEN);
        add(button1);

        button2.setFont(fb1);
        button2.setBackground(Color.RED);
        add(button2);

        addButton = new JButton("Add");
        addButton.setFont(fb1);
        addButton.setBackground(Color.GREEN);
        add(addButton);

        editButton = new JButton("Edit");
        editButton.setFont(fb1);
        editButton.setBackground(Color.PINK);
        add(editButton);

        clearButton = new JButton("Clear");
        clearButton.setFont(fb1);
        clearButton.setBackground(Color.ORANGE);
        add(clearButton);

        deleteButton = new JButton("Delete");
        deleteButton.setFont(fb1);
        deleteButton.setBackground(Color.RED);
        add(deleteButton);

        // Product list
        label6 = new JLabel("Product List");
        label6.setFont(BOLD_Font);
        add(label6);

        String column[] = {"Product Name", "Category", "Unit Price", "Quantity", "Total"};
        String rows[] = new String[5];

        table = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        table.setModel(model);
        table.setFont(Tf_Font);
        table.setSelectionBackground(Color.GREEN);
        table.setBackground(Color.GRAY);
        table.setRowHeight(30);

        scroll = new JScrollPane(table);
        add(scroll);

        confirmButton = new JButton("Confirm");
        confirmButton.setFont(fb1);
        confirmButton.setBackground(Color.PINK);
        add(confirmButton);
        

        // Button actions
        addButton.addActionListener(e -> {
            rows[0] = cb2.getItemAt(cb2.getSelectedIndex());
            rows[1] = cb1.getItemAt(cb1.getSelectedIndex());
            rows[2] = String.valueOf(s.getCategories()[cb1.getSelectedIndex()].getProduct()[cb2.getSelectedIndex()].getPrice());
            rows[3] = tf3.getText();
            double Total = Integer.parseInt(rows[3])
                    * s.getCategories()[cb1.getSelectedIndex()].getProduct()[cb2.getSelectedIndex()].getPrice();
            rows[4] = String.valueOf(Total);
            int n = Integer.parseInt(rows[3]);
            boolean check = s.getCategories()[cb1.getSelectedIndex()].getProduct()[cb2.getSelectedIndex()].checkStock(n);

            if (check)
				{
                s.getCategories()[cb1.getSelectedIndex()].getProduct()[cb2.getSelectedIndex()].availableQuantity(n);
                model.addRow(rows);
            } else 
			{
                JOptionPane.showMessageDialog(FrameDesign.this, "Stock out");
            }
        });

        clearButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(FrameDesign.this,
                    "If you clear, all data will be erased.\n\nAre you sure?\n\n", "Confirm Clear",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                tf1.setText("");
                tf2.setText("");
                tf3.setText("1");
                model.setRowCount(0);
            }
        });

        deleteButton.addActionListener(e -> {
            int num_row = table.getSelectedRow();
            if (num_row >= 0) {
                String prodName = String.valueOf(model.getValueAt(num_row, 0));
                String catName = String.valueOf(model.getValueAt(num_row, 1));
                int qty = Integer.parseInt(model.getValueAt(num_row, 3).toString());

                for (Category cat : s.getCategories()) {
                    if (catName.equals(cat.getType())) {
                        for (Product p : cat.getProduct()) {
                            if (prodName.equals(p.getName())) {
                                p.addQuantity(qty);
                                break;
                            }
                        }
                        break;
                    }
                }
                model.removeRow(num_row);
            } else {
                JOptionPane.showMessageDialog(FrameDesign.this, "No Row has been selected");
            }
        });

        editButton.addActionListener(e -> {
            int num_row = table.getSelectedRow();
            if (num_row >= 0) {
                String ProductName = s.getCategories()[cb1.getSelectedIndex()].getProduct()[cb2.getSelectedIndex()].getName();
                String CategoryName = cb1.getItemAt(cb1.getSelectedIndex());
                String UnitPrice = String.valueOf(s.getCategories()[cb1.getSelectedIndex()].getProduct()[cb2.getSelectedIndex()].getPrice());
                String QuantityInfo = tf3.getText();
                double Total = Integer.parseInt(QuantityInfo)
                        * s.getCategories()[cb1.getSelectedIndex()].getProduct()[cb2.getSelectedIndex()].getPrice();
                String TotalInfo = String.valueOf(Total);

                model.setValueAt(ProductName, num_row, 0);
                model.setValueAt(CategoryName, num_row, 1);
                model.setValueAt(UnitPrice, num_row, 2);
                model.setValueAt(QuantityInfo, num_row, 3);
                model.setValueAt(TotalInfo, num_row, 4);
            } else {
                JOptionPane.showMessageDialog(FrameDesign.this, "No Row has been selected");
            }
        });

        cb1.addActionListener(e -> {
            int x = cb1.getSelectedIndex();
            String[] items;
            if (x < 0) items = new String[]{"Select category first..."};
            else {
                int n = s.getCategories()[x].getProduct().length;
                items = new String[n];
                for (int i = 0; i < n; i++) {
                    items[i] = s.getCategories()[x].getProduct()[i].getName();
                }
            }
            cb2.setModel(new DefaultComboBoxModel<>(items));
            cb2.setSelectedIndex(0);
        });

        button1.addActionListener(e -> tf3.setText(String.valueOf(Integer.parseInt(tf3.getText()) + 1)));

        button2.addActionListener(e -> {
            int qty = Integer.parseInt(tf3.getText());
            if (qty > 1) tf3.setText(String.valueOf(qty - 1));
        });

        confirmButton.addActionListener(e -> {
            customer.setName(tf1.getText());
            customer.setContactInfo(tf2.getText());
            int row_number = table.getRowCount();
            if (row_number == 0) {
                JOptionPane.showMessageDialog(FrameDesign.this, "Table is Empty");
                return;
            }

            double total_amount = 0;
            for (int i = 0; i < row_number; i++) {
                total_amount += Double.parseDouble(model.getValueAt(i, 4).toString());
            }

            int choice = JOptionPane.showConfirmDialog(FrameDesign.this,
                    "Total Bill: " + total_amount + "\nDiscount: " + customer.applyDiscount(total_amount),
                    "Confirmation", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(FrameDesign.this, "Payment receipt file saved");
                sales = new Sales();
                sales.insertInfo(model, customer);
                check();
            }
        });
        

        // Layout
        c.setBackground(Color.WHITE);
        GroupLayout layout = new GroupLayout(c);
        c.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(label1)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(label2)
                                        .addComponent(label3)
                                        .addComponent(label5))
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(tf1)
                                        .addComponent(cb1)
                                        .addComponent(tf3))
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(label4)
                                        .addComponent(label7))
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(tf2)
                                        .addComponent(cb2)))
                        .addComponent(scroll)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(addButton)
                                .addComponent(editButton)
                                .addComponent(clearButton)
                                .addComponent(deleteButton))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(button1)
                                .addComponent(button2))
                        .addComponent(confirmButton)
                        .addComponent(label6)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label1)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label2)
                                .addComponent(tf1)
                                .addComponent(label4)
                                .addComponent(tf2))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label3)
                                .addComponent(cb1)
                                .addComponent(label7)
                                .addComponent(cb2))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label5)
                                .addComponent(tf3))
                        .addComponent(scroll)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addButton)
                                .addComponent(editButton)
                                .addComponent(clearButton)
                                .addComponent(deleteButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button1)
                                .addComponent(button2))
                        .addComponent(confirmButton)
                        .addComponent(label6)
        );

        setSize(1050, 750);
        setTitle("Super Shop Sales Management System");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void check() {
        try {
            File file = new File("./Data/Payment receipt.txt");
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
                br.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error!");
        }
    }

    // public static void main(String[] args) {
    //     new FrameDesign().work();
    // }
}