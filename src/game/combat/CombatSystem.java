package game.combat;
import game.characters.Enemy;
import game.characters.PlayerCharacter;


/**
 * Handles resolving combat between any two combatants.
 */
public class CombatSystem {


    /**
     * Resolves combat between an attacker and defender, handling range, evasion, damage, and defeat.
     */
    public static void resolveCombat(Combatant attacker, Combatant defender) {
        if (attacker == null || defender == null) return;


        // condition whether the attacker is a melee fighter
        if (attacker instanceof MeleeFighter melee && melee.isInMeleeRange(attacker.getPosition(), defender.getPosition())) {

            melee.fightClose(defender);

            // Check defeat
            if (!defender.isDead()) return;

            if (defender instanceof Enemy enemy) {
                System.out.println(enemy.enemyDiscription() + " has been defeated!");
                enemy.defeat();

            } else if (defender instanceof PlayerCharacter player) {
                System.out.println("Game Over! " + player.getName() + " has been defeated.");
                System.out.println("Total treasure: " + player.getTreasurePoints());
            }

            return; // don't allow a second attack
        }


        // condition whether the attacker is a ranged fighter
        if (attacker instanceof RangedFighter ranged && ranged.isInRange(attacker.getPosition(), defender.getPosition())) {


            ranged.fightRanged(defender);

            // Check defeat
            if (!defender.isDead()) return;

            if (defender instanceof Enemy enemy) {
                System.out.println(enemy.enemyDiscription() + " has been defeated!");
                enemy.defeat();

            } else if (defender instanceof PlayerCharacter player) {
                System.out.println("Game Over! " + player.getName() + " has been defeated.");
                System.out.println("Total treasure: " + player.getTreasurePoints());
            }

            return;

        }
    }



}

