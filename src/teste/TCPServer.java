/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

/**
 *
 * @author Pedro Cotta Badaro 201776014
 */
import java.io.*; 
import java.net.*; 

class TCPServer { 

  public static void main(String argv[]) throws Exception 
    { 
        String clientSentence; 
        String capitalizedSentence; 
        String serverSentence; 
      ServerSocket welcomeSocket = new ServerSocket(6789); 
        System.out.println("SERVIDOR INICIADO NA PORTA 6789");
        Socket connectionSocket = welcomeSocket.accept();
      while(true) { 
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(System.in)); 
	   DataOutputStream  outToClient = new DataOutputStream(connectionSocket.getOutputStream()); 
            //MSG RECEBIDA
           clientSentence = inFromClient.readLine(); 
           if(clientSentence.contains("ENCERRAR")){
                     System.out.println("ENTROU IF ENCERRAR 3");
               welcomeSocket.close();
               System.exit(1);
           }
           System.out.println("FROM CLIENT "+clientSentence);
           
           //Enviar MSG
           System.out.println("Digitar mensagem:\n");
           serverSentence = inFromServer.readLine();
           capitalizedSentence = serverSentence + '\n'; 
           
           if(capitalizedSentence.contains("ENCERRAR")){
                     System.out.println("ENTROU IF ENCERRAR 4");
               welcomeSocket.close();
               System.exit(1);
           }
           
           outToClient.writeBytes(capitalizedSentence); 
           
            
         
        } 
    } 
} 
 