package Characters;
import Attacks.MeleeAttack;//importing MeleeAttack
import java.awt.*;

public class Warrior extends Melee{
    /**
     * creating a constructor of class warrior
     * @param characterName: name of the character performing attack
     * @param primaryStat : primary stat of the character
     * @param maxHP : the maximum hp of the character
     * @param startPosition : position allotted to the character in the starting
     * @param maxStat: maximum stat of the character
     */
    public Warrior(String characterName, int primaryStat, int maxHP, Point startPosition, int maxStat)
    {
        super(characterName,primaryStat,maxHP,startPosition,maxStat);
        //adding the characteristic of the attack to attack list
        attackList.add(new MeleeAttack(0, "Punch", 5, 3));
        attackList.add(new MeleeAttack(3, "Slam", 5, 3));
        attackList.add(new MeleeAttack(20, "Charge", 30, 15));
    }
}