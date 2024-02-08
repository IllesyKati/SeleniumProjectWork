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
 * A Savings típusú accountok megadásakor használt adatok külön oszályba
 * kiszervezése.
 * */

public class Savings {
    private String accountTypes;

    private String ownershipTypes;

    private String accountName;

    private String openingBalance;

}