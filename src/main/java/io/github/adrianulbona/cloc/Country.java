package io.github.adrianulbona.cloc;

import java.util.Objects;

public final class Country implements Comparable<Country> {
    public final String name;
    public final String iso2;
    public final String iso3;
    public final String isoId;

    public Country(final String name, final String iso2, final String iso3, final String isoId) {
        this.name = name;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.isoId = isoId;
    }

    public String getName() {
        return name;
    }

    public String getIso2() {
        return iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public String getIsoId() {
        return isoId;
    }

    @Override
    public int compareTo(Country that) {
        return this.name.compareTo(that.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name) &&
                Objects.equals(iso2, country.iso2) &&
                Objects.equals(iso3, country.iso3) &&
                Objects.equals(isoId, country.isoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, iso2, iso3, isoId);
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\":\"" + name + "\"," +
                "\"iso2\":\"" + iso2 + "\"," +
                "\"iso3\":\"" + iso3 + "\"," +
                "\"isoId\":" + isoId +
                '}';
    }
}
