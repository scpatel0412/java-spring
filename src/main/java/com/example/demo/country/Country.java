package com.example.demo.country;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "official_name", nullable = false)
    private String officialName;

    @Column(name = "cca2_code", nullable = false)
    private String cca2Code;

    @Column(name = "ccn3_code", nullable = false)
    private String ccn3Code;

    @Column(nullable = false)
    private String currencies; 

    @Column(nullable = false)
    private String capital; 

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String languages; 

    @Column(name = "latlng", nullable = false)
    private String latlng; 

    @Column(name = "area_code", nullable = false)
    private Integer areaCode;

    @Column(name = "googlemaps", nullable = false)
    private String googlemaps;

    @Column(nullable = false)
    private Integer population;

    @Column(nullable = false)
    private String timezone; 

    @Column(nullable = false)
    private String continent;

    @Column(nullable = false)
    private String flag;

    @Column(name = "created_at", updatable = false)
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

    @Column(name = "deleted_at")
    private java.sql.Timestamp deletedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getCca2Code() {
        return cca2Code;
    }

    public void setCca2Code(String cca2Code) {
        this.cca2Code = cca2Code;
    }

    public String getCcn3Code() {
        return ccn3Code;
    }

    public void setCcn3Code(String ccn3Code) {
        this.ccn3Code = ccn3Code;
    }

    public String getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String currencies) {
        this.currencies = currencies;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getLatlng() {
        return latlng;
    }

    public void setLatlng(String latlng) {
        this.latlng = latlng;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public String getGooglemaps() {
        return googlemaps;
    }

    public void setGooglemaps(String googlemaps) {
        this.googlemaps = googlemaps;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public java.sql.Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public java.sql.Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.sql.Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public java.sql.Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(java.sql.Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    

    @Override
    public String toString() {
        String currenciesString = currencies != null ? currencies.toString() : "No currencies";
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", officialName='" + officialName + '\'' +
                ", cca2Code='" + cca2Code + '\'' +
                ", ccn3Code='" + ccn3Code + '\'' +
                ", currencies='" + currenciesString + '\'' +
                ", capital='" + capital + '\'' +
                ", region='" + region + '\'' +
                ", languages='" + languages + '\'' +
                ", latlng='" + latlng + '\'' +
                ", areaCode=" + areaCode +
                ", googlemaps='" + googlemaps + '\'' +
                ", population=" + population +
                ", timezone='" + timezone + '\'' +
                ", continent='" + continent + '\'' +
                ", flag='" + flag + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
