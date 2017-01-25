package com.augustanasi.vickingstudyroomexpress;

/**
 * Created by AbbyThomson on 1/22/2017.
 */

public class Room {
    int roomNumber;
    boolean computer;
    int minP;
    boolean availible;
    String roomId;
    String description;

    public Room(int num, int min, boolean comp, boolean avi, String id){
        roomNumber = num;
        computer = comp;
        minP = min;
        availible = avi;
        roomId = id;
        description = this.toString();
    }

    public Room(){

    }

    public void setRoomId(String id){
        roomId = id;
    }
    public void setRoomNumber(int num){
        roomNumber = num;
    }
    public void setComputer(boolean comp){
        computer = comp;
    }
    public void setMinP(int min){
        minP=min;
    }
    public void setAvailible(boolean free){
        availible = free;
    }
    public String getRoomId(){return roomId;}
    public int getRoomNum(){
        return roomNumber;
    }

    public boolean getComp(){
        return computer;
    }

    public int getMinP(){
        return minP;
    }

    public boolean isAvailible(){
        return availible;
    }

    public String toString(){
        String temp = roomId;
        if(minP==4){
            temp=temp+"  Number of People: 4+";
        }
        else{
            temp=temp+"  Number of People: 1-4";
        }
        if(computer){
            temp=temp+" This room has a computer";
        }
        if(availible){
            temp=temp+"\nThis room is AVAILIBLE";
        }
        else{
            temp=temp+"\nThis room is NOT AVAILIBLE";
        }
        return temp;
    }
}
