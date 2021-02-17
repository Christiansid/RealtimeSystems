// Code skeleton for the SquareWave class in the Buttons exercise

public class SquareWave extends Thread {
	private Regul regul;
	private int sign = 1;
	
	private AmplitudeMonitor ampMon;
	
	// Internal AmplitudeMonitor class
	// Constructor not necessary
	private class AmplitudeMonitor {
		private double amp = 0.0;
		
		// Synchronized access methods. The amplitude should always be non-negative.
		public synchronized double getAmp() {
            return amp;
        }
		public synchronized void setAmp(double amp) {
            if(amp<0){
                this.amp = 0;
            }else{
                this.amp = amp;
            }
        }
	}
	
	// Constructor
	public SquareWave(Regul regul, int priority) {
        this.regul = regul;
        this.setPriority(priority);
        this.sign = 1;
    }
	
	// Public methods to decrease and increase the amplitude by delta. Called from Buttons
	// Should be synchronized on ampMon. Should also call the setRef method in Regul
	public void incAmp(double delta) {
        synchronized(ampMon){
            ampMon.setAmp(ampMon.getAmp()+delta);
        }
        regul.setRef(sign*ampMon.getAmp());
    }
	public void decAmp(double delta) {
        this.incAmp(-delta);
    }
	
	// run method
	public void run() {
        final int period = 10000;

        try {
            while (!interrupted()) {
                sign = -sign;
                regul.setRef(sign*ampMon.getAmp());
                Thread.sleep(period);

                Thread.sleep(period);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("SquareWave stopped.");
    }
}

