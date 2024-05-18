package GUI;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainFrame extends JFrame {
    private Messenger messenger;
    private JTextField userName;
    private JTextField port;
    private JTextField host;
    private JTextField userMessage;
    private JTextArea textArea;

    public MainFrame() {
        initMainFrame();
        initMainFrameComponents();
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    private void initMainFrame() {
        setTitle("Text Conference");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initConnection() {
        int port;
        InetAddress address;
        String name;
        try {
            address = InetAddress.getByName(host.getText());
            port = Integer.parseInt(this.port.getText());
            name = userName.getText();
            UITasks uiTasks = (UITasks) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{UITasks.class}, new EDTInvocationHandler(new UITasksImpl(textArea, userMessage)));
            messenger = new MessengerImpl(uiTasks, address, port, name);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void initMainFrameComponents() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 2));
        userMessage = new JTextField(30);
        JButton sendButton = new JButton("Надіслати");
        sendButton.addActionListener(e -> {
            if(messenger != null) {
                messenger.send();
            }else{
                userMessage.setText("З'єднання не встановлено");
            }
        });
        topPanel.add(userMessage);
        topPanel.add(sendButton);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(0, 2));
        JPanel socketPanel = new JPanel(new GridLayout(5, 2));
        host = new JTextField("224.0.0.1");
        port = new JTextField("12345");
        userName = new JTextField("Name1");
        socketPanel.add(new JLabel("Адрес"));
        socketPanel.add(host);
        socketPanel.add(new JLabel("Порт"));
        socketPanel.add(port);
        socketPanel.add(new JLabel("Ім'я"));
        socketPanel.add(userName);
        socketPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        socketPanel.setPreferredSize(new Dimension(100, 50));
        textArea = new JTextArea();
        textArea.setBorder(BorderFactory.createTitledBorder("Чат"));
        centerPanel.add(socketPanel);
        centerPanel.add(textArea);
        add(centerPanel, BorderLayout.CENTER);
        JPanel downPanelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));

        JButton connectButton = new JButton("З'єднати");
        JButton disconnectButton = new JButton("Роз'єднати");
        JButton clearButton = new JButton("Очистити");
        JButton exitButton = new JButton("Завершити");

        connectButton.addActionListener(e -> {
            initConnection();
            messenger.start();
            connectButton.setEnabled(false);
            disconnectButton.setEnabled(true);
            host.setEnabled(false);
            port.setEnabled(false);
            userName.setEnabled(false);
        });

        disconnectButton.addActionListener(e -> {
            if(messenger != null){
                messenger.stop();
                connectButton.setEnabled(true);
                disconnectButton.setEnabled(false);
            }
            host.setEnabled(true);
            port.setEnabled(true);
            userName.setEnabled(true);
        });

        clearButton.addActionListener(e -> {
            textArea.setText("");
            userMessage.setText("");
        });

        exitButton.addActionListener(e -> {
            messenger.stop();
            System.exit(0);
        });
        downPanelButtons.add(connectButton);
        downPanelButtons.add(disconnectButton);
        downPanelButtons.add(clearButton);
        downPanelButtons.add(exitButton);
        add(downPanelButtons, BorderLayout.SOUTH);
    }



}
