package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDatesPojo {
    private String checkin;
    private String checkout;

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingDatesPojo() {
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
/*
 "firstname": "Fabio",
                                   "lastname": "Dominguez",
                                   "totalprice": 111,
                                   "depositpaid": true,
                                   "bookingdates": {
                                       "checkin": "2018-01-01",
                                       "checkout": "2019-01-01"
                                   },
                                   "additionalneeds": "Breakfast"
 */