### Clase RandomAccessFile

Para acceso aleatorio.

    RandomAccessFile file = new RandomAccessFile("c:\\programas\\holamundo.kt", "rw");

El constructor recibe la ruta y después se especifica el modo:

- r > lectura
- rw > lectura y escritura
- rwd > sincrónicamente
- rws > sincrónicamente (también metadatos)

#### Métodos: 

seek(long p);

Permite situar el puntero en la posición indicada en el interior.

getFilePointer();

Devuelve la posición actual del puntero (un long).

#### !!ES MUY IMPORTANTE CONOCER LA POSICIÓN PARA REALIZAR LAS OPERACIONES

read();

Lee un byte del archivo (avanza el puntero). Hay diversos métodos para todo tipo de datos (readInt, readBoolean...)

readLine()

Lee la siguiente linea del archivo

read(byte [] b)

String	readUTF()
Lectura de un String.

Puede leer un array de bytes:

    byte[] dest = new byte[1024]; // Array de bytes donde se almacenarán los datos leídos, llamado buffer.
    int offset = 0; // Posición inicial de lectura
    int length  = 1024; // Posición final de lectura 
    int bytesLeidos = randomAccessFile.read(dest, offset, length); // 
    
    // aplicación del ejemplo:
    RandomAccessFile randomAccessFile = new RandomAccessFile("programas/datos.txt", "r");
    byte[] dest = new byte[1024];
    int bytesLeidos;
    
    // mientras no devuelva -1, se lee a través del buffer a partir de la posicion 0 y hasta el final 
    while ((bytesLeidos = randomAccessFile.read(dest, 0, dest.length)) != -1) {
    // Procesa el bloque de datos leído, almacenado en 'dest'
        System.out.write(dest, 0, bytesLeidos); // Ejemplo: escribir en consola
    }
    randomAccessFile.close();



write(int b) y write(byte [] b)

El método de escritura toma un entero como parámetro o un array.
Sin embargo, tiene versiones para diferentes tipos de datos.

writeUTF(String str)

Escribe un String

int skipBytes(int n)

Mueve el puntero hacia delante n bytes.

close();

Es necesario cerrar el archivo tras su uso, o usar try-with-resources

    try (RandomAccessFile file = new RandomAccessFile("c:\\programas\\holamundo.kt", "rw")) {

    // lectura o escritura en el RandomAccessFile }   // lo cierra el programa por nosotros


## EXCEPCIONES

Para todas las rutinas de lectura en esta clase que, si se alcanza el final del archivo antes de que se haya leído el número deseado de bytes, se lanza una excepción EOFException (que es un tipo de IOException).

Si no se puede leer ningún byte por alguna razón que no sea el final del archivo, se lanza una IOException distinta a EOFException. En particular, puede lanzarse una IOException si el flujo ha sido cerrado.

   
    
    try {
        RandomAccessFile raf = new RandomAccessFile("proba.txt", "rw");
        raf.writeUTF("Hola, mundo!");
        raf.seek(0);
        System.out.println(raf.readUTF());
        raf.close();
    } catch (IOException e) {
        e.printStackTrace();
    }












