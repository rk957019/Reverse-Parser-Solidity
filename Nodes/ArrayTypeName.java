package Nodes;
import java.io.*; 
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ArrayTypeName {
                /*
                     Add member variables for this class as per your needs 
                */

                /*
	                 processsing arrayTypeName Node
	                 jsonObject --> contains information of this Node 
	                 valueMap --> maps the childs of this node if it exists with the code  generated by them
                */
	                 public static String arrayTypeName(JSONObject jsonObject,HashMap<JSONObject, String> valueMap)
					{
						JSONArray jsonArray = (JSONArray)jsonObject.get("children");
						StringBuilder sb = new StringBuilder();
						sb.append(valueMap.get(jsonArray.get(0)));
						JSONObject j = (JSONObject)jsonObject.get("attributes");
						sb.append("[]");
						return sb.toString();
					}
				
}