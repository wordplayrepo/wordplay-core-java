package org.syphr.wordplay.bot.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

import org.syphr.wordplay.bot.RobotStrategy;
import org.syphr.wordplay.core.board.ValuedPlacement;

public class RandomSelectionStrategy implements RobotStrategy
{
    private final RandomGenerator rng = new Random();

    private final List<ValuedPlacement> placements = new ArrayList<ValuedPlacement>();

    @Override
    public Collection<ValuedPlacement> getDataStructure()
    {
        return placements;
    }

    @Override
    public ValuedPlacement selectPlacement()
    {
        if (placements.isEmpty()) {
            return null;
        }

        return placements.get(rng.nextInt(placements.size()));
    }

    @Override
    public String toString()
    {
        return "Random Selection";
    }
}
