## FLUJOS DE DATOS

Los flujos son esenciales para garantizar la persistencia de datos en los programas y aplicaciones.

Es importante distinguir entre tubos de entrada (InputStream/Reader) y salida (OutputStream/Writer).

Principal diferencia:

- flujos de bytes > en binario > heredan de Input/OutputStream

- flujos de caracteres > en datos de texto > heredan de Reader/Writer. Transforma caracteres Unicode (formato de Java) al conjunto de caracteres local.

El procedimiento habitual suele ser envolver el flujo en un Buffer para mejorar el rendimiento de lectura/escritura.

(OJO: PrintWriter y PrintStream no tienenn un input correspondiente. Se usan para facilitar la escritura de datos formateados en un flujo) 

### SINTAXIS

Los flujos encapsulan otros y se rodean de try with resources, para evitar tener que usar close() (es necesario cerrarlos).

    try (FileInputStream in = new FileInputStream("otto.txt");
        FileOutputStream out = new FileOutputStream("nohaycole.txt")) {
        int c;
        while ((c = in.read()) != -1) {
            out.write(c);
        }

Explicación del ejemplo de un código de COPIA DE ARCHIVOS: 

1- apertura de flujos a un File (I/O) rodeada con try resources
2- se declara un índice temporal (int c) que irá tomando los valores de read() en el flujo de entrada.
3- el método leerá el archivo (otto) mientras no devuelva -1 (señal de END OF FILE)
4- a medida que avanza la lectura de bytes en read, write los va escribiendo a través del flujo de salida en el File indicado (nohaycole)


### FLUJOS PARA ARCHIVOS

FileInputStream/FileOutputStream : acceso a archivos 

### FLUJOS PARA OBJETOS

ObjectInputStream/ObjectOutputStream

Para serializar/desearlizar (construir o destruir objeto en secuencia de bytes) es necesario que la clase del objeto implemente Serializable

Usa los métodos writeObject y readObject.

Si se le añade el parámetro true en el constructor de FileOutputStream se indica que el archivo se abrirá en modo de "añadir" (append mode), en lugar de sobrescribirlo.


    public static void buscar(Scanner sc) throws FileNotFoundException, IOException, ClassNotFoundException, EOFException {
    System.out.println("Introduce el nombre de la persona a buscar: ");
    String nomB = sc.nextLine();
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("personas.dat"))) {    
                Object persona;
                while ((persona = ois.readObject()) != null) {
                    Persona p = (Persona) persona;
                    if (p.getNombre().equals(nomB)){
                        System.out.println(persona);
                        break;
                    } else {
                        System.out.println("No se ha encontrado a la persona");
                    }                       
                }    
            }    
        }

Este método permite hacer una búsqueda de una lista de objetos Persona guardados en un archivo.

DETALLES A TENER EN CUENTA:

- La lectura desde archivos de objetos suele ser o hasta que devuelva null o con un while(true), puesto que reconoce el fin de archivo con un EOF Exception 
- El OIS encapsula el flujo del archivo
- Al leer un objeto, readObject devuelve un Object que es necesario castear al tipo que nos interesa
- En ocasiones, la clase serializable lleva un ID único que identifica esa versión concreta en el momento en el que se serializó. Este identificador ayuda a la máquina virtual (JVM) 
a verificar que la versión de la clase que se está deserializando es compatible
con la versión de la clase que fue serializada originalmente.EJEMPLO: 



    public class ColeccionPersonas implements Serializable {

      private static final long serialVersionUID = 1L;
      private List<Persona> personas;

      public ColeccionPersonas() {
        this.personas = new ArrayList<>();
     }

      public void agregarPersona(Persona persona) {
          personas.add(persona);
      }

       public List<Persona> getPersonas() {
         return personas;
      }

     @Override
     public String toString() {
        return "ColeccionPersonas{" + "personas=" + personas + '}';
       }
    }

- atributo transient: En Java, cuando se declara un atributo como transient, se está indicando que ese atributo no debe ser serializado. Esto significa que cuando un objeto se convierte a un flujo de bytes para almacenarlo o transmitirlo (proceso de serialización), los atributos marcados como transient no serán incluidos en ese flujo.

- sobreescribir Equals y Hashcode es necesario para comparar objetos. Por ej, si quieres hacer un método para recorrer un archivo para buscar un objeto Persona en concreto comparándolo con otro. 

#### MUY IMPORTANTE

