javac -d ./ ../src/agents/*.java ../src/equipments/*.java ../src/main/*.java ../src/skeleton/*.java ../src/tiles/*.java
jar cfe ../prototype.jar main/Main agents/*.class equipments/*.class main/*.class skeleton/*.class tiles/*.class