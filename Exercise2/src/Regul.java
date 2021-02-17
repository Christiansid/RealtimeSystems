// Code skeleton for the Regul class in the Buttons exercise

import SimEnvironment.*;

public class Regul extends Thread {
	// Analog inputs and outputs
	private AnalogSource yIn;
	private AnalogSink uOut;
	private AnalogSink rOut;
	
	// Box lamp outputs
	private DigitalButtonOut onButtonLamp;
	private DigitalButtonOut offButtonLamp;
	
	// Internal Monitors
	private ParameterMonitor paramMon;
	private ReferenceMonitor refMon;
	private OnMonitor onMon;
	
	// Constructor
	// Here the internal monitor objects should be created and
	// the inputs and outputs should be initialized.
	public Regul(int priority, Box b, FirstOrderProcess proc) {
		this.onButtonLamp = b.getOnButtonLamp();
		this.offButtonLamp = b.getOffButtonLamp();
		this.setPriority(priority);
		this.yIn = proc.getSource(0);
		this.uOut = proc.getSink(0);
		this.rOut = proc.getSink(1);
		this.paramMon = new ParameterMonitor();
		this.refMon = new ReferenceMonitor();
		this.onMon = new OnMonitor();
        //TODO C2.E11: Initialize the sources and sinks //
        //TODO C2.E11: Set the buttons and thread priority //
    }
	
	// Public method to set K. Should not be synchronized.
	public void setK(double K) {
        paramMon.setK(K);
    }
	
	// Public method to set the reference. Should not be synchronized.
	public void setRef(double ref) {
        refMon.setRef(ref);
    }
	
	// Method to check if the controller  is on. Should be private
	// since it is only called from Regul itself.
	private boolean isOn() {
        return onMon.isOn();
    }
	
	// Public methods to turn off and on the controller
	// Should not be synchronized. Should update the button lamps
	public void turnOff() {
        onMon.setOn(false);
    }
	public void turnOn() {
        onMon.setOn(true);
    }
	
	// Class definition for internal ParameterMonitor
	private class ParameterMonitor {
		private double K = 1.0;
		
		// Synchronized access methods. K should always be non-negative.
		public synchronized double getK() {
            return K;
        }
		public synchronized void setK(double K) {
			if(K<0)
				System.out.println("K ignored " + K);
			else
				this.K = K;
        }
	}
	
	// Class definition for internal ReferenceMonitor
	private class ReferenceMonitor {
		private double ref = 0.0;
		
		// Synchronized access methods
		public synchronized double getRef() {
            return ref;
        }
		public synchronized void setRef(double ref) {
            this.ref = ref;
        }
	}
	
	// Class definition for internal OnMonitor
	private class OnMonitor {
		private boolean on = false;
		
		// Synchronized access methods
		public synchronized boolean isOn() {
            return on;
        }
		public synchronized void setOn(boolean on) {
            this.on = on;
        }
	}
	
	// Run method
	public void run() {
        final int period = 100;
		double k; 
		double ref;
		double currSignal;

        try {
            while (!interrupted()) {
			   //TODO C2.E11: Write your code here // 
			   currSignal = yIn.get();
			   ref = refMon.getRef();
			   k = paramMon.getK();
			   double signal = k * (ref - currSignal);
			   uOut.set(signal);
			   rOut.set(ref);
               Thread.sleep(period);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Regul stopped.");
    }
}
