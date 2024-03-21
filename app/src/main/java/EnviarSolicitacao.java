import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnviarSolicitacao {
    private JButton enviarButton;
    private JComboBox<String> comboBox;
    private JFrame frame;

    public EnviarSolicitacao(int width, int height) {
        // Inicializa o JFrame
        frame = new JFrame("Enviar Solicitação");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fechar apenas esta janela quando clicar em fechar

        // Inicializa o JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Adiciona uma label no topo
        JLabel label = new JLabel("Selecione uma opção:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        // Adiciona um JComboBox para seleção de opções
        String[] options = {"Opção 1", "Opção 2", "Opção 3"};
        comboBox = new JComboBox<>(options);
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(comboBox, BorderLayout.CENTER);

        // Inicializa o botão enviarButton
        enviarButton = new JButton("Enviar");
        enviarButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adiciona um ActionListener ao botão enviarButton
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ao clicar em enviarButton, executar enviarWebhook() da classe App
                App.enviarWebhook();
            }
        });

        // Adiciona o botão ao JPanel
        panel.add(enviarButton, BorderLayout.SOUTH);

        // Adiciona o JPanel ao JFrame
        frame.add(panel);

        // Centraliza a janela na tela
        frame.setLocationRelativeTo(null);
    }

    // Método para exibir a janela
    public void mostrarJanela() {
        frame.setVisible(true);
    }
}
