public class Award {

    public int awardId;
    public String awardName;

    public void setAwardId(int awardId) {
        this.awardId = awardId;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public int getAwardId() {
        return awardId;
    }

    public String getAwardName() {
        return awardName;
    }

    public Award(int awardId, String awardName) {
        this.awardId = awardId;
        this.awardName = awardName;
    }
}
