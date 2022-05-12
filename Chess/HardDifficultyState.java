
public class HardDifficultyState implements IDifficultyState {
    
    private IDifficultyStateManager dsm;
    
    public HardDifficultyState(IDifficultyStateManager _dsm) {
        dsm = _dsm;
    }
    
    public void setEasyDifficulty() {
        dsm.setEasyDifficultyState();
    }
    
    public void setMediumDifficulty() {
        dsm.setMediumDifficultyState();
    }
    
    public void setHardDifficulty() {
        dsm.setHardDifficultyState();
    }
}