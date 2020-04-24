package Nodes;
import java.io.*; 
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// contains some utility functions

public class Utility {
                    public static Boolean is(String name)
					{
						if(name.equals("DoWhileStatement") || name.equals("WhileStatement") || name.equals("ForStatement") || name.equals("IfStatement"))return false;
						return true;
					}
					public static Boolean isBlock (String name)
					{
						return name.equals("Block");
					}

					
}