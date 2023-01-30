package exercise13;

import java.lang.Math;

public class WreckBeach {

    /**
     * Find the maximum possible stamina level left after climbing
     * the stairs of wreck beach.
     * Precondition: 0 <= stamina <= 1000
     * @param reqStaminas an array of stamina levels for each step
     * @param initStamina initial stamina level
     * @return the maximum remaining stamina level
     * @throws InsufficientStaminaException if the maximum stamina
     * level is non-positive (stamina <= 0)
     */
    public static int maxStamina(int[] reqStaminas, int initStamina) throws InsufficientStaminaException {
        int countStairs = reqStaminas.length;
        int[] maxStaminas = new int[countStairs + 1];
        int maxStamina = 0;

        maxStaminas[0] = initStamina;
        for (int i = 1; i < countStairs; i++) {
            maxStaminas[i] = 0;
        }

        findStaminas(0, maxStaminas, reqStaminas);

        for (int i = Math.max(0, countStairs - 2); i <= countStairs; i++) {
            if (maxStamina < maxStaminas[i] - (countStairs - i)) {
                maxStamina = maxStaminas[i] - (countStairs - i);
            }
        }

        if (maxStamina <= 0) {
            throw new InsufficientStaminaException();
        }

        return maxStamina;
    }

    /**
     * Recursively find the maximum possible stamina level left after climbing
     * the stairs of wreck beach.
     * Precondition: 0 <= stamina <= 1000
     * @param maxStaminas an array of stamina levels for each step
     * @param reqStaminas the array of required staminas for climbing each step
     * @param pos the stair the person is located on
     */
    private static void findStaminas(int pos, int[] maxStaminas, int[] reqStaminas) {
        if (pos == reqStaminas.length) {
            return;
        }

        int newStamina;
        pos += 1;

        for (int i = 0; i <= Math.min(2, reqStaminas.length - pos); i++) {
            newStamina = maxStaminas[pos - 1] - reqStaminas[pos - 1 + i] - i;
            if (maxStaminas[pos + i] < newStamina) {
                maxStaminas[pos + i] = newStamina;
            }
        }

        findStaminas(pos, maxStaminas, reqStaminas);
    }
}
