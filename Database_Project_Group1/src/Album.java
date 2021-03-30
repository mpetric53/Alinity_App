import java.sql.*;

public class Album {

    public int albumId;
    public String albumName;
    public String albumInfo;
    public String releaseDate;

    public Album(int albumId, String albumName, String albumInfo, String releaseDate){
        this.albumId = albumId;
        this.albumName = albumName;
        this.albumInfo = albumInfo;
        this.releaseDate = releaseDate;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setAlbumInfo(String albumInfo) {
        this.albumInfo = albumInfo;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumInfo() {
        return albumInfo;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
