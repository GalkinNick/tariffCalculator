package ru.fastdelivery.usecase;

import lombok.RequiredArgsConstructor;
import ru.fastdelivery.domain.common.price.Price;

import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Named
@RequiredArgsConstructor
public class DeliveryCostCalculator {

    private final double EARTH_RADIUS_KM = 6371.0;
    private final double MIN_DISTANCE = 450.0;

    public double calculateDistance(double lat1, double lon1,
                                    double lat2, double lon2){

        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double differenceLon = lon2 - lon1;
        double differenceLat = lat2 - lat1;

        double SquareHalfLengthChord = Math.pow(Math.sin(differenceLat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(differenceLon / 2), 2);

        double centralAngle = 2 * Math.atan2(Math.sqrt(SquareHalfLengthChord),
                Math.sqrt(1 - SquareHalfLengthChord));

        if (SquareHalfLengthChord > 0.9999) {
            centralAngle = Math.PI;
        }

        return EARTH_RADIUS_KM * centralAngle;
    }

    public BigDecimal calculateTotalDeliveryCost(double distance, Price price) {

        double distanceSegments = Math.ceil(distance / MIN_DISTANCE);

        BigDecimal distanceSurcharge = price.amount().multiply(BigDecimal.valueOf(distanceSegments));

        BigDecimal totalCost = price.amount().add(distanceSurcharge);

        return totalCost.setScale(2, RoundingMode.CEILING);
    }

}
