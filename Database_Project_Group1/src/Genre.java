public class Genre {

    public int genreId;
    public String Name;

    public Genre(int genreId, String name) {
        this.genreId = genreId;
        Name = name;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
