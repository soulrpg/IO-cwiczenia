class Lamp {
  
  // stores the value for light
  // true if light is on
  // false if light is off
  boolean isOn;
  
  public Lamp(boolean isON) {
	  this.isOn = isOn;
  }

  // method to turn on the light
  void turnOn() {
    isOn = true;
    System.out.println("Light on? " + isOn);

  }

  // method to turnoff the light
  void turnOff() {
    isOn = false;
    System.out.println("Light on? " + isOn);
  }
}

class Main {
  public static void main(String[] args) {

    // create objects led and halogen
    Lamp led = new Lamp(true);
    Lamp halogen = new Lamp(true);
	
	

    // turn on the light by
    // calling method turnOn()
    led.turnOn();

    // turn off the light by
    // calling method turnOff()
    halogen.turnOff();
  }
}