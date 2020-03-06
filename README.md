# Reverse-Parser-Solidity
Generate the source code from the parsed input file of the solidity source code.

-- Add dependencies

Download json-simple-1.1.1.jar from below link in usr/local/lib
https://cdn.crunchify.com/wp-content/uploads/code/json-simple-1.1.1.jar

Add CLASSPATH
$ export CLASSPATH=".:/usr/local/lib/json-simple-1.1.1.jar:$CLASSPATH"

--Running commands

 /* specify input file address inside main funtion in ReverseParser.java file */
 
javac ReverseParser.java 

java ReverseParser

/* a output file will be generated */

outputV2.txt is the source code according to inputV2.json