- Cada operación de escritura de un objeto con este flujo escribe una cabecera por lo que, con multiples entradas, acabas reescribiendo varias cabeceras y puede dar problemas. La solución es 
- SOLUCIÓN: crear una clase ObjectOutputStream personalizada y sobreescribir el método que escribe la cabecera en archivo cada vez.

      // clase propia que hereda de ObjectOutputStream que sobreescribe sin contenido writeStreamHeader()
      // se utiliza para los casos en los que se quiera añadir un objeto nuevo al archivo y evitar que escriba una nueva cabecera
    
      public class MiObjectOutputStream extends ObjectOutputStream {
          public MiObjectOutputStream(FileOutputStream out) throws IOException {
          super(out);
      }
    
          @Override
          protected void writeStreamHeader() throws IOException {
              // Se sobreescribe vacío
              reset();
          }
      }

¿Cómo se gestiona el guardado de objetos si quieres usar append?

O sobreescribes de nuevo el archivo una y otra vez de principio a fin o generas una secuencia lógica cada vez que quieras abrir el flujo de escritura.

Si es la primera vez (si el archivo está vacío), abres el flujo habitual (ObjectOutputStream).
Si el archivo existe, abres el flujo personalizado (MiObjectOutputStream).

    public static void añadir(Scanner sc) throws IOException {
        boolean existe = new File("personas.dat").exists();
        try (ObjectOutputStream oos = existe ? 
                new MiObjectOutputStream(new FileOutputStream("personas.dat", true)) : 
                new ObjectOutputStream(new FileOutputStream("personas.dat"));) {
    
            System.out.println("Introduce el nombre de la persona: ");
            String nombre = sc.nextLine();
            System.out.println("Introduce la edad de la persona:");
            int edad = sc.nextInt();
            sc.nextLine();
            Persona nueva = new Persona(nombre, edad);
            oos.writeObject(nueva);
            System.out.println("Datos añadidos correctamente");
        }
    }


### FLUJOS PARA LEER DESDE URL

Se utiliza URI para construir un Url (el constructor está deprecado) para abrir una conexión. EJEMPLO:

      import java.io.*;
      
      public class LeerURL {
      public static void main(String[] args) throws Exception {
           URI uri = new URI("https://manuais.pages.iessanclemente.net/plantillas/dam/ad/");
           URL url = uri.toURL();
      
              try (InputStream is = url.openStream();
                   InputStreamReader isr = new InputStreamReader(is); // es un puente de bytes a caracteres.
                   int c;
                   while ((c = isr.read()) != -1) {
                       System.out.print((char) c);
                   }
              }
      // Código equivalente con buffer:
            try (InputStream is = url.openStream();
                 InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader br = new BufferedReader(isr)) { // Lo veremos en el siguiente apartado.
                 String line;
               while ((line = br.readLine()) != null) {
                 System.out.println(line);
                }
             }
      }
      }

PASOS:

- se crea un objeto URI que acepta el String como parámetro
- A partir del URI, se construye un objeto URL
- Se abre el flujo mediante el método openStream();
- Se pasa el flujo a un lector (ejemplo, InputStreamReader)
- En el ejemplo se usa Buffered Reader, que encapsula el ISR para leer por líneas enteras en vez de byte a byte

#### URLConnection 

es una clase de Java que proporciona una interfaz para interactuar con una URL, es decir, para establecer una conexión con un recurso de red (como una página web, un archivo, una API, etc.).

Permite tanto leer desde una URL como enviar datos a través de ella (por ejemplo, enviar peticiones HTTP).
1. Conexión a un recurso: 

            URL url = new URL("http://www.example.com");
            URLConnection connection = url.openConnection();
2. Leer desde la URL:

       InputStream inputStream = connection.getInputStream();

3. Escribir a URL: 
      
            OutputStream outputStream = connection.getOutputStream();

4. Obtener tipos de contenido y cabeceras

        String contentType = connection.getContentType(); // Tipo de contenido de la respuesta
        int responseCode = ((HttpURLConnection) connection).getResponseCode(); // Si es HTTP
        // Obtener todas las cabeceras HTTP
            Map<String, java.util.List<String>> cabeceras = conexion.getHeaderFields();

