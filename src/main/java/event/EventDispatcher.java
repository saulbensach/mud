package event;

import java.util.HashMap;
import java.util.Map;

public class EventDispatcher {

    private static EventDispatcher dispatcher = null;

    private Map<Class<? extends Event>, Handler<? extends Event>> handlers;

    private EventDispatcher(){
        handlers = new HashMap<>();
    }

    public static EventDispatcher getInstance(){
        if(dispatcher == null){
            dispatcher = new EventDispatcher();
        }
        return dispatcher;
    }

    public <E extends Event> void registerHandler(Class<E> eventType, Handler<E> handler) {
        handlers.put(eventType, handler);
    }

    @SuppressWarnings("unchecked")
    public <E extends Event> void dispatch(E event) {
        Handler<E> handler = (Handler<E>) handlers.get(event.getClass());
        if (handler != null) {
            handler.onEvent(event);
        }
    }
}
