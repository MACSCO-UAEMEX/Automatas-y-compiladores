@echo off
title Menu Automata

:prueba
set/p archivo= Escribe el nombre de el archivo: 

:inicio
set var=0
cls

echo Bienvenido
echo.

echo Componentes:
echo.

echo Prueba actual: %archivo%
echo.

echo 1 Estados Alcanzables
echo 2 Automata no determinista a determinista
echo 3 Minimizacion
echo 4 Cadenas validas
echo 5 Maquina de Turing
echo 6 Todos individual
echo 7 Todos en serie
echo 8 Cambiar prueba
echo 0 Salir
echo.

set/p var= Selecciona una opcion (0-8)
if "%var%"== "1" goto o1
if "%var%"== "2" goto o2
if "%var%"== "3" goto o3
if "%var%"== "4" goto o4
if "%var%"== "5" goto o5
if "%var%"== "6" goto o6
if "%var%"== "7" goto o7
if "%var%"== "8" goto o8
if "%var%"== "0" goto o0

echo El numero "%var%" no es una opcion valida, por favor intente de nuevo
echo.
pause
goto:inicio

:o1
cls
echo 
Estados Alcanzables
echo.
echo -FWORK "%archivo%\In_Automata" >> "%archivo%\Out_EdosAlcanzables\CONFIG.txt"
echo -FSALIDA "%archivo%\Out_EdosAlcanzables" >> "%archivo%\Out_EdosAlcanzables\CONFIG.txt"
java -jar BIBLIOTECA_COMPONENTES/EstadosAlcanzables.jar -CONFIG "%CD%\%archivo%\Out_EdosAlcanzables\CONFIG.txt"
echo.

echo Estados Alcanzables completado
echo.
pause
goto:inicio

:o2
cls
echo 
AFND_TO_AFD
echo.
echo -FWORK "%archivo%\In_Automata" >> "%archivo%\Out_AFND_TO_AFD\CONFIG.txt"
echo -FSALIDA "%archivo%\Out_AFND_TO_AFD" >> "%archivo%\Out_AFND_TO_AFD\CONFIG.txt"
java -jar BIBLIOTECA_COMPONENTES/AFNDtoAFD.jar -CONFIG "%CD%\%archivo%\Out_AFND_TO_AFD\CONFIG.txt"
echo.

echo Transformacion completada
echo.
pause
goto:inicio

:o3
cls
echo 
AFD_TO_AFDM
echo.
echo -FWORK "%archivo%\In_Automata" >> "%archivo%\Out_AFD_TO_AFDM\CONFIG.txt"
echo -FSALIDA "%archivo%\Out_AFD_TO_AFDM" >> "%archivo%\Out_AFD_TO_AFDM\CONFIG.txt"
java -jar BIBLIOTECA_COMPONENTES/AFD_To_AFDM.jar -CONFIG "%CD%\%archivo%\Out_AFD_TO_AFDM\CONFIG.txt"
echo.

echo Minimizacion completada
echo.
pause
goto:inicio

:o4
cls
echo CADENAS VALIDAS
echo.
echo -FWORK "%archivo%\In_Automata" >> "%archivo%\Out_PruebaCadenas\CONFIG.txt"
echo -OUT "%archivo%\Out_PruebaCadenas" >> "%archivo%\Out_PruebaCadenas\CONFIG.txt"
java -jar BIBLIOTECA_COMPONENTES/PruebaCadenas.jar -CONFIG "%CD%\%archivo%\Out_PruebaCadenas\CONFIG.txt"
echo.

echo Cadenas validas completado
echo.
pause
goto:inicio

:o5
cls
echo MAQUINA DE TURING
echo.
echo -FWORK "%archivo%\In_Automata" >> "%archivo%\Out_Turing\CONFIG.txt"
echo -OUT "%archivo%\Out_Turing" >> "%archivo%\Out_Turing\CONFIG.txt"
java -jar BIBLIOTECA_COMPONENTES/Turing.jar -CONFIG "%CD%\%archivo%\Out_Turing\CONFIG.txt"
echo.

echo Maquina de Turing completado
echo.
pause
goto:inicio

