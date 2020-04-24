# Reverse-Parser-Solidity
Generate the source code from the parsed input file of the solidity source code.

Status: Completed

Files:
- ReverseParser.java: The main file
- V2_input.json : Final version of input file 
- Library.sol : Output generated on final version of input file
- inputV2.json : Initial version of input file
- outputV2.txt : Output generated on initial version of input file
- Nodes : A package which contains all .java and .class of all the nodes
- README.txt: Meta information file


Execution Instructions/Commands:

Download json-simple-1.1.1.jar from below link in usr/local/lib
https://cdn.crunchify.com/wp-content/uploads/code/json-simple-1.1.1.jar

Add CLASSPATH
$export CLASSPATH=".:/usr/local/lib/json-simple-1.1.1.jar:$CLASSPATH"

javac ReverseParser.java

java ReverseParser

A output file will be generated in current directory
