Euclid21.jar: *.java
	mkdir -p ./build
	javac -d ./build $?
	pushd ./build; jar cvmf ../Manifest.txt Euclid21.jar *.class
	mv ./build/Euclid21.jar .