:o6
cls
echo.
echo Todos individual
echo.
echo -FWORK "%archivo%\In_Automata" >> "%archivo%\Out_EdosAlcanzables\CONFIG.txt"
echo -FSALIDA "%archivo%\Out_EdosAlcanzables" >> "%archivo%\Out_EdosAlcanzables\CONFIG.txt"
java -jar BIBLIOTECA_COMPONENTES/EstadosAlcanzables.jar -CONFIG "%CD%\%archivo%\Out_EdosAlcanzables\CONFIG.txt"
echo Estados Alcanzables completado
pause
echo -FWORK "%archivo%\In_Automata" >> "%archivo%\Out_AFND_TO_AFD\CONFIG.txt"
echo -FSALIDA "%archivo%\Out_AFND_TO_AFD" >> "%archivo%\Out_AFND_TO_AFD\CONFIG.txt"
java -jar BIBLIOTECA_COMPONENTES/AFNDtoAFD.jar -CONFIG "%CD%\%archivo%\Out_AFND_TO_AFD\CONFIG.txt"
echo Transformacion completada
pause
echo -FWORK "%archivo%\In_Automata" >> "%archivo%\Out_AFD_TO_AFDM\CONFIG.txt"
echo -FSALIDA "%archivo%\Out_AFD_TO_AFDM" >> "%archivo%\Out_AFD_TO_AFDM\CONFIG.txt"
java -jar BIBLIOTECA_COMPONENTES/AFD_To_AFDM.jar -CONFIG "%CD%\%archivo%\Out_AFD_TO_AFDM\CONFIG.txt"
echo Minimizacion completada
pause
echo -FWORK "%archivo%\In_Automata" >> "%archivo%\Out_PruebaCadenas\CONFIG.txt"
echo -OUT "%archivo%\Out_PruebaCadenas" >> "%archivo%\Out_PruebaCadenas\CONFIG.txt"
java -jar BIBLIOTECA_COMPONENTES/PruebaCadenas.jar -CONFIG "%CD%\%archivo%\Out_PruebaCadenas\CONFIG.txt"
echo Cadenas validas completado
pause
echo -FWORK "%archivo%\In_Automata" >> "%archivo%\Out_Turing\CONFIG.txt"
echo -OUT "%archivo%\Out_Turing" >> "%archivo%\Out_Turing\CONFIG.txt"
java -jar BIBLIOTECA_COMPONENTES/Turing.jar -CONFIG "%CD%\%archivo%\Out_Turing\CONFIG.txt"
echo Maquina de Turing completado
echo.
pause
goto:inicio

:o7
cls
echo.
echo Todos en serie
echo.
echo -FWORK "%archivo%\In_Automata" >> "%archivo%\Out_EdosAlcanzables\CONFIG.txt"
echo -FSALIDA "%archivo%\Out_EdosAlcanzables" >> "%archivo%\Out_EdosAlcanzables\CONFIG.txt"
java -jar BIBLIOTECA_COMPONENTES/EstadosAlcanzables.jar -CONFIG "%CD%\%archivo%\Out_EdosAlcanzables\CONFIG.txt"
echo Estados Alcanzables completado
pause
echo -FWORK "%archivo%\Out_EdosAlcanzables" >> "%archivo%\Out_AFND_TO_AFD\CONFIG.txt"
echo -FSALIDA "%archivo%\Out_AFND_TO_AFD" >> "%archivo%\Out_AFND_TO_AFD\CONFIG.txt"
java -jar BIBLIOTECA_COMPONENTES/AFNDtoAFD.jar -CONFIG "%CD%\%archivo%\Out_AFND_TO_AFD\CONFIG.txt"
echo Transformacion completada
pause
echo -FWORK "%archivo%\Out_AFND_TO_AFD" >> "%archivo%\Out_AFD_TO_AFDM\CONFIG.txt"
echo -FSALIDA "%archivo%\Out_AFD_TO_AFDM" >> "%archivo%\Out_AFD_TO_AFDM\CONFIG.txt"
java -jar BIBLIOTECA_COMPONENTES/AFD_To_AFDM.jar -CONFIG "%CD%\%archivo%\Out_AFD_TO_AFDM\CONFIG.txt"
echo Minimizacion completada
pause
echo -FWORK "%archivo%\AFD_To_AFDM" >> "%archivo%\Out_PruebaCadenas\CONFIG.txt"
echo -OUT "%archivo%\Out_PruebaCadenas" >> "%archivo%\Out_PruebaCadenas\CONFIG.txt"
java -jar BIBLIOTECA_COMPONENTES/PruebaCadenas.jar -CONFIG "%CD%\%archivo%\Out_PruebaCadenas\CONFIG.txt"
echo Cadenas validas completado
pause
echo -FWORK "%archivo%\PruebaCadenas" >> "%archivo%\Out_Turing\CONFIG.txt"
echo -OUT "%archivo%\Out_Turing" >> "%archivo%\Out_Turing\CONFIG.txt"
java -jar BIBLIOTECA_COMPONENTES/Turing.jar -CONFIG "%CD%\%archivo%\Out_Turing\CONFIG.txt"
echo Maquina de Turing completado
echo.
pause

goto:inicio


:o8
cls
goto:prueba


:o0
@cls&exit


