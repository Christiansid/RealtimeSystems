import java.util.ArrayList;

public class Periodic extends Thread {
	private final int period;

    public Periodic(final int period) {
        this.period = period;

    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.print(period);
                System.out.print(",");

                Thread.sleep(period);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Thread interrupted");
        }

    }

    public static void main(String[] args) {
        if(args.length==0){
			ArrayList<String> p = new ArrayList();
			for(int i = 0; i<5;i++){
				p.add(Integer.toString((i+1)*200));
			}
			for (String arg : p) {
				new Periodic(Integer.parseInt(arg)).start();
			}
            }else{
			for (String arg : args) {
				new Periodic(Integer.parseInt(arg)).start();
			}
		}

	}

}
