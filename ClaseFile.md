## Operaciones con ficheros

Clases de E/S utilizadas:

- java.io.File > anterior a Java 7, operaciones con ficheros y directorios
- java.io.RandomAccessFile > permite acceso aleatorio a archivos
- java.nio.Path > posterior a Java 7, más eficiente, uso junto a Files
- java.nio.Files > métodos estáticos para ficheros, directorios, flujos

### Clase File

Operaciones de lectura, listado, creació/eliminación > archivos/directorios

No contiene info, se utiliza para pasar una referencia a métodos/constructores

#### Constructores:

- Ruta completa (absoluta/relativa)

- Dos strings que construyen la ruta padre/hijo

Ejemplo:
File apuntes = new File("/home/otto", "apuntes/javaio.md");

- Un file que referencia al directorio y un string que completa la ruta de archivp

Ejemplo:

File directorioPadre = new File("/home/otto");

File arquivo3 = new File(directorioPadre, "apuntes/javaio.md");

#### Métodos:

boolean createNewFile()

- crea un nuevo archivo> se invoca desde un File que almacena la ruta donde queremos crearlo

Ej: filePrueba.createNewFile() > devolverá true si tiene éxito

static File createTempFile(String prefix, String suffix)

boolean delete();

- Borra el archivo > devuelve true o false, se usa por ello en secuencias condicionales/lógicas

boolean exists();

- Comprueba si existe

String getAbsolutePath()

- devuelve la ruta absoluta

String getName()

- devuelve nombre de archivo o directorio (nombre+extensión: ej: prueba.txt)

String getParent()

- devuelve el directorio en el que se encuentra la ruta (null si no hay)

boolean isDirectory() / boolean isFile()

- comprueba is es directorio o archivo

long lastModified()

- el tiempo transcurrido en milisegundos desde que se modificó

long length()

- devuelve el tamaño del archivo

String list()

- devuelve un array de Strings con los archivos/directorios de la ruta

File[] listFiles()

- devuelve un array de objetos File de un directorio: muy útil para listar una carpeta de archivos que se quiere procesar

Ej: usar el método en una carpeta con archivos y luego trabajar sobre ellos con un forEach

static File[] listRoots()

- método estático que devuelve las raíces del sistema de directorios

boolean mkdir()

- crea directorio en la ruta especificada (caso de uso similar a delete())

boolean mkdirs()

- Crea el directorio especificado en la ruta, incluyendo cualquier directorio principal inexistente

boolean renameTo(File dest)

- cambia el nombre del arch/dir al especificado en esa ruta

Path toPath()

- para convertir un File a un Path

Uri toURI()

- lo convierte en URI

### EXCEPCIONES Y VERIFICACIONES COMUNES

#### IOEXCEPTION

    try {
        File file = new File("ruta/del/archivo.txt");
        file.createNewFile();
    } catch (IOException e) {
        System.out.println("Error de entrada/salida: " + e.getMessage());
        e.printStackTrace();
    }

Una excepción IOException (o alguna de sus subclases) puede ocurrir en muchas operaciones de archivos, 
especialmente al leer o escribir datos.
Esta excepción debe manejarse o declararse en el método. 
A menudo surge debido a problemas con permisos o recursos del sistema de archivos.

#### SECURITYEXCEPTION

Una SecurityException puede ocurrir si el programa no tiene permisos suficientes
para acceder al archivo o directorio.

    try {
        File file = new File("ruta/del/archivo.txt");
        file.canRead();  // método para ver si es de lectura
    } catch (SecurityException e) {
        System.out.println("Error de seguridad: No se puede acceder al archivo.");
        e.printStackTrace();
    }

#### NULLPOINTEREXCEPTION

Al intentar operar a un archivo que no existe (es null). Es necesario tratar los nulos.

    File[] files = file.listFiles();
    if (files != null) {
        for (File f : files) {
        System.out.println("Nombre del archivo: " + f.getName());
        }
    } else {
        System.out.println("Directorio vacío o inaccesible.");
    }


PARA VERIFICACIONES SE RECOMIENDA USAR MÉTODOS COMO:

exists() > verificar existencia 

isFile(), isDirectory() > ver si es archivo o directorio

canRead(), canWrite() y canExecute() > verificar permisos

getFreeSpace() > verificar espacio de un directorio

verificaciones de directorios vacíos:

    File[] files = file.listFiles();
        if (files == null || files.length == 0) {
        System.out.println("El directorio está vacío o no se puede leer.");
    }

