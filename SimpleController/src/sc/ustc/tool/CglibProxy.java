package sc.ustc.tool;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy {
	private Actionbean actionbean1;
	public   Object getProxy(Class<?> clazz, Actionbean actionbean) {
		CglibProxyX proxy=new CglibProxyX();//����һ���µķ�������
		Enhancer enhancer=new Enhancer();//����һ���ֽ�����ǿ�������������Ҫ������������չ
		enhancer.setSuperclass(clazz);//���������������Ϊ����
		enhancer.setCallback(proxy);//����hui'dian
		this.actionbean1=actionbean;
		return enhancer.create();//����һ����̬�Ĵ�����
	}
class CglibProxyX implements MethodInterceptor{
//objectΪ�������ԭ����methodΪ�����õĵ�ǰ������paramsΪ�÷����Ĳ������ϣ������÷����Ĵ���
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
		String result = (String) proxy.invokeSuper(object, args);//���ø���ķ���
		System.out.println("resultname "+result);
		if(result!=null) {
		afterMethod.invoke(cl.newInstance(), result);
		System.out.println("afteraction");
		}
		return result;
	}
	
}
	

}
