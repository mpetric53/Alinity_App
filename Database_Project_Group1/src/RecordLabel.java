public class RecordLabel {

    public int recordLabelId;
    public String recordLabelName;
    public String recordDateCreated;
    public String labelInfo;

    public int getRecordLabelId() {
        return recordLabelId;
    }

    public void setRecordLabelId(int recordLabelId) {
        this.recordLabelId = recordLabelId;
    }

    public String getRecordLabelName() {
        return recordLabelName;
    }

    public void setRecordLabelName(String recordLabelName) {
        this.recordLabelName = recordLabelName;
    }

    public String getRecordDateCreated() {
        return recordDateCreated;
    }

    public void setRecordDateCreated(String recordDateCreated) {
        this.recordDateCreated = recordDateCreated;
    }

    public String getLabelInfo() {
        return labelInfo;
    }

    public void setLabelInfo(String labelInfo) {
        this.labelInfo = labelInfo;
    }

    public RecordLabel(int recordLabelId, String recordLabelName, String recordDateCreated, String labelInfo) {
        this.recordLabelId = recordLabelId;
        this.recordLabelName = recordLabelName;
        this.recordDateCreated = recordDateCreated;
        this.labelInfo = labelInfo;
    }
}
