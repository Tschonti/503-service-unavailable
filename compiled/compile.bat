javac -d ./ ../src/agents/*.java ../src/equipments/*.java ../src/main/*.java ../src/tiles/*.java
jar cfe ../prototype.jar main/Main agents/*.class equipments/*.class main/*.class tiles/*.class