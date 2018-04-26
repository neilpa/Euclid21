classfiles := $(wildcard *.0)

Euclid21.jar: *.java
	mkdir -p ./build
	javac -d ./build $?
	cd ./build; jar cvmf ../Manifest.txt Euclid21.jar *.class
