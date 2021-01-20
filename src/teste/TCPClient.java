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

public class TCPClient {
    public static void main(String argv[]) throws Exception 
    { 
        String sentence; 
        String modifiedSentence;
     
        Socket clientSocket = new Socket("192.168.0.131", 6789);
            while(true){
             
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                //ENVIAR MSG
                System.out.println("INSERIR MENSAGEM:\n");
                sentence = inFromUser.readLine(); 
                
                if(sentence.contains("ENCERRAR")){
                    System.out.println("ENTROU IF ENCERRAR 1");
                    clientSocket.close();
                    System.exit(1);
                }
                outToServer.writeBytes(sentence + '\n'); 
                
                
                modifiedSentence = inFromServer.readLine(); 
                //MSG RECEBIDA
                if(modifiedSentence.contains("ENCERRAR")){
                    System.out.println("ENTROU IF ENCERRAR 2");
                    clientSocket.close();
                    System.exit(1);
                }
                System.out.println("FROM SERVER: " + modifiedSentence);

                
        }
           
   
    } 


        
}
