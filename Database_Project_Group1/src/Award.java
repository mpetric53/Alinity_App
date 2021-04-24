import java.util.ArrayList;

public class Award {

    private int awardId;
    private String awardName;
    private String awardInfo;
    private int artistId;

    public String getAwardInfo() {
        return awardInfo;
    }

    public void setAwardInfo(String awardInfo) {
        this.awardInfo = awardInfo;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

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

    public Award(String awardName, String awardInfo, int artistId) {
        this.awardName = awardName;
        this.awardInfo = awardInfo;
        this.artistId = artistId;
    }

    public Award() {

    }

    /**
     * selectAward method of the Award class.
     * This method will use the getData(String, ArrayList<String>) method of the Alinity
     * class. The SQL statement will select Awards where the ID value input will be bound
     * from the ArrayList, and save the result.
     * Sets ID, name based on result data.
     * This method will specifically work only when selecting ALL the information for said award
     * id.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public ArrayList<ArrayList<String>> selectAward(User user, String awardName) throws AlinityException {
        try {
            if(user.getRole().equals("General") || user.getRole().equals("Admin")){
                ArrayList<String> info = new ArrayList<>();
                info.add(awardName);
                return AlinityMain.alinityDB.getData("SELECT * FROM Award WHERE awardName = ?" , info);
//                System.out.print("\nColumn headers: " + result.get(0));
//                ArrayList<String> awardData = result.get(1);
//                setAwardId(Integer.parseInt(awardData.get(0)));
//                setAwardName(awardData.get(1));
//                setAwardInfo(awardData.get(2));
//                setArtistId(Integer.parseInt(awardData.get(3)));
//                printAward();
            } else System.out.println("You do not have access to this function. Please contact an administrator."); return null;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Award WHERE awardName = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Award WHERE awardName = ?");
        }
    }

    public ArrayList<ArrayList<String>> selectAward(User user, int awardId) throws AlinityException {
        try {
            if(user.getRole().equals("General") || user.getRole().equals("Admin")){
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(awardId));
                return AlinityMain.alinityDB.getData("SELECT * FROM Award WHERE awardId = ?" , info);
//                System.out.print("\nColumn headers: " + result.get(0));
//                ArrayList<String> awardData = result.get(1);
//                setAwardId(Integer.parseInt(awardData.get(0)));
//                setAwardName(awardData.get(1));
//                setAwardInfo(awardData.get(2));
//                setArtistId(Integer.parseInt(awardData.get(3)));
//                printAward();
            } else System.out.println("You do not have access to this function. Please contact an administrator."); return null;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Award WHERE awardName = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Award WHERE awardName = ?");
        }
    }

    public ArrayList<ArrayList<String>> selectArtistAward(User user, Artist artist) throws AlinityException {
        try {
            if(user.getRole().equals("General") || user.getRole().equals("Admin")){
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(artist.getArtistId()));
                return AlinityMain.alinityDB.getData("SELECT Award.awardName FROM Award WHERE Award.artistId = ?" , info);
//                System.out.print("\nColumn headers: " + result.get(0));
//                ArrayList<String> awardData = result.get(1);
//                setAwardId(Integer.parseInt(awardData.get(0)));
//                setAwardName(awardData.get(1));
//                setAwardInfo(awardData.get(2));
//                setArtistId(Integer.parseInt(awardData.get(3)));
//                printAward();
            } else System.out.println("You do not have access to this function. Please contact an administrator."); return null;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in obtaining data (IndexOutOfBoundsException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Award WHERE awardName = ?");
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in obtaining data (NullPointerException) from the database. Please check your syntax in the selectAll(ArrayList<String>) method.", "SELECT * FROM Award WHERE awardName = ?");
        }
    }

    public void selectAwardHandler(ArrayList<ArrayList<String>> result) {
        for(int i = 1; i < result.size(); i++){
            //System.out.print("\nColumn headers: " + result.get(i));
            ArrayList<String> awardData = result.get(i);
            setAwardId(Integer.parseInt(awardData.get(0)));
            setAwardName(awardData.get(1));
            setAwardInfo(awardData.get(2));
            setArtistId(Integer.parseInt(awardData.get(3)));
            printAward();
        }
    }

    public void selectArtistAwardHandler(ArrayList<ArrayList<String>> result) {
        for(int i = 1; i < result.size(); i++){
            //System.out.print("\nColumn headers: " + result.get(i));
            ArrayList<String> awardData = result.get(i);
            setAwardName(awardData.get(1));
            printAward();
        }
    }

    /**
     * updateAward method of the Award class.
     * A SQL statement as a String is created using the current name, info,
     * and artistId where the awardId is based on the values which will be bound
     * from the input ArrayList of Strings.
     * Executes using the setDada method.
     * This method will specifically work only when updating ALL the information for said award
     * id.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean updateAward(User user, String awardName, String awardInfo, Artist artist) throws AlinityException {
        try {
            if(user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(awardName);
                info.add(awardInfo);
                info.add(String.valueOf(artist.getArtistId()));
                info.add(String.valueOf(this.getAwardId()));
                String putStmt = "UPDATE Award SET awardName = ? , awardInfo = ?, artistId = ? WHERE awardId = ?";
                return AlinityMain.alinityDB.setData(putStmt, info);
            } else System.out.println("You do not have access to this function. Please contact an administrator."); return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Award SET awardName = ? , awardInfo = ?, artistId = ? WHERE awardId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the updateAll(ArrayList<String>) method.", "UPDATE Award SET awardName = ? , awardInfo = ?, artistId = ? WHERE awardId = ?");
        }
    }

    /**
     * insertAward method of the Award class.
     * A SQL statement as a String is created, which takes in
     * values based on the values to be bound from the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when inserting ALL the information for said award.
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean insertAward(User user, String awardName, String awardInfo, Artist artist) throws AlinityException {
        try {
            if(user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(awardName);
                info.add(awardInfo);
                info.add(String.valueOf(artist.getArtistId()));
                String insertStmt = "INSERT INTO Award (awardName, awardInfo, artistId) VALUES (?, ?, ?)";
                return AlinityMain.alinityDB.setData(insertStmt, info);
            } else System.out.println("You do not have access to this function. Please contact an administrator."); return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Award (awardName, awardInfo, artistId) VALUES (?, ?, ?)");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the insertAll(ArrayList<String>) method.","INSERT INTO Award (awardName, awardInfo, artistId) VALUES (?, ?, ?)");
        }
    }

    /**
     * deleteAward method of the Award class.
     * Deletes the given award data where the awardId is bound
     * via tha value inside the ArrayList of Strings.
     * Executes using the setData method.
     * This method will specifically work only when deleting ALL the information for said award
     * id
     * If the attempt of the executing the statement fails, log the error to the file.
     *
     * @throws AlinityException
     */
    public boolean deleteAward(User user) throws AlinityException {
        try {
            if(user.getRole().equals("Admin")) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(this.getAwardId()));
                String deleteStmt = "DELETE FROM Award WHERE awardId = ?";
                return AlinityMain.alinityDB.setData(deleteStmt, info);
            } else System.out.println("You do not have access to this function. Please contact an administrator."); return false;
        } catch (NullPointerException npe) {
            throw new AlinityException(npe, "-> Error in manipulating data (NullPointerException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Award WHERE awardId = ?");
        } catch (IndexOutOfBoundsException ioobe) {
            throw new AlinityException(ioobe, "-> Error in manipulating data (IndexOutOfBoundsException) from the database. Please check your syntax in the deleteAll() method.", "DELETE FROM Award WHERE awardId = ?");
        }
    }

    /**
     * printAward method of the Award class.
     * Print values for the current awardId,
     * awardName, information about the award,
     * and the id of the artist (will be changed
     * later to represent the artist itself).
     */
    public void printAward() {
        System.out.println("\nID: " + this.awardId);
        System.out.println("Name: " + this.awardName);
        System.out.println("Info: " + this.awardInfo);
        System.out.println("Artist: " + this.artistId);
    }
}
