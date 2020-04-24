package Nodes;
import java.io.*; 
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Literal {
                /*
                     Add member variables for this class as per your needs 
                */

                /*
	                 processsing literal Node
	                 jsonObject --> contains information of this Node 
                */

					public static String literal(JSONObject jsonObject)
					{
						JSONObject j =  (JSONObject) jsonObject.get("attributes");
						return j.get("value").toString();
					}
				
}