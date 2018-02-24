/**
 * Noah’s Ark!
 */
public class Ark {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Animal[] animals = new Animal[]{
                new Bear(), new Beetle(), new Cat(), new Crocodile(), new Dog(), new Dolphin(), new Eagle(), new Fly(),
                new Frog(), new Lizard(), new Monkey(), new Pigeon(), new Salmon(), new Shark(), new Snake(),
                new Whale()
        };

        for (Animal animal: animals) {
            animal.call();
        }

    }

}
