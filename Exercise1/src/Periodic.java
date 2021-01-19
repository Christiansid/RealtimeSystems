
public class Periodic extends Thread {
	private int period;

	public Periodic(int period) {
		this.period = period;
		
	}
	@Override
	public void run() {
		try {
		while(!Thread.interrupted()) {
			System.out.print(period);
			System.out.print(",");

				Thread.sleep(period);
			} 

		}catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Thread interrupted");
	}

	}
	
	public static void main(String[] args) {
		for(String arg : args) {
			new Periodic(Integer.parseInt(arg)).start();
		}
	}

}
