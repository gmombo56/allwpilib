package edu.wpi.first.wpilibj;

public class Timer {
	private static StaticInterface impl;

	public static void SetImplementation(StaticInterface ti) {
		impl = ti;
	}
	
    /**
     * Return the system clock time in seconds. Return the time from the
     * FPGA hardware clock in seconds since the FPGA started.
     *
     * @return Robot running time in seconds.
     */
    public static double getFPGATimestamp() {
    	if (impl != null) {
    		return impl.getFPGATimestamp();
    	} else {
    		return 0; // TODO: Handle error
    	}
    }
    
    /**
     * Return the approximate match time
     * The FMS does not currently send the official match time to the robots
     * This returns the time since the enable signal sent from the Driver Station
     * At the beginning of autonomous, the time is reset to 0.0 seconds
     * At the beginning of teleop, the time is reset to +15.0 seconds
     * If the robot is disabled, this returns 0.0 seconds
     * Warning: This is not an official time (so it cannot be used to argue with referees)
     * @return Match time in seconds since the beginning of autonomous
     */
    public static double getMatchTime() {
    	if (impl != null) {
    		return impl.getMatchTime();
    	} else {
    		return 0; // TODO: Handle error
    	}
    }
    
    /**
     * Pause the thread for a specified time. Pause the execution of the
     * thread for a specified period of time given in seconds. Motors will
     * continue to run at their last assigned values, and sensors will continue
     * to update. Only the task containing the wait will pause until the wait
     * time is expired.
     *
     * @param seconds Length of time to pause
     */
    public static void delay(final double seconds) {
    	if (impl != null) {
    		impl.delay(seconds);
    	} else {
    		// TODO: Handle error
    	}
    }
    
    public interface StaticInterface {
    	double getFPGATimestamp();
    	double getMatchTime();
    	void delay(final double seconds);
    	Interface newTimer();
    }
    
    private Interface timer;
    
    public Timer() {
    	timer = impl.newTimer();
    }

    /**
     * Get the current time from the timer. If the clock is running it is derived from
     * the current system clock the start time stored in the timer class. If the clock
     * is not running, then return the time when it was last stopped.
     *
     * @return Current time value for this timer in seconds
     */
    public double get() {
    	return timer.get();
    }

    /**
     * Reset the timer by setting the time to 0.
     * Make the timer startTime the current time so new requests will be relative now
     */
    public void reset() {
    	timer.reset();
    }

    /**
     * Start the timer running.
     * Just set the running flag to true indicating that all time requests should be
     * relative to the system clock.
     */
    public void start() {
    	timer.start();
    }

    /**
     * Stop the timer.
     * This computes the time as of now and clears the running flag, causing all
     * subsequent time requests to be read from the accumulated time rather than
     * looking at the system clock.
     */
    public void stop() {
    	timer.stop();
    }
    
    public interface Interface {
	    /**
	     * Get the current time from the timer. If the clock is running it is derived from
	     * the current system clock the start time stored in the timer class. If the clock
	     * is not running, then return the time when it was last stopped.
	     *
	     * @return Current time value for this timer in seconds
	     */
	    public double get();
	
	    /**
	     * Reset the timer by setting the time to 0.
	     * Make the timer startTime the current time so new requests will be relative now
	     */
	    public void reset();
	
	    /**
	     * Start the timer running.
	     * Just set the running flag to true indicating that all time requests should be
	     * relative to the system clock.
	     */
	    public void start();
	
	    /**
	     * Stop the timer.
	     * This computes the time as of now and clears the running flag, causing all
	     * subsequent time requests to be read from the accumulated time rather than
	     * looking at the system clock.
	     */
	    public void stop();
    }
}
