javac -d .\bin -cp .\bin .\src\principal\*.java
xcopy .\src\imagenes .\bin\imagenes\ /E /Y
copy .\manifest.mf .\bin\
cd bin && jar cvfm BlocDeNotas.jar .\manifest.mf .\*
cd ..

java -jar bin\BlocDeNotas.jar