public class DifficultyStateManager implements IDifficultyStateManager
{
    
    private IDifficultyState easy;
    private IDifficultyState med;
    private IDifficultyState hard;
    
    private IDifficultyState curDifficulty;
    
    public DifficultyStateManager() {
        easy = new EasyDifficultyState(this);
        med = new MediumDifficultyState(this);
        hard = new HardDifficultyState(this);
        
        curDifficulty = easy;
    }
    
    public String getCurDifficulty() { 
        return curDifficulty.getClass().getName(); 
    }
    
    public void setEasyDifficulty() {
        curDifficulty.setEasyDifficulty();
    }
    
    public void setMediumDifficulty() {
        curDifficulty.setMediumDifficulty();
    }
    
    public void setHardDifficulty() {
        curDifficulty.setHardDifficulty();
    }
    
    public void setEasyDifficultyState() {
        curDifficulty = easy;
    }
    
    public void setMediumDifficultyState() {
        curDifficulty = med;
    }
    
    public void setHardDifficultyState() {
        curDifficulty = hard;
    }
}
