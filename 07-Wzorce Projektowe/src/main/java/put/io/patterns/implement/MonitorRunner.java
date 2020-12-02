package put.io.patterns.implement;

import oshi.hardware.UsbDevice;

public class MonitorRunner {

    public static void main(String args[]){
        SystemMonitor monitor = new SystemMonitor();

        SystemStateObserver infObserver = new SystemInfoObserver();
        SystemStateObserver usbObserver = new USBDeviceObserver();
        SystemStateObserver garbagecollObserver = new SystemGarbageCollectorObserver();
        SystemStateObserver coolerObserver = new SystemCoolerObserver();
        monitor.addSystemStateObserver(infObserver);
        monitor.addSystemStateObserver(usbObserver);
        monitor.addSystemStateObserver(garbagecollObserver);
        monitor.addSystemStateObserver(coolerObserver);

        while (true) {

            monitor.probe();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
