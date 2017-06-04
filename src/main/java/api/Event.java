package api;

/**
 * This represents all the events that can happen in the game.
 * GAME_STARTED: the game has started.
 * FOE1_MOVED: foe number 1 has done his action.
 * FOE2_MOVED: foe number 2 has done his action.
 * FOE3_MOVED: foe number 3 has done his action.
 * FOE1_DISCONNECTED: foe number 1 has disconnected.
 * FOE2_DISCONNECTED: foe number 2 has disconnected.
 * FOE3_DISCONNECTED: foe number 3 has disconnected.
 * YOU_WIN: you have won.
 * YOU_LOSE: you have lost.
 * YOUR_TURN: it's your turn. Perform an action.
 * FOE1_TURN: it's foe1's turn. 
 * FOE2_TURN: it's foe2's turn. 
 * FOE3_TURN: it's foe3's turn. 
 * 
 */
public enum Event {
	FOE1_MOVED, FOE2_MOVED, FOE3_MOVED, FOE1_DISCONNECTED, FOE2_DISCONNECTED, FOE3_DISCONNECTED, YOU_WIN, YOU_LOSE, YOUR_TURN, FOE1_TURN, FOE2_TURN, FOE3_TURN, GAME_STARTED
}
