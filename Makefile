classfiles := $(wildcard *.0)

Euclid21.jar: *.java Manifest.txt
	javac $?
	jar cvmf Manifest.txt Euclid21.jar *.class
