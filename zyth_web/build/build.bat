@echo off
rem ������������Ϣ�Ƿ���ȷ
set "CURRENT_DIR=%~dp0"
%~d0
cd %CURRENT_DIR%
cd ..
set "PROJECT_DIR=%cd%"
set "LOGFILE=%PROJECT_DIR%\build\build.log"
echo PROJECT Paht:%PROJECT_DIR% > %LOGFILE%
if exist "%PROJECT_DIR%\pom.xml" goto choice
if not exist "%PROJECT_DIR%\pom.xml" echo %PROJECT_DIR%\pom.xml is not exist! >> %LOGFILE%
goto end

rem ѡ��������������ʽ���������²��Ի���
:choice
set /p PACKAGE_TYPE=choose package type (test or local)[caojun_aliyun:online_setting]:
if "%PACKAGE_TYPE%" == "online_setting"  goto package
if "%PACKAGE_TYPE%" == ""  goto package
if "%PACKAGE_TYPE%" == "caojun_aliyun"   goto package


echo ERROR : please input exact value... >> %LOGFILE%
goto end

rem ���
:package
echo --------package for %PACKAGE_TYPE%-------- >> %LOGFILE%
mvn clean install compiler:compile  package -D project.build.package^=%PACKAGE_TYPE%  >> %LOGFILE%

rem �������
:end