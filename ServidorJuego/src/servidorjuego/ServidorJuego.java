/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorjuego;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 *
 * @author administrador
 */
public class ServidorJuego {

    private Socket socket;
    private ServerSocket serverSocket;
    private DataInputStream bufferEntrada = null;
    private DataOutputStream bufferSalida = null;
    Scanner scaner = new Scanner(System.in);
    final String COMANDO_SALIR = "salir()";
    int puntos = 0;
    int buenas =0;
    int index=0;
    String respuesta = "";
    String adivinanzas[] = new String[50];
    String respuestas[] = new String[50];
    int orden[] = null;

    public void llenarAdivinanzas() {
        adivinanzas[0] = "Adivina quién soy, cuanto más lavo más sucia voy.";
        adivinanzas[1] = "La habrás oído nombrar. Piensa, medita, recuerda, ¿qué instrumento musical no tiene mas que una cuerda?";
        adivinanzas[2] = "Todo el mundo lo lleva, todo el mundo lo tiene, porque a todos le dan uno en cuanto al mundo viene.";
        adivinanzas[3] = "Todos me usan para descansar. Si ya te lo he dicho, no me busques más.";
        adivinanzas[4] = "Blanco por dentro, verde por fuera. Si quieres que te lo diga espera.";
        adivinanzas[5] = "Somos muchos hermanitos que en la misma casa vivimos, si nos rascan la cabeza al instante morimos.";
        adivinanzas[6] = "Es su madre tartamuda, y su padre un buén cantor. Tiene su vestido blanco y amarillo el corazón.";
        adivinanzas[7] = "En lo alto vive, en lo alto mora, en lo alto teje la tejedora.";
        adivinanzas[8] = "¿Cual es el animal que tiene más dientes?";
        adivinanzas[9] = "Muy chiquito, muy chiquito, él pone fin a lo escrito.";
        adivinanzas[10] = "Al nacer soy algo verde, al morir bastante rojo. Por dentro estoy más vacío que la cabeza de un loco.";
        adivinanzas[11] = "¿Qué será? ¿Qué puede ser? ¿Qué cuanto más grande se hace menos la podemos ver?";
        adivinanzas[12] = "Sube llena y baja vacía, y si no se da prisa la sopa se enfría.";
        adivinanzas[13] = "Dos compañeras que siempre van al compás, con las manos por delante y los ojos por detrás.";
        adivinanzas[14] = "Qué cosa será, qué cosa es, que te da siempre en la cara, pero tu nunca la vez.";
        adivinanzas[15] = "Tengo cabeza redonda, sin nariz, ojos ni frente. Y mi cuerpo se compone tan solo de blancos dientes.";
        adivinanzas[16] = "Cerca del polo, desnuda. Sentada sobre una roca; negra, suave y bigotuda.";
        adivinanzas[17] = "Pérez anda, Gil camina, tonto es quién no lo adivina.";
        adivinanzas[18] = "Te la digo y no me entiendes, te la repito y no me comprendes.";
        adivinanzas[19] = "Yo tengo calor y frío, y no frío sin calor, y sin ser mar ni río, peces en mí he visto yo.";
        adivinanzas[20] = "Viste de chaleco blanco y también de negro frac. Es un ave que no vuela, pero si sabe nadar.";
        adivinanzas[21] = "Con unos zapatos grandes, y la cara muy pintada, soy el que hace reir a toda la chiquillada.";
        adivinanzas[22] = "Tiene famosa memoria, fino olfato y dura piel, y las mayores narices que en el mundo puede haber.";
        adivinanzas[23] = "Poncho duro por arriba, poncho duro por abajo, patitas cortas y corto el paso. ¿Quién soy?";
        adivinanzas[24] = "Se parece a mi madre pero es mucho más mayora. Tiene otros hijos que mis tíos son.";
        adivinanzas[25] = "No es león pero tiene garra, no es pato pero tiene pata. ¿Qué es?";
        adivinanzas[26] = "Mi picadura es dañina, mi cuerpo insignificante; pero el nectar que yo doy os lo comeis al instante.";
        adivinanzas[27] = "Tiene luna y no es planeta, tiene un marco y no es puerta. ¿Qué es?";
        adivinanzas[28] = "Unas son redondas y otras ovaladas, unas piensan mucho y otras casi nada.";
        adivinanzas[29] = "Quién es, quién es, el que bebe por los pies?";
        adivinanzas[30] = "Tiene ojos de gato y no es un gato, orejas de gato y no es un gato, patas de gato y no es un gato, rabo de gato y no es un gato. Maulla y no es un gato. ¿Qué es?";
        adivinanzas[31] = "Oro parece, plata no es. El que no lo adivine tontorrón es.";
        adivinanzas[32] = "Estando sano me cortan, sin ser enfermo me curan; y en lonchas o empedacitos, dicen que estoy de locura.";
        adivinanzas[33] = "En marcar está el comienzo y en mentir está el final, el final es el comienzo y el comienzo es el final. Soluciona este problema y mi nombre acertarás.";
        adivinanzas[34] = "Lomos y cabeza tengo, y aunque vestida no estoy muy larga falda contengo.";
        adivinanzas[35] = "En las manos de mujeres casi siempre estoy metido, unas veces estirado y otras veces recogido.";
        adivinanzas[36] = "Viene y va, viene y va, y lo que antes estaba ... ya no está!!!!";
        adivinanzas[37] = "Soy bonito por delante, algo feo por detrás. Me transformo a cada instante, porque imito a los demás.";
        adivinanzas[38] = "Dos buenas piernas tenemos, más no sabemos andar. Pero un hombre si nosotros nunca a la calle saldrá.";
        adivinanzas[39] = "Bramido a bramido, antes de las tormentas todos los hemos oído.";
        adivinanzas[40] = "Y lo es, y lo es, y no lo adivinas ni en un més.";
        adivinanzas[41] = "Redondo, redondo, no tiene tapa y no tiene fondo.";
        adivinanzas[42] = "Desde el día en que nací, corro y corro sin cesar; corro de día, corro de noche, hasta llegar a la mar.";
        adivinanzas[43] = "Lana sube, lana baja, y a lo que toca lo raja.";
        adivinanzas[44] = "Todos me pisan a mi, pero yo a nadie piso; todos preguntan por mi, y yo a nadie pregunto.";
        adivinanzas[45] = "Piensa poco y salta mucho, dime su nombre que no te escucho.";
        adivinanzas[46] = "No es cama ni es león, pero desaparece en cualquier rincón.";
        adivinanzas[47] = "Zorra le dicen, ya ves, aunque siempre del revés. Se lo come el japonés y plato muy rico es.";
        adivinanzas[48] = "El roer es mi trabajo, el queso mi aperitivo, y el gato ha sido siempre mi más temible enemigo.";
        adivinanzas[49] = "Ni espero que me lo aciertes, ni espero que me bendigas, y con un poco de suerte espero que me lo digas.";
    }

