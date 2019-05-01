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
		response.setContentType("text/html");//告诉浏览器来处理不同类型的数据
		response.setCharacterEncoding("utf-8");//输出流的格式为utf-8类型
		 String loginname=request.getParameter("name");
		 String loginpass=request.getParameter("password");
		 System.out.println("loginpass:"+loginpass+" "+loginname);
//	        PrintWriter out = response.getWriter();
//	        out.print("<html>");
//	        out.print("<head>");
//	        out.print("<title>SimpleController</title>");
//	        out.print("</head>");
//	        out.print("<body>欢迎使用SimpleController</body>");
//	        out.print("</html>");
	     request.setCharacterEncoding("utf-8");
	     String ActionName=request.getServletPath().toString();//获取servlet路径
	     String [] Action=ActionName.split("/");//将获取的路径分为不同的String数组
	     ActionName=Action[Action.length-1];//获取最后一个路径的名称即为Action的名称
	     ActionName=ActionName.substring(0, ActionName.indexOf("."));
	     ReadDi readDi=new ReadDi();//实例化一个对象
	      try {
			dibean=readDi.readi();//获取解析出来的Bean对象
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
		   actionbean.setActionClass(Actionclass);//实体类ActionClass的设置
		   actionbean.setActionMethod(Actionmethod);//实体类ActionMethod的设置
		   System.out.println(Actionclass);
		   int indexdi=dibean.indexOf(ActionName);//查找是否有bean
		   Class  class1=Class.forName(Actionclass);//通过class类中的静态方法获取到一个类的字节码对象
//		   Object obj1=class1.newInstance();
		   
	     if(indexdi!=-1) {
	    	 fieldbean=readDi.readiclass(ActionName);
	    	
		   @SuppressWarnings("unchecked")
		Method method=class1.getDeclaredMethod(Actionmethod, HttpServletRequest.class,HttpServletResponse.class);//获取私有方法
	    
//		  String result=(String)method.invoke(class1.newInstance(), request,response);//获得返回的result
//		  actionbean.setActionresult(result);//实体类Actionresult的赋值
//		  Resultarray = mytool.getResult(ActionName);//获得返回的字符放在一个数组中
//		String[] result1=Resultarray[0].split("\\s+");//将获得的字符串用空格分开转换为一个数组
//		 String[] type1=Resultarray[1].split("\\s+");//
//		 String[] value1=Resultarray[2].split("\\s+");
//		 int index1=indexofArr(result1, result);
//		 if(index1==-1) {
//			 response.sendRedirect("/UseSC/LoginAction.html");
//		 }
//		 else {
//			 String Actiontype=type1[index1];//根据result的位置找到type的位置
//			 String Actionvalue=value1[index1];//根据result的位置找到value的值
//			 System.out.println(Actiontype+" "+Actionvalue);
//			 actionbean.setActiontype(Actiontype);//实体类Actiontype的设置
//			 actionbean.setActionvalue(Actionvalue);//实体类Actionvalue的设置
		   ArrayList<String> list=(ArrayList<String>) mytool.haveinterceptro(ActionName);
			System.out.println("list"+mytool.haveinterceptro(ActionName));
			if(list!=null||list.size()!=0)
			{
				CglibProxy proxy=new CglibProxy();//创建一个动态代理类
				 Object clc= proxy.getProxy(class1,actionbean);//返回一个动态代理类对象
				 if(fieldbean!=null) {
		    		 String beanref=fieldbean.get(1);
		    		 System.out.println("beanref"+beanref);
		    		 String beanname=readDi.getclass(beanref);
		    		 System.out.println("beanname:"+beanname);
		    		 Class  class2=Class.forName(beanname);//根据类名获取classduixiang 
		    		 Object obj=class2.newInstance();
//		    		 Class[] parameterTypes= {String.class,String.class};//参数类型数组
//		    		 Constructor constructor=class2.getConstructor(parameterTypes);//根据参数类型获取相应的构造函数
//		    		 Object[] parameters= {loginname,loginpass};//参数数组
//		    		 Object o=constructor.newInstance(parameters);//根据获取的参数数组和参数创建实例
		    		 BeanInfo beanInfo=Introspector.getBeanInfo(class2);
		    		 PropertyDescriptor pd=new PropertyDescriptor("userName",class2);//获取name属性的属性描述器
		    		 Method writerMethod=pd.getWriteMethod();//获取name属性的写方法
		    		 writerMethod.invoke(obj, loginname);//执行setter方法
		    		 PropertyDescriptor pd1=new PropertyDescriptor("userPass",class2);
		    		 Method writerMethod1=pd1.getWriteMethod();
		    		 writerMethod1.invoke(obj, loginpass);
		    		 PropertyDescriptor pd3=new PropertyDescriptor("user",class1);//获取loginaction的user属性
		  		     Method writerMethod2=pd3.getWriteMethod();//获取loginaction的set方法
		  		     writerMethod2.invoke(clc, obj);//将user对象赋值给clcobject
		    	 }		   	  		   
				 result=(String) method.invoke(clc,request,response);//利用反射机制进行调用
			}
			else {
				 result=(String)method.invoke(class1.newInstance(), request,response);
			}
			}
			System.out.println("Result "+result);
			 actionbean.setActionresult(result);
			 Resultarray = mytool.getResult(ActionName);
			 String[] result1=Resultarray[0].split("\\s+");//将获得的字符串用空格分开转换为一个数组
			 String[] type1=Resultarray[1].split("\\s+");//
			 String[] value1=Resultarray[2].split("\\s+");
			 int index1=indexofArr(result1, result);
			 String Actiontype=type1[index1];//根据result的位置找到type的位置
		 String Actionvalue=value1[index1];//根据result的位置找到value的值
			 System.out.println(Actiontype+" "+Actionvalue);
			 actionbean.setActiontype(Actiontype);//实体类Actiontype的设置
		     actionbean.setActionvalue(Actionvalue);
			 if(Actiontype.equals("foward")) {
				 System.out.println("欢迎界面");//请求获取转发器的对象通过调用forward方法进行转发请求
				 request.getRequestDispatcher(Actionvalue).forward(request, response);
			 }
			 if(Actiontype.equals("redirect")) {
					response.sendRedirect(Actionvalue); //进行重新定位
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
