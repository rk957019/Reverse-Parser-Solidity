import java.io.*; 
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ReverseParser{

     /*
       visitMap to check if node is visited?
       if visited valueMap stores the value of source code generated due to that node
     */
     public static HashMap<JSONObject, Integer> visitMap;
     public static HashMap<JSONObject, String> valueMap;


	 public static void main(String[] args) throws Exception  
	 {
	 	    JSONParser parser = new JSONParser();
	 		Object obj = parser.parse(new FileReader("/home/rahul/CS391/LAB5/inputV2.json"));
			JSONObject jsonObject = (JSONObject) obj;
			visitMap = new HashMap<>();
            valueMap = new HashMap<>();
			dfs(jsonObject);
			System.out.println(valueMap.get(jsonObject));
	 }

	 /*
	   traversing the reversed parsed code in using depth-first travel technique
     */
	 public static void dfs(JSONObject jsonObject)
	 {
	        visitMap.put(jsonObject,1); // node is visited

	        JSONArray jsonArray =  (JSONArray)jsonObject.get("children"); // all child nodes

	        if(jsonArray != null)
	        {
	            for(int i = 0; i < jsonArray.size(); i++)
	            {
	            	JSONObject j = (JSONObject)jsonArray.get(i);
	            	if(visitMap.containsKey(j))continue;
	                dfs(j); // traversing its child nodes first
	            }
          }
              
              /*
                storing result of this node in form of key value pair
              */
            valueMap.put(jsonObject, process(jsonObject));
	 }


	 /*
	  calling processing function according to name of nodes
	 */
     public static String process(JSONObject jsonObject)
     {
            String ans = "";
            switch(jsonObject.get("name").toString())
            {
            	case  "SourceUnit" :
            	      ans = sourceUnit(jsonObject);
            	      break;
            	case "PragmaDirective" : 
            	      ans = pragmaDirective(jsonObject);
            	      break;
            	case "ContractDefinition" :
            	      ans = contractDefinition(jsonObject);
            	      break;
            	case "VariableDeclaration" :
            	      ans = variableDeclaration(jsonObject);
            	      break;
            	case "ElementaryTypeName" : 
            	      ans = elementaryTypeName(jsonObject);
            	      break;
            	case "FunctionDefinition" :
            	      ans = functionDefinition(jsonObject);
            	      break;
            	case "ParameterList" : 
            	      ans = parameterList(jsonObject);
            	      break;
            	case "Literal" :
            	      ans = literal(jsonObject);
            	      break;
            	case "Identifier" :
            	      ans = identifier(jsonObject);
            	      break;
            	case "Assignment" :
            	      ans = assignment(jsonObject);
            	      break;
            	case "ExpressionStatement" :
            	      ans = expressionStatement(jsonObject);
            	      break;
            	case  "Block" :
                    ans = block(jsonObject);
                    break;
              case  "Return" :
                     ans = Return(jsonObject);
                     break;
              case  "TupleExpression" :
                    ans = tupleExpression(jsonObject);
                    break;
            	default :
            	      ans = "";
            }
            return ans;
     }

     // processsing if node name is sourceUnit
     public static String sourceUnit(JSONObject jsonObject)
	 {
	 	StringBuilder sb = new StringBuilder();
	 	JSONObject jo = (JSONObject)jsonObject.get("attributes");
	 	String file_name = jo.get("absolutePath").toString();
	 	try{
	 	PrintStream o = new PrintStream(new File(file_name)); 
	 	System.setOut(o); 
	    }
	    catch(Exception e)
	    {

	    }
	 	JSONArray jsonArray =  (JSONArray)jsonObject.get("children");
	    if(jsonArray != null)
	    {
	         for(int i = 0; i < jsonArray.size(); i++)
	         {
		        	JSONObject j1 = (JSONObject)jsonArray.get(i);
	                sb.append(valueMap.get(j1));
	         }
	    }
	    return sb.toString();
	 }
  
	 // processing pragmaDirective
	 public static String pragmaDirective(JSONObject jsonObject)
	 {
	 	    StringBuilder sb = new StringBuilder();
	 	    sb.append("pragma ");
	 	    JSONObject j = (JSONObject)jsonObject.get("attributes");
	 	    JSONArray jsonArray = (JSONArray) j.get("literals");
	 	    for(int i=0;i<jsonArray.size();i++)
	 	    {
	 	    	sb.append(jsonArray.get(i).toString());
	 	    	if(i==0)
	 	    	sb.append(" ");
	 	    }
	 	    sb.append(";\n");
	 	    return sb.toString(); 
	 }

	 //processing contractDefinition
	 public static String contractDefinition(JSONObject jsonObject)
	 {
          StringBuilder sb = new StringBuilder();
          JSONObject j = (JSONObject)jsonObject.get("attributes");
          sb.append(j.get("contractKind").toString()+" ");
          sb.append(j.get("name").toString()+" {\n");
          JSONArray jsonArray =  (JSONArray)jsonObject.get("children");
          if(jsonArray != null)
          {
	          for(int i = 0; i < jsonArray.size(); i++)
	          {
		        	JSONObject j1 = (JSONObject)jsonArray.get(i);
                    sb.append(valueMap.get(j1));
                    if(!j1.get("name").equals("FunctionDefinition"))sb.append(";");
                    sb.append("\n");
	          }
	      }
	      sb.append("}");
          return sb.toString();
	 }

     // processing variableDeclaration
	 public static String variableDeclaration(JSONObject jsonObject)
	 {
          StringBuilder sb = new StringBuilder();
          JSONObject j = (JSONObject)jsonObject.get("attributes");
          JSONArray jsonArray =  (JSONArray)jsonObject.get("children");
          if(jsonArray != null)
          {
	          for(int i = 0; i < jsonArray.size(); i++)
	          {
		        	JSONObject j1 = (JSONObject)jsonArray.get(i);
                    sb.append(valueMap.get(j1));
	          }
	      }
          sb.append(j.get("name").toString());
          return sb.toString();
	 }
     
     // processing elementaryTypeName
	 public static String elementaryTypeName(JSONObject jsonObject)
	 {
	      StringBuilder sb = new StringBuilder();
          JSONObject j = (JSONObject)jsonObject.get("attributes");
          sb.append(j.get("name").toString()+" ");
          return sb.toString();
	 }

     // processing functionDefinition
	 public static String functionDefinition(JSONObject jsonObject)
	 {
	 	  StringBuilder sb = new StringBuilder();
          JSONObject j = (JSONObject)jsonObject.get("attributes");
          sb.append(j.get("kind").toString());
          if(!j.get("name").toString().equals(""))sb.append(" ");
          sb.append(j.get("name").toString());
          JSONArray jsonArray =  (JSONArray)jsonObject.get("children");
          sb.append(valueMap.get(jsonArray.get(0))+" ");
          sb.append(j.get("visibility").toString()+" ");
          try{
          sb.append(j.get("stateMutability").toString()+" ");
          }
          catch(Exception e)
          {

          }
          if(!valueMap.get(jsonArray.get(1)).equals("()")){sb.append("returns"+valueMap.get(jsonArray.get(1)));}
          sb.append("{\n");
          sb.append(valueMap.get(jsonArray.get(2))+"\n");
          sb.append("}");
          return sb.toString();
	 }

     // processing parameterList
	 public static String parameterList(JSONObject jsonObject)
	 {
          StringBuilder sb = new StringBuilder();
          JSONArray jsonArray =  (JSONArray)jsonObject.get("children");
          sb.append("(");
          for(int i = 0; i < jsonArray.size(); i++)
	      {
	      	        if(i>0)sb.append(",");
		        	JSONObject j1 = (JSONObject)jsonArray.get(i);
                    sb.append(valueMap.get(j1));
	      }
	      sb.append(")");
	      return sb.toString();
	 }

       // processing identifier
	 public static String identifier(JSONObject jsonObject)
	 {
	 	  JSONObject j =  (JSONObject) jsonObject.get("attributes");
	 	  return j.get("value").toString();
	 }
	 
	  // processing literal
	  public static String literal(JSONObject jsonObject)
	 {
	 	  JSONObject j =  (JSONObject) jsonObject.get("attributes");
	 	  return j.get("value").toString();
	 }

     // processing assignment
     public static String assignment(JSONObject jsonObject)
     {
         JSONArray jsonArray =  (JSONArray)jsonObject.get("children");
         StringBuilder  sb = new StringBuilder();
         sb.append(valueMap.get(jsonArray.get(0)));
         JSONObject j =  (JSONObject) jsonObject.get("attributes");
         sb.append(j.get("operator").toString());
         sb.append(valueMap.get(jsonArray.get(1)));
        
         return sb.toString();
     }

    // processing expressionStatement
     public static String expressionStatement(JSONObject jsonObject)
     {
     	JSONArray jsonArray =  (JSONArray)jsonObject.get("children");
        return valueMap.get(jsonArray.get(0));
     }

    // processing block
     public static String block(JSONObject jsonObject)
     {
     	JSONArray jsonArray =  (JSONArray)jsonObject.get("children");
     	StringBuilder sb = new StringBuilder();
     	if(jsonArray==null)return sb.toString();
     	for(int i=0;i<jsonArray.size();i++)
     		sb.append(valueMap.get(jsonArray.get(i))+";");
     	return sb.toString();
     }

   // processing Return
     public static String Return(JSONObject jsonObject)
     {
     	JSONArray jsonArray =  (JSONArray)jsonObject.get("children");
        return "return "+ valueMap.get(jsonArray.get(0));
     }

    // processing tupleExpression
     public static String tupleExpression(JSONObject jsonObject)
     {
     	JSONArray jsonArray =  (JSONArray)jsonObject.get("children");
     	StringBuilder sb  = new StringBuilder();
     	sb.append("(");
     	for(int i=0;i<jsonArray.size();i++)
     	{
     		if(i>0)sb.append(", ");
     		sb.append(valueMap.get(jsonArray.get(i)));
     	}
     	sb.append(")");
     	return sb.toString();
     }

}