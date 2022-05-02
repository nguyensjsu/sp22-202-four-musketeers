public interface IChessMoveSubject {
    void notifyObservers(int turn, String movement);

    void addObserver(IChessMoveObserver obs);

    void deleteObserver(IChessMoveObserver obs);

    void processMove(int x, int y, String pieceType);
}
