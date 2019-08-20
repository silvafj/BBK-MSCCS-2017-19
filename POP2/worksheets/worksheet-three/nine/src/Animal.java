/**
 *
 */
abstract public class Animal {

    /**
     *
     */
    final public void call() {
        String animalName = this.getClass().getName();
        if (this instanceof Aquatic) {
            System.out.println(animalName +  " will not come...");
        } else if (this instanceof Aerial) {
            System.out.println(animalName + " now flying, will come later when tired...");
        } else if (this instanceof Terrestrial) {
            System.out.println(animalName + " coming...");
        } else {
            throw new UnsupportedOperationException(animalName + " is not Aquatic, Aerial or Terrestrial!");
        }
    }

    /**
     *
     */
    final public void reproduce() {
        if (this instanceof Mammal) {
            ((Mammal)this).giveBirth();
        } else {
            ((NotMammal)this).layEggs();
        }
    }

    /**
     *
     */
    abstract public void makeSound();
}
