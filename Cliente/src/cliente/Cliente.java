/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Administrador
 */
public class Cliente {
    
    private Socket socket;
    private DataOutputStream bufferSalida = null;
    private DataInputStream bufferEntrada = null;
    
    Scanner scan = new Scanner(System.in);
    
    final String COMANDO_SALIR = "exit";
    
    public void conectar(String ip, int puerto){
        try {
            socket = new Socket(ip, puerto);
            System.out.print("Conectar a:" + socket.getInetAddress().getHostName());
        } catch (Exception e) {
            System.out.println("Error al conectar con el servidor:" + e.getMessage());
        }
    }
    
    public void cerrarConexion(){
        try {
            bufferEntrada.close();
            bufferSalida.close();
            socket.close();
            System.out.println("Fin de conexion");
        } catch (IOException e) {
            System.out.println("Error al cerrar la conexion:" + e.getMessage());
        }finally{
            System.exit(0);
        
        }
    }
    
    public void getDatos(){
        String mensaje= "";
        try {
            do {  
                bufferEntrada = new DataInputStream(socket.getInputStream());
                mensaje = (String) bufferEntrada.readUTF();
                System.out.print("\n *Servidor* >" + mensaje+"\n");
                System.out.print("\n *Cliente* >");
            } while (!mensaje.equals(COMANDO_SALIR));
        } catch (IOException e) {
            System.out.println("Error al recibir mensaje: " + e.getMessage());
        }
    }
    
    public void sedDatos(){
        String entrada = "";
        while (true) {
            entrada = scan.nextLine();
            System.out.print(" Tu respuesta ->"+entrada);  
            
            if(entrada.length()> 0){
            enviar(entrada);
            }
        }
      }
    
    public void enviar(String mensaje){
        try {
            bufferSalida = new DataOutputStream(socket.getOutputStream());
            bufferSalida.writeUTF(mensaje);
            bufferSalida.flush();
        } catch (IOException e) {
            System.out.println("No se puede enviar mensaje:" + e.getMessage());
        }
    }
    
    public void ejecutarCliente( final String ip, final int puerto){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    conectar(ip, puerto);
                    getDatos();
                } finally {
                    cerrarConexion();
                }
            }
        });
        hilo.start();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        
        Scanner scan = new Scanner(System.in);
        System.out.print("Escriba la IP: [localhost]");
        String ip = scan.nextLine();
        if(ip.length()<= 0){
            ip = "localhost";
        
        }
        System.out.println("Escriba el puerto: [6060] ");
        String puerto = scan.nextLine();
        if(puerto.length()<= 0){
            puerto = "6060";
        
        }
        
        cliente.ejecutarCliente(ip, Integer.parseInt(puerto));
        cliente.sedDatos();
        
        
    }
    
}
