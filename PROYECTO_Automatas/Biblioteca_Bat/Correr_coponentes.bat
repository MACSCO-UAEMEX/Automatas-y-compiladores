@ECHO off
REM "Desactivamos la salida en consola de los comandos"

REM "Definimos el Alcance de nuestras variables; Habilitamos las extensiones de los comandos; creamos variables para el path y la ruta del archivo .bat"
SETLOCAL ENABLEEXTENSIONS
SET me=%~n0
SET parent=%~dp0

REM "Cambiamos el color de nuestra terminal:"
COLOR 0A

REM "Indica el titulo"
TITLE Procesando Automata

REM "Despliega las instrucciones"
ECHO Este archivo bat ejecuta los componentes para procesar un Automata
ECHO.



:: *********************** main logic *******************************
:opcion
:: "Imprime la ubicacion actual"
CD
:: "Ejecuta los componentes"
::CD ..\Biblioteca_Componentes\
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

java -jar ..\Biblioteca_Componentes\AFNDtoAFD.jar -CONFIG "01_In_Automata/CONFIG.txt"
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

:: force execution to quit at the end of the "main" logic
EXIT /B %ERRORLEVEL%




:: *********************** Cuasi Functions ***************************


:: FUNCTION to write to a log file and write to stdout
:tee
ECHO %* >> "%log%"
ECHO %*
EXIT /B 0




