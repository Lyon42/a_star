package main.java.lyon.a_star.events;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.lyon.a_star.utils.Tuple2;
import main.java.lyon.a_star.utils.Utils;

public class EventHandler 
{
	private final Map<Class<? extends Event>, List<Tuple2<Object, Method>>> listenerMap = new HashMap<Class<? extends Event>, List<Tuple2<Object, Method>>>();
	
	public void registerEventListener(Object obj)
	{
		if(obj != null)
		{
			List<Method> methods = Utils.getAllAnnotatedMethods(obj.getClass(), EventListener.class);
			
			if(!methods.isEmpty())
			{
				for(Method m : methods)
				{
					if(m.getParameterCount() == 1 && Event.class.isAssignableFrom(m.getParameterTypes()[0]))
					{
						Class<? extends Event> clazzEvent = m.getParameterTypes()[0].asSubclass(Event.class);
						
						if(!this.listenerMap.containsKey(clazzEvent))
						{
							this.listenerMap.put(clazzEvent, new ArrayList<Tuple2<Object, Method>>());
						}
						
						this.listenerMap.get(clazzEvent).add(new Tuple2<Object, Method>(obj, m));
					}
				}
			}
		}
	}
	
	public void onEvent(Event event)
	{
		if(event != null)
		{
			if(this.listenerMap.containsKey(event.getClass()))
			{
				try
				{
					for(Tuple2<Object, Method> tuple : listenerMap.get(event.getClass()))
					{
						tuple.get2().invoke(tuple.get1(), event);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface EventListener{}
}
