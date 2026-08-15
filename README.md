Manejo de Mayúsculas y Minúsculas: 
Se ha establecido un tratamiento equivalente. Independientemente de si el usuario ingresa una letra en minúscula o mayúscula, 
la lógica del sistema (en AhorcadoBase) convierte automáticamente cualquier carácter ingresado a 

Tratamiento de Tildes y la Letra Ñ: 
Se ha decidido que para las tildes y caracteres acentuados . Si el jugador ingresa una letra con tilde, el sistema lo vera como una palabra sin tilde normal. Entonces el jugador al momento de adivinar la letra solo debe usar las letras normales.
Es valido utilizar la letra ñ, y si una palabra contiene ñ el jugador debe ponerla en el teclado.

Mensajes de Error y Retroalimentación:

Caracter Inválido (CaracterInvalidoException): Se muestra cuando el usuario ingresa números, símbolos, espacios en blanco.
Mensaje: "El caracter ingresado no es válido. Debe ingresar solo letras de la A a la Z (sin tildes) o Ñ."

Letra Duplicada (LetraDuplicadaException): Se activa cuando el usuario intenta repetir una letra que ya había ingresado previamente en la partida.
Mensaje : "La letra: [X] ha sido ingresada previamente."

Palabra Existente / Vacía (PalabraExistenteException): Utilizada al momento de registrar o configurar palabras nuevas en el administrador para evitar duplicados o campos vacíos.
Intentos Agotados / Derrota: Cuando el contador de intentos restantes llega a cero, se notifica al jugador el fin de la partida revelando la palabra secreta correspondiente.
