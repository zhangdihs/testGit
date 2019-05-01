package sc.ustc.controller;

import java.awt.List;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

import javax.lang.model.element.Element;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Document;

import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import sc.ustc.tool.Actionbean;
import sc.ustc.tool.CglibProxy;
import sc.ustc.tool.ReadDi;
import sc.ustc.tool.readtool;

public class SimpleController extends  HttpServlet  {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	String[] Actionarray=null;
	String[] Resultarray=null;
	String result;
	ArrayList<String> dibean;
	ArrayList<String> fieldbean;
	public SimpleController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");//���������������ͬ���͵�����
		response.setCharacterEncoding("utf-8");//������ĸ�ʽΪutf-8����
		 String loginname=request.getParameter("name");
		 String loginpass=request.getParameter("password");
		 System.out.println("loginpass:"+loginpass+" "+loginname);
//	        PrintWriter out = response.getWriter();
//	        out.print("<html>");
//	        out.print("<head>");
//	        out.print("<title>SimpleController</title>");
//	        out.print("</head>");
//	        out.print("<body>��ӭʹ��SimpleController</body>");
//	        out.print("</html>");
	     request.setCharacterEncoding("utf-8");
	     String ActionName=request.getServletPath().toString();//��ȡservlet·��
	     String [] Action=ActionName.split("/");//����ȡ��·����Ϊ��ͬ��String����
	     ActionName=Action[Action.length-1];//��ȡ���һ��·�������Ƽ�ΪAction������
	     ActionName=ActionName.substring(0, ActionName.indexOf("."));
	     ReadDi readDi=new ReadDi();//ʵ����һ������
	      try {
			dibean=readDi.readi();//��ȡ����������Bean����
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//	      int indexdi=dibean.indexOf(ActionName);
//	     if(indexdi!=-1)
	     
	     System.out.println(ActionName);
	     Actionbean actionbean=new Actionbean();
	     actionbean.setActionname(ActionName);
	     readtool mytool=new readtool();
	     
	     try {
			ArrayList<String> Name=mytool.readXml();
			int index=Name.indexOf(ActionName);
			System.out.println(index);
			if(index==-1) {
				response.sendRedirect("/UseSC/LoginAction.html");
			}
			else {
			Actionarray=mytool.getclassAndmethod(ActionName);
		   String Actionclass =Actionarray[0];
		   String Actionmethod=Actionarray[1];
		   actionbean.setActionClass(Actionclass);//ʵ����ActionClass������
		   actionbean.setActionMethod(Actionmethod);//ʵ����ActionMethod������
		   System.out.println(Actionclass);
		   int indexdi=dibean.indexOf(ActionName);//�����Ƿ���bean
		   Class  class1=Class.forName(Actionclass);//ͨ��class���еľ�̬������ȡ��һ������ֽ������
//		   Object obj1=class1.newInstance();
		   
	     if(indexdi!=-1) {
	    	 fieldbean=readDi.readiclass(ActionName);
	    	
		   @SuppressWarnings("unchecked")
		Method method=class1.getDeclaredMethod(Actionmethod, HttpServletRequest.class,HttpServletResponse.class);//��ȡ˽�з���
	    
//		  String result=(String)method.invoke(class1.newInstance(), request,response);//��÷��ص�result
//		  actionbean.setActionresult(result);//ʵ����Actionresult�ĸ�ֵ
//		  Resultarray = mytool.getResult(ActionName);//��÷��ص��ַ�����һ��������
//		String[] result1=Resultarray[0].split("\\s+");//����õ��ַ����ÿո�ֿ�ת��Ϊһ������
//		 String[] type1=Resultarray[1].split("\\s+");//
//		 String[] value1=Resultarray[2].split("\\s+");
//		 int index1=indexofArr(result1, result);
//		 if(index1==-1) {
//			 response.sendRedirect("/UseSC/LoginAction.html");
//		 }
//		 else {
//			 String Actiontype=type1[index1];//����result��λ���ҵ�type��λ��
//			 String Actionvalue=value1[index1];//����result��λ���ҵ�value��ֵ
//			 System.out.println(Actiontype+" "+Actionvalue);
//			 actionbean.setActiontype(Actiontype);//ʵ����Actiontype������
//			 actionbean.setActionvalue(Actionvalue);//ʵ����Actionvalue������
		   ArrayList<String> list=(ArrayList<String>) mytool.haveinterceptro(ActionName);
			System.out.println("list"+mytool.haveinterceptro(ActionName));
			if(list!=null||list.size()!=0)
			{
				CglibProxy proxy=new CglibProxy();//����һ����̬������
				 Object clc= proxy.getProxy(class1,actionbean);//����һ����̬���������
				 if(fieldbean!=null) {
		    		 String beanref=fieldbean.get(1);
		    		 System.out.println("beanref"+beanref);
		    		 String beanname=readDi.getclass(beanref);
		    		 System.out.println("beanname:"+beanname);
		    		 Class  class2=Class.forName(beanname);//����������ȡclassduixiang 
		    		 Object obj=class2.newInstance();
//		    		 Class[] parameterTypes= {String.class,String.class};//������������
//		    		 Constructor constructor=class2.getConstructor(parameterTypes);//���ݲ������ͻ�ȡ��Ӧ�Ĺ��캯��
//		    		 Object[] parameters= {loginname,loginpass};//��������
//		    		 Object o=constructor.newInstance(parameters);//���ݻ�ȡ�Ĳ�������Ͳ�������ʵ��
		    		 BeanInfo beanInfo=Introspector.getBeanInfo(class2);
		    		 PropertyDescriptor pd=new PropertyDescriptor("userName",class2);//��ȡname���Ե�����������
		    		 Method writerMethod=pd.getWriteMethod();//��ȡname���Ե�д����
		    		 writerMethod.invoke(obj, loginname);//ִ��setter����
		    		 PropertyDescriptor pd1=new PropertyDescriptor("userPass",class2);
		    		 Method writerMethod1=pd1.getWriteMethod();
		    		 writerMethod1.invoke(obj, loginpass);
		    		 PropertyDescriptor pd3=new PropertyDescriptor("user",class1);//��ȡloginaction��user����
		  		     Method writerMethod2=pd3.getWriteMethod();//��ȡloginaction��set����
		  		     writerMethod2.invoke(clc, obj);//��user����ֵ��clcobject
		    	 }		   	  		   
				 result=(String) method.invoke(clc,request,response);//���÷�����ƽ��е���
			}
			else {
				 result=(String)method.invoke(class1.newInstance(), request,response);
			}
			}
			System.out.println("Result "+result);
			 actionbean.setActionresult(result);
			 Resultarray = mytool.getResult(ActionName);
			 String[] result1=Resultarray[0].split("\\s+");//����õ��ַ����ÿո�ֿ�ת��Ϊһ������
			 String[] type1=Resultarray[1].split("\\s+");//
			 String[] value1=Resultarray[2].split("\\s+");
			 int index1=indexofArr(result1, result);
			 String Actiontype=type1[index1];//����result��λ���ҵ�type��λ��
		 String Actionvalue=value1[index1];//����result��λ���ҵ�value��ֵ
			 System.out.println(Actiontype+" "+Actionvalue);
			 actionbean.setActiontype(Actiontype);//ʵ����Actiontype������
		     actionbean.setActionvalue(Actionvalue);
			 if(Actiontype.equals("foward")) {
				 System.out.println("��ӭ����");//�����ȡת�����Ķ���ͨ������forward��������ת������
				 request.getRequestDispatcher(Actionvalue).forward(request, response);
			 }
			 if(Actiontype.equals("redirect")) {
					response.sendRedirect(Actionvalue); //�������¶�λ
				 }
		 }
//			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	public static int indexofArr(String[] arr,String result) {
		int m = -1;
		for(int i=0;i<arr.length;i++)
			{
			if(arr[i].equals(result)) {
				m=i;
				}
			}
		return m;
	}
//	public  String  Actionresult() {
//		String [] Actionresult=new String[] {ActionName}
//		return null;
//		
//	}
}
