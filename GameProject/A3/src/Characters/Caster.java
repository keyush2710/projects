package Characters;
import Attacks.*;//importing all classes inside package Attacks
import java.awt.Point;//importing point class from awt

/**
 * this class is an abstract class which is extending the RPGCharacter class
 * this class is interacting with target by selecting the attack which is to be done on the opponent
 * @author Keyush Patel
 */
public abstract class Caster extends RPGCharacter
{
    //declaring private variables
    private int maxMana;
    private int currentMana;

    /**
     * creating the constructor of class caster
     * @param name : name of the character
     * @param intellect: intellect of the character
     * @param maxHP:maximum HP of the character
     * @param position: position of the character
     * @param maxMana: maximum mana of the character
     */
    public Caster(String name, int intellect, int maxHP, Point position,int maxMana) {
        super(name, intellect,1 , maxHP, position);
        currentMana=maxMana;
        this.maxMana=maxMana;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    /**
     *
     * @param character is the target on which the attack has to be done
     * @param select the index value of the attack which the user wants to perform
     * @return return the CurrentHP of the target
     */
    public int attack(RPGCharacter character, int select)
    {
        if (attackList.size() <= select || select<0)//checking the select(index value) is valid or not
        {
            return -1;//returning -1 if select in not valid index
        }
        Attack attack=attackList.get(select);
        double distance = character.getPosition().distance(getPosition());//finding the distance between the two opponents
        if (distance > attack.getRange() && attack.getRange()>=0 )//checking whether the target os within range
        {
            return -2;//returning -2 if target in not range of the attack
        }
        if (currentMana < 0||currentMana<attack.getCost()) //checking whether the mana is enough or not to the attack
        {
            return -3;//return -3 if the mana is not enough
        }
        if(attack instanceof DamageSpell)//checking the whether the attack is instance of DamageSpell
        {
            attack.interactWithTarget(character,this.intellect);//calling interactWithTarget method
        }
        if(attack instanceof HealingSpell)//checking the whether the attack is instance of HealingSpell
        {
            attack.interactWithTarget(character, this.intellect);//calling interactWithTarget method
            currentHp = currentHp + attack.interactWithTarget(character, intellect);//calling interactWithTarget method
        }
        if(attack instanceof MeleeAttack)//checking the whether the attack is instance of MeleeAttack
        {
            attack.interactWithTarget(character,0);//calling interactWithTarget method
        }
        if(attack instanceof Resurrection)//checking the whether the attack is instance of Resurrection
        {
            attack.interactWithTarget(character, 0);//calling interactWithTarget method

        }
        currentMana=currentMana-attack.getCost();//subtracting the cost from the currentMana
        return character.getCurrentHP();//returning the currentHP of character
    }
    public String toString()
    {
        return (super.toString()+"\nMana: "+currentMana+"/"+maxMana);
    }
}
