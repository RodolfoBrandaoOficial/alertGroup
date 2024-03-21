import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Timer;
import java.util.TimerTask;

import static spark.Spark.*;

public class App extends JFrame {

    private static final String USERNAME = "Admin";
    private static final String PASSWORD = "Senha@123123";

    public static void enviarWebhook() {
        try {
            // Enviar a solicitação POST
            URL url = new URL("http://127.0.0.1:3377/webhook?alert=sim&mensagem=Thargo e Douglas&tiposervico=Instalação");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            // Adicionar autenticação básica aos headers
            String authString = USERNAME + ":" + PASSWORD;
            String encodedAuth = Base64.getEncoder().encodeToString(authString.getBytes());
            connection.setRequestProperty("Authorization", "Basic " + encodedAuth);
            int responseCode = connection.getResponseCode();
            System.out.println("Response code: " + responseCode);

            // Exibir a notificação de alerta
            showNotification("Thargo e Douglas", "Instalação");

            // Exibir um alerta oculto
            showHiddenAlert();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Método para reproduzir o som
    private static void playSound() {
        try {
            // Carregar o arquivo de som

            URL soundUrl = App.class.getResource("/som.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundUrl));
            // Reproduzir o som
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
//CHAMAR TELA
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                EnviarSolicitacao enviarSolicitacao = new EnviarSolicitacao(300, 200);
                enviarSolicitacao.mostrarJanela();
            }
        });

        // Configurar o SparkJava para escutar na porta 3377
        port(3377);

        // Configurar filtro para autenticação básica
        before((request, response) -> {
            String authHeader = request.headers("Authorization");
            if (authHeader == null || !authHeader.startsWith("Basic ")) {
                halt(401, "Autenticação necessária");
            } else {
                String encodedAuth = authHeader.substring("Basic ".length()).trim();
                String decodedAuth = new String(Base64.getDecoder().decode(encodedAuth));
                String[] credentials = decodedAuth.split(":");
                if (credentials.length != 2 || !credentials[0].equals(USERNAME) || !credentials[1].equals(PASSWORD)) {
                    halt(403, "Credenciais inválidas");
                }
            }
        });

        // Definir rota para receber o webhook
        post("/webhook", (req, res) -> {

            String message = req.queryParams("mensagem");
            String servico = req.queryParams("tiposervico");

            // Verificar se o webhook contém o parâmetro "alert" com o valor "sim"
            if ("sim".equals(req.queryParams("alert"))) {
                // Se sim, exibir a interface gráfica e enviar a notificação de alerta
                SwingUtilities.invokeLater(() -> {
                    // Exibir a notificação de alerta
                    showNotification(message, servico);

                });
            }
            return "Webhook recebido com sucesso!";
        });

    }

    private static void showNotification(String message, String servico) {
        // Reproduzir o som
        playSound();
        // Criar uma nova janela
        JFrame frame = new JFrame("Alerta");
        frame.setSize(600, 400); // Definir o tamanho da janela como 600x400 pixels

        // HTML com CSS para a mensagem de alerta
        String htmlContent = "<html><head><style>"
                + "body { background-color: #f0f0f0; font-family: Arial, sans-serif; font-size: 20px; text-align: center; }"
                + ".alert { background-color: #f0f0f0; padding: 20px; border-radius: 20px; box-shadow: 0px 0px 10px #888888; }"
                + "h1 { color: #ff3366; font-size: 40px; margin-bottom: 10px; text-shadow: 2px 2px 2px #888888; }"
                + "h2 { color: #0033cc; font-size: 35px; margin-bottom: 10px; }"
                + "p { color: #6600ff; font-size: 25px; margin-bottom: 10px; }"
                + "</style></head>"
                + "<body><div class=\"alert\"><h1>Alerta recebido!</h1>"
                + "<p>" + message + "</p></div>"
                + "<div><h2>" + servico + "</h2></div></body></html>";

        // Criar um editor de texto formatado para exibir HTML com CSS
        JEditorPane editorPane = new JEditorPane("text/html", htmlContent);
        editorPane.setEditable(false); // Tornar o editor de texto somente leitura

        // Adicionar o editor de texto à janela
        frame.add(new JScrollPane(editorPane));

        // Centralizar a janela na tela
        frame.setLocationRelativeTo(null);

        // Definir a janela para sempre ficar no topo
        frame.setAlwaysOnTop(true);

        // Definir o estado da janela como normal para evitar minimização
        frame.setState(JFrame.NORMAL);

        // Adicionar um evento para fechar a janela ao clicar no botão de fechar
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Tornar a janela visível
        frame.setVisible(true);

        // Agendar o fechamento da janela após 5 segundos
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                frame.dispose(); // Fechar a janela
            }
        }, 5000); // 5 segundos em milissegundos
    }

    private static void showHiddenAlert() {
        // Exibir um alerta oculto
        JOptionPane.showMessageDialog(null, "This is a hidden alert");
    }
}
