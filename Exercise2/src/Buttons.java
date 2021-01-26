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
        //TODO C2.E8: Create help-variables //
        boolean prevOn = false;
        
        try {
            while (!interrupted()) {
                if(onInput.get())
                //TODO C2.E8: Check button-status and take action accordingly, every 10 ms //
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Buttons stopped.");
    }
}
