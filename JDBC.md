## CONEXIONES CON BD - JDBC

### Establecer conexión: 

se utiliza un objeto Connection y la clase DriveManager, que permite establecer la conexión a la DB
mediante el método get connection()

    private Connection con = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);

La forma habitual es pasarle como parámetros url de la BD, usuario y contraseña,
pero tiene varias versiones.

ej de url: "jdbc:h2:C:\\Users\\a23albertogc\\Desktop\\AD\\biblioteca2"

### CONSULTAS

JDBC ofrece métodos y clases que permiten recuperar un conjunto de resultados de la BD,
a partir de una declaración de consulta SQL.

Para ello se crean objetos que implementen la interfaz Statement:

- Statement

- PreparedStatement

- CallableStatement

- RowSet

A partir de estos se obtiene un objeto Result Set: una tabla de datos que representa un conjunto de resultados de la consulta

### Statement (método básico de ejemplo de consulta)

    String query = "select nome, idProveedor, precio, ventas, total from Cafe";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String nombreCafe = rs.getString("nome");
                    int idProveedor = rs.getInt("idProveedor");
                    float precio = rs.getFloat("precio");
                    int ventas = rs.getInt("ventas");
                    int total = rs.getInt("total");
                    System.out.println(nombreCafe + ", " + idProveedor + ", " + precio +
                        ", " + ventas + ", " + total);
                }
        } catch (SQLException e) {
            // Manejo de excepciones
        }
        
Explicación del ejemplo: 

Un string almacena la sentencia SQL que indica la consulta.

Se crea un Statement a partir de la conexión. 

Se ejecuta la consulta creando un Result Set, e invocando al método executeQuery(consulta) con el objeto Statement

A partir de ahí, se recorren los resultados de esa consulta mediante el método next() de ResultSet

Este método, que arranca antes de la primera fila, mueve el cursor a la siguiente fila mediante un bucle while, 
para así ir permitiendo la recuperación de datos de las columnas de esa fila mediante los diferentes get que ofrece ResultSet

### CURSORES

Los cursores se pueden configurar a través de los parámetros y argumentos que se le pasen a createStatement, prepareStatement, prepareCall

#### primer argumento: tipo 

TYPE_FORWARD_ONLY (prederminado): el cursor se mueve solo hacia adelante

TYPE_SCROLL_INSENSITIVE: el cursor puede moverse hacia adelante y hacia atrás 
con respecto a la posición actual, y puede moverse a una posición absoluta. 
El ResultSet no es sensible a los cambios realizados en la base de datos

TYPE_SCROLL_SENSITIVE: el cursor puede moverse hacia adelante y hacia atrás
con respecto a la posición actual, y puede moverse a una posición absoluta.
El ResultSet refleja los cambios realizados en la base de datos subyacente mientras está abierto.

No todas las BD soportan todos estos tipos de RS, por ello existen formas de saberlo:

  
#### Segundo argumento: concurrencia

Determina el nivel de funcionalidad de actualización que permite:

CONCUR_READ_ONLY: ResultSet no se puede actualizar.

CONCUR_UPDATABLE: ResultSet se puede actualizar.

Ejemplo de sintaxis y métodos para verificar si la BD soporta ambos tipos de arg

    try {
    if (con.getMetaData().supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE)) {
    System.out.println("Soporta TYPE_SCROLL_INSENSITIVE");
    } else {
    System.out.println("No soporta TYPE_SCROLL_INSENSITIVE");
    } if (con.getMetaData().supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_UPDATABLE)) {
    System.out.println("Soporta CONCUR_UPDATABLE");
    } else {
    System.out.println("No soporta CONCUR_UPDATABLE");
    }
    } catch (SQLException ex) {
    System.out.println("Error al obtener metadatos: " + ex.getMessage());
    }

#### Tercer argumento: retención del cursor

HOLD_CURSORS_OVER_COMMIT: los cursores ResultSet no se cierran;
permanecen abiertos cuando se llama al método commit. 
Los cursores retenidos pueden ser ideales si la aplicación utiliza principalmente 
objetos ResultSet de solo lectura.

CLOSE_CURSORS_AT_COMMIT: Los objetos ResultSet (cursores) se cierran 
cuando se llama al método commit.
Cerrar cursores al llamar a este método puede dar mejor rendimiento para algunas aplicaciones.


DatabaseMetaData.supportsResultSetHoldability devuelve true si se soporta este tipo de arg

### Métodos para mover el cursor

next() > adelante una fila (ÚNICO COMPATIBLE CON TYPE_FORWARD_ONLY)

previous > atrás una fila

first > a la primera fila

last > a la última fila

beforeFirst > al comienzo del RS (antes de la primera fila)

afterLast > después de última fila

relative (int rows) > lo mueve en relación a su posición actual

absolute (int rows) > lo mueve a la posición indicada por parámetro

### Métodos de recuperación y actualización de datos

Métodos get > recuperar valores de las filas del RS
métodos update >actualizar campos del RS

