package hu.masterfield.datatypes;

import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode

public class ModifyProfileData {

    /**
     * Hozz�f�r�s biztos�t�sa a globalTestData.properties file-hoz.
     */

    protected static GlobalTestData globalTestData = new GlobalTestData();

    private String modFirstName;
    private String modLastName;

    /** Param�ter n�lk�li konstruktor, amivel a globalTestData.properties fileb�l
     * hozz�f�r�nk a tesztadatokhoz.
     *
     */

    public ModifyProfileData() {
        this.modFirstName = globalTestData.getProperty(Consts.MOD_FIRST_NAME);
        this.modLastName = globalTestData.getProperty(Consts.MOD_LAST_NAME);

    }

}
