import java.io.*;
import java.util.*;

public class ScoutingProgram{
    public static void main(String[] args)throws FileNotFoundException{
        Scanner console = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String filename = console.next();
        File file = new File(filename);
        while (!file.exists() || file.isDirectory()){
            System.out.print("Invalid file. Try again: ");
            filename = console.next();
            file = new File(filename);
        }
        Scanner input = new Scanner(file);
        int numMatches = 0;
        while (input.hasNextLine()){
            if (input.nextLine().equals("")){
                break;
            }
            numMatches++;
        }
        System.out.printf("Number of matches: %d\n", numMatches);
        input = new Scanner(file);   
        int[][] rawData = new int[numMatches][17];
        fillRawData(input, rawData);
        TeamList teams = new TeamList(rawData);
        while (true){
            String command = console.next().toLowerCase();
            if (command.equals("quit")){
                break;
            }
            String parameter = console.next().toLowerCase();
            if (parameter.equals("quit")){
                break;
            }
            if (command.equals("sort")){
                switch (parameter){
                    case "avgscore":
                    case "score":
                        teams.sort(1);
                        break;
                    case "avgauto":
                    case "auto":
                        teams.sort(2);
                        break;
                    case "avgtele":
                    case "teleop":
                    case "tele":
                        teams.sort(3);
                        break;
                    case "avgend":
                    case "endgame":
                    case "end":
                        teams.sort(4);
                        break;
                    case "team":
                        teams.sort(5);
                    default:
                        System.out.println("Invalid input. Try again");
                }
            } else if (command.equals("print")){
                switch (parameter.toLowerCase()){
                    case "scores":
                    case "teams":
                    case "list":
                        teams.printTable();
                        break;
                    case "data":
                        printRawData(rawData);
                        break;
                    default:
                        try {
                            teams.printTeam(Integer.parseInt(parameter));
                        } catch (NumberFormatException e){
                            System.out.println("Invalid input. Try again");
                            break;
                        }
                }
            } else{
                System.out.println("Invalid input. Try again");
            }
        }
    } 
    public static void fillRawData(Scanner input, int[][] rawData){
        //First column is match numbers
        for (int i = 0; i < rawData.length; i++){
            rawData[i][0] = i+1;
        }
        for (int i = 0; i < rawData.length; i++){
            String next = input.next();
            next = input.next();
            int hyphen = next.indexOf('-');
            rawData[i][1] = Integer.parseInt(next.substring(0,hyphen));
            rawData[i][2] = Integer.parseInt(next.substring(hyphen+1));
            next = input.next();
            for (int j = 0; j < 4; j++){
                next = input.next();
                if (next.charAt(next.length()-1) == '*'){
                    next = next.substring(0,next.length()-1);
                }
                rawData[i][j+3] = Integer.parseInt(next);
            }
            for (int j = 0; j < 2; j++){
                next = input.next();
                rawData[i][j+7] = Integer.parseInt(next);
            }
            next = input.next();
            for (int j = 0; j < 5; j++){
                next = input.next();
                rawData[i][j+9] = Integer.parseInt(next);
            }
            next = input.next();
            for (int j = 0; j < 3; j++){
                next = input.next();
                rawData[i][j+14] = Integer.parseInt(next);
            }
        }
    }
    public static void printRawData(int[][]rawData){
        System.out.printf("%7s%7s%7s%7s%7s%7s%7s%7s%7s%7s%7s%7s%7s%7s%7s%7s%7s\n", 
        "Match", "Red", "Blue", "Red1", "Red2", "Blue1", "Blue2", "Red", "RAuto",
        "RTele", "REnd", "RPenal", "Blue", "BAuto", "BTele", "BEnd", "BPenal");
        for (int i = 0; i < rawData.length; i++){
            for (int j = 0; j < rawData[0].length; j++){
                System.out.printf("%7d", rawData[i][j]);
            }
            System.out.println();
        }
    }
}