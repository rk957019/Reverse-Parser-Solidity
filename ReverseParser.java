		import java.io.*; 
		import java.util.*;
		import org.json.simple.JSONArray;
		import org.json.simple.JSONObject;
		import org.json.simple.parser.JSONParser;
		import Nodes.*;


		public class ReverseParser{

						 /*
							 visitMap to check if node is visited?
							 if visited valueMap stores the value of source code generated due to that node
						 */

							 public static HashMap<JSONObject, Integer> visitMap;
							 public static HashMap<JSONObject, String> valueMap;
							 public static int tabs;
							 public static void main(String[] args) throws Exception  
							 {
							 	JSONParser parser = new JSONParser();
							 	Object obj = parser.parse(new FileReader("inputV2.json"));
							 	JSONObject jsonObject = (JSONObject) obj;
							 	visitMap = new HashMap<>();
							 	valueMap = new HashMap<>();
							 	tabs=0;
							 	dfs(jsonObject);
							 	String ans = indent(valueMap.get(jsonObject));
							 	System.out.println(ans);
							 }

					    /*
						 traversing the reversed parsed code in using depth-first travel technique
						 */
						 public static void dfs(JSONObject jsonObject)
						 {
									visitMap.put(jsonObject,1); // node is visited

									JSONArray jsonArray =  (JSONArray)jsonObject.get("children"); // all child nodes
								//  System.out.println(jsonObject.get("name"));
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
									 // if(jsonObject.get("name").equals("UnaryOperation"))System.out.println(valueMap.get(jsonObject));
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
								ans = SourceUnit.sourceUnit(jsonObject,valueMap);
								break;
								case "PragmaDirective" : 
								ans = PragmaDirective.pragmaDirective(jsonObject,valueMap);
								break;
								case "ContractDefinition" :
								ans = ContractDefinition.contractDefinition(jsonObject,valueMap);
								break;
								case "VariableDeclaration" :
								ans = VariableDeclaration.variableDeclaration(jsonObject,valueMap);
								break;
								case "ElementaryTypeName" : 
								ans = ElementaryTypeName.elementaryTypeName(jsonObject,valueMap);
								break;
								case "FunctionDefinition" :
								ans = FunctionDefinition.functionDefinition(jsonObject,valueMap);
								break;
								case "ParameterList" : 
								ans = ParameterList.parameterList(jsonObject,valueMap);
								break;
								case "Literal" :
								ans = Literal.literal(jsonObject);
								break;
								case "Identifier" :
								ans = Identifier.identifier(jsonObject);
								break;
								case "Assignment" :
								ans = Assignment.assignment(jsonObject,valueMap);
								break;
								case "ExpressionStatement" :
								ans = ExpressionStatement.expressionStatement(jsonObject,valueMap);
								break;
								case  "Block" :
								ans = Block.block(jsonObject,valueMap);
								break;
								case  "Return" :
								ans = ReturnNode.Return(jsonObject,valueMap);
								break;
								case  "TupleExpression" :
								ans = TupleExpression.tupleExpression(jsonObject,valueMap);
								break;
								case  "ArrayTypeName" :
								ans = ArrayTypeName.arrayTypeName(jsonObject,valueMap);
								break;
								case  "VariableDeclarationStatement" :
								ans = VariableDeclarationStatement.variableDeclarationStatement(jsonObject,valueMap);
								break;
								case  "UnaryOperation" :
								ans = UnaryOperation.unaryOperation(jsonObject,valueMap);
								break;
								case  "ElementaryTypeNameExpression" : 
								ans = ElementaryTypeNameExpression.elementaryTypeNameExpression(jsonObject,valueMap);
								break;
								case  "BinaryOperation" :
								ans = BinaryOperation.binaryOperation(jsonObject,valueMap);
								break;
								case  "IndexAccess" :
								ans = IndexAccess.indexAccess(jsonObject,valueMap);
								break;
								case  "FunctionCall" :
								ans = FunctionCall.functionCall(jsonObject,valueMap);
								break;
								case   "ForStatement" :
								ans  = ForStatement.forStatement(jsonObject,valueMap);
								break;
								case   "IfStatement" :
								ans  = IfStatement.ifStatement(jsonObject,valueMap);
								break;
								case   "WhileStatement" :
								ans = WhileStatement.whileStatement(jsonObject,valueMap);
								break;
								case   "DoWhileStatement" :
								ans = DoWhileStatement.doWhileStatement(jsonObject,valueMap);
								break;
								case    "Conditional" : 
								ans = Conditional.conditional(jsonObject,valueMap);
								break;
								default :
								ans = "";
							}
							return ans;
						}



					public static String indent(String s)
					{
						String ans = "";
						for(int i=0;i<s.length();i++)
						{
							if(s.charAt(i)=='{')tabs++;
							if(s.charAt(i)=='}')
							{
								tabs--;
								ans = ans.substring(0, ans.length() - 1);
								ans = ans.substring(0, ans.length() - 1);
							}
							ans = ans + s.charAt(i);
							if(s.charAt(i)=='\n')
							{
								for(int j=0;j<tabs;j++)ans = ans + "\t\t";
							}
					    }
					    return ans;
				    }

			}