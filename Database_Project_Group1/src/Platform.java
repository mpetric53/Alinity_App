public class Platform {

    public int platformId;
    public String platformName;
    public String platformInfo;

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformInfo() {
        return platformInfo;
    }

    public void setPlatformInfo(String platformInfo) {
        this.platformInfo = platformInfo;
    }

    public Platform(int platformId, String platformName, String platformInfo) {
        this.platformId = platformId;
        this.platformName = platformName;
        this.platformInfo = platformInfo;
    }
}
