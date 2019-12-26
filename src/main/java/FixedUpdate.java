public class FixedUpdate extends Thread {

    private static final int TICK_RATE = 2;
    private static final double ns = 1000000000f / TICK_RATE;

    private long last_time = System.nanoTime();
    private double delta_time = 0;

    private World world;

    public FixedUpdate(World world){
        this.world = world;
    }

    @Override
    public void run(){
        while(Server.RUNNING){
            long now = System.nanoTime();
            delta_time += ((now - last_time) / ns);
            if(delta_time >= 1){
                tick();
                delta_time--;
            }
            last_time = now;
        }
    }

    private void tick(){
        world.update();
    }
}
