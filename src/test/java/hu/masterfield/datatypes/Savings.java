package hu.masterfield.datatypes;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

/**
 * A Savings t�pus� accountok megad�sakor haszn�lt adatok k�l�n osz�lyba
 * kiszervez�se.
 * */

public class Savings {
    private String accountTypes;

    private String ownershipTypes;

    private String accountName;

    private String openingBalance;

}