package org.example.restrsiprojekt.util;

public class CinemaSlots {

    public static Integer ROWS = 8;
    public static Integer COLUMNS = 14;

    public static Integer[][] room = initRoom();

    // 1 - pojedyncze wolne , 2 - podwojne wolne, 3 - pojedyncze zajete, 4 - podwojne zajete
    // 0 - miejsce nie istnieje (fizycznie np. sa tam schody)
    private static Integer[][] initRoom(){
        Integer[][] room = new Integer[ROWS][COLUMNS];
        for(int i = 0; i < room.length; i++){
            for(int j = 0; j < room[0].length; j++){
                room[i][j] = 1;
                if(i > 6){room[i][j] = 2;}
            }
        }
        return room;
    }

}
