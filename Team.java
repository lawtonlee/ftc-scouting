import java.util.*;

public class Team{
   
    int number;
    Vector<Integer> matches = new Vector<Integer>();
    Vector<Character> color = new Vector<Character>();
    Vector<Integer> partners = new Vector<Integer>();
    int matchesPlayed;
    Vector<Integer> matchScores =new Vector<Integer>();
    Vector<Integer> autoScores = new Vector<Integer>();
    Vector<Integer> rAutoScores = new Vector<Integer>();
    Vector<Integer> bAutoScores = new Vector<Integer>();
    Vector<Integer> teleScores = new Vector<Integer>();
    Vector<Integer> endScores = new Vector<Integer>();
    double avgScore, avgAuto, avgTele, avgEnd;
    //No getters for below
    double sdScore, sdAuto, sdTele, sdEnd;
    int minScore, minAuto, minTele, minEnd;
    int maxScore, maxAuto, maxTele, maxEnd;
    
    public Team(int team, int[][] rawData){
        number = team;
        for (int i = 0; i < rawData.length; i++){
            if (rawData[i][3] == team || rawData[i][4] == team){
                matches.add(rawData[i][0]);
                color.add('R');
                matchScores.add(rawData[i][7]);
                autoScores.add(rawData[i][8]);
                rAutoScores.add(rawData[i][8]);
                bAutoScores.add(null);
                teleScores.add(rawData[i][9]);
                endScores.add(rawData[i][10]);
                if (rawData[i][3] == team){
                    partners.add(rawData[i][4]);
                } else if (rawData[i][4] == team){
                    partners.add(rawData[i][3]);
                }
            } else if (rawData[i][5] == team || rawData[i][6] == team){
                matches.add(rawData[i][0]);
                color.add('B');
                matchScores.add(rawData[i][12]);
                autoScores.add(rawData[i][13]);
                rAutoScores.add(null);
                bAutoScores.add(rawData[i][13]);
                teleScores.add(rawData[i][14]);
                endScores.add(rawData[i][15]);
                if (rawData[i][5] == team){
                    partners.add(rawData[i][6]);
                } else if (rawData[i][6] == team){
                    partners.add(rawData[i][5]);
                }
            }
        }
        matchesPlayed = matches.size();
        avgScore = getAvg(matchScores);
        avgAuto = getAvg(autoScores);
        avgTele = getAvg(teleScores);
        avgEnd = getAvg(endScores);
        sdScore = getSD(matchScores, avgScore);
        sdAuto = getSD(autoScores, avgAuto);
        sdTele = getSD(teleScores, avgTele);
        sdEnd = getSD(endScores, avgEnd);
        minScore = Collections.min(matchScores);
        minAuto = Collections.min(autoScores);
        minTele = Collections.min(teleScores);
        minEnd = Collections.min(endScores);
        maxScore = Collections.max(matchScores);
        maxAuto = Collections.max(autoScores);
        maxTele = Collections.max(teleScores);
        maxEnd = Collections.max(endScores);
    }
    private double getAvg(Vector<Integer> vec){
        int sum = 0; 
        for (Integer i : vec){
            sum += i;
        }
        return (double)sum / vec.size();
    }
    private double getSD(Vector<Integer> vec, double avg){
        double sum = 0;
        for (Integer i : vec){
            sum += Math.pow(avg - i, 2);
        }
        double variance = sum / vec.size();
        return Math.sqrt(variance);
    }
    
    public int getNumber(){
        return number;
    }    
    public double getAvgScore(){
        return avgScore;
    }
    public double getAvgAuto(){
        return avgAuto;
    }    
    public double getAvgTele(){
        return avgTele;
    }
    public double getAvgEnd(){
        return avgEnd;
    }    
    /*public Vector<Integer> getMatches(){
        return matches;
    }
    public Vector<Integer> getMatchScores(){
        return matchScores;
    }
    public Vector<Integer> getAutoScores(){
        return autoScores;
    }
    public Vector<Integer> getTeleScores(){
        return teleScores;
    }
    public Vector<Integer> getEndScores(){
        return endScores;
    }*/

    public void printData(){
        System.out.println("Team " + number + " Information:");
        System.out.println("Avg Match score: " + avgScore);
        System.out.println("Avg Auto score: " + avgAuto);
        System.out.println("Avg Tele score: " + avgTele);
        System.out.println("Avg Endgame score: " + avgEnd);
    }
    public void printMatchHistory(){
        System.out.printf("\nTeam %d Information:\n", number);
        System.out.printf("%7s%7s%7s%7s%7s%7s%7s\n", "Match", "RAuto", "BAuto", 
            "Tele", "End", "Total", "Team 2");
        for (int i = 0; i < matchesPlayed; i++){
            System.out.printf("%7d%7d%7d%7d%7d%7d%7d\n", matches.get(i), 
                rAutoScores.get(i), bAutoScores.get(i), teleScores.get(i), 
                endScores.get(i), matchScores.get(i), partners.get(i));
        }
        System.out.println("--------------------------------------------");
        System.out.printf("%7s%10d%11d%7d%7d\n", "Min", minAuto, minTele, minEnd, minScore);
        System.out.printf("%7s%10d%11d%7d%7d\n", "Max", maxAuto, maxTele, maxEnd, maxScore);
        System.out.printf("%7s%10.2f%11.2f%7.2f%7.2f\n", "Average", avgAuto, avgTele, 
            avgEnd, avgScore);
        System.out.printf("%7s%10.2f%11.2f%7.2f%7.2f\n", "Std Dev", sdAuto, sdTele, 
            sdEnd, sdScore);
    }
}