    public void llenarRespuestas() {
        respuestas[0] = "El agua.";
        respuestas[1] = "La campana.";
        respuestas[2] = "El nombre.";
        respuestas[3] = "La silla.";
        respuestas[4] = "La pera.";
        respuestas[5] = "Las cerillas.";
        respuestas[6] = "El huevo.";
        respuestas[7] = "La araña.";
        respuestas[8] = "El ratoncito Pérez.";
        respuestas[9] = "El punto.";
        respuestas[10] = "El pimiento.";
        respuestas[11] = "La oscuridad.";
        respuestas[12] = "La cuchara.";
        respuestas[13] = "Las tijeras.";
        respuestas[14] = "El viento.";
        respuestas[15] = "El ajo.";
        respuestas[16] = "La foca.";
        respuestas[17] = "El perejil.";
        respuestas[18] = "La tela.";
        respuestas[19] = "La sarten.";
        respuestas[20] = "El pingüino.";
        respuestas[21] = "El payaso.";
        respuestas[22] = "El elefante.";
        respuestas[23] = "La tortuga.";
        respuestas[24] = "La abuela.";
        respuestas[25] = "La garrapata.";
        respuestas[26] = "La abeja.";
        respuestas[27] = "El espejo.";
        respuestas[28] = "La cabeza.";
        respuestas[29] = "El árbol.";
        respuestas[30] = "Una gata.";
        respuestas[31] = "El platano.";
        respuestas[32] = "El jamón.";
        respuestas[33] = "Carmen.";
        respuestas[34] = "La montaña.";
        respuestas[35] = "El abanico.";
        respuestas[36] = "La goma de borrar.";
        respuestas[37] = "El espejo.";
        respuestas[38] = "Los pantalones.";
        respuestas[39] = "El trueno.";
        respuestas[40] = "El hilo.";
        respuestas[41] = "El anillo.";
        respuestas[42] = "El río.";
        respuestas[43] = "La navaja.";
        respuestas[44] = "El camino.";
        respuestas[45] = "El sapo.";
        respuestas[46] = "El camaleón.";
        respuestas[47] = "El arroz.";
        respuestas[48] = "El ratón.";
        respuestas[49] = "El níspero.";
    }

