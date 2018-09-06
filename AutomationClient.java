package ApiTesting;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.poi.xwpf.usermodel.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.JsonObject;


public class AutomationClient {

	public static void main(String[] args) {
			System.out.println();
		new AutomationClient().ToTakeFromXml();
		
		DefaultHttpClient client=new DefaultHttpClient();
			
		try {
		HttpGet request=new HttpGet("http://restapi.demoqa.com/utilities/weather/city/Chennai");
		
		HttpPost post=new HttpPost("http://restapi.demoqa.com/utilities/weather/city/Chennai");
			
		HttpResponse respo= client.execute(request);
		
		int statuscode=respo.getStatusLine().getStatusCode();
		if(statuscode==200)
		{
			System.out.println("execution sucess");
			
			HttpEntity entity = respo.getEntity();

			StringBuffer sb=null;
			String line=null;
			
			 if(!(entity.getContent()==null))
			 {
				 BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
				 sb  = new StringBuffer();

				 while ((line = reader.readLine()) != null) {
			            sb.append(line + "");
			        }
			 }
				 
			 
			line=sb.toString();
			
			//Removing the unwanted Special Char		
			line=line.replaceAll("[-+^{}\"]","");
			
			String Svalue[]=line.split(",");
			
				String TargetValue[] = null;
			
				List<ValueList> lvalue=  new LinkedList<ValueList>();
				String firstName="";
				String SecondName="";
				
			for(int i=0;i<Svalue.length;i++)
			{
				TargetValue=Svalue[i].split(":");
				
				firstName=TargetValue[0];
				SecondName=TargetValue[1];
				
				ValueList api=new ValueList(firstName, SecondName);
				lvalue.add(api);
				
			}
			
			System.out.println("Size for the List is:-"+lvalue.size());
			
			for(ValueList l1:lvalue)
			{
				System.out.println("First Value: "+l1.Key +"    Second Value:- "+l1.Value);
			}
			//post Response 
		
		}
		
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	
	}
	
	protected void ToTakeFromXml()
	{
		DefaultHttpClient clint= new DefaultHttpClient();
		
		try
		{
		HttpGet request= new HttpGet("http://www.cbr.ru/scripts/XML_dynamic.asp?VAL_NM_RQ=R01235&date_req1=01.08.2016&date_req2=18.08.2016&rt=1&mode=1[1]");
		
		HttpResponse resp= clint.execute(request);
		
		if((resp.getStatusLine().getStatusCode())==200)
		{
			HttpEntity entry=resp.getEntity();
			
			 if(!(entry.getContent()==null))
			 {
				 InputStream ins=entry.getContent();
				 
			     DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			     DocumentBuilder db = dbf.newDocumentBuilder();
			     org.w3c.dom.Document doc =  db.parse(ins);
			     NodeList nodeList = doc.getElementsByTagName("Record");
			     
			     List<DataStore> Recodata=null;
			   
			     for (int temp = 0; temp < nodeList.getLength(); temp++) {

			 		NodeList childList = nodeList.item(temp).getChildNodes();
			 				
			 		/*System.out.println("\nCurrent Element :" + nNode.);
			 				
			 		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			 			Node eElement =  nNode;

			 			System.out.println("Staff id : " + eElement.getAttributes());
			 			
			 			System.out.println(eElement.getNodeValue());
			 			
			 			
			 		}*/
			 	   Recodata = new ArrayList<DataStore>();
			 		 	
			            for (int i = 0; i < childList.getLength(); i++) 
			            {
			            	  Node childNode = childList.item(i);
			            	  
			            	  Recodata.add(getValues(childNode,childList,i));
			            	  
			            }
			            for (DataStore DS : Recodata) {
			                System.out.println(DS.toString());
			            }
			        		                
			     }
			     for (DataStore DS : Recodata) {
		                System.out.println(DS.toString());
		            }
		             
			 }
		}
		
		
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
			
	}
    private static DataStore getValues(Node childNode,NodeList childList,int j) 
    {
        //XMLReaderDOM domReader = new XMLReaderDOM();
    	DataStore ds = new DataStore();
        
    	 ds.setTag(childNode.getNodeName());
    	 
         ds.setValue(childList.item(j).getTextContent().trim());
        

        return ds;
    }

}
class DataStore {
    private String Tag;
    private String Value;
    public String getTag() {
        return Tag;
    }
    public void setTag(String Tag) {
        this.Tag = Tag;
    }
    public String getValue() {
        return Value;
    }
    public void setValue(String Value) {
        this.Value = Value;
    }
    
    
    @Override
    public String toString() {
        return "Tag=" + this.Tag + " Value=" + this.Value ;
    }
    
}

interface A
{
	public void dell();
	
}

class ValueList
{
	String Key;
	String Value;
	
  public ValueList(String Key,String Value)
  {
	  this.Key=Key;
	  this.Value=Value;
  }
}