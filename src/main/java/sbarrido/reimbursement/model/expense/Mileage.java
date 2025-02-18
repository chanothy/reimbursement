package sbarrido.reimbursement.model.expense;

import sbarrido.reimbursement.model.destination.Destination;
import java.util.Date;

import javax.persistence.*;

@Entity
@DiscriminatorValue("mileage")
public class Mileage extends Reimbursement {
    public static final double _COMMUTE = 15.2;
    private static final double _RATE = 0.56;
    //(cascade = {CascadeType.ALL})
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "destination_id")
    private Destination destination;

    public Mileage() {}

    public Mileage(Date date, String description, Destination destination) {
        setDate(date);
        setDescription(description);
        setDestination(destination);
        setCost(destination.getDistance());
    }

    public double calculate() {
        double val = (this.cost - Mileage._COMMUTE) * Mileage._RATE;

        return val;
    }
    public void setDestination(Destination loc) {
        this.destination = loc;
    }
    public Destination getDestination() {
        return this.destination;
    }
}