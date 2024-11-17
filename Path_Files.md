## Operaciones con ficheros: Path y Files

NIO.2 es una sustitución para la antigua clase java.io.File

Proporciona mejor rendimiento.

### Path

Similar a File > se le pasa un String que representa una ubicación de archivo (relativa o absoluta)

Se construlle con Path.of()

    Path path1 = Path.of("fotos/batman.png");

    Path path2 = Path.of("c:", "users", "pepe", "notas.txt");

    Path path3 = Path.of("/", "home", "otto");

o Paths.get()

    Path path1 = Paths.get("fotos/batman.png");
    Path path2 = Paths.get("c:\\users\\pepe\\notas.txt");
    Path path3 = Paths.get("/", "home", "otto");

Constructor mediante File:

    File file = new File("wittgenstein.png");
    Path path = file.toPath();
    File vuetaAFile = path.toFile();

Constructor mediante URI

    URI a = new URI("file://nohaycole.txt");
    Path b = Path.of(a);

Usando FileSystem > permite trabajar directamente con el sistema de archivos

    Path path1 = FileSystems.getDefault().getPath("fotos/batman.png");
    Path path2 = FileSystems.getDefault().getPath("c:\\users\\pepe\\notas.txt");
    Path path3 = FileSystems.getDefault().getPath("/home/otto");

Además, ofrece acceso remoto: 

    FileSystem fileSystem = FileSystems.getFileSystem(new URI("http://www.imdb.con"));
    Path path = fileSystem.getPath("top250.txt");

### MÉTODOS de PATH y de FILES


public boolean isAbsolute()

Detecta si la ruta es absoluta

public Path toAbsolutePath()

Convierte un path relativo en absoluto

getFileName()

Devuelve el nombre del archivo

getParent()

Devuelve el padre

getRoot()

Devuelve la raíz

subpath()

Permite seleccionar partes de un path para crear uno nuevo 

    var p = Paths.get("/mamifero/omnivoro/mapache.imagen");
    System.out.println("El Path es: " + p);
    
    for (int i = 0; i < p.getNameCount(); i++) {
    System.out.println(" Elemento " + i + " es: " + p.getName(i));
    }
    
    System.out.println();
    
    System.out.println("subpath(0,3): " + p.subpath(0, 3));
    System.out.println("subpath(1,2): " + p.subpath(1, 2));
    System.out.println("subpath(1,3): " + p.subpath(1, 3));


    El Path es: /mamifero/omnivoro/mapache.imagen
    Elemento 0 es: mamifero
    Elemento 1 es: omnivoro
    Elemento 2 es: mapache.imagen
    
    subpath(0,3): mamifero/omnivoro/mapache.imagen
    subpath(1,2): omnivoro
    subpath(1,3): omnivoro/mapache.imagen

toString()

devuelve una cadena del path


public int getNameCount()

Devuelve el número de elementos de la ruta 

    Path path = Paths.get("C:/Users/John/Documents/MyFile.txt");
    int nameCount = path.getNameCount();
    System.out.println(nameCount); // Imprime: 4

    Explicación: El índice corresponde a estas partes:

    0: Users
    1: John
    2: Documents
    3: MyFile.txt

public Path getName(int index)

Devuelve el nombre del elemento en una posición específica dentro de la ruta.

    Path name = path.getName(2);
    System.out.println(name); // Imprime: Documents

Path relativize(Path other)

Este método calcula una ruta relativa de un Path actual hacia otro Path proporcionado como argumento.

    Path path1 = Paths.get("C:/Users/John/Documents");
    Path path2 = Paths.get("C:/Users/John/Downloads");
    
    Path relativePath = path1.relativize(path2);
    System.out.println(relativePath); // Salida: ../Downloads

Path resolve(Path other)

Este método combina un Path base con otro Path proporcionado.

Si el Path proporcionado es absoluto, se devuelve tal cual.

Si es relativo, se añade al final del Path base.

    Path base = Paths.get("C:/Users/John");
    Path relativePath = Paths.get("Documents/MyFile.txt");
    Path absolutePath = Paths.get("D:/Backup");
    
    Path resolvedRelative = base.resolve(relativePath);
    Path resolvedAbsolute = base.resolve(absolutePath);
    
    System.out.println(resolvedRelative); // Salida: C:/Users/John/Documents/MyFile.txt
    System.out.println(resolvedAbsolute); // Salida: D:/Backup


Path normalize()

Este método elimina las partes redundantes de un Path, 
como . (directorio actual) y .. (directorio padre).

    
    
    Path path = Paths.get("C:/Users/John/Documents/../Downloads/./MyFile.txt");
    Path normalizedPath = path.normalize();
    
    System.out.println(normalizedPath); // Salida: C:/Users/John/Downloads/MyFile.txt

Path toRealPath(LinkOption... options)

Convierte un Path en su forma real y absoluta.

Comprueba que el archivo o directorio realmente exista en el sistema de archivos.
Si el Path contiene enlaces simbólicos, los resuelve.


    Path symbolicPath = Paths.get("C:/Shortcut/MyFile.txt");
    try {
    Path realPath = symbolicPath.toRealPath();
    System.out.println(realPath); // Salida: C:/Users/John/Documents/MyFile.txt
    } catch (IOException e) {
    System.out.println("El archivo no existe: " + e.getMessage());
    }

Explicación:

Si el archivo existe y hay enlaces simbólicos, este método devuelve la ruta real resolviendo los enlaces simbólicos.
Si el archivo no existe, lanza una excepción IOException.


Files.exists()

Verifica si el archivo existe

Files.copy

Copiar archivos o directorios

Parámetros: ruta origen, destino, y opción específica (ejemplo, decir que sobre escriba)

source: La ruta del archivo o directorio de origen.
target: La ruta del archivo o directorio de destino.
options (opcional): Puedes usar opciones como StandardCopyOption.REPLACE_EXISTING para sobrescribir un archivo si ya existe.

    public static void main(String[] args) {
        Path source = Paths.get("C:/MiCarpeta/origen.txt");
        Path target = Paths.get("C:/MiCarpeta/copia.txt");

        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

Files.move

    public static void main(String[] args) {
    Path source = Paths.get("C:/MiCarpeta/origen.txt");
    Path target = Paths.get("C:/MiCarpeta/movido.txt");
    
            try {
                Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Archivo movido exitosamente.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }






Files.walkFileTree

Este método recorre un árbol de directorios de manera recursiva.
Para cada elemento encontrado (archivos o directorios), 
ejecuta las acciones definidas en un objeto de tipo FileVisitor, 
que en este caso es una instancia de SimpleFileVisitor.
    
    import java.io.IOException;
    import java.nio.file.*;
    import java.nio.file.attribute.BasicFileAttributes;
    
    public class CopyDirectoryExample {
    public static void main(String[] args) {
    Path sourceDir = Paths.get("C:/MiCarpeta/Origen");
    Path targetDir = Paths.get("C:/MiCarpeta/Destino");

        try {
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Path targetPath = targetDir.resolve(sourceDir.relativize(file));
                    Files.copy(file, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path targetPath = targetDir.resolve(sourceDir.relativize(dir));
                    Files.createDirectories(targetPath);
                    return FileVisitResult.CONTINUE;
                }
            });
            System.out.println("Directorio copiado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




### EXCEPCIONES

IOException

Por no existir el archivo, problemas de conexión o de permisos del archivo