import javafx.util.Pair;

import java.util.HashSet;

public interface IMoveSet {
    HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY, boolean isCheckingNoMoves);
}
