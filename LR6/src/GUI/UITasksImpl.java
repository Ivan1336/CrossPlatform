package GUI;

import javax.swing.*;

public class UITasksImpl implements UITasks {
    private JTextArea textArea;
    private JTextField textField;

    public UITasksImpl(JTextArea textArea, JTextField textField) {
        this.textArea = textArea;
        this.textField = textField;
    }

    @Override
    public String getMessage() {
        String res = textField.getText();
        textField.setText("");
        return res;
    }

    @Override
    public void setText(String msg) {
        textArea.append(msg + "\n");
    }
}

