javac src/main/java/ru/nsu/chepik/*.java -d ./build
javadoc -d build/docs/javadoc -sourcepath src/main/java -subpackages ru.nsu.chepik
java -cp ./build ru.nsu.chepik.Game