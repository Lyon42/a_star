package main.java.lyon.a_star.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Utils 
{
	public static List<Method> getAllAnnotatedMethods(Class clazz, Class<? extends Annotation> annotation)
	{
		List<Method> methodList = new ArrayList<Method>();
		
		if(clazz != null && annotation != null)
		{
			Method[] methods = clazz.getMethods();
			
			for(Method m : methods)
			{
				if(m.isAnnotationPresent(annotation))
				{
					methodList.add(m);
				}
			}
		}
		
		return methodList;
	}
}
