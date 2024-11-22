## PATRÓN SINGLETON

1- Declaración de objeto 

    private static final ClaseSingleton instancia = new ClaseSingleton();

    private static final ClaseSingleton instancia;
    
        static {
            instancia = new ClaseSingleton();
            // Realizar pasos adicionales
        }


2- Bloque estático 


    private static final ClaseSingleton instancia;

    static {
        instancia = new ClaseSingleton();
        // Realizar pasos adicionales
    }


3- Instanciación perezosa

    private static ClaseSingleton instancia;
    
        private ClaseSingleton() {
        }
    
        public static ClaseSingleton getInstance() {
            if (instancia == null) {
                instancia = new ClaseSingleton(); // ¡NO ES SEGURO PARA HILOS!
            }
            return instancia;
        }

BLOQUE ESTÁTICO: 

Permite realizar pasos adicionales para configurar el singleton 
después de que se ha creado. 

Permite gestionar casos en los que el constructor lanza una excepción.

Dado que el singleton se crea cuando se carga la clase, 
permite marcarla como final> garantiza que sólo se creará una instancia dentro de la aplicación.

INSTANCIACIÓN PEREZOSA:

Da problemas en un entorno multihilo> varios hilos pueden acceder al mismo tiempo
y, entonces, detectarlo como nullal mismo tiempo y crear instancia

Solución? hacerlo seguro y sincronizarlo:

    
    // método sincronizado para controlar el acceso simultáneo
    synchronized public static ClaseSingletonAD getInstance() {
    if (instance == null) {
    // si la instancia es nula, inicializar
    instance = new ClaseSingletonAD();
    }
 
Pegas? La sincronización es POCO EFICIENTE> consume y ralentiza
dado que el resto de hilos se quedan a la espera SIEMPRE que se 
intenta acceder a la isntancia

Por eso se usa un patrón de DOBLE COMPROBACIÓN

private static volatile ClaseSingleton instance;

    public static ClaseSingleton getInstance() {
        if (instance == null) {
            synchronized (ClaseSingleton.class) {
                if (instance == null) {
                    instance = new ClaseSingleton();
                }
             }
         }
        return instance;
    }

Funcionamiento: 

> los hilos SOLO TIENEN QUE ESPERAR LA PRIMERA VEZ que se crea el objeto
> Es decir, cuando es NULL, dado que se usa un bloque de código sincronizado
> Detecta null y es cuando se accede al bloque de código sincronizado
> 