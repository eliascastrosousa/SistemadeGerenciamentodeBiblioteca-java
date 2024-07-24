package dev.eliascastro.sgb.infra.emprestimo;

import java.util.Random;

public class IdGenerator {
    private static final Random random = new Random();
    private static final int LOWER_BOUND = 10000;
    private static final int UPPER_BOUND = 99999;

    public static int generateRandomId() {
        return random.nextInt((UPPER_BOUND - LOWER_BOUND) + 1) + LOWER_BOUND;
    }
}
