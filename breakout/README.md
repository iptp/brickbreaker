# Notes and to-do's
0. Implement victory
	* How to determine when no bricks are left?
	* Would like to avoid getObjects(Brick.class)
	* Also would like to avoid a global counter
	* Which class updates this information?
0. Create powerup brick(s)
	* Extra ball
	* Long paddle
0. High-score board
	* Probably needs a new (non-Actor) class
	* Save to file?
0. Finish refactoring the GameScreen-Ball-Brick interaction
	* Would like avoid casting World to GameScreen
	* Should create a that Ball calls on the Bricks it hits or something like that
0. Refactor scoring
	* Bonus for each consecutive brick
	* Reset bonus when ball hits paddle
	* Maybe create separate class for this
0. Refactor collision
	* Separate act() into more methods
	* Improve Ball-Brick collision
	* Or just make it simpler and easier to explain
0. Main menu screen
	* Maybe unnecessary?
	* What features?
	* Useful for teaching how to change current World
0. NOT going to make a "MyActor" class with a bunch of custom methods
	* Not good for this tutorial IMO
0. Should teach while() at some point
	* Not nice to teach for() without teaching while() first
0. Avoid wasting time on unnecessary arithmetic explanations

# Tutorial topics
0. [tumbleweed]
