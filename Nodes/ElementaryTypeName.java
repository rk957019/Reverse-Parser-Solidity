package Nodes;
import java.io.*; 
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ElementaryTypeName{
                /*
                     Add member variables for this class as per your needs 
                */

                /*
	                 processsing elementaryTypeName Node
	                 jsonObject --> contains information of this Node 
	                 valueMap --> maps the childs of this node if it exists with the code  generated by them
                */

             	 // processing elementaryTypeName
				public static String elementaryTypeName(JSONObject jsonObject,HashMap<JSONObject, String> valueMap)
				{
					StringBuilder sb = new StringBuilder();
					JSONObject j = (JSONObject)jsonObject.get("attributes");
					sb.append(j.get("name").toString());
					return sb.toString();
				}

				
}