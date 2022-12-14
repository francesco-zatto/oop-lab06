package it.unibo.exceptions.fakenetwork.impl;

import it.unibo.exceptions.arithmetic.ArithmeticService;
import it.unibo.exceptions.fakenetwork.api.NetworkComponent;
import it.unibo.exceptions.fakenetwork.exceptions.NetworkException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

import static it.unibo.exceptions.arithmetic.ArithmeticService.KEYWORDS;
import static it.unibo.exceptions.arithmetic.ArithmeticUtil.nullIfNumberOrException;

/**
 * A {@link NetworkComponent} mimicking an unstable network.
 */
public final class ServiceBehindUnstableNetwork implements NetworkComponent {
    private final double failProbability;
    private final RandomGenerator randomGenerator;
    private final List<String> commandQueue = new ArrayList<>();

    /**
     * @param failProbability the probability that a network communication fails
     * @param randomSeed random generator seed for reproducibility
     */
    public ServiceBehindUnstableNetwork(final double failProbability, final int randomSeed) {
        if (illegalProbability(failProbability)) {
            throw new IllegalArgumentException();
        }
        this.failProbability = failProbability;
        randomGenerator = new Random(randomSeed);
    }

    /**
     * @param failProbability the probability that a network communication fails
     */
    public ServiceBehindUnstableNetwork(final double failProbability) {
        this(failProbability, 0);
    }

    /**
     * Builds a new service with an unstable network.
     */
    public ServiceBehindUnstableNetwork() {
        this(0.5);
    }

    public void sendData(final String data) throws IOException {
        accessTheNework(data);
        final var exceptionWhenParsedAsNumber = nullIfNumberOrException(data);
        if (KEYWORDS.contains(data) || exceptionWhenParsedAsNumber == null) {
            commandQueue.add(data);
        } else {
            final var message = data + " is not a valid keyword (allowed: " + KEYWORDS + "), nor is a number";
            commandQueue.clear();
            throw new IllegalArgumentException(message, exceptionWhenParsedAsNumber);
        }
    }

    public String receiveResponse() throws IOException {
        accessTheNework(null);
        try {
            return new ArithmeticService(Collections.unmodifiableList(commandQueue)).process();
        } finally {
            commandQueue.clear();
        }
    }

    private void accessTheNework(final String message) throws NetworkException {
        if (randomGenerator.nextDouble() < failProbability) {
            throw message == null ? new NetworkException() : new NetworkException(message);
        }
    }

    private boolean illegalProbability(double failProbability) {
        return failProbability < 0 || failProbability >= 1;
    }

}
