package sc.ustc.tool;

import java.io.File;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.event.DocumentEvent;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class readtool {
	private static    String Path="E:\\eclipseworkspace\\UseSC\\src\\controller.xml";
	public static  ArrayList<String> readXml() throws DocumentException {
		ArrayList<String> NameArray = new ArrayList<String>();
		SAXReader reader = new SAXReader(); 
		Document document = reader.read(new File(Path));
		String xpath="//sc-configuration//controller//action";
		@SuppressWarnings("unchecked")
		List<String> list=document.selectNodes(xpath);
		Iterator iter=list.iterator();
		while( iter.hasNext()) {
			Element element = (Element) iter.next();
		    NameArray.add(element.attribute(0).getValue());
	    System.out.println(element.attribute(0).getValue());
//		    getclassAndmethod(element.attribute(0).getValue());
//		    getResult(element.attribute(0).getValue());
		    
		}
		return NameArray;
	}
	public   String[] getclassAndmethod(String Actionname) throws DocumentException {
		String fieldclass="";
		String  fieldmethod="";
		String[] Actionarray=null;
		SAXReader reader1=new SAXReader();
		Document document1=reader1.read(new File(Path));
		String xpath="//sc-configuration//controller//action[@name='"+Actionname+"']";
		@SuppressWarnings("unchecked")
		List<String> list=document1.selectNodes(xpath);
		Iterator iter = list.iterator();
		while(iter.hasNext()) {
			Element element = (Element) iter.next();
			fieldclass=fieldclass+element.attribute(1).getValue();
			fieldmethod=fieldmethod+element.attribute(2).getValue();
			System.out.println(fieldmethod+" "+fieldclass);
	
		}
		Actionarray=new String[] {fieldclass,fieldmethod};
		return Actionarray;
	}
   public  String[] getResult(String Actionname) throws DocumentException {
	  String resultname="";
	  String resulttype="";
	  String resultvalue="";
	  String[] Resultarray=null;
	  SAXReader reader2=new SAXReader();
	  Document document2=reader2.read(new File(Path));
	  String xpath="//sc-configuration//controller//action[@name='"+Actionname+"']//result";
	  List<String> list=document2.selectNodes(xpath);
	  Iterator iter=list.iterator();
	  while(iter.hasNext()) {
		  Element element=(Element) iter.next();
		  resultname=resultname+element.attribute(0).getValue()+" ";
		  resulttype=resulttype+element.attribute(1).getValue()+" ";
		  resultvalue=resultvalue+element.attribute(2).getValue()+" ";

	  }
	  resultname=resultname.substring(0,resultname.length()-1);
	  resulttype=resulttype.substring(0, resulttype.length()-1);
	  resultvalue=resultvalue.substring(0,resultvalue.length()-1);
	  Resultarray=new String[] {resultname,resulttype,resultvalue};
//	  for(int i=0;i<Resultarray.length;i++)
//	  {
//		  System.out.println(Resultarray[i]);
//	  }
	  return Resultarray;
	   
   }
   public List<String> haveinterceptro(String Actionname) throws DocumentException {
	   ArrayList<String> interArray = new ArrayList<String>();
	   SAXReader reader3=new SAXReader();
	   Document document3=reader3.read(new File(Path));
	   String xpath="//sc-configuration//controller//action[@name='"+Actionname+"']//interceptor-ref";
	   List<String> list=document3.selectNodes(xpath);
//	    return list; 
	   Iterator iter=list.iterator();
	   while( iter.hasNext()) {
			Element element = (Element) iter.next();
			 interArray.add(element.attribute(0).getValue());
			 System.out.println("interarray "+element.attribute(0).getValue());
	   }
	return interArray;
   }
  public String[] getinterceptro() throws DocumentException {
	  String interclass="";
	  String intername="";
	  String interpredo="";
	  String interafterdo="";
	  String[] interarray=null;
	  SAXReader reader4=new SAXReader();
	  Document document4=reader4.read(new File(Path));
	  String xpath="//sc-configuration//interceptor";
	  List<String> list=document4.selectNodes(xpath);
	  Iterator iter=list.iterator();
	  while(iter.hasNext()) {
		  Element element=(Element) iter.next();
		  intername=intername+element.attribute(0).getValue();
		  interclass=interclass+element.attribute(1).getValue();
		  interpredo=interpredo+element.attribute(2).getValue();
		  interafterdo=interafterdo+element.attribute(3).getValue();
		  
	  }
	  interarray=new String[] {intername,interclass,interpredo,interafterdo};
	  
	return interarray;
	  
  }
//   public static void main(String[] args) throws DocumentException {
//	   readXml();
//   }
}

		
		
		
		
		
		
		


