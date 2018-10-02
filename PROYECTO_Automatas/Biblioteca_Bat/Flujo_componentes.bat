@ECHO off

:flujo
ECHO %1
ECHO %2
ECHO %3
ECHO %4
ECHO %5
::-FWORK "0002__Entradas\Pruebas_documento\Prueba_1"
::-FSALIDA	"0003__Salidas\0001__Salidas_1"

DIR
ECHO.
ECHO Copie los archivos del automata a la carpeta: 01_In_Automata. Y verifique la ruta -FSALIDA del archivo de salida CONFIG
ECHO.
PAUSE

::cli.py pathDelArchivoCadena\cadena.txt
ECHO.
ECHO Verifique la ruta -FSALIDA del archivo de salida CONFIG
ECHO.
PAUSE

::java -jar ..\Biblioteca_Componentes\AFNDtoAFD.jar -CONFIG "01_In_Automata/CONFIG.txt"
ECHO.
ECHO Verifique la ruta -FSALIDA del archivo de salida CONFIG
ECHO.
PAUSE

::java -jar ..\Biblioteca_Componentes\EstadosAlcanzables.jar -CONFIG "02_Out_AFND_TO_AFD/CONFIG.txt"
ECHO.
ECHO Verifique la ruta -FSALIDA del archivo de salida CONFIG
ECHO.
PAUSE

::java -jar ..\Biblioteca_Componentes\AFD_To_AFDM.jar -CONFIG "03_Out_EdosAlcanzables/CONFIG.txt"
ECHO.
ECHO Verifique la ruta -FSALIDA del archivo de salida CONFIG
ECHO.
PAUSE

::java -jar ..\Biblioteca_Componentes\PruebaCadenas.jar -CONFIG "04_Out_AFD_TO_AFDM/CONFIG.txt"
ECHO.
ECHO Verifique la ruta -FSALIDA del archivo de salida CONFIG
ECHO.
PAUSE

::java -jar ..\Biblioteca_Componentes\Turing.jar -CONFIG "05_Out_PruebaCadenas/CONFIG.txt"
PAUSE