Una vez actualizados, se debe llamar a updateRow para hacer efectivos los cambios

    public void actualizaPrecios(float percentage) throws SQLException {
        try (Statement stmt =
        con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
        ResultSet uprs = stmt.executeQuery("SELECT * FROM Cafe");
         while (uprs.next()) {
            float f = uprs.getFloat("precio");
            uprs.updateFloat("precio", f * percentaje);
            uprs.updateRow();
         }
    } catch (SQLException e) {
    // Manejo de excepciones
    }
    }

En el ejemplo, se ejecuta una consulta en la tabla Café.

El cursor está configurado para poder moverse y poder actualizar datos.

Se recupera el campo precio de cada fila de la tabla y se modifica con updateFloat.

Tras cada iteracción, se confirman los cambios con updateRow()

### ACTUALIZACIONES POR LOTES

...



### Prepared Statement

Es la recomendada en la mayoría de los casos. Es una sentencia precompilada que se puede ejecutar repetidas veces.
A diferencia de Statement, se le pasa el String de la consulta cuando se crea. 
    
    public Book get(long id) {
        String selecc = "select * from Book where idBook=?";
        try (PreparedStatement ps = con.prepareStatement(selecc)){
            ps.setLong(1, id);
            ResultSet resultado= ps.executeQuery(selecc);
            Book temp = new Book();
            while (resultado.next()){
                Long identif = resultado.getLong("idBook");
                String is = resultado.getString("isbn");
                String tit = resultado.getString("titulo");
                String aut = resultado.getString("autor");
                int year = resultado.getInt("anho");
                boolean disp = resultado.getBoolean("disponible");
                byte [] port = resultado.getBytes("portada");
                Date date = resultado.getDate("dataPublicacion");
                temp.setIdBook(identif).setIsbn(is).setTitle(tit).setAuthor(aut).setYear(year).setAvaliable(disp).setPortada(port).setDataPublicacion(date);
        }
        return temp;

        } catch (SQLException e) {
            if (con != null) {
                try {
                    System.err.print("La transacción se está revirtiendo");
                    con.rollback();
                } catch (SQLException excep) {
                    // Gestión de excepciones.
                }
            }throw new RuntimeException(e);
        }

La sentencia utiliza ? para determinar los parámetros que se le van a enviar a la BD
Con el set, se sitúa el orden del ? que quieres modificar y después se le añade el valor que quieres modificar

EL método retiene el valor que le mandas al ? hasta que se reestablece o se use clearParameters

...



### CLAVES GENERADAS

...

### TRANSACCIONES

Métodos esenciales: 

con.setAutoCommit(false); // Desactivación del autocommit

con.commit(); // Confirmación de los cambios de la operación

Savepoint puntoSalvar = con.setSavepoint(); // Creación de punto de guardado que vuelve a un punto determinado

con.rollback(puntoSalvar); // con esta sentencia se revierte hasta al punto rollback indicado


### OBJETOS GRANDES - LOB 

BLOB > se usa para binarios

CLOB > se usa para cadenas largas de caracteres

AÑADIR OBJETOS CLOB:

setClob tiene varias versiones con flujos de datos, en este caso de tipo Reader

    void setClob(int parameterIndex, Reader reader);
    void setClob(int parameterIndex, Reader reader, long length);
    void setClob(int parameterIndex, Clob x);
    
    void setCharacterStream(int parameterIndex, Reader reader);
    void setCharacterStream(int parameterIndex, Reader reader, int length);
    void setCharacterStream(int parameterIndex, Reader reader, long length);

Para crear el objeto primero se tiene que crear a partir de un objeto Connection,
con el método createClob(); y luego setearlo en el prepareStatement: 
    
    public void addDescripcionProducto(String nome, String nomeArquivo) throws SQLException {
    // Cfreación del objeto Clob:
    Clob clobDescripcion = this.con.createClob();
    
        try (PreparedStatement pstmt = this.con.prepareStatement("INSERT INTO Producto VALUES(?,?)");
          Writer clobWriter = clobDescripcion.setCharacterStream(1);){ 
            // setCharacterStream devuelve un objeto Writer y recibe un entero que indica la posición inicial del Clob.
            
          String str = this.readFile(nomeArquivo, clobWriter); // Lee el conteido del archivo. 
          System.out.println("Escribo el texto: " + clobWriter.toString());
          
          // Si el archivo es demasiado grande, se puede escribir en el Clob en trozos.
          clobDescripcion.setString(1, str);
          
          System.out.println("Longitud del clob: " + clobDescripcion.length());
          pstmt.setString(1, nome);
          pstmt.setClob(2, clobDescripcion); // Se añade el Clob al PreparedStatement.
          pstmt.executeUpdate();
          
        } catch (SQLException sqlex) {
          // Gestión de excepciones.
        } catch (Exception ex) {
          System.out.println("Excepción no esperada: " + ex.toString());
        }
    }
    
    private String readFile(String nomeArquivo, Writer writer) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
    String nextLine = "";
    StringBuffer sb = new StringBuffer();
    while ((nextLine = br.readLine()) != null) {
    System.out.println("Escribiendo: " + nextLine);
    writer.write(nextLine);
    sb.append(nextLine);
    }
    // Convertir el contenido en una cadena
    String datosClob = sb.toString();
    // devolución de los datos.
    return datosClob;
    }
    }




## EXCEPCIONES

SqlException