La clase HttpURLConnection, que extiende URLConnection, proporciona métodos adicionales específicos para manejar conexiones HTTP (como obtener el código de estado HTTP, redirigir la solicitud, etc.).

        URL url = new URI("https://manuais.pages.iessanclemente.net/plantillas/dam/ad/").toURL();
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();  // Hereda de URLConnection
        httpConnection.getInputStream();
        httpConnection.setRequestMethod("HEAD");
        long tamanho = httpConnection.getContentLengthLong();

        // EJEMPLO GRANDE 
          try {
                // Crear la URL del recurso que quieres acceder
                URL url = new URL("https://manuais.pages.iessanclemente.net/plantillas/dam/ad/");

                // Abrir la conexión y obtener una instancia de HttpURLConnection
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Configurar la solicitud
                connection.setRequestMethod("GET");  // Establece el método HTTP (GET, POST, etc.)
                connection.setConnectTimeout(5000);  // Timeout para conectar
                connection.setReadTimeout(5000);     // Timeout para leer la respuesta

                // Leer la respuesta
                int responseCode = connection.getResponseCode();  // Obtener el código de respuesta HTTP
                System.out.println("Código de respuesta: " + responseCode);

                // Si la respuesta es exitosa (200 OK), leer el contenido
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();  // Flujo de entrada de la respuesta
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); // Leer línea por línea
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);  // Imprimir cada línea de la respuesta
                    }
                    reader.close();  // Cerrar el lector
                } else {
                    System.out.println("Error en la conexión: " + responseCode);
                }

                connection.disconnect();  // Desconectar después de usar la conexión
            } catch (IOException e) {
                e.printStackTrace();  // Manejo de excepciones
            }
    

#### Flujos de caracteres

Heredan de las clases abstractas Reader y Writer

File Reader/File Writer
InputStreamReader/OutputStreamWriter

### Flujos con Buffer

Representan flujos de alto nivel que encapsulan los de bajo nivel 
Son más eficientes y permiten leer  datos en bloques almacenándolos en un buffer
Un flujo de buffer puede agregar nuevos métodos, como readLine(),
así como mejoras de rendimiento para leer y filtrar los datos de bajo nivel.


    try (var br = new BufferedReader(new FileReader("noHayCole.txt"))) {
    System.out.println(br.readLine());
    }

También encapsulan otros de alto nivel, generando una cadena de encapsulamiento: 

    try (var ois = new ObjectInputStream(new BufferedInputStream(
    new FileInputStream("noHayCole.txt")))) {
    System.out.print(ois.readObject());
    }

- En el ejemplo, se lee el archivo mediante un FIS (bajo nivel).
- Se encapsula en un Buffer para mejorar el rendimiento.
- Se encapsulta en un OIS para abrir la lectura de objetos.

TABLA DE FLUJOS con nivel y descriocion

FileInputStream,	Bajo,	Lee datos de archivos como bytes

FileOutputStream	Bajo	Escribe datos de archivos como bytes

FileReader	Bajo	Lee datos de archivos como caracteres

FileWriter	Bajo	Escribe datos de archivos como caracteres

BufferedInputStream	Alto	Lee datos de bytes de un flujo de entrada existente de manera bufferizada, lo que mejora la eficiencia y el rendimiento

BufferedOutputStream	Alto	Escribe datos de bytes en un flujo de salida existente de manera bufferizada, lo que mejora la eficiencia y el rendimiento

BufferedReader	Alto	Lee datos de caracteres de un objeto Reader existente de manera bufferizada, lo que mejora la eficiencia y el rendimiento

BufferedWriter	Alto	Escribe datos de caracteres en un objeto Writer existente de manera bufferizada, lo que mejora la eficiencia y elrendimiento

ObjectInputStream	Alto	Deserializa tipos de datos primitivos de Java y gráficos de objetos de Java a partir de un flujo de entrada existente

ObjectOutputStream	Alto	Serializa tipos de datos primitivos de Java y gráficos de objetos de Java en un flujo de salida existente

PrintStream	Alto	Escribe representaciones formateadas de objetos Java en un flujo binario

PrintWriter	Alto	Escribe representaciones formateadas de objetos Java en un flujo de caracteres


### Métodos habituales y de manipulación de flujos

read()

write()

readline()

