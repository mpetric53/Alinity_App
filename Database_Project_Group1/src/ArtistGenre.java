public class ArtistGenre {

    public int artistId;
    public int genreId;

    public ArtistGenre(int artistId, int genreId) {
        this.artistId = artistId;
        this.genreId = genreId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getArtistId() {
        return artistId;
    }

    public int getGenreId() {
        return genreId;
    }
}
