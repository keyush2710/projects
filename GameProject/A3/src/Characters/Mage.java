package Characters;
import Attacks.DamageSpell;//importing DamageSpell
import Attacks.MeleeAttack;//importing MeleeAttack
import java.awt.*;
public class Mage extends Caster{
    /**
     * creating a constructor of class mage
     * @param characterName: name of the character performing attack
     * @param primaryStat : primary stat of the character
     * @param maxHP : the maximum hp of the character
     * @param startPosition : position allotted to the character in the starting
     * @param maxStat: maximum stat of the character
     */
    public Mage(String characterName, int primaryStat, int maxHP, Point startPosition, int maxStat)
    {
        super(characterName,primaryStat,maxHP,startPosition,maxStat);
        //adding the characteristic of the attack to attack list
        attackList.add(new MeleeAttack(0, "Staff", 3, 3));
        attackList.add(new DamageSpell(20,"Fire Ball",10, 20));
        attackList.add(new DamageSpell(15,"Frost Ball",7, 15));
        attackList.add(new DamageSpell(30,"Lightning",15, 20));
    }

}
