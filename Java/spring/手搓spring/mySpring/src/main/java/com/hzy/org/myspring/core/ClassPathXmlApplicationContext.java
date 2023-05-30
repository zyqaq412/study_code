package com.hzy.org.myspring.core;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: ClassPathXmlApplicationContext
 * @Author zxwyhzy
 * @Date: 2022/11/29 12:51
 * @Version 1.0
 */
public class ClassPathXmlApplicationContext implements ApplicationContext{

    //private static final Logger LOGGER = LoggerFactory.getLogger(ClassPathXmlApplicationContext.class);
    private Map<String,Object> singletonObjects = new HashMap<>();


    /**
     * 解析myspring的配置文件
     * @param configLocation
     */
    public ClassPathXmlApplicationContext(String configLocation){
        //解析myspring.xml文件，然后实例化Bean，将Bean放到singletonObjects集合当中
        //这是dom4j解析xml文件的核心对象
        SAXReader reader = new SAXReader();
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(configLocation);
        try {
            //读文件
            Document document = reader.read(in);
            //获取所有的Bean
            List<Node> nodes = document.selectNodes("//bean");
            //遍历Bean标签
            nodes.forEach(node -> {
                //System.out.println(node);org.dom4j.tree.DefaultElement@45c8e616
                //输出的是 Element 类型
                //向下转型 Element 方法更多
                Element beanElt = (Element) node;
                //获取id属性
                String id = beanElt.attributeValue("id");
                //获取class属性
                String className = beanElt.attributeValue("class");
                //LOGGER.info("beanName = "+id);
                //LOGGER.info("className = "+className);
                //通过反射机制创建对象，将其放到map集合提前曝光
                try {
                    Class<?> aClass = Class.forName(className);
                    //获取无参构造函数
                    Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
                    //调用方法
                    Object bean = declaredConstructor.newInstance();
                    //将Bean曝光 加入map集合
                    singletonObjects.put(id,bean);
                    //LOGGER.info(singletonObjects.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            //再次将bean标签遍历 给bean属性赋值
            nodes.forEach(node -> {


                try {
                    Element beanElt = (Element) node;
                    //获取id属性
                    String id = beanElt.attributeValue("id");
                    //获取class属性
                    String className = beanElt.attributeValue("class");
                    Class<?> aClass = Class.forName(className);
                    //获取bean标签下所有的属性标签
                    List<Element> propertys = beanElt.elements("property");
                    //遍历所有属性标签
                    propertys.forEach(property ->{

                        try {//获取属性名
                            String propertyName = property.attributeValue("name");
                            //获取属性类型
                            Field field = aClass.getDeclaredField(propertyName);
                            //LOGGER.info("属性名："+propertyName + " 属性类型："+field);
                            //获取set方法名
                            String setMethodName = "set"+propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
                            //获取set方法
                            Method method = aClass.getDeclaredMethod(setMethodName, field.getType());
                            //获取set方法的具体参数
                            String value = property.attributeValue("value");
                            String ref = property.attributeValue("ref");
                            Object actualValue = null; // 真值
                            if (value !=null) {
                                // 说明这个值是简单类型
                                // 调用set方法(set方法没有返回值)
                                // 我们myspring框架声明一下：我们只支持这些类型为简单类型
                                // byte short int long float double boolean char
                                // Byte Short Integer Long Float Double Boolean Character
                                // String
                                // 获取属性类型名
                                String propertyTypeSimpleName = field.getType().getSimpleName();//不带包名
                                switch (propertyTypeSimpleName) {
                                    case "byte":
                                        actualValue = Byte.parseByte(value);
                                        break;
                                    case "short":
                                        actualValue = Short.parseShort(value);
                                        break;
                                    case "int":
                                        actualValue = Integer.parseInt(value);
                                        break;
                                    case "long":
                                        actualValue = Long.parseLong(value);
                                        break;
                                    case "float":
                                        actualValue = Float.parseFloat(value);
                                        break;
                                    case "double":
                                        actualValue = Double.parseDouble(value);
                                        break;
                                    case "boolean":
                                        actualValue = Boolean.parseBoolean(value);
                                        break;
                                    case "char":
                                        actualValue = value.charAt(0);
                                        break;
                                    case "Byte":
                                        actualValue = Byte.valueOf(value);
                                        break;
                                    case "Short":
                                        actualValue = Short.valueOf(value);
                                        break;
                                    case "Integer":
                                        actualValue = Integer.valueOf(value);
                                        break;
                                    case "Long":
                                        actualValue = Long.valueOf(value);
                                        break;
                                    case "Float":
                                        actualValue = Float.valueOf(value);
                                        break;
                                    case "Double":
                                        actualValue = Double.valueOf(value);
                                        break;
                                    case "Boolean":
                                        actualValue = Boolean.valueOf(value);
                                        break;
                                    case "Character":
                                        actualValue = Character.valueOf(value.charAt(0));
                                        break;
                                    case "String":
                                        actualValue = value;
                                }
                                method.invoke(singletonObjects.get(id),actualValue);
                            }
                            if (ref !=null) {
                                method.invoke(singletonObjects.get(id),singletonObjects.get(ref));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }



                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String beanName) {
        return singletonObjects.get(beanName);
    }
}
