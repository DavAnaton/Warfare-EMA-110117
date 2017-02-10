# Warfare-EMA-110117
## Warfare

[![LogoWarfare](https://github.com/DavAnaton/Warfare-EMA-110117/raw/master/Docs/logo.png)]()

This we had to do for l'Ecole des Mines d'Alès. We had to create AIs that can solve 3 types of problems.<br/>
It is divided in 3 parts.

### 1) Part 1 _ Filling of a cargo bay
#### 1.1) Presentation
A cargo bay contains 9 slots and must be filled with different containers.<br/>
Each slot can accommodate up to 4 containers.<br/><br/>

The different containers are:
- Food:
  -  These containers are to be treated first at the arrival of the boat and must be placed at the top of the stacks.
- Artillery:
  - These containers are the heaviest and must be placed at the bottom of the stack in order not to damage the others ones.
  -  Because of their weight and not to unbalance the boat, we must limit the number of artillery containers to 2 per pile.
- Ammunition:
  -  These containers may explode and may not appear more than one in each slot.
  -  In case of an attack on the boat, a copy of this container must be accessed quickly. To do so, place one at the location closest to the cockpit.
- Light fighting:
  -  In case of an attack on the boat, a copy of this container must be accessed quickly. To do so, place one at the location closest to the cockpit.

You can find the boat's below:<br/>

[![map](https://github.com/DavAnaton/Warfare-EMA-110117/raw/master/Docs/img/Mission2.png)]()
<br/><br/>
The list of containers to be loaded includes:
- 8 Artilleries
- 11 Foods
- 9 Ammos
- 8 LightFights

### 2) Part 2 _ Transport of vessels between combat zones
#### 2.1) Presentation
Once the boats are loaded, they must be moved to the battlefield.<br/>
So we have 4 boats to move:
- An XC21 that makes the journey in 45 minutes.
- An XC56 that makes the journey in 1 hour and 30 minutes.
- An XC100 that can be active on the battlefield after 3 hours and 45 minutes.
- An XC800 that takes 6 hours to join the party.

A boat may only cross adverse zones if accompanied by a security team.<br/>
Only one security team is available and it may protect 2 boats at once.<br/>
We have to minimize the sum of the travel times of each convoy.

### 3) Part 3 - Automation of combat phases
#### 3.1) Presentation
Once soldiers are in place and containers are delivered to the battlefield, we need to order them to move and shoot. We want to limit allied losses and get rid of enemy forces.
<br/>
<br/>
We therefore have 3 types of resources at our disposal:
[![map](https://github.com/DavAnaton/Warfare-EMA-110117/raw/master/Docs/img/Mission3_1.png)]()
And this is how forces are at the begining of the battle.
[![map](https://github.com/DavAnaton/Warfare-EMA-110117/raw/master/Docs/img/Mission3_2.png)]()
<br/><br/>
We need to create an AI that uses a Minimax algorithm to calculate 3 moves ahead and find the best moves. 
<br/>