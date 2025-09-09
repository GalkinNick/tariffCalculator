package ru.fastdelivery.usecase;

import ru.fastdelivery.domain.common.dimensions.Dimensions;
import ru.fastdelivery.domain.common.weight.Weight;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DeliveryCostCalculator {

    private final double EARTH_RADIUS_KM = 6371.0;
    private final double DISTANCE = 450.0;

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

    public BigDecimal calculateTotalDeliveryCost(double distance, Weight weight, Dimensions dimensions) {



      /*  double distanceSegments = Math.ceil(distance / DISTANCE);

        //BigDecimal surchargePerSegment = calculateBaseCost(weightInKilograms, volumeInCubicMeters);
        BigDecimal distanceSurcharge = surchargePerSegment.multiply(BigDecimal.valueOf(distanceSegments));

        BigDecimal totalCost = baseCost.add(distanceSurcharge);

        return totalCost.setScale(2, RoundingMode.CEILING);*/
        return null;
    }

}
