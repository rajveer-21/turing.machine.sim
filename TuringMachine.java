import java.io.*;
import java.util.*;

class TuringMachine
{
    HashMap<Integer, State> states = new HashMap<>();
    StringBuffer tape;
    char blankSymbol = '_';
    int startState = 0;
    int acceptState;
    int currentState;
    int head = 0;
    public void loadMachine(String filePath) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File(filePath));
        acceptState = Integer.parseInt(scanner.nextLine().trim());
        while(scanner.hasNextLine() == true)
        {
            String currentTransition = scanner.nextLine().trim() + ' ';
            String parts[] = new String[5];
            String currPath = "";
            int partsPointer = 0;
            for(int i = 0; i < currentTransition.length(); i++)
            {
                if(currentTransition.charAt(i) != ' ')
                {
                    currPath = currPath + currentTransition.charAt(i);
                }
                else
                {
                    parts[partsPointer] = currPath;
                    currPath = "";
                    partsPointer++;
                }
            }
            int from = Integer.parseInt(parts[0]);
            char read = parts[1].charAt(0);
            char write = parts[2].charAt(0);
            char move = parts[3].charAt(0);
            int to = Integer.parseInt(parts[4]);
            states.putIfAbsent(from, new State(from));
            states.putIfAbsent(to, new State(to));
            Transition newTransition = new Transition(read, write, move, to);
            states.get(from).addTransition(newTransition);
        }
        scanner.close();
    }
    
    public void intializeTape(String input)
    {
        tape = new StringBuffer(input);
        currentState = startState;
        head = 0;
    }
    
    public void simulateTuringMachine()
    {
        while(currentState != acceptState)
        {
            if(head < 0)
            {
                tape.insert(0, blankSymbol);
                head = 0;
            }
            if(head >= tape.length())
            {
                tape.append(blankSymbol);
                head = tape.length() - 1;
            }
            Transition t = states.get(currentState).match(tape.charAt(head));
            if(t == null)
            {
                System.out.println("Halting, State not found.");
                return;
            }
            tape.setCharAt(head, t.write);
            currentState = t.nextState;
            if(t.shift == 'R')
            head++;
            else
            head--;
            System.out.println("Current State - " + currentState + " Tape - " + tape.toString() + " head location - " + head);
        }
        System.out.println("Input has been accepted by the machine!");
    }
    
    public static void main(String args[])throws IOException , FileNotFoundException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter filepath for your Turing Machine - ");
        String filePath = br.readLine();
        TuringMachine tm = new TuringMachine();
        tm.loadMachine(filePath);
        System.out.println("Enter the input for intialization of turing tape - ");
        String input = br.readLine();
        tm.intializeTape(input);
        tm.simulateTuringMachine();
    }
}