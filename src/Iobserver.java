import java.util.Map;

public interface Iobserver {
    public void update(Observable obj, Map<String, String> data);
    public void update(Observable ob);
}
