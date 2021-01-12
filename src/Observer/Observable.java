package Observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.State;


public abstract class Observable {

    List<Iobserver> listeners;
    public State state;

    public Observable(){
        listeners = new ArrayList<>();
    }
    
    public void register(Iobserver observer){
        listeners.add(observer);
    }
    public void unregister(Iobserver observer){
        listeners.remove(observer);
    }
    public void notifyObservers(Map<String, String> data){
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
