import java.io.*;
import java.util.*;

class State
{
    int id;
    ArrayList<Transition> transitions = new ArrayList<>();
    
    State(int id)
    {
        this.id = id;
    }
    
    void addTransition(Transition newTransition)
    {
        transitions.add(newTransition);
    }
    
    Transition match(char id)
    {
        for(int i = 0; i < transitions.size(); i++)
        {
            if(transitions.get(i).read == id)
            return transitions.get(i);
        }
        return null;
    }
}