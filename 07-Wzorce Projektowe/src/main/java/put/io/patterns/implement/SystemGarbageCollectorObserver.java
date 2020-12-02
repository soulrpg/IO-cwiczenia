package put.io.patterns.implement;

public class SystemGarbageCollectorObserver implements SystemStateObserver{
    public void update(SystemMonitor monitor) {
        SystemState lastSystemState = monitor.getLastSystemState();
        // Run garbage collector when out of memory
        if (lastSystemState.getAvailableMemory() < 200.00){
            System.out.println("> Running garbage collector...");
        }
    }
}