    public void generarOrden() {
        int[] numerosAleatorios = IntStream.rangeClosed(1, 20).toArray();
        Random r = new Random();
        for (int i = numerosAleatorios.length; i > 0; i--) {
            int posicion = r.nextInt(i);
            int tmp = numerosAleatorios[i - 1];
            numerosAleatorios[i - 1] = numerosAleatorios[posicion];
            numerosAleatorios[posicion] = tmp;
        }
        orden = numerosAleatorios;
    }

    public void bienvenida() {
                  
            sendDatos("Bienvenido a adivinansas 2.0 remkae \n Optendras 1 punto al contestar correctamente una adivinanza"
                + "\n Ganaras si contestas  correctamente todo "
                    + "\n el juego termina al acumular 3 respuestas erroneas \n Comenzemos");
        
    }

    public void adivino(String respCliente) {
        if (respCliente.equals(respuestas[orden[index]])) {
            sendDatos("Respuesta correcta "); 
            index++;
                    buenas++;
            mandarSiguiente();
        } else {
            sendDatos("Respuesta incorrecta");
            sendDatos("La espuesta correcta era :-> "+respuestas[orden[index]]);
            puntos++;
            index++;
            mandarSiguiente();
        }
    }    
    
    public void mandarSiguiente(){
        try {
           if (puntos<3) {
               if(puntos==2){
                   sendDatos("Cuidado ya solo tienes una oportunidad la proxima "
                           + "\n                terminara el juego");
               }
               System.out.println("Respueta esperada : "+respuestas[orden[index]]);
               String siguiente=adivinanzas[orden[index]];               
               sendDatos(siguiente);  
        }else{
            sendDatos("Fin del juego.");
            sendDatos("Acumulaste "+buenas+" puntos");
            sendDatos("exit()");
            bufferEntrada.close();
            bufferSalida.close();
            socket.close();
            System.exit(0);
        } 
        } catch (Exception e) {
        }finally{
        
        }
        
        
        
    }    
    
    public void conectar(int puerto) {
        try {
            serverSocket = new ServerSocket(puerto);
            System.out.println("Esperando conexion puerto:" + String.valueOf(puerto));
            socket = serverSocket.accept();
            System.out.println("Conexion establecida con:" + socket.getInetAddress().getHostName());
            bienvenida();
            mandarSiguiente();
            
        } catch (IOException e) {
            System.out.println("Error al conectar:" + e.getMessage());
            System.exit(0);
        }
    }

    public void cerrarConexion() {
        try {
            bufferEntrada.close();
            bufferSalida.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar la conexion:" + e.getMessage());
        } finally {
            System.out.println("Conexion finalizada.");
            System.exit(0);
        }
    }

    public void getDatos() {
        String texto = "";
        try {
            bufferEntrada = new DataInputStream(socket.getInputStream());
            do {
                texto = (String) bufferEntrada.readUTF();
                adivino(texto);
            } while (!texto.equals(COMANDO_SALIR));
        } catch (IOException e) {
            cerrarConexion();
        }

    }

    public void sendDatos(String mensaje) {
        try {
            bufferSalida = new DataOutputStream(socket.getOutputStream());
            bufferSalida.writeUTF(mensaje);
            bufferSalida.flush();
        } catch (IOException e) {
            cerrarConexion();
            System.out.println("Error al enviar mensaje:" + e.getMessage());
        }

    }

    public void escribirMensaje() {
        while (true) {
            System.out.print("*Servidor* >");
            sendDatos(scaner.nextLine());
            
        }
    }

    public void establecerConexion(int puerto) {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        conectar(puerto);
                        getDatos();
                    } finally {
                        cerrarConexion();
                    }
                }
            }
        });
        hilo.start();
    }

    public static void main(String[] args) {
        ServidorJuego servidor = new ServidorJuego();
        servidor.llenarAdivinanzas();
        servidor.llenarRespuestas();
        servidor.generarOrden();
        Scanner scan = new Scanner(System.in);
        System.out.print("Escribe el puerto [6060 por default]:");
        String puerto = scan.nextLine();

        if (puerto.length() <= 0) {
            puerto = "6060";
        }
        
        servidor.establecerConexion(Integer.parseInt(puerto));
        servidor.escribirMensaje();
    }

}
