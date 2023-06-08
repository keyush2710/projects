package Characters;
import Attacks.Attack;//importing Attack class from Attacks package
import java.awt.Point;//importing point class from awt
import java.util.ArrayList;//importing ArrayList from util

/**
 * this class is used to implement the methods such as take damage, heal, printing the attack list
 * initializing the values of the character ,to move the character from one place to another.
 * @author Keyush Patel
 */
public abstract class RPGCharacter{
    //declaring private and protected variables
    private String name;
    private int maxHP;
    private Point position;
    protected int currentHp;
    protected int intellect;
    protected int strength;
    protected ArrayList<Attack> attackList;

    /**
     * creating the constructor class RPGCharacter
     * @param name : name of the character
     * @param intellect :intellect of the character
     * @param strength:strength of the character
     * @param maxHP:maximum hp of the character
     * @param position:position of the character
     */
    public RPGCharacter(String name, int intellect, int strength,int maxHP, Point position)
    {
        //initializing the variables
        this.name = name;
        this.intellect=intellect;
        this.strength=strength;
        currentHp=maxHP;
        this.maxHP = maxHP;
        this.position = position;
        attackList=new ArrayList<>();
    }

    public int getCurrentHP()
    {
        return currentHp;//returning the currentHp
    }

    public Point getPosition() {
        return position;//returning the position
    }

    public int getMaxHP() {
        return maxHP;//returning the maximum value of HP
    }

    public String getName() {
        return name;//returning the name of the character
    }

    /**
     *
     * @param x to move the character in the x(horizontally) direction
     * @param y to move the character in the y(vertical) direction
     */
    public void move(int x, int y)
    {
        position.translate(x,y);//translating the character to new position
    }

    /**
     *
     * @param damage taking the amount of damage to be done on the opponent
     * @return false if the HP of the character is zero which means the character is dead and
     * true if HP is left
     */
    public boolean takeDamage(int damage)
    {
        currentHp=currentHp-damage;//subtracting the current damage from the HP
        if(currentHp<=0)
        {
            currentHp=0;
            return false;
        }
        return true;
    }

    /**
     *
     * @param healing taking the amount of healing to be done
     * @return false if the HP of the character is maximum which means the character is fully healed and
     * true if HP is not maximum
     */
    public boolean heal(int healing)
    {
        currentHp=currentHp+healing;
        if(currentHp>maxHP)
        {
            currentHp=maxHP;
            return true;
        }
        return false;
    }
    //abstract method attack
    public abstract int attack(RPGCharacter target, int selectAttack);

    /**
     * to display the attacks that the character can do
     * @return a string value
     */
    public String getAttacks()
    {
        String attack="";
        for(int i=0;i<attackList.size();i++)
        {

            attack=attack+i+" - "+attackList.get(i)+"\n";//displaying every attack on new line

        }
        if(attack=="")//if attack is empty String
        {
            return attack;
        }
        return attack+"\n";//displaying every attack on new line
    }
    public String toString()
    {
        return name+" ("+getClass().getSimpleName()+") - "+currentHp+"/"+maxHP;
    }
}
