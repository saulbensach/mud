package event;

public interface Event {
    Class<? extends Event> getType();
}
