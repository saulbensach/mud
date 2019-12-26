package tick;

import event.EventDispatcher;

public class TickManager extends Thread{

    private static final int TICK_RATE = 2;
    private static final double ns = 1000000000f / TICK_RATE;

    private long last_time = System.nanoTime();
    private double delta_time = 0;

    private EventDispatcher dispatcher = EventDispatcher.getInstance();

    @Override
    public void run(){
        while(true){
            long now = System.nanoTime();
            delta_time += ((now - last_time) / ns);
            if(delta_time >= 1){
                dispatcher.dispatch(new TickEvent());
                delta_time--;
            }
            last_time = now;
        }
    }
}
