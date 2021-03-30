public class Artist {

    public int artistId;
    public String artistName;
    public String artistInfo;
    public String recordLabelId;


    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setArtistInfo(String artistInfo) {
        this.artistInfo = artistInfo;
    }

    public void setRecordLabelId(String recordLabelId) {
        this.recordLabelId = recordLabelId;
    }

    public Artist(int artistId, String artistName, String artistInfo, String recordLabelId) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistInfo = artistInfo;
        this.recordLabelId = recordLabelId;
    }

    public String getRecordLabelId() {
        return recordLabelId;
    }

    public String getArtistInfo() {
        return artistInfo;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getArtistId() {
        return artistId;
    }


}
