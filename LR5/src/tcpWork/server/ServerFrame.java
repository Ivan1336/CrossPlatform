package tcpWork.server;


import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;

import static java.awt.EventQueue.invokeLater;


public class ServerFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    public static int PORT = 12345;
    static JTextArea Text;
    private JButton StartButton;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JTextField TextPort;

    public ServerFrame() {
        initComponents();
    }

    public static void main(String[] args) {
        invokeLater(() -> new ServerFrame().setVisible(true));
    }

    private void initComponents() {
        setTitle("TCP Server");
        jScrollPane1 = new JScrollPane();
        Text = new JTextArea();
        StartButton = new JButton();
        jLabel1 = new JLabel();
        TextPort = new JTextField();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Text.setColumns(20);
        Text.setRows(5);
        Text.setEditable(false);
        jScrollPane1.setViewportView(Text);

        StartButton.setText("Start");

        jLabel1.setText("Port:");

        TextPort.setText("12345");


        StartButton.setText("Start");
        StartButton.addActionListener(evt -> {
            try {
                start();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(ServerFrame.this, "Произошла ошибка", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(StartButton)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TextPort, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(155, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(StartButton)
                                        .addComponent(jLabel1)
                                        .addComponent(TextPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 15, Short.MAX_VALUE))
        );

        pack();
        this.setResizable(false);
    }

    private void start() throws IOException {
        PORT = Integer.parseInt(TextPort.getText());
        ServerSocket servSoc = new ServerSocket(PORT);
        TextPort.setEditable(false);
        StartButton.setEnabled(false);
        JOptionPane.showMessageDialog(ServerFrame.this, "Server started working", "Yes", JOptionPane.INFORMATION_MESSAGE);
        new Start(servSoc).start();
    }


}


