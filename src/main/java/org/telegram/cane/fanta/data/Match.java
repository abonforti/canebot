package org.telegram.cane.fanta.data;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by Federico on 21/09/16.
 */
public class Match {
    private Map<Integer,String> scores;
    private String team1;
    private String team2;
    private String result;
    public Match(String team1,  String team2, String result, Map<Integer,String> scores) {
        this.team1=team1;
        this.team2=team2;
        if(result.isEmpty()) {
           this.result =" Calcolati ->";
        }else{
            this.result = result;
        }
        this.scores=scores;
    }
    @Override
    public String toString(){
        return this.team1+" VS "+this.team2+" = "+this.result + " ("+this.scores.get(0)+" - "+this.scores.get(1)+")";
    }
}
