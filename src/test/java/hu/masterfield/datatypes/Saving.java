package hu.masterfield.datatypes;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

/**
 * A Saving t�pus� accountok megad�sakor haszn�lt adatok oszt�lyba szervez�se.
 */

public class Saving {
    // fi�k t�pusa
    // Savings
    // Money Market
    @CsvBindByName(column="accountTypes")
    private String accountTypes;

    // tulajdonos t�pus
    // Individual
    // Joint
    @CsvBindByName(column="ownershipTypes")
    private String ownershipTypes;

    // account name
    @CsvBindByName(column="accountName")
    private String accountName;

    // opening balance
    @CsvBindByName(column="openingBalance")
    private String openingBalance;

}