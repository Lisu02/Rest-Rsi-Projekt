package org.example.restrsiprojekt.model;


import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Showing {

    private Long showingId;
    private Movie movie;
    private String showingDateAndTime;
    private Long[][] seats = new Long[10][10];

    public Showing(){
        initSeats();
    }
    public Showing(Movie movie, String date) {
        this.movie = movie;
        this.showingDateAndTime = date;
        initSeats();
    }

    private void initSeats(){
        for(int i = 0 ; i < 10; i++){
            for(int j = 0; j < 10; j++){
                seats[i][j] = 0L;
            }
        }
    }

    public void makeSeatReservation(List<SeatLocation> seatLocationList, Long reservationId){
        System.out.println("Wejście do metody makeSeatReservation: " + seatLocationList);
        Iterator<SeatLocation> iterator = seatLocationList.iterator();
        while (iterator.hasNext()){
            System.out.println("Wejście do metody makeSeatReservation WHILE");
            SeatLocation seatLocation = iterator.next();
            Integer x = seatLocation.getX();
            Integer y = seatLocation.getY();
            System.out.println(x + " " + y + " " + reservationId);
            if(seats[x][y] == 0){
                seats[x][y] = reservationId;
            }
        }

    }

    public void removeSpecifiedSeatReservation(Reservation reservation,List<SeatLocation> seatLocationList){
        //todo: dokonczyc usuwanie tylko wybranych miejsc z rezerwacji (nie konieczne do zrobienia)
    }

    public void removeAllSeatReservation(Reservation reservation){
        Long reservationId = reservation.getReservationId();
        for(int i = 0 ; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(Objects.equals(seats[i][j], reservationId)){
                    seats[i][j] = 0L;
                }
            }
        }
    }

    public Long getShowingId() {
        return showingId;
    }

    public void setShowingId(Long showingId) {
        this.showingId = showingId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getShowingDateAndTime() {
        return showingDateAndTime;
    }

    public void setShowingDateAndTime(String showingDateAndTime) {
        this.showingDateAndTime = showingDateAndTime;
    }

    public Long[][] getSeats() {
        return seats;
    }

    public void setSeats(Long[][] seats) {
        this.seats = seats;
    }
}
