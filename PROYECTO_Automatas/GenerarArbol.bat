@ECHO off
REM "Desactivamos la salida en consola de los comandos"

REM "Definimos el Alcance de nuestras variables; Habilitamos las extensiones de los comandos; creamos variables para el path y la ruta del archivo .bat"
SETLOCAL ENABLEEXTENSIONS
SET me=%~n0
SET parent=%~dp0

REM "Cambiamos el color de nuestra terminal:"
COLOR 0A

REM "Indica el titulo"
TITLE Genera el Arbol de Carpetas

REM "Despliega las instrucciones"
ECHO Este archivo bat genera la estructura de carpetas para una prueba de expresion regular o de Automata 
ECHO.



:: ******** Loop principal ********
:opcion

REM "Almacena en la variable %expReg_o_Aut%" el tipo de estructura a procesar.
SET /p expReg_o_Aut=Que vas a procesar (escribre la letra)?     Expresion Regular [e]  ,  Automata [a]  = 
ECHO.

REM "almacena en %id% el identificador de la prueba"
SET /p id=Introduce  el ID de la prueba: 
ECHO.

IF "%expReg_o_Aut%"=="e" (
   ECHO Se procesara una Expresion Regular.
   ECHO.
   :: "Llama a la funcion crearArbolExpReg"
   CALL :crearArbolExpReg "%id%"
) ELSE (
   IF "%expReg_o_Aut%"=="a" (
      ECHO Se procesara un Automata
      ECHO.
      :: "Llama a la funcion crearArbolAutomata"
      CALL :crearArbolAutomata "%id%"
   ) ELSE (
      ECHO Escriba una opcion correcta: e, a =  
      CALL :opcion
   )
)

PAUSE

:: force execution to quit at the end of the "main" logic
EXIT /B %ERRORLEVEL%




:: *********************** CuasiFunctions ***************************


:: a function to write to a log file and write to stdout
:tee
ECHO %* >> "%log%"
ECHO %*
EXIT /B 0

:: FUNCTION to create a tree of RegularExpressionTest kind directories
:crearArbolExpReg
ECHO Creando el arbol de Directorios para Pruebas de Expresiones Regulares %*
ECHO.
SET CARP=Pruebas_ExpReg_%*
MD %CARP%
CD %CARP%
MD 01_In_ExpReg_To_Automata
MD 02_Out_AutomataND
MD 03_Out_AFND_TO_AFD
MD 04_Out_EdosAlcanzables
MD 05_Out_AFD_TO_AFDM
MD 06_Out_PruebaCadenas
MD 07_Out_Turing
DIR
COPY ..\Biblioteca_bat\Correr_coponentes_ExpReg.bat
COPY ..\CONFIG.txt

SET file=Cadena.txt
IF EXIST "%file%" DELETE /Q %file% >NUL
ECHO. > %file%

CD
::CALL %CARP%\Correr_coponentes_ExpReg.bat %CARP
EXIT /B 0


:: FUNCTION to create a tree of AutomataTest kind directories
:crearArbolAutomata
ECHO Creando el arbol de Directorios para Pruebas de Automatas %*
ECHO.
SET CARP=Pruebas_Automatas_%*
MD %CARP%
CD %CARP%
MD 01_In_Automata
MD 02_Out_AFND_TO_AFD
MD 03_Out_EdosAlcanzables
MD 04_Out_AFD_TO_AFDM
MD 05_Out_PruebaCadenas
MD 06_Out_Turing
DIR
COPY ..\Biblioteca_bat\Correr_coponentes.bat
COPY ..\CONFIG.txt

SET file=Cadena.txt
IF EXIST "%file%" DELETE /Q %file% >NUL
ECHO. > %file%

CD
::CALL %CARP%\Correr_coponentes.bat %CARP
EXIT /B 0

