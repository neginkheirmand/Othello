# Othello
click [here](https://www.eothello.com/) to play othello.

![](https://github.com/neginkheirmand/Othello/blob/master/project%20description%20and%20examples/header.jpg?raw=true)

### Rules of the Game
read [rules of the game](https://service.mattel.com/instruction_sheets/T8130-Eng.pdf)

Features:
* player able to play against pc or human opponent
the AI code is based on the minimax algorithm and improved by using alpha beta pruning.

### code explanation
[Minimax](https://en.wikipedia.org/wiki/Minimax) is a decision rule used in artificial intelligence, decision theory and game theory for minimizing the possible loss for a worst case (maximum loss) scenario. Originally formulated for two-player zero-sum game theory, covering both the cases where players take alternate moves and those where they make simultaneous moves, it has also been extended to more complex games and to general decision-making in the presence of uncertainty.
[Alpha–beta pruning](https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning) is a search algorithm that seeks to decrease the number of nodes that are evaluated by the minimax algorithm in its search tree. It is an adversarial search algorithm used commonly for machine playing of two-player games (in this case Othello). It stops evaluating a move when at least one possibility has been found that proves the move to be worse than a previously examined move. Such moves need not be evaluated further. When applied to a standard minimax tree, it returns the same move as minimax would, but prunes away branches that cannot possibly influence the final decision.

for more explanation see [this video](https://www.youtube.com/watch?v=l-hh51ncgDI&ab_channel=SebastianLague) and [this one](https://www.youtube.com/watch?v=5oXyibEgJr0&ab_channel=Computerphile) too.

using minimax i was able to go 7 levels deep in the game decision making tree and after implementing the alpha beta pruning, the code was able to search for the best decision to make considering the result in the 10th level of the tree.



### Examples

![](https://github.com/neginkheirmand/Othello/blob/master/project%20description%20and%20examples/example_1.png?raw=true)

![](https://github.com/neginkheirmand/Othello/blob/master/project%20description%20and%20examples/example_2.png?raw=true)

![](https://github.com/neginkheirmand/Othello/blob/master/project%20description%20and%20examples/example_3.png?raw=true)

![](https://github.com/neginkheirmand/Othello/blob/master/project%20description%20and%20examples/example_4.png?raw=true)

