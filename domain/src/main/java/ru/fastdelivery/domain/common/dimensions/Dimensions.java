package ru.fastdelivery.domain.common.dimensions;

import java.math.BigInteger;

public record Dimensions(
        BigInteger length,
        BigInteger width,
        BigInteger height) implements Comparable<Dimensions>{

    private static boolean isLassThenZero(BigInteger price){return BigInteger.ZERO.compareTo(price) > 0;}


    public Dimensions{
        if (isLassThenZero(length)){
            throw new IllegalArgumentException("Weight cannot be below Zero!");
        }
        if (isLassThenZero(width)){
            throw new IllegalArgumentException("Weight cannot be below Zero!");
        }
        if (isLassThenZero(height )){
            throw new IllegalArgumentException("Weight cannot be below Zero!");
        }
    }


    @Override
    public int compareTo(Dimensions d) {
        return d.length().compareTo(length()) + d.width().compareTo(width()) + d.height().compareTo(height());
    }
}
