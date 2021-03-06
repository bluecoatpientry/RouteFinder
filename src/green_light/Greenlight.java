//Greenlight
package green_light;

import java.util.Collections;
import java.util.ArrayList;

import com.pi4j.io.serial.*;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.*;

public class Greenlight {
    static ArrayList<Vertex> nodes = new ArrayList<>();
    static ArrayList<Edge> edges = new ArrayList<>();
    static int NODE_COUNT = 11;
    static ArrayList<Integer> pixelXLoc = new ArrayList<>();
    static ArrayList<Integer> pixelYLoc = new ArrayList<>();
    static String[] colors = {"red", "yellow", "blue", "green", "brown", "grey", "black", "orange", "cyan", "magenta", "#CB4154"};
    static Graph simGraph;

    public static final ArrayList<GpioPinDigitalInput> pinSensors = new ArrayList<>(8);
    public static final ArrayList<Integer> trafficDs = new ArrayList<>(8);

    public static void addLane(String laneId, int sourceLocNo, int destLocNo, int duration) {
        Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }

    public static void addReverses() {
        ArrayList<Edge> nonReversedEdges = simGraph.edges;
        Collections.reverse(simGraph.edges);
        simGraph.edges.addAll(nonReversedEdges);
    }

    public static void main(String[] args) {
        for (int i = 0; i < NODE_COUNT; i++) {
            Vertex location = new Vertex(i, String.valueOf(i));
            nodes.add(location);
        }

        addLane("Edge_0", 0, 1, 85);
        addLane("Edge_1", 0, 2, 217);
        addLane("Edge_2", 0, 4, 173);
        addLane("Edge_3", 2, 6, 186);
        addLane("Edge_4", 2, 7, 103);
        addLane("Edge_5", 3, 7, 183);
        addLane("Edge_6", 5, 8, 250);
        addLane("Edge_7", 8, 9, 84);
        addLane("Edge_8", 7, 9, 167);
        addLane("Edge_9", 4, 9, 502);
        addLane("Edge_10", 9, 10, 40);
        addLane("Edge_11", 1, 10, 600);

        // Lets check from location Loc_1 to Loc_10
        simGraph = new Graph(nodes, edges);
        DAlgorithm dijkstra = new DAlgorithm(simGraph);

        int source = 0;
        int dest = 10;

        addReverses();

        dijkstra.execute(nodes.get(source)); ///source was 0
        ArrayList<Vertex> path = dijkstra.getPath(nodes.get(dest)); // dest was 10

        assert (path != null);
        assert (path.size() > 0);

        for (Vertex vertex : path) {
            System.out.println("ROUTE: " + vertex);
        }

       /* final Serial serial = SerialFactory.createInstance();
        serial.addListener(new SerialDataListener() {
             @Override
             public void dataReceived(SerialDataEvent event) {
                  System.out.println(event.getData());
             }
        });
            
        try {
            serial.open("/dev/ttyUSB0", 2400);
            System.out.println(Serial.DEFAULT_COM_PORT);
        } catch (SerialPortException ex) {
            System.out.println(ex.getMessage());
            return;
        }*/
        final GpioController gpio = GpioFactory.getInstance();
	pinSensors.add(gpio.provisionDigitalInputPin(RaspiPin.GPIO_00, PinPullResistance.PULL_DOWN));
        pinSensors.add(gpio.provisionDigitalInputPin(RaspiPin.GPIO_01, PinPullResistance.PULL_DOWN));
        pinSensors.add(gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN));
        pinSensors.add(gpio.provisionDigitalInputPin(RaspiPin.GPIO_03, PinPullResistance.PULL_DOWN));
	pinSensors.add(gpio.provisionDigitalInputPin(RaspiPin.GPIO_04, PinPullResistance.PULL_DOWN));
	pinSensors.add(gpio.provisionDigitalInputPin(RaspiPin.GPIO_05, PinPullResistance.PULL_DOWN));
	pinSensors.add(gpio.provisionDigitalInputPin(RaspiPin.GPIO_06, PinPullResistance.PULL_DOWN));
        pinSensors.add(gpio.provisionDigitalInputPin(RaspiPin.GPIO_07, PinPullResistance.PULL_DOWN));
        pinSensors.add(gpio.provisionDigitalInputPin(RaspiPin.GPIO_08, PinPullResistance.PULL_DOWN));
        for (int x = 0; x < pinSensors.size(); x++) {
            trafficDs.add(0);
            pinSensors.get(x).addListener(new GpioPinListenerDigital() {
                @Override
                public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                    System.out.println(event.getPin().toString());
                    switch (event.getPin().toString()) {
                       case "\"GPIO 3\" <GPIO 3>":
                            Greenlight.trafficDs.set(3, new Integer(Greenlight.trafficDs.get(3).intValue() - 1));
                            System.out.println(Greenlight.trafficDs.get(3).toString());
                            break;
                      case "\"GPIO 6\" <GPIO 6>":
                            Greenlight.trafficDs.set(6, new Integer(Greenlight.trafficDs.get(6).intValue() + 1));
                            System.out.println(Greenlight.trafficDs.get(6).toString());
                            break;
                    }
                    System.out.println("GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
                    System.out.println("Car detected at sensor: " + event.getPin()); //Convert to serial
                  //  pinSensors.get(0).high();
                }
            });
        }
       // Thread.sleep(10000);
       // final GpioPinDigitalOutput inputPin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Sensor1In");
//	System.out.println("--> GPIO state should be: ON");
	try {
          Thread.sleep(10000);
//	inputPin1.low();
  //      Thread.sleep(10000);
    //    inputPin1.high();
       }  catch (Exception e) { }
    }
}
