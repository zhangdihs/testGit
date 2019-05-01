package water.ustc.Dao;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import water.ustc.bean.Classxml;
import water.ustc.bean.Column;
import water.ustc.bean.Jdbc;
import water.ustc.bean.OrMapping;

public class Configuration {
	private OrMapping orMapping;

//	public Configuration() throws IllegalAccessException, InvocationTargetException{
//	try {
//		this.readOrMapping();
//	} catch (DocumentException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	}

	public  OrMapping readOrMapping() throws DocumentException, IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		orMapping=new OrMapping();
		SAXReader reader=new SAXReader();
		//System.out.println(1);
//		InputStream in=this.getClass().getResourceAsStream("E:\\eclipseworkspace\\UseSC\\or_mapping.xml");
		Document document =reader.read(new File("E:\\eclipseworkspace\\UseSC\\or_mapping.xml"));
		//System.out.println(2);
		Element root=document.getRootElement();
		Element elementjdbc=root.element("jdbc");
		@SuppressWarnings("unchecked")
		List<Element> pro=(List<Element>) elementjdbc.elements("property");
		Map<String,String> map=new HashMap<>();
		for(Element element:pro)
		{
			String name=element.element("name").getText();
			String value=element.element("value").getText();
			System.out.println(name+"    "+value);
			map.put(name, value);
		}
		 for(Map.Entry<String, String> entry : map.entrySet()){
			 System.out.println("键 key ："+entry.getKey()+" 值value ："+entry.getValue());
       }

		Jdbc jdbc=new Jdbc();
		BeanUtils.populate(jdbc, map);//将map的值进行映射到bean中
		orMapping.setJdbc(jdbc);
		System.out.println("封装成功："+jdbc.getDriver()+jdbc.getUrl()+jdbc.getUsername()+jdbc.getPassword());
		@SuppressWarnings("unchecked")
		List<Element> classlist=root.elements("class");
		List<Classxml> classxml=new ArrayList<>();
		System.out.println(3);
		for(Element element:classlist) {
			Classxml classxml1=new Classxml();
			classxml1.setName(element.element("name").getText());
			System.out.println(element.element("name").getText());
			classxml1.setTable(element.element("table").getText());
		    classxml1.setIdString(element.element("id").element("name").getText());
		    System.out.println(element.element("name").getText()+"  "+element.element("table").getText()+"  "+element.element("id").element("name").getText());
		    classxml.add(classxml1);
		    @SuppressWarnings("unchecked")
			List<Element> columnlist=element.elements("property");
		    List<Column>  columns=new ArrayList<>();
		    for(Element column:columnlist) {
		    	Column column1=new Column();
		    	column1.setName(column.element("name").getText());
		    	column1.setColumn(column.element("column").getText());
		    	column1.setType(column.element("type").getText());
		    	column1.setLazy(column.element("lazy").getText());
		    	columns.add(column1);
		    	System.out.println(column.element("name").getText()+"  "+column.element("column").getText()+"  "+column.element("type").getText()+"  "+column.element("lazy").getText());
		    }
		    classxml1.setColumn(columns);
		    classxml.add(classxml1);
		}
		orMapping.setClassxml(classxml);
		return orMapping;
		
}
//	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
//		Configuration configuration=new Configuration();
//		try {
//			configuration.readOrMapping();
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public Conversation createConversation() {
//		Conversation conversation = Conversation.getInstance();//定义一个函数在conversation中进行调用获得实体类ormapping
//		conversation.setOrMapping(orMapping);
//		return conversation;
//	}
}
