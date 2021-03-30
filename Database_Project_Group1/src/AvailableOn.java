public class AvailableOn {

    public int artistId;
    public int platformId;

    public AvailableOn(int artistId, int platformId) {
        this.artistId = artistId;
        this.platformId = platformId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public int getArtistId() {
        return artistId;
    }

    public int getPlatformId() {
        return platformId;
    }
}
