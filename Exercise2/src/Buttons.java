// Code skeleton for the Buttons class in the Buttons exercise

import SimEnvironment.*;

public class Buttons extends Thread {
	private Regul regul;
    private SquareWave square;

	// Inputs and outputs
	private DigitalButtonIn onInput;
	private DigitalButtonIn offInput;
	private DigitalButtonIn incInput;
	private DigitalButtonIn decInput;

	// Constructor
	public Buttons(Regul regul, SquareWave square, int priority, Box b) {
        this.regul = regul;
        this.square = square;
        this.setPriority(priority);
        onInput = b.getOnButtonInput();
        offInput = b.getOffButtonInput();
        incInput = b.getIncButtonInput();
        decInput = b.getDecButtonInput();
    }

	// run method
	public void run() {
        final int period = 10;
        final double delta = 10.0 / (60.0 * 1000.0) * period;
        
        try {
            while (!interrupted()) {
                if(onInput.get())
                    regul.turnOn();
                else if(offInput.get())
                    regul.turnOff();
                else if(incInput.get())
                    square.incAmp(delta);
                else if(decInput.get())
                    square.decAmp(delta);
                Thread.sleep(period);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Buttons stopped.");
    }
}
