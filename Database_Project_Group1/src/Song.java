public class Song {

    public int songId;
    public String songName;
    public int songDuration;
    public int artistId;
    public int albumId;
    public int genreId;

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(int songDuration) {
        this.songDuration = songDuration;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public Song(int songId, String songName, int songDuration, int artistId, int albumId, int genreId) {
        this.songId = songId;
        this.songName = songName;
        this.songDuration = songDuration;
        this.artistId = artistId;
        this.albumId = albumId;
        this.genreId = genreId;
    }
}
