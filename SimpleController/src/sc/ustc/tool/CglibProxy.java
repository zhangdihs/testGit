package sc.ustc.tool;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy {
	private Actionbean actionbean1;
	public   Object getProxy(Class<?> clazz, Actionbean actionbean) {
		CglibProxyX proxy=new CglibProxyX();//定义一个新的方法对象
		Enhancer enhancer=new Enhancer();//定义一个字节码增强器，方便对你想要处理的类进行扩展
		enhancer.setSuperclass(clazz);//将被代理的类设置为父类
		enhancer.setCallback(proxy);//设置hui'dian
		this.actionbean1=actionbean;
		return enhancer.create();//生成一个动态的代理类
	}
class CglibProxyX implements MethodInterceptor{
//object为被代理的原对象，method为被调用的当前方法，params为该方法的参数集合，被调用方法的代理
	@Override
	public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		// TODO Auto-generated method stub
		readtool readinter=new readtool();
		String[] inter=readinter.getinterceptro();
		String classI=inter[1];
		String predoI=inter[2];
		String afterdoI=inter[3];
		System.out.println(classI+" "+predoI+" "+afterdoI);
		Class<?> cl=Class.forName(classI);
		Method preMethod=cl.getMethod(predoI, String.class);
		System.out.println(preMethod);
		Method afterMethod=cl.getMethod(afterdoI, String.class);
		System.out.println(afterMethod);
		System.out.println("preaction");
		System.out.println("actionbean "+actionbean1.getActionname());
		preMethod.invoke(cl.newInstance(), actionbean1.getActionname());
		String result = (String) proxy.invokeSuper(object, args);//调用父类的方法
		System.out.println("resultname "+result);
		if(result!=null) {
		afterMethod.invoke(cl.newInstance(), result);
		System.out.println("afteraction");
		}
		return result;
	}
	
}
	

}
