_________________________________________________________________________________________________
               0002__AFND_To_AFD     Autómata finito No Determinista a Autómata Finito Determinista
                                    MCC 4 generacion  
                                          >--ö--< 
https://github.com/MACSCO-UAEMEX/Automatas-y-compiladores/tree/master/Programa%203%20-%20AFD%20to%20AFDM
_________________________________________________________________________________________________


Este programa convierte un autómata finito no determinista en autómata finito determinista.
En este programa se verifican las transiciones de cada estado, los estados que tienen mas de
una transicion con el mismo alfabeto se unen y son retiquetados, estos estados pasan a formar
un nuevo estado y esto es repetido hasta que no existan mas uniones de estados. Los estados únicos
que van con el mismo alfabeto a mas de un estado son eliminados.

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

