import java.io.*;
import java.util.*;

class Transition
{
    char read;
    char write;
    char shift;
    int nextState;
    
    Transition(char read, char write, char shift, int nextState)
    {
        this.read = read;
        this.write = write;
        this.shift = shift;
        this.nextState = nextState;
    }
}