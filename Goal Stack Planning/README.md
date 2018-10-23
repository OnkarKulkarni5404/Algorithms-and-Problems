# Goal Stack Planning
### What ?
  The reasoning strategy used by STRIPS is goal stack planning. In goal stack planning, the problem solver makes use of a goal stack GS that contains both subgoals and actions that have been proposed to satisfy those subgoals. It also relies on a database DB that describes the current situation, and a set of actions described by precondition, add and delete lists.
### Actions and Rules 
##### R1 :
    pickup(x)
    armempty()
    onT(x)
    clear(x)
##### R2 :
    putdown(x)
    holding(x)
##### R3 :
    stack(x,y)
    holding(x)
    clear(y)
##### R4 :
    unstack(x,y)
    armempty()
    on(x,y)
    clear(x)
