package org.example.restrsiprojekt.Controller;


import org.example.restrsiprojekt.Controller.Exception.ReservationNotFoundException;
import org.example.restrsiprojekt.DAO.*;
import org.example.restrsiprojekt.model.Movie;
import org.example.restrsiprojekt.model.Reservation;
import org.example.restrsiprojekt.model.Showing;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController()
@RequestMapping("/reservation")
public class ReservationController {

    private final MovieDao movieDao = MovieDaoImpl.getMovieDaoInstance();
    private final ReservationDao reservationDao = ReservationDaoImpl.getReservationDaoInstance();
    private final ShowingDao showingDao = ShowingDaoImpl.getShowingDaoInstance();

    @PostMapping("/{id}")
    public Reservation createReservation(@PathVariable Long id,@RequestBody Reservation reservation) {
        if(showingDao.findById(id).isPresent() && !reservation.getSeatLocation().isEmpty()){
            Showing showing = showingDao.findById(id).get();
            reservation.setShowingId(id);
            reservationDao.save(reservation);
            showing.makeSeatReservation(reservation.getSeatLocation(),reservation.getReservationId());
        }
        return null;
    }
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        if(reservationDao.findById(id).isPresent()){
            Reservation reservation = reservationDao.findById(id).get();

            Showing showing = showingDao.findById(reservation.getShowingId()).orElseThrow();
            showing.removeAllSeatReservation(reservation);
            reservationDao.delete(id);
        }
    }
    @PatchMapping()
    public void updateReservation(@RequestBody Reservation reservation) {
        if (reservation != null) {
            Reservation res = reservationDao.findById(reservation.getReservationId()).orElseThrow();
            Showing showing = showingDao.findById(res.getShowingId()).orElseThrow();

            showing.removeAllSeatReservation(res);

            res.setSeatLocation(reservation.getSeatLocation());
            reservationDao.update(res);

            showing.makeSeatReservation(res.getSeatLocation(), res.getReservationId());
        }
    }
    @GetMapping
    public List<Reservation> findAllReservations() {
        return reservationDao.findAll();
    }
    @GetMapping("/{id}")
    public EntityModel<Reservation> findReservation(@PathVariable Long id) {
        Reservation reservation = reservationDao.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));

        return EntityModel.of(reservation,
                linkTo(methodOn(ReservationController.class).findReservation(id)).withSelfRel(),
                linkTo(methodOn(ReservationController.class).findAllReservations()).withRel("all")
        );
    }
    @GetMapping("/movie/{id}")
    public CollectionModel<EntityModel<Reservation>> findAllReservationsForMovie(@PathVariable Long id) {


        List<Reservation> reservationList = reservationDao.findByMovieId(id);

        List<EntityModel<Reservation>> reservations = reservationList.stream()
                .map(reservation -> EntityModel.of(reservation,
                                linkTo(methodOn(ReservationController.class).findReservation(reservation.getReservationId())).withSelfRel()
                        )
                ).toList();


        return CollectionModel.of(reservations,
                linkTo(methodOn(ReservationController.class).findAllReservations()).withSelfRel());

    }

//    public DataHandler getReservationPDF(Long reservationId) {
//        try {
//            Optional<Reservation> optionalReservation = reservationDao.findById(reservationId);
//            Reservation reservation;
//            if (optionalReservation.isEmpty()) {
//                reservation = new Reservation();
//                Showing showing = new Showing();
//                showing.setShowingId(-1L);
//                showing.setShowingDateAndTime("Jutro");
//                Movie movie = new Movie(
//                        -1L,
//                        "Tytul",
//                        "Rezyser",
//                        "Wczoraj",
//                        "Opis",
//                        MovieType.ACTION,
//                        null
//                );
//                showing.setMovie(movie);
//                reservation.setShowingId(showing.getShowingId());
//                LinkedList<SeatLocation> seatLocations = new LinkedList<SeatLocation>();
//                seatLocations.add(new SeatLocation(0,0));
//                seatLocations.add(new SeatLocation(1,1));
//                reservation.setSeatLocation(seatLocations);
//            }else{
//                reservation = optionalReservation.get();
//            }
//
//            Document document = new Document();
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            PdfWriter.getInstance(document, baos);
//
//            document.open();
//
//            // Dodanie tytulu
//            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
//            Paragraph title = new Paragraph("Reservation Confirmation", titleFont);
//            title.setAlignment(Element.ALIGN_CENTER);
//            title.setSpacingAfter(20);
//            document.add(title);
//
//            Showing showing = showingDao.findById(reservation.getShowingId()).orElseThrow();
//
//            // Szczegoly rezerwacji
//            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
//            document.add(new Paragraph("Reservation ID: " + reservationId, normalFont));
//            document.add(new Paragraph("Movie title: " + showing.getMovie().getTitle(), normalFont));
//            document.add(new Paragraph("Showing date: " + showing.getShowingDateAndTime(), normalFont));
//            document.add(new Paragraph("Reserved seats (in progress): ", normalFont));
//
//            document.close();
//            byte[] pdfBytes = baos.toByteArray();
//
//            DataSource dataSource = new ByteArrayDataSource(pdfBytes, "application/pdf");
//            return new DataHandler(dataSource);
//
//
//            //return baos.toByteArray();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

}
