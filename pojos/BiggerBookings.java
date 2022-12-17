package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BiggerBookings {
    private Integer bookingid;
    private BookingsPojo booking;

    public BiggerBookings(Integer bookingid, BookingsPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public BiggerBookings() {
    }



    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public BookingsPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingsPojo booking) {
        this.booking = booking;
    }
    @Override
    public String toString() {
        return "BiggerBookings{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
