package Characters;
import Attacks.Attack;//importing Attack
import Attacks.MeleeAttack;//importing MeleeAttack
import java.awt.*;//importing point class from awt
/**
 * this class is an abstract class which is extending the RPGCharacter class
 * this class is interacting with target by selecting the attack which is to be done on the opponent
 * @author Keyush Patel
 */
public abstract class Melee extends RPGCharacter {
    //declaring private variables
    private int maxEnergy;
    private int currentEnergy;

    /**
     * creating the constructor of class caster
     * @param name : name of the character
     * @param strength: strength of the character
     * @param maxHP:maximum HP of the character
     * @param position: position of the character
     * @param maxEnergy: maximum Energy of the character
     */
    public Melee(String name, int strength, int maxHP, Point position, int maxEnergy) {
        super(name, 1, strength, maxHP, position);
        currentEnergy = maxEnergy;
        this.maxEnergy = maxEnergy;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    /**
     * @param character is the target on which the attack has to be done
     * @param select the index value of the attack which the user wants to perform
     * @return return the CurrentHP of the target
     */
    public int attack(RPGCharacter character, int select)
    {
        if (attackList.size() <= select ||select<0)//checking the select(index value) is valid or not
        {
            return -1;//returning -1 if select in not valid index
        }
        Attack attack=attackList.get(select);
        double distance = character.getPosition().distance(getPosition());//finding the distance between the two opponents
        if (distance > attack.getRange())//checking whether the target os within range
        {
            return -2;//returning -2 if target in not range of the attack
        }
        if (currentEnergy < 0||currentEnergy<attack.getCost())//checking whether the Energy is enough or not to the attack
        {
            return -3;//return -3 if the Energy is not enough
        }
        if(attack instanceof MeleeAttack)//checking the whether the attack is instance of MeleeAttack
        {
            attack.interactWithTarget(character,this.strength);//calling interactWithTarget method
        }
        currentEnergy=currentEnergy-attack.getCost();//subtracting the cost from the currentEnergy
        return character.getCurrentHP();//returning the currentHP of character
    }
    public String toString()
    {
        return (super.toString()+"\nEnergy: "+currentEnergy+"/"+maxEnergy);
    }
}
