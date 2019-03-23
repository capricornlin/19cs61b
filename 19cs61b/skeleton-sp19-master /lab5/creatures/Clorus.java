package creatures;
import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.*;

public class Clorus extends Creature {

    private int r;

    private int b;

    private int g;

    public Clorus(double e){
        super("clorus");
        r = 34;
        b = 231;
        g = 0;
        energy = e;
    }
    public Color color() {
        g = 0;
        r = 34;
        b = 231;
        return color(r, g, b);
    }

    public void move(){
        energy-=0.03;
    }

    public void stay(){
        energy-=0.01;
    }

    public void attack(Creature c) {
        this.energy = c.energy();
    }

    public Clorus replicate() {
        energy = energy/2;

        return new Clorus(energy);
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {

        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();

        for (Direction d : neighbors.keySet()) {
            Occupant a = neighbors.get(d);
            if (a.name() == "empty") {
                emptyNeighbors.add(d);
            }else if(a.name() == "plip"){
                plipNeighbors.add(d);
            }
        }
        //if neighbos have no "Empty"
        if(emptyNeighbors.size() == 0){
            return new Action(Action.ActionType.STAY);
        }else if(plipNeighbors.size()!=0){//neighbors have "empty" and neighbors have "Plip"
            Random rand = new Random();
            Iterator<Direction> plip = plipNeighbors.iterator();
            int Numplip = rand.nextInt(plipNeighbors.size());

            //if(plipNeighbors.size() != 0){
                Direction choosedir = Direction.BOTTOM;
                while(plip.hasNext()){
                    if(Numplip == 0){
                        choosedir = plip.next();
                        break;
                    }else{
                        plip.next();
                        Numplip--;
                    }
                }
                return new Action(Action.ActionType.ATTACK, choosedir);


            }else{// neighbors have "empty" and don't have "Plip"
                Iterator<Direction> empty = emptyNeighbors.iterator();
                Random rand = new Random();
                int Numempty = rand.nextInt(emptyNeighbors.size());

                if(energy >=1){//energy greater eqaul than 1
                    Direction choosedir = Direction.BOTTOM;
                    while(empty.hasNext()){
                        if(Numempty == 0){
                            choosedir = empty.next();
                            break;
                        }else{
                            empty.next();
                            Numempty--;
                        }
                    }
                    return new Action(Action.ActionType.REPLICATE, choosedir);

                }else{// energy smaller than 1
                    Direction choosedir = Direction.BOTTOM;
                    while(empty.hasNext()){
                        if(Numempty == 0){
                            choosedir = empty.next();
                            break;
                        }else{
                            empty.next();
                            Numempty--;
                        }
                    }
                    return new Action(Action.ActionType.MOVE, choosedir);

                }


            }


        }

    }

