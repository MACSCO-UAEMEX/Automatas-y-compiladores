_______________________________________________________________________________
		     AFDtoAFDM -Autómata finito determinista a determinista
                                         a  Autómata finito determinista minimizado.
		    Teoría de la computación

Este componente minimiza un  Autómata finito determinista, por lo que si se desconoce en que condición 
esta el autómata, es recomendable primero usar el componente de transfomacion de un AFND a AFD, además 
de que existe la posibilidad que el automata ingresado ya este minimizado, por lo que en estos casos los
 archivos de salida seran exactamente los mismos que de entrada.
_______________________________________________________________________________

SINTAXIS

-CONFIG:str  

-CONFIG  Archivo donde se encuentran los parametros que contiene las entradas para el componente, a continuación se describren:

-Q:str  -A:str  -EI:str  -F:str -T:str -FWORK:str  -FSALIDA:str 

  Donde:

-Q	Conjunto de estados del autómata, entre cada estado existe una coma (,)  para su separacion 

                  Nombre de archivo: EstadosT.txt


-A	Alfabeto de las transiciones, entre cada transicion existe una coma (,)  para su separacion 
                  Nombre de archivo: Alfabeto_Espanol.txt


-EI	EI es el estado incial que corresponde al automata, pertenece a Q,de forma predeterminiada el estado inicial es 0



-F	Corresponde a los estados o estado final que corresponde al automata, pertenece a Q
                  Nombre de archivo: Finales.txt


-T	Corresponde a la funcion de transicion, el estado X con el elemento del alfabeto Y va hacia otro estado X.
                  Nombre de archivo: FT.txt

#Rutas de trabajo

-FWORK		"0001__Entrada\Prueba_1"
-FSALIDA	  0002__Salida

Ejemplo:

El componente buscara los archivos especificados anteriormente en el archivo CONFIG, ya que aqui estan almacenadas las rutas de los
 parametros solicitados en el componente, primero se valida que el contenido del parametro T este correcto, para esto se compara su 
contenido con el contenido de los parametros Q, A, EI y F.

Después de validar las respectivas entradas, se procede a minimizar el autómata, para eso se empiezan a dividir en sub-grupos
 los estados, separando aquellos que se dirigen a los estdos finales y aquellos que no, despues se repite el proceso diviendo 
aquellos grupos que se dirigen al nuevo grupo creado y aquellos, asi hasta terminar con todos los estados.

Como ultimo paso solo se deja un representante de cada grupo y los demas ya no se consideran, solo existe la excepcion de que si en 
un grupo existe un estado inicial este sera el que se considere.



