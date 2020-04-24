package Nodes;
import java.io.*; 
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class VariableDeclarationStatement {
                /*
                     Add member variables for this class as per your needs 
                */

                /*
	                 processsing variableDeclarationStatement Node
	                 jsonObject --> contains information of this Node 
	                 valueMap --> maps the childs of this node if it exists with the code  generated by them
                */
             public static String variableDeclarationStatement(JSONObject jsonObject,HashMap<JSONObject, String> valueMap)
				{
					JSONArray jsonArray = (JSONArray)jsonObject.get("children");

					return valueMap.get(jsonArray.get(0))+ " = " +valueMap.get(jsonArray.get(1));
				}

				
}