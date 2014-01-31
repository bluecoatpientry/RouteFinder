Instructions
=============

Are you interested in experimenting with this project concept yourself, or wish to see the steps we took?

Here we present a step-based guide to creating basic route finding on a Raspberry Pi.


Hardware / Electronics
======================

In order to replicate the traffic model effectively, some form of sensor input to create artificial data for traffic will be required.

We utilised Passive-InfraRed Motion sensors and Red Push Buttons.

The Raspberry Pi will suffice for the scale we prototyped. However if resources aren't as strictly limited as we were, an additional microcontroller such as a PICKAXE chip can be used in order to extend the number of sensors the Raspberry Pi can utilise.
We experimented with those approach, but due to time constraints and difficulties with serial port configuration we had to revert to the initial plan.


Software / APIs
================

In order to develop on the Raspberry Pi we utilised the following main tools:

* The [Netbeans](http://en.wikipedia.org/wiki/Netbeans) IDE.
* The [nano](http://en.wikipedia.org/wiki/GNU_nano) editor.
* The terminal and shell scripts; for compilation and running.
* The Pi4J Java API allowed us to program in a language we were already vaguely familiar with, while also providing us with direct communcation with the underlying Raspberry Pi hardware.
* The PICKAXE Editor was used for investigating ideas for scalability and future expansions of our project, however was not directly used in the finished model.


Code / Logic
================

On the code side, the src [source code] section includes a necessary example of accessing hardware features on both a Raspberry Pi and PICKAXE (if you have the resources).

The underlying calculations of the fastest route is created through the utilization of a fundemental aspect of basic Decision Mathematics.

The code itself, uses this decision algorithm and input sensors to map the data into an efficient data structure, which is then evaluated to retrieve an efficient route based on a simplistic traffic model with many assumptions required. With more time and resources, these assumptions and accounting for more events or uncertainities could be tackled, greatly improving the projects potential for environmental benefit and commercial viability.

Building
=========

1. The first step to setting up our project was to prototype traffic simulations on a HTML/Dart2Javascript page, in order to test the appropriateness of the algorithm.
2. We then began building a traffic model board design to enable us to realistically test the Pi as a route finder. At this point we ordered the materials.
3. With the materials having arrived, we constructed the initial electronics on a breaker board allowing us to safely connect up sensors to the Raspberry Pi GPIO pins.
4. We wrote the software on the Pi proper (i.e. the Nano software), and began to configure it to communicate with the Pi4J API in order that we might interact with underlying hardware.
5. Then we began the testing process and started to combine the electronics with the software side of the project.
6. We finalised the program, allowing the Pi to generate optimised routes based on the input data and variable assumptions.
