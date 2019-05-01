package sc.ustc.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadDi {
	DiBean diBean;
	private static String path="E:\\eclipseworkspace\\UseSC\\di.xml";
	public static ArrayList<String> readi() throws DocumentException{
		ArrayList<String> idStrings=new ArrayList<String>();
		SAXReader reader=new SAXReader();
		Document document=reader.read(new File(path));
		String xpath="//sc-di//bean";
		List<String> list=document.selectNodes(xpath);
		Iterator iter=list.iterator();
		while(iter.hasNext()) {
			Element element=(Element) iter.next();
			idStrings.add(element.attribute(0).getValue());
			System.out.println(element.attribute(0).getValue());
			
		}
		return idStrings;
		
	}
	public static ArrayList<String> readiclass(String diclass) throws DocumentException{
		ArrayList<String> classfield=new ArrayList<>();
		SAXReader reader1=new SAXReader();
		Document document=reader1.read(new File(path));
//		System.out.println(diclass);
		String xpath="//sc-di//bean[@id='"+diclass+"']//field";
		List<String> list=document.selectNodes(xpath);
		Iterator iter=list.iterator();
		System.out.println(iter.hasNext());
//		int i=0;
		while(iter.hasNext()) {
//			System.out.println(1);
			Element element=(Element) iter.next();
			classfield.add(element.attribute(0).getValue());
			classfield.add(element.attribute(1).getValue());
			System.out.println(element.attribute(0).getValue());
			System.out.println(element.attribute(1).getValue());
//			i=i+1;
//			System.out.println(i);
		}
		
		return classfield;
		
	}
	public String getclass(String name) throws DocumentException {
		String classname="";
		SAXReader reader1=new SAXReader();
		Document document=reader1.read(new File(path));
		String xpath="//sc-di//bean[@id='"+name+"']";
		List<String> list=document.selectNodes(xpath);
		Iterator iter=list.iterator();
		while(iter.hasNext()) {
			Element element = (Element) iter.next();
			classname=classname+element.attribute(1).getValue();
			System.out.println(classname);
		}
		return classname;
		
		
	}
	public static void main(String[] args) throws DocumentException {
		ReadDi readDi=new ReadDi();
		readDi.readiclass("login");
}
	
}
