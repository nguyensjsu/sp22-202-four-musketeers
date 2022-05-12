
public class EasyDifficultyState implements IDifficultyState {
    
    private IDifficultyStateManager dsm;
    
    public EasyDifficultyState(IDifficultyStateManager _dsm) {
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
