
Requirements for the Skier
-If there are no subsequent paths, they are finished
-If there is exactly one subsequent path, they must take that path regardless of what it
contains or does not contain
-If there are two subsequent paths,
-If both contain jumps, choose the one with the greater height or go right if their
heights are equal
 +If exactly one contains a jump, take that path
 +If both contain slaloms, go to the one whose direction is leeward (you can safely assume that any 
 time there are two slaloms from the same segment, one is leeward and one is windward – never two of the same direction)
 +If exactly one contains a slalom and the other is a regular segment, go to the
 slalom ONLY if its direction is leeward, otherwise go to the regular segment
 +If both are regular segments, choose right as the default direction

TreeBuilder.java
-This class is used to create the binary trees for the program. They must be created using a
 queue-based approach, very similar to the level-order traversal that uses queues, but here the
 tree is being built rather than traversed. This class must use generics to build trees storing data
 of any type.
-The class should not have any instance variables and only needs one method. Note that this
 class does not require a constructor since there is nothing to initialize. Java has a "default
 constructor" when none are explicitly provided, so it knows how to initialize the object when you
 use the "new" keyword. The only required method in this class is: