package creatures;
import huglife.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import java.util.Map;

public class TestClorus {

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Plip());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // have empty, have Plip
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> Topplip = new HashMap<Direction, Occupant>();
        Topplip.put(Direction.TOP, new Plip());
        Topplip.put(Direction.BOTTOM, new Empty());
        Topplip.put(Direction.LEFT, new Impassible());
        Topplip.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(Topplip);
        expected = new Action(Action.ActionType.ATTACK,Direction.TOP);

        assertEquals(expected, actual);


        //have empty but dont have Plip energy >=1
        c= new Clorus(1.2);
        HashMap<Direction, Occupant> NoPenergybigger1 = new HashMap<Direction, Occupant>();
        NoPenergybigger1.put(Direction.TOP, new Impassible());
        NoPenergybigger1.put(Direction.BOTTOM, new Impassible());
        NoPenergybigger1.put(Direction.LEFT, new Impassible());
        NoPenergybigger1.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(NoPenergybigger1);
        expected = new Action(Action.ActionType.REPLICATE,Direction.RIGHT);

        assertEquals(expected,actual);

        //have empty but dont have Plip energy < 1
        c= new Clorus(0.8);
        HashMap<Direction, Occupant> NoPenergyamsller1 = new HashMap<Direction, Occupant>();
        NoPenergyamsller1.put(Direction.TOP, new Impassible());
        NoPenergyamsller1.put(Direction.BOTTOM, new Impassible());
        NoPenergyamsller1.put(Direction.LEFT, new Impassible());
        NoPenergyamsller1.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(NoPenergyamsller1);
        expected = new Action(Action.ActionType.MOVE,Direction.RIGHT);

        assertEquals(expected,actual);








    }

}
