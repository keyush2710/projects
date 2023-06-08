import Characters.*;
import java.awt.*;
public class test {
    public static void main(String[] args) {
        Caster aCaster = new Priest("Znarf", 10, 100, new Point(2, 0), 100);
        Melee attackTarget = new Warrior("Gulf", 20, 100, new Point(0, 0), 100);
//Lets inflict some damage on the caster first (so we can heal it)
        System.out.println(aCaster.getCurrentHP());
        attackTarget.attack(aCaster, 2);
        //After this attack aCaster has 50 of HP, now we can try to heal it
        int healAttackReturnValue = aCaster.attack(attackTarget, 2);
//The return value should be the target's HP (the warrior here)
        System.out.println(aCaster.getCurrentHP());
        if (healAttackReturnValue != 100) {
            System.out.println("the method should return the target's hp");

        }
//Now let's see if the heal worked. HP  was 50, healed by 15 + 5 (base heal + half of the intellect)
        if (aCaster.getCurrentHP() != 70) {
            System.out.println(aCaster.getCurrentHP());
            System.out.println("Make sure you are healing the caster according to the specifications (check the comment above)");
        }
    }
}
