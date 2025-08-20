package ru.fastdelivery.domain.common.dimensions;

import java.math.BigInteger;

public record Dimensions(
        BigInteger length,
        BigInteger width,
        BigInteger height
                         ) {


}
