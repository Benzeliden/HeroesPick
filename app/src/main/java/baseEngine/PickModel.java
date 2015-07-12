package baseEngine;

public class PickModel {

    PickModel(int id, String castleName, boolean checked, String Name) {
        this.Id = id;
        this.Checked = checked;
        this.Name = Name;
        this.CastleName = castleName;
    }

    public int Id;

    public boolean Checked;

    public String Name;

    public String CastleName;

    @Override
    public String toString()   {

        return String.format("%s ( %s )", Name, CastleName);
    }

}


