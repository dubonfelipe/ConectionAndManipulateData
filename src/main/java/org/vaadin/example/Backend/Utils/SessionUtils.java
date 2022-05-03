public class SessionUtils {

    public final static void setAttribute(String key, Object object){
        if(VaadinSession.getCurrent() != null){
            VaadinSession.getCurrent().setAttribute(key,object);
        }
    }

    public final static <E> E getAttribute(Class<E> type, String key){
        if(VaadinSession.getCurrent() != null){
            Object value = (E) VaadinSession.getCurrent().getAttribute(type);
            if(type.isInstance(value) ){
                return (E) value;
            }
        }
        return null;
    }
}
