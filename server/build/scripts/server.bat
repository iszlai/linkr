@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  server startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and SERVER_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args
if "%@eval[2+2]" == "4" goto 4NT_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*
goto execute

:4NT_args
@rem Get arguments from the 4NT Shell from JP Software
set CMD_LINE_ARGS=%$

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\server.jar;%APP_HOME%\lib\scala-library-2.11.8.jar;%APP_HOME%\lib\http4s-dsl_2.11-0.15.0a.jar;%APP_HOME%\lib\http4s-blaze-server_2.11-0.15.0a.jar;%APP_HOME%\lib\http4s-blaze-client_2.11-0.15.0a.jar;%APP_HOME%\lib\slf4j-simple-1.6.2.jar;%APP_HOME%\lib\argonaut_2.11-6.1.jar;%APP_HOME%\lib\http4s-core_2.11-0.15.0a.jar;%APP_HOME%\lib\scala-java8-compat_2.11-0.8.0.jar;%APP_HOME%\lib\http4s-blaze-core_2.11-0.15.0a.jar;%APP_HOME%\lib\http4s-server_2.11-0.15.0a.jar;%APP_HOME%\lib\http4s-client_2.11-0.15.0a.jar;%APP_HOME%\lib\monocle-core_2.11-1.1.0.jar;%APP_HOME%\lib\monocle-macro_2.11-1.1.0.jar;%APP_HOME%\lib\scala-reflect-2.11.6.jar;%APP_HOME%\lib\http4s-websocket_2.11-0.1.6.jar;%APP_HOME%\lib\log4s_2.11-1.3.3.jar;%APP_HOME%\lib\macro-compat_2.11-1.1.1.jar;%APP_HOME%\lib\parboiled_2.11-2.1.3.jar;%APP_HOME%\lib\scalaz-stream_2.11-0.8.6a.jar;%APP_HOME%\lib\blaze-http_2.11-0.12.4.jar;%APP_HOME%\lib\shapeless_2.11-2.3.0.jar;%APP_HOME%\lib\scalaz-concurrent_2.11-7.2.7.jar;%APP_HOME%\lib\scodec-bits_2.11-1.1.2.jar;%APP_HOME%\lib\blaze-core_2.11-0.12.4.jar;%APP_HOME%\lib\hpack-v1.0.1.jar;%APP_HOME%\lib\alpn-api-1.1.2.v20150522.jar;%APP_HOME%\lib\scala-xml_2.11-1.0.5.jar;%APP_HOME%\lib\scalaz-effect_2.11-7.2.7.jar;%APP_HOME%\lib\scalaz-core_2.11-7.2.7.jar;%APP_HOME%\lib\slf4j-api-1.7.21.jar

@rem Execute server
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %SERVER_OPTS%  -classpath "%CLASSPATH%" Main %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable SERVER_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%SERVER_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
