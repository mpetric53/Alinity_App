public class SavedArtists {
    public int userId;
    public int artistId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public SavedArtists(int userId, int artistId) {
        this.userId = userId;
        this.artistId = artistId;
    }
}
