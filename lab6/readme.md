## 1.f/

Issue: Bug     
Problem description: in Dip.java a we create a new Random Object. It's not efficient and may not produce random numbers which is not good.      
How to solve: create a Random Object and reuse this object when we want a new random number

Issue: Vulnerability
0 found

Issue: (major) Code Smell
Problem description: in Dip.java the i variable is being incremented inside the loop body.
How to solve: increment this variable outside the loop body.

Issue: (major) Code Smell
Problem description: in DemoMain.java the print messages are made in the standard output which does not record this messages.
How to solve: use a Logger instead of "System.out"

## 2.a/
Debt: 2h 20min
This value its the time we have to spend to fix all the problems detected by sonar.

## 2.d/
42 Uncovered lines
14 Uncovered conditions