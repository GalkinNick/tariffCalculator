package ru.fastdelivery.usecase;

import lombok.RequiredArgsConstructor;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.shipment.Shipment;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class TariffCalculateUseCase {
    private final WeightPriceProvider weightPriceProvider;
    private final DeliveryCostCalculator deliveryCostCalculator;

    public Price calc(Shipment shipment) {
        var weightAllPackagesKg = shipment.weightAllPackages().kilograms();
        var minimalPrice = weightPriceProvider.minimalPrice();

        var dimensionAllPackages = shipment.dimensionsAllPackages().volume();


        return weightPriceProvider
                .costPerKg()
                .multiply(weightAllPackagesKg)
                .max(minimalPrice)
                .multiply(dimensionAllPackages);
    }

    public Price minimalPrice() {
        return weightPriceProvider.minimalPrice();
    }

    public Price maxPrice(){
        return weightPriceProvider.maxPrice();
    }
}
