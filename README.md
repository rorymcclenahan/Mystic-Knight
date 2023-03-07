# Mystic Knight
 
2D Hack and Slash Java game created by Rory McClenahan as part of a project for UCD's COMP30540 Game Development Module.

## Controls:

### Player 1:
1. W A S D for movement
2. F or Space for slash attack

### Player 2:
1. Arrow keys for movement
2. M for slash attack

## Rules:
1. Each player has 3 lives, they lose on when enemies touch them.
2. If a player dies, the game ends.

## Objectives:
1. If enough enemies are slain, the door will be removed and the player(s) can move onto the next level if they get close enough.
2. Once a player is on the second level, and they slay 50 enemies, they will win.

## Features:
1. Normalization path finding between the enemies and the player(s) once the player(s) move within 300 pixels of distance
2. The enemies also find the closest player to figure out which player to head towards
3. The players also use a closest enemy and normalization methods to figure out which enemy is closest and what direction they are in to use their slash attack
4. A second level that has faster and larger enemies that present a larger threat to the player(s)
5. Working background sound that stops when the game is over
6. Object animations that change based on what direction the object is heading towards
