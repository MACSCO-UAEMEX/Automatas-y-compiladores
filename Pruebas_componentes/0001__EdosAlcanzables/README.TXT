_________________________________________________________________________________________________
               0001__Estados_No_Alcanzables     Eliminar estados no alcanzables de un autómata
                                    MCC 4 generacion  
                                          >--ö--< 
https://github.com/MACSCO-UAEMEX/Automatas-y-compiladores/tree/master/Programa%203%20-%20AFD%20to%20AFDM
_________________________________________________________________________________________________


Este programa elimina los estados no alcanzables de un autómata dados un conjunto 
de parametros que son: los estados del autómata, estados iniciales, estados finales
y la funcion de transiciones.

Para determinar si un estado es alcanzable o no, se comienza a recorrer a partir del 
estado inicial, los estados por los que transita el estado inicial son añadidos a un mapa 
de transiciones. Este proceso se hace para todos los estados transitables a partir del estado
inicial, los estados que no forman parte del hashmap de estados final, son no transitables.

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

#Rutas de trabajo

-FWORK		"0001__Entrada\prueba_1"
-FSALIDA	"0002__Salida"