EJEMPLO DE BUFFER

    import java.io.FileOutputStream;
    import java.io.IOException;
    
    public class EjemploWriteConOffset {
    public static void main(String[] args) {
    // El mensaje original a escribir
    String mensaje = "Este es un ejemplo de uso del método write con offset y length.";

        // Convertimos el mensaje a un array de bytes
        byte[] datos = mensaje.getBytes();

        // Especificamos el archivo de salida
        try (FileOutputStream fos = new FileOutputStream("salida.txt")) {
            // Escribimos solo una parte del array de bytes (por ejemplo, desde el índice 5 y 10 bytes)
            int offset = 5;
            int length = 10;

            fos.write(datos, offset, length); // Escribe los 10 bytes comenzando desde el índice 5
            
            System.out.println("Parte del mensaje escrita correctamente en el archivo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


Manipulación de flujos de entrada: Mark, Reset y Skip

Todos los flujos de entrada

boolean markSupported()

Este método se usa para saber si el flujo soporta el uso del método mark() y reset(). No todos los flujos permiten "marcar" una posición y luego "resetear" (volver) a esa posición más tarde, por lo que markSupported() te ayuda a determinar si esta funcionalidad está disponible.

Devuelve true si la clase de flujo admite mark()

    InputStream inputStream = new FileInputStream("archivo.txt");
    
    if (inputStream.markSupported()) {
    System.out.println("El flujo soporta mark/reset.");
    } else {
    System.out.println("El flujo NO soporta mark/reset.");
    }


void mark(int readLimit)

Marca la posición actual en el flujo

Este método permite marcar una posición en el flujo de entrada. 
Luego, puedes usar reset() para volver a esa posición marcada. 
El parámetro readLimit indica el número de bytes que pueden ser leídos antes de que la marca se invalida.

    InputStream inputStream = new FileInputStream("archivo.txt");
    
    // Marca la posición actual en el flujo
    inputStream.mark(100);  // Puede leer hasta 100 bytes antes de perder la marca
    
    // Lee algunos bytes del flujo
    inputStream.read();
    inputStream.read();
    
    // Volver a la posición marcada
    inputStream.reset();

void reset()	

Intenta restablecer el flujo a la posición marcada

Este método se usa para devolver el flujo de entrada a la última posición marcada con el método mark().
Si no se ha marcado previamente una posición, se lanza una excepción IOException.

    InputStream inputStream = new FileInputStream("archivo.txt");
    
    inputStream.mark(200);  // Marca la posición actual
    
    inputStream.read();  // Lee un byte
    
    inputStream.reset();  // Vuelve a la posición marcada


long skip(long n)	

Lee y descarta un número especificado de caracteres

Este método permite saltar un número específico de bytes en el flujo de entrada. 
Es útil si quieres saltarte una parte del flujo sin procesarla.

    InputStream inputStream = new FileInputStream("archivo.txt");
    
    // Salta 100 bytes en el flujo
    long bytesSaltados = inputStream.skip(100);
    System.out.println("Se saltaron " + bytesSaltados + " bytes.");


void flush() (flujos de salida)	


Descripción: Este método se usa en los flujos de salida (como OutputStream o Writer) 
para asegurar que los datos en el búfer de salida sean escritos efectivamente en el destino. Algunos flujos de salida, como BufferedOutputStream o BufferedWriter, almacenan datos temporalmente en un búfer antes de escribirlos en el destino final, 
y el método flush() asegura que esos datos sean vaciados y enviados al destino inmediatamente.

Vacía los datos acumulados a través del flujo

    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("archivo.txt"));
    bufferedOutputStream.write("Texto que se va a escribir".getBytes());
    
    // Asegura que todos los datos en el búfer se escriban en el archivo
    bufferedOutputStream.flush();
    
    // Después de flush, los datos están realmente en el archivo
    bufferedOutputStream.close();



### GESTIÓN DE EXCEPCIONES

El uso de flujos obliga a controlar excepciones en función de cada tipo. Las habituales:

Con flujos de archivos: 

### FileNotFoundException

Ejemplo: Ocurre si intentas leer un archivo con new FileInputStream("ruta_del_archivo") y el archivo no se encuentra.


### IOException

Es una excepción general de entrada y salida que cubre una amplia gama de errores.

Es la superclase de muchas otras excepciones de E/S, incluyendo FileNotFoundException.

### EOFException

(End of File Exception)

Aparece cuando se intenta leer más allá del final de un archivo o flujo de datos. Si estás leyendo un archivo binario y el flujo llega al final, EOFException se lanzará indicando que ya no hay más datos para leer.


    import java.io.*;
    
    public class ManejoExcepcionesFlujos {
        public static void main(String[] args) {
            try (FileInputStream fis = new FileInputStream("archivo.txt")) {
                int dato;
                while ((dato = fis.read()) != -1) {
                     System.out.print((char) dato);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Archivo no encontrado: " + e.getMessage());
            } catch (EOFException e) {
                System.out.println("Fin del archivo alcanzado.");
            } catch (IOException e) {
                System.out.println("Error de E/S: " + e.getMessage());
            }
        }
    }

### OTRAS:

URISyntaxException

MalformedURLException:

Se lanza cuando se intenta crear una URL con un formato inválido.
EJ: si proporcionas una cadena de texto que no cumple con el formato de una URL válida en new URL("url_incorrecta").

ClassNotFountException:

Ocurre durante la deserialización si la clase del objeto no se encuentra en el classpath.

Ejemplo: Aparece si intentas deserializar un objeto en un entorno donde la clase no está disponible (como en una máquina diferente).

