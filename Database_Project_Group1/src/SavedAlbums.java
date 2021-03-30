public class SavedAlbums {

    public int userId;
    public int albumId;

    public SavedAlbums(int userId, int albumId) {
        this.userId = userId;
        this.albumId = albumId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
}