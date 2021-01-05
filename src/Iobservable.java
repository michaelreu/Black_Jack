import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract class Observable {

    List<Iobserver> listeners;

    public Observable(){
        listeners = new ArrayList<>();
    }
    
    public void register(Iobserver observer){
        listeners.add(observer);
    }
    public void unregister(Iobserver observer){
        listeners.remove(observer);
    }
    public void notifyObservers(){
        Iterator<Iobserver> listener = listeners.iterator();
        while (listener.hasNext()){
            listener.next().update();
        }
    }
}
