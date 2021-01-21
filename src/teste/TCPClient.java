package teste;
//@author Pedro Cotta Badaro - 201776014
//@author Davi Rezende Domingues - 201765505ab
import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;
        Socket clientSocket = new Socket("192.168.0.131", 6789);
        
        while (true) {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //captura a mensagem a ser enviada
            System.out.println("INSERIR MENSAGEM: ");
            sentence = inFromUser.readLine();
            //verifica se eh o codigo de encerramento
            if (sentence.contains("ENCERRAR")) {
                System.out.println("ENCERRADO");
                clientSocket.close();
                System.exit(1);
            }
            //envia a mensagem
            outToServer.writeBytes(sentence + '\n');

            //captura a mensagem que chega do servidor
            modifiedSentence = inFromServer.readLine();
            //analisa mensagem recebibda
            if (modifiedSentence.contains("ENCERRAR")) {
                System.out.println("ENCERRADO");
                clientSocket.close();
                System.exit(1);
            }
            //imprime mensagem recebida
            System.out.println("FROM SERVER: " + modifiedSentence);
        }
    }
}