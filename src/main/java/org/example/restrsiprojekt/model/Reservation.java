package org.example.restrsiprojekt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Reservation {

    private Long reservationId;
    private Long showingId;
    private List<SeatLocation> seatLocation;
    public Reservation(List<SeatLocation> seatLocation) {
        this.seatLocation = seatLocation;
    }

    public Reservation() {
    }

    public Reservation(Long reservationId, Long showingId, List<SeatLocation> seatLocation) {
        this.reservationId = reservationId;
        this.showingId = showingId;
        this.seatLocation = seatLocation;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getShowingId() {
        return showingId;
    }

    public void setShowingId(Long showingId) {
        this.showingId = showingId;
    }

    public List<SeatLocation> getSeatLocation() {
        return seatLocation;
    }

    public void setSeatLocation(List<SeatLocation> seatLocation) {
        this.seatLocation = seatLocation;
    }
}
