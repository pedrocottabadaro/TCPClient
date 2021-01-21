package teste;
//@author Pedro Cotta Badaro 201776014
//@author Davi Rezende Domingues - 201765505ab
import java.io.*;
import java.net.*;

class TCPServer {
    public static void main(String argv[]) throws Exception {
        String clientSentence;
        String serverSentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        System.out.println("SERVIDOR INICIADO NA PORTA 6789");
        Socket connectionSocket = welcomeSocket.accept();
        
        while (true) {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            
            //captura mensagem recebida do client
            clientSentence = inFromClient.readLine();
            //analisa mensagem recebida
            if (clientSentence.contains("ENCERRAR")) {
                System.out.println("ENCERRANDO CHAT");
                welcomeSocket.close();
                System.exit(1);
            }
            //imprime mensagem recebida
            System.out.println("FROM CLIENT: " + clientSentence);

            //captura mensagem pra enviar pro client
            System.out.println("INSERIR MENSAGEM: ");
            serverSentence = inFromServer.readLine();
            //analisa mensagem a ser enviada
            if (serverSentence.contains("ENCERRAR")) {
                System.out.println("ENCERRANDO CHAT");
                welcomeSocket.close();
                System.exit(1);
            }
            //envia mensagem pro client
            outToClient.writeBytes(serverSentence);
        }
    }
}