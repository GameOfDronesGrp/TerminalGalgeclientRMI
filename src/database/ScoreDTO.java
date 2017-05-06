/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
/**
 *
 * @author haydar
 */
public class ScoreDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userID;
    private int score;
    private String time;
    private int rank;


    public ScoreDTO(String user_id) {
        this.userID = user_id;
        this.score = 0;
        this.time = "NOW()";
    }

    public ScoreDTO(String userID, int score, String time) {
        this.userID = userID;
        this.score = score;
        this.time = time;

    }
    public ScoreDTO(int rank, String userID, int score, String time) {
        this.rank = rank;
        this.userID = userID;
        this.score = score;
        this.time = time;
    }

    public ScoreDTO(Object hs){
        this.userID = ((ScoreDTO) hs).getUserID();
        this.score = ((ScoreDTO) hs).getScore();
        this.time = ((ScoreDTO) hs).getDatetime();
    }

    public int getRank(String userID){
        if(rank > 0){
            return 1;
        }else{
            return -1;
        }
    }

    public void setScore(int score) {
        if(score>0)
            this.score = score; 
        else this.score = 0;
    }
    
    public String getUserID() { return userID; }
    public int getScore() { return score; }
    public void setUserID(int id) { this.userID = userID; }
    public String getDatetime() { return time; }
    public void setDatetime(String datetime) { this.time = datetime; }
    public String toString() { return userID + "\t" + score + "\t" + time; }
}

