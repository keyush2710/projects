package Characters;
import Attacks.*;//importing all classes inside package Attacks
import java.awt.*;

public class Priest extends Caster{
    /**
     * creating a constructor of class priest
     * @param characterName: name of the character performing attack
     * @param primaryStat : primary stat of the character
     * @param maxHP : the maximum hp of the character
     * @param startPosition : position allotted to the character in the starting
     * @param maxStat: maximum stat of the character
     */
    public Priest(String characterName, int primaryStat, int maxHP, Point startPosition, int maxStat)
    {
        super(characterName,primaryStat,maxHP,startPosition,maxStat);
        //adding the characteristic of the attack to attack list
        attackList.add(new MeleeAttack(0,"Wand",3, 3));
        attackList.add(new DamageSpell(10,"Smite",10, 7));
        attackList.add(new HealingSpell(20,"Flash Heal",15, 15));
        attackList.add(new Resurrection());
    }
}
