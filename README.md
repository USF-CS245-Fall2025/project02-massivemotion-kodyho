# PROJECT 02

- Please note that this was my first attempt to use Javadoc, so if the comments are weird, forgive me.

HOW TO RUN

javac MassiveMotion.java
java MassiveMotion MassiveMotion.txt


FILE DESCRIPTIONS

MassiveMotion.java
> The main file handles the primary program functions like creating ui and animation.

List.java
> The interface that came with the skeleton program
 
MyArrayList.java
> A standard implementation of Array List
 - add(T e） 》 	O(1) amortized (like we said in class)
 - add(int index, T e)  》  O(n)
 - get(int index)  》  O(1)
 - remove(int index)  》  O(n)
 - size()  》  O(1)

MyLinkedList.java 
> A standard Linked List implementation. This was chosen for the Massive Motion program because dynamic removal (things in the middle of lists are removed easier)
 - add(T e） 》 	O(1)
 - add(int index, T e)  》  O(n)
 - get(int index)  》  O(n)
 - remove(int index)  》  O(n)
 - size()  》  O(1)

MyDoublyLinkedList.java * If you want to use any of these or array lists, you have to manually change them because it was never specified what to do with these.
> A Doubly Linked List implementation where the nodes point both to the next and previous node.
 - add(T e） 》 	O(1)
 - add(int index, T e)  》  O(n)
 - get(int index)  》  O(n)
 - remove(int index)  》  O(n)
 - size()  》  O(1)

MyDummyHeadLinkedList.java
> A Dummy Head Linked List where the head node has no data to make working with the beginning of the list easier.
 - add(T e） 》 	O(1) 
 - add(int index, T e)  》  O(n)
 - get(int index)  》  O(n)
 - remove(int index)  》  O(n)
 - size()  》  O(1)

Body.java
> A file outlining the Body class used to create celestial bodies.


ADDRESSING THE RUBRIC

IMPLEMENTATION
> print statements are used to ensure the proper function of the program.
> defining a random seed during the testing of the program to ensure repeatability to compare changes.
EFFICIENCY
> seen above, but LinkedList is optimal because of the constant adding and removal.
> I initially thought ArrayList but removal requires items to be shifted down the array. 
> I didn't do anything big with performance optimization, and i think it could certainly slow down as the bodies increase, but current technology proves that this would probably noticeably occur at much higher counts.
DECOMPOSITION
> I break the problem down by usual. I tackle small portions first. For this project, it was the lists, creating the canvas, then the bodies, then getting the movement down.
> Each class is designed to be easily read. The important work is done by MassiveMotion and anything specific gets it's own class like the lists or the body.
> Accounting for gravity would require the implementation of mass and math to calculate the trajectory of the body passing the star.
> I think implementing the detection of crashes might not be so bad to implement, but it might take up way more time to calculate which bodies it is colliding with and affect performance.