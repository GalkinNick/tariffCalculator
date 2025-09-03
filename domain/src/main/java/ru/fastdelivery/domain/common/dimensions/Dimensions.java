package ru.fastdelivery.domain.common.dimensions;

import ru.fastdelivery.domain.common.weight.Weight;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public record Dimensions(
        BigInteger length,
        BigInteger width,
        BigInteger height) implements Comparable<Dimensions>{

    private static final BigInteger MAX_LENGTH_DIMENSION = BigInteger.valueOf(1500);
    private static final BigInteger BILLION = BigInteger.valueOf(1_000_000_000);
    private static final int SCALE = 4;
    private static final BigInteger SCALE_FACTOR = BigInteger.TEN.pow(SCALE);

    private static boolean isLassThenZero(BigInteger price){return BigInteger.ZERO.compareTo(price) > 0;}
    private static boolean isMoreThenMaxDimension(BigInteger price) {return MAX_LENGTH_DIMENSION.compareTo(price) > 0;}

    public Dimensions{
        if (isLassThenZero(length) || isLassThenZero(width) || isLassThenZero(height)){
            throw new IllegalArgumentException("Dimensions cannot be below Zero!");
        }
        if (isMoreThenMaxDimension(length) || isMoreThenMaxDimension(width) || isMoreThenMaxDimension(height)){
            throw new IllegalArgumentException("Weight cannot be more max dimension!");
        }

    }

    public BigDecimal volume(){
        BigInteger product = width.multiply(height).multiply(length);

        product = product.multiply(SCALE_FACTOR);

        return new BigDecimal(product.divide(BILLION));
    }


    public static Dimensions zero() {
        return new Dimensions(BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO);
    }

    public Dimensions add(Dimensions additionalDimensions) {
        return new Dimensions(this.length.add(additionalDimensions.length),
                this.width.add(additionalDimensions.width),
                this.height.add(additionalDimensions.height));
    }

    @Override
    public int compareTo(Dimensions d) {
        return d.length().compareTo(length()) + d.width().compareTo(width()) + d.height().compareTo(height());
    }
}
