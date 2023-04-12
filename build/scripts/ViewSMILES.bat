@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  ViewSMILES startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and VIEW_SMILES_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\ViewSMILES-0.1.0-SNAPSHOT.jar;%APP_HOME%\lib\cdk-bundle-2.8.jar;%APP_HOME%\lib\javafx-swing-11.0.2-win.jar;%APP_HOME%\lib\javafx-controls-11.0.2-win.jar;%APP_HOME%\lib\javafx-graphics-11.0.2-win.jar;%APP_HOME%\lib\javafx-graphics-11.0.2.jar;%APP_HOME%\lib\javafx-base-11.0.2-win.jar;%APP_HOME%\lib\javafx-base-11.0.2.jar;%APP_HOME%\lib\cdk-qsarprotein-2.8.jar;%APP_HOME%\lib\cdk-pdbcml-2.8.jar;%APP_HOME%\lib\cdk-pdb-2.8.jar;%APP_HOME%\lib\cdk-qsarcml-2.8.jar;%APP_HOME%\lib\cdk-libiomd-2.8.jar;%APP_HOME%\lib\cdk-libiocml-2.8.jar;%APP_HOME%\lib\cdk-legacy-2.8.jar;%APP_HOME%\lib\cdk-extra-2.8.jar;%APP_HOME%\lib\cdk-builder3d-2.8.jar;%APP_HOME%\lib\cdk-depict-2.8.jar;%APP_HOME%\lib\cdk-sdg-2.8.jar;%APP_HOME%\lib\cdk-io-2.8.jar;%APP_HOME%\lib\cdk-qsaratomic-2.8.jar;%APP_HOME%\lib\cdk-forcefield-2.8.jar;%APP_HOME%\lib\cdk-qsarmolecular-2.8.jar;%APP_HOME%\lib\cdk-atomtype-2.8.jar;%APP_HOME%\lib\cdk-builder3dtools-2.8.jar;%APP_HOME%\lib\cdk-fragment-2.8.jar;%APP_HOME%\lib\cdk-tautomer-2.8.jar;%APP_HOME%\lib\cdk-smiles-2.8.jar;%APP_HOME%\lib\cdk-model-2.8.jar;%APP_HOME%\lib\cdk-fingerprint-2.8.jar;%APP_HOME%\lib\cdk-qsarbond-2.8.jar;%APP_HOME%\lib\cdk-charges-2.8.jar;%APP_HOME%\lib\cdk-structgen-2.8.jar;%APP_HOME%\lib\cdk-valencycheck-2.8.jar;%APP_HOME%\lib\cdk-pcore-2.8.jar;%APP_HOME%\lib\cdk-smarts-2.8.jar;%APP_HOME%\lib\cdk-reaction-2.8.jar;%APP_HOME%\lib\cdk-ctab-2.8.jar;%APP_HOME%\lib\cdk-isomorphism-2.8.jar;%APP_HOME%\lib\cdk-formula-2.8.jar;%APP_HOME%\lib\cdk-cip-2.8.jar;%APP_HOME%\lib\cdk-signature-2.8.jar;%APP_HOME%\lib\cdk-qsar-2.8.jar;%APP_HOME%\lib\cdk-renderawt-2.8.jar;%APP_HOME%\lib\cdk-renderextra-2.8.jar;%APP_HOME%\lib\cdk-renderbasic-2.8.jar;%APP_HOME%\lib\cdk-inchi-2.8.jar;%APP_HOME%\lib\cdk-group-2.8.jar;%APP_HOME%\lib\cdk-standard-2.8.jar;%APP_HOME%\lib\cdk-datadebug-2.8.jar;%APP_HOME%\lib\cdk-data-2.8.jar;%APP_HOME%\lib\cdk-silent-2.8.jar;%APP_HOME%\lib\cdk-hash-2.8.jar;%APP_HOME%\lib\cdk-render-2.8.jar;%APP_HOME%\lib\cdk-core-2.8.jar;%APP_HOME%\lib\cdk-diff-2.8.jar;%APP_HOME%\lib\cdk-dict-2.8.jar;%APP_HOME%\lib\cdk-interfaces-2.8.jar;%APP_HOME%\lib\cdk-ioformats-2.8.jar;%APP_HOME%\lib\cdk-ionpot-2.8.jar;%APP_HOME%\lib\cdk-qsarionpot-2.8.jar;%APP_HOME%\lib\cdk-control-2.8.jar;%APP_HOME%\lib\cdk-qm-2.8.jar;%APP_HOME%\lib\cdk-jniinchi-support-2.8.jar;%APP_HOME%\lib\vecmath-1.5.2.jar;%APP_HOME%\lib\cmlxom-4.3.jar;%APP_HOME%\lib\euclid-2.3.jar;%APP_HOME%\lib\xom-1.3.7.jar;%APP_HOME%\lib\beam-func-1.3.4.jar;%APP_HOME%\lib\beam-core-1.3.4.jar;%APP_HOME%\lib\jama-1.0.3.jar;%APP_HOME%\lib\signatures-1.1.jar;%APP_HOME%\lib\jna-inchi-core-1.1.jar;%APP_HOME%\lib\jna-inchi-api-1.1.jar;%APP_HOME%\lib\jgrapht-0.6.0.jar;%APP_HOME%\lib\freehep-graphicsio-pdf-2.4.jar;%APP_HOME%\lib\freehep-graphicsio-ps-2.4.jar;%APP_HOME%\lib\xercesImpl-2.8.0.jar;%APP_HOME%\lib\xalan-2.7.2.jar;%APP_HOME%\lib\joda-time-2.11.0.jar;%APP_HOME%\lib\commons-io-2.11.0.jar;%APP_HOME%\lib\log4j-1.2-api-2.18.0.jar;%APP_HOME%\lib\tagsoup-1.2.1.jar;%APP_HOME%\lib\serializer-2.7.2.jar;%APP_HOME%\lib\jna-5.10.0.jar;%APP_HOME%\lib\jna-inchi-darwin-aarch64-1.1.jar;%APP_HOME%\lib\jna-inchi-darwin-x86-64-1.1.jar;%APP_HOME%\lib\jna-inchi-linux-arm-1.1.jar;%APP_HOME%\lib\jna-inchi-linux-x86-1.1.jar;%APP_HOME%\lib\jna-inchi-linux-x86-64-1.1.jar;%APP_HOME%\lib\jna-inchi-win32-x86-1.1.jar;%APP_HOME%\lib\jna-inchi-win32-x86-64-1.1.jar;%APP_HOME%\lib\freehep-graphicsio-tests-2.4.jar;%APP_HOME%\lib\freehep-graphicsio-2.4.jar;%APP_HOME%\lib\freehep-graphics2d-2.4.jar;%APP_HOME%\lib\freehep-graphicsbase-2.4.jar;%APP_HOME%\lib\log4j-core-2.18.0.jar;%APP_HOME%\lib\commons-lang3-3.12.0.jar;%APP_HOME%\lib\commons-math-2.2.jar;%APP_HOME%\lib\log4j-api-2.18.0.jar;%APP_HOME%\lib\freehep-io-2.2.2.jar


@rem Execute ViewSMILES
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %VIEW_SMILES_OPTS%  -classpath "%CLASSPATH%" de.whs.ibci.mrottmann.main.Main %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable VIEW_SMILES_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%VIEW_SMILES_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
