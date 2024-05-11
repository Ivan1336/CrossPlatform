package tcpWork.client;

import tcpWork.interfaces.Result;

import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.*;




public class ClientFrame extends JFrame {

    public static int PORT = 12345;

    private static final long serialVersionUID = 1L;

    private JButton EnterButton;
    private JButton ConnectButton;
    private JLabel Label;
    private JTextArea Text;
    private JTextField TextNumber;
    private JScrollPane jScrollPane1;

    private Socket client;
    private ObjectOutputStream out;
    private ObjectInputStream in;


    public ClientFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("TCP Client");
        jScrollPane1 = new JScrollPane();
        Text = new JTextArea();
        Label = new JLabel();
        TextNumber = new JTextField();
        EnterButton = new JButton();
        ConnectButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Text.setColumns(25);
        Text.setRows(10);
        jScrollPane1.setViewportView(Text);
        Text.setEditable(false);
        EnterButton.setEnabled(false);

        Label.setText("Enter number: ");

        EnterButton.setText("Enter");
        EnterButton.addActionListener(evt -> {
            try {
                countResult();
            }catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        });


        ConnectButton.setText("Connect");
        ConnectButton.addActionListener(evt -> {
            try {
                connect();
            } catch (ConnectException e) {
                JOptionPane.showMessageDialog(ClientFrame.this, "Server is not available", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Label)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TextNumber, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(EnterButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ConnectButton))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(Label)
                                        .addComponent(TextNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(EnterButton)
                                        .addComponent(ConnectButton))
                                .addContainerGap())
        );
        pack();
        this.setResizable(true);
    }

    private void countResult() throws IOException, ClassNotFoundException{
        try{
            String classFile = "out/production/LR5/tcpWork/client/Factorial.class";
            out.writeObject(classFile);
            FileInputStream fis = new FileInputStream(classFile);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.writeObject(b);
            int number = Integer.parseInt(TextNumber.getText());
            Factorial factorial = new Factorial(number);
            out.writeObject(factorial);

            classFile = (String) in.readObject();
            b = (byte[]) in.readObject();
            FileOutputStream fos = new FileOutputStream(classFile);
            fos.write(b);

            Result result = (Result) in.readObject();

            String res = "Result = " + result.output() + ", time taken = " + result.scoreTime() + " ns";

            Text.setText(res);
            
        }catch(SocketException e) {
            e.printStackTrace();
        }
    }

    private void connect() throws ConnectException {
        try {
            client = new Socket("localHost",PORT);
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
            EnterButton.setEnabled(true);
            ConnectButton.setEnabled(false);
            JOptionPane.showMessageDialog(ClientFrame.this, "Connection established", "Yes", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(ClientFrame.this, "Server is not available", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new ClientFrame().setVisible(true));
    }

}
