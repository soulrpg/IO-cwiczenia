// Program that manages lamps

class Lamp {
  
  // stores the value for light
  // true if light is on
  // false if light is off
  boolean isOn;
  float voltage;
  
  public Lamp(boolean isON) {
	  this.isOn = isOn;
  }

  // method to turn on the light
  void turnOn() {
    isOn = !isOn;
    System.out.println("Light on? " + isOn);

  }

  // method to turnoff the light
  void turnOff() {
    isOn = !isOn;
    System.out.println("Light on? " + isOn);
  }
  
  void setVoltage(float voltage) {
	this.voltage = voltage;
  }
}

class Main {
  public static void main(String[] args) {

    // create objects led and halogen
	
	System.out.println("Main function start");
    Lamp led = new Lamp(true);
    Lamp halogen = new Lamp(true);
	
	

    // turn on the light by
    // calling method turnOn()
    led.turnOn();
	
	// setting voltage
	led.setVoltage(2.7f);
	
    // turn off the light by
    // calling method turnOff()
    halogen.turnOff();
  }
}