import java.util.*;

public class TeamList{
    
    Vector<Team> teams = new Vector<Team>();
    int numberOfTeams;
    
    public TeamList(int[][] rawData){
        for (int[] i: rawData){
            for (int j = 3; j < 7; j++){
                if (teams.size() == 0) {
                    teams.add(new Team(i[j], rawData));
                } else {
                    boolean foundTeam = false;
                    for (Team x : teams){
                        if (x.getNumber() == i[j]){
                            foundTeam = true;
                            break;
                        }
                    }
                    if (!foundTeam){
                        teams.add(new Team(i[j], rawData));
                    }
                }
            }
        }
        numberOfTeams = teams.size();
    }
    
    public void sort(int sortVariable){
        for (int i = 0; i < numberOfTeams-1; i++){
            double maxValue = -1;
            int maxIndex = i;
            for (int j = i; j < numberOfTeams; j++){
                double temp = -1;
                switch (sortVariable){
                    case 1:
                        temp = teams.get(j).getAvgScore();
                        break;
                    case 2: 
                        temp = teams.get(j).getAvgAuto();
                        break;
                    case 3:
                        temp = teams.get(j).getAvgTele();
                        break;
                    case 4:
                        temp = teams.get(j).getAvgEnd();
                        break;
                    case 5:
                        temp = teams.get(j).getNumber();
                }
                if (temp > maxValue){
                    maxValue = temp;
                    maxIndex = j;
                }
            }
            Collections.swap(teams, i, maxIndex);
        }
    }
    
    public void printTeam(int number){
        boolean found = false;
        for (Team x : teams){
            if (x.getNumber() == number){
                x.printMatchHistory();
                found = true;
            }
        }
        if (!found){
            System.out.println("Team not found.");
        }
    }
    public void printTable(){
        System.out.printf("%5s%7s%8s%8s%8s%8s\n", "Rank", "Team", "Average", "AvgAuto", 
        "AvgTele", "AvgEnd");
        int rank = 1;
        for (Team x : teams){
            System.out.printf("%5d%7d%8.1f%8.1f%8.1f%8.1f\n", rank, x.getNumber(),
            x.getAvgScore(), x.getAvgAuto(), x.getAvgTele(), x.getAvgEnd());
            rank++;
        }
    }
    public void printTeamData(){
        System.out.printf("Number of teams: %d\n", numberOfTeams);
        for (Team x : teams){
            x.printData();
            System.out.println();
        }
    }
}