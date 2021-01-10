import java.util.Map;

public interface Iobserver {
    public void update(Observable obj, Map<String, Object> data);
    public void update(Observable ob);
}
