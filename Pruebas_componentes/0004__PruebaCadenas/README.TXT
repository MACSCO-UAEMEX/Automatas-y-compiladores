========================
BUILD OUTPUT DESCRIPTION
========================


Este programa valida conforma a un automata (Léxico o Sintáctico) varias cadenas, regresando de salida 'true' sí es una cadena válida o 'false' si es una cadena no válida.

________________________________________________________________________________
                                                                            
SINTAXIS:                                                                   

#ELEMENTOS NECESARIOS PARA EL AUTOMATA

# Q es igual al conjunto de estados del automata, los estados van separados por coma

-Q	"Estados.txt"

# A es el alfabeto que corresponde al automata

-A	"AlfeboEsp.txt"

# EI es el estado incial que corresponde al automata, pertenece a Q

-EI	0

# F corresponde a los estados o estado final que corresponde al automata, pertenece a Q

-F	"Finales.txt"


# T corresponde a la funcion de transicion, el estado X con el elemento del alfabeto Y hacia que estado va
# se define por [estado, alfabeto, estado] (Tabulador) [estado, alfabeto, estado]

-T	"FT.txt"

# CAD corresponde al parámetro dónde se colocarán todas las cadenas a validar con el automata generado, cada una separado por comas, y cada una de las transiciones debes estar separadas por espacios.

-CAD "Juan Carlos, Juan Carlos es, a b c, d, e f g h i"

#Rutas de trabajo

-FWORK		"0001__Entrada\prueba_1"
-FSALIDA "0002__Salida"
