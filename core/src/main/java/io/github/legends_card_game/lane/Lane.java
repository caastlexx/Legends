package io.github.legends_card_game.lane;

public class Lane {
    private final boolean[] positionA;
    private final boolean[] positionM;
    private final boolean[] positionB;

    public Lane() {
        positionA = new boolean[]{true, false};
        positionM = new boolean[]{false, false};
        positionB = new boolean[]{false, true};
    }

    public boolean progressTokenA() {
        if (positionA[0]) {
            if (positionA[1]) {
                positionA[1] = false;
                positionM[1] = true;
            } else {
                positionA[0] = false;
                positionM[0] = true;
            }
        } else if (positionM[0]) {
            if (positionM[1]) {
                positionM[1] = false;
                positionB[1] = true;
            } else {
                positionM[0] = false;
                positionB[0] = true;
            }
        } else {
            return true;
        }
        return false;
    }

    public boolean progressTokenB() {
        if (positionB[0]) {
            if (positionB[1]) {
                positionB[1] = false;
                positionM[1] = true;
            } else {
                positionB[0] = false;
                positionM[0] = true;
            }
        } else if (positionM[0]) {
            if (positionM[1]) {
                positionM[1] = false;
                positionA[1] = true;
            } else {
                positionM[0] = false;
                positionA[0] = true;
            }
        } else {
            return true;
        }
        return false;
    }

    public boolean[] getPositionA() {
        return positionA;
    }

    public boolean[] getPositionB() {
        return positionB;
    }

    public boolean[] getPositionM() {
        return positionM;
    }
}
