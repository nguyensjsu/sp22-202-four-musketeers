
public class MediumDifficultyState implements IDifficultyState {
     
    private IDifficultyStateManager dsm;
    
    public MediumDifficultyState(IDifficultyStateManager _dsm) {
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
