package baseEngine;

/**
 * Created by DeS on 21.06.2015.
 */
public class PickModel {

    PickModel(int id, int castleId, boolean checked, String Name){
        this.Id = id;
        this.Checked = checked;
        this.Name = Name;
        this.CastleId = castleId;
    }

    public int Id;

    public boolean Checked;

    public String Name;

    public int CastleId;

}
