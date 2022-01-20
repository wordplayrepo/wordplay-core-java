/*
 * Copyright © 2012-2022 Gregory P. Moyer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.syphr.wordplay.core.space;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DimensionTest
{
    @Test
    public void of_WidthHeight()
    {
        assertThat(Dimension.of(1, 2), equalTo(new Dimension(1, 2, 1)));
    }

    @Test
    public void of_WidthHeightDepth()
    {
        assertThat(Dimension.of(1, 2, 3), equalTo(new Dimension(1, 2, 3)));
    }

    @ParameterizedTest
    @CsvSource({ "-1,1,1", "1,-1,1", "1,1,-1", "0,1,1", "1,0,1", "1,1,0" })
    public void construct_IllegalValues(int width, int height, int depth)
    {
        assertThrows(IllegalArgumentException.class, () -> Dimension.of(width, height, depth));
    }

    @ParameterizedTest
    @CsvSource({ "0,0,0", "1,0,0", "0,1,0", "0,0,1", "0,1,1", "1,0,1", "1,1,0", "1,1,1" })
    public void contains_True(int x, int y, int z)
    {
        assertTrue(Dimension.of(2, 2, 2).contains(Location.at(x, y, z)));
    }

    @ParameterizedTest
    @CsvSource({ "-1,0,0", "0,-1,0", "0,0,-1", "2,0,0", "0,2,0", "0,0,2" })
    public void contains_False(int x, int y, int z)
    {
        assertFalse(Dimension.of(2, 2, 2).contains(Location.at(x, y, z)));
    }

    @Test
    public void compareTo()
    {
        assertAll(() -> assertThat(Dimension.of(1, 1, 1), comparesEqualTo(Dimension.of(1, 1, 1))),
                  () -> assertThat(Dimension.of(1, 1, 1), lessThan(Dimension.of(2, 1, 1))),
                  () -> assertThat(Dimension.of(1, 1, 1), lessThan(Dimension.of(1, 2, 1))),
                  () -> assertThat(Dimension.of(1, 1, 1), lessThan(Dimension.of(1, 1, 2))));
    }
}
