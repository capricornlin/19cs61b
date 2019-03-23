package creatures;

import huglife.*;

import java.awt.Color;
import java.util.*;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        //.name() method call by Occupant constructor.
        super("plip");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        //Green g = 96*energy+63
        g = (int)Math.round(96*energy+63);
        r = 99;
        b = 76;
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        if(energy <= 0){
            energy = 0;
        }else {
            energy -= 0.15;
        }
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        if(energy >= 2){
            energy = 2;
        }else {
            energy += 0.2;
            if(energy >=2){
                energy = 2;
            }

        }
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        energy = energy/2;

        return new Plip(energy);
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> clorusNeighbors = new ArrayDeque<>();
        boolean anyClorus = false;
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}
        for (Direction d : neighbors.keySet()) {
            Occupant a = neighbors.get(d);
            if (a.name() == "empty") {
                emptyNeighbors.add(d);
            }else if(a.name() == "clorus"){
                clorusNeighbors.add(d);
            }
        }
        // Rule 2
        // HINT: randomEntry(emptyNeighbors)

        // Have "Empty" neighbor.
        if (emptyNeighbors.size() != 0) {
            Random rand = new Random();
            Iterator<Direction> x = emptyNeighbors.iterator();
            int Nummove = rand.nextInt(emptyNeighbors.size());
            //Direction choosedir = Direction.TOP;
            if (this.energy >= 1) {
                Direction choosedir = Direction.BOTTOM;
                while (x.hasNext()) {
                    if (Nummove == 0) {
                        choosedir = x.next();
                        break;
                        //return new Action(Action.ActionType.REPLICATE, choosedir);
                    } else {
                        x.next();
                        Nummove--;
                    }
                }
                return new Action(Action.ActionType.REPLICATE, choosedir);

                // Rule 3
                //don't have clorus
            } else if(clorusNeighbors.size()== 0){
                while (x.hasNext()) {
                    if (Nummove == 0) {
                        Direction choosedir = x.next();
                        return new Action(Action.ActionType.STAY);
                    } else {
                        x.next();
                        Nummove--;
                    }
                }
            }else{
                int Num = rand.nextInt(1);
                //have colrus and Replicate
                if(Num == 0){
                    while (x.hasNext()) {
                        if (Nummove == 0) {
                            Direction choosedir = x.next();
                            return new Action(Action.ActionType.REPLICATE, choosedir);
                        } else {
                            x.next();
                            Nummove--;
                        }
                    }
                    //have colrus and move
                }else{
                    while (x.hasNext()) {
                        if (Nummove == 0) {
                            Direction choosedir = x.next();
                            return new Action(Action.ActionType.MOVE, choosedir);
                        } else {
                            x.next();
                            Nummove--;
                        }
                    }
                }

            }
        }
        // Rule 4
        return new Action(Action.ActionType.STAY);

    }

}
