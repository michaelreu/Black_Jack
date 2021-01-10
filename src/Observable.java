import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


abstract class Observable {

    List<Iobserver> listeners;
    protected State state;

    public Observable(){
        listeners = new ArrayList<>();
    }
    
    public void register(Iobserver observer){
        listeners.add(observer);
    }
    public void unregister(Iobserver observer){
        listeners.remove(observer);
    }
    public void notifyObservers(Map<String, Object> data){
        Iterator<Iobserver> listener = listeners.iterator();
        while (listener.hasNext()){
            listener.next().update(this, data);
        }
    }
    public void notifyObservers(){
        Iterator<Iobserver> listener = listeners.iterator();
        while (listener.hasNext()){
            listener.next().update(this);
        }
    }
}
