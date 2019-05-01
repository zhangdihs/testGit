package water.ustc.interceptor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;


public class LogInterceptor  extends HttpServlet{
	private static String name1; 
	private static String result1;
	private static String starttime;
	
	
	public  void PreAction(String name) throws IOException {
		this.name1=name;
		System.out.println("name1 "+name1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		starttime=sdf.format(new Date());
     }
     public  void afterAction(String result) throws IOException {
    	this.result1=result;
    	System.out.println("result1 "+result1);
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    	 Document document1=DocumentHelper.createDocument();
    	 Document document=DocumentHelper.createDocument();
    	 Element rootEle=document.addElement("log");
    	 Element actionEle=rootEle.addElement("action");
    	 actionEle.addElement("name").setText(name1);
    	 actionEle.addElement("s-time").setText(starttime);
    	 actionEle.addElement("e-time").setText(sdf.format(new Date()));
    	 actionEle.addElement("result").setText(result1);
    	 Writer writer=new FileWriter("E:\\eclipseworkspace\\UseSC\\log.xml");
    	 XMLWriter xmlwriter=new XMLWriter(writer);
    	 xmlwriter.write(document);
    	 xmlwriter.close();
    	 
    	 
     }
}
