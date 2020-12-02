package put.io.patterns.implement;

public class USBDeviceObserver implements SystemStateObserver{

    private SystemState previousSystemState;

    public void update(SystemMonitor monitor) {
        SystemState lastSystemState = monitor.getLastSystemState();
        if(previousSystemState != null && previousSystemState.getUsbDevices() != lastSystemState.getUsbDevices()) {
            System.out.println("Number of USB devices changed!");

        }
        previousSystemState = lastSystemState;
    }
}
