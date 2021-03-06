/* 
 * Copyright 2011 Cologne University of Applied Sciences Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.amcgala.framework.animation.interpolation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Eine Interpolation mit kleinen Schritten zu Beginn, die zum Ende hin größer
 * werden. Auf diese Weise ist es zum Beispiel möglich Beschleunigungen zu
 * animieren.
 *
 * @author Robert Giacinto
 */
public class EaseInInterpolation extends Interpolation {

    private static final Logger log = LoggerFactory.getLogger(EaseInInterpolation.class);

    /**
     * Erzeugt eine neue EaseInInterpolation.
     *
     * @param start     der Startwert, bei der die Interpolation beginnen soll
     * @param end       der Endwert, bei der die Interpolation enden soll
     * @param stepCount die Anzahl der Schritte der Interpolation
     * @param cyclic    true, wenn die Intpoleration zyklisch von Neuem beginnen soll
     */
    public EaseInInterpolation(double start, double end, int stepCount, boolean cyclic) {
        super(start, end, stepCount, cyclic);
    }

    @Override
    public double nextValue() {
        if (stepCounter++ < stepCount) {
            double x = (stepCounter / stepCount);
            x = Math.pow(x, 2);
            return min + max * x;
        } else if (cyclic) {
            stepCounter = 0;
        }
        return max;
    }
}
