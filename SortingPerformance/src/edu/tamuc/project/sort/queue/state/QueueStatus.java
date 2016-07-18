package edu.tamuc.project.sort.queue.state;

public class QueueStatus {


	public void queuePopulationStatus(boolean queuePopulationStatus) {
		this.queuePopulationStatus = queuePopulationStatus;
	}
	
	public boolean isQueuePopulationStatus() {
		return queuePopulationStatus;
	}

	private volatile boolean queuePopulationStatus = false;
}
