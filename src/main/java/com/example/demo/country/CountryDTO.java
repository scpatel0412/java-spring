    package com.example.demo.country;

    import java.util.List;
import java.util.Map;

    public class CountryDTO {
        private String cca2;
        private String cca3;
        private String ccn3;
        private String countryCode;
        private String region;
        private List<String> capital;
        private String flag;
        private Map<String,String> languages;
        // private Int area;
        // private Map<String, String> translations;
        private Name name;
        private int population;
        private Integer area;
        private Map<String, Currency> currencies; // Added field for currencies
        private List<Double> latlng; // Added field for latlng
        private Maps maps; // Added field for maps (including Google Maps)
        private List<String> timezones; // Added field for timezones
        private List<String> continents; // Added field for continents
        private Flags flags; // Added field for flags (includes flag image URL)

        // Getters and Setters for all fields

        public String getCca2() {
            return cca2;
        }

        public void setCca2(String cca2) {
            this.cca2 = cca2;
        }

        public String getCca3() {
            return cca3;
        }

        public void setCca3(String cca3) {
            this.cca3 = cca3;
        }

        public String getCcn3() {
            return ccn3;
        }

        public void setCcn3(String ccn3) {
            this.ccn3 = ccn3;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public List<String> getCapital() {
            return capital;
        }

        public void setCapital(List<String> capital) {
            this.capital = capital;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public Map<String,String> getLanguages() {
            return languages;
        }

        public void setLanguages(Map<String,String> languages) {
            this.languages = languages;
        }

        // public Map<String, String> getTranslations() {
        //     return translations;
        // }

        // public void setTranslations(Map<String, String> translations) {
        //     this.translations = translations;
        // }

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        public Integer getArea() {
            return area;
        }

        public void setArea(Integer area) {
            this.area = area;
        }

        public Map<String, Currency> getCurrencies() {
            return currencies;
        }

        public void setCurrencies(Map<String, Currency> currencies) {
            this.currencies = currencies;
        }

        public List<Double> getLatlng() {
            return latlng;
        }

        public void setLatlng(List<Double> latlng) {
            this.latlng = latlng;
        }

        public Maps getMaps() {
            return maps;
        }

        public void setMaps(Maps maps) {
            this.maps = maps;
        }

        public List<String> getTimezones() {
            return timezones;
        }

        public void setTimezones(List<String> timezones) {
            this.timezones = timezones;
        }

        public List<String> getContinents() {
            return continents;
        }

        public void setContinents(List<String> continents) {
            this.continents = continents;
        }

        public Flags getFlags() {
            return flags;
        }

        public void setFlags(Flags flags) {
            this.flags = flags;
        }
        

        // Inner classes to map additional fields
        public static class Name {
            private String common;
            private String official;
        
            public String getCommon() {
                return common;
            }
        
            public void setCommon(String common) {
                this.common = common;
            }
        
            public String getOfficial() {
                return official;
            }
        
            public void setOfficial(String official) {
                this.official = official;
            }
        
            @Override
            public String toString() {
                return "Name{" +
                        "common:'" + common + '\'' +
                        ", official:'" + official + '\'' +
                        '}';
            }
        }
        
        public static class Currency {
            private String name;
            private String symbol;
        
            // Getters and Setters
            public String getName() {
                return name;
            }
        
            public void setName(String name) {
                this.name = name;
            }
        
            public String getSymbol() {
                return symbol;
            }
        
            public void setSymbol(String symbol) {
                this.symbol = symbol;
            }
        
            @Override
            public String toString() {
                return "Currency{" +
                        "name:'" + name + '\'' +
                        ", symbol:'" + symbol + '\'' +
                        '}';
            }
        }
        
        public static class Maps {
            private String googleMaps;
        
            public String getGoogleMaps() {
                return googleMaps;
            }
        
            public void setGoogleMaps(String googleMaps) {
                this.googleMaps = googleMaps;
            }
        
            @Override
            public String toString() {
                return "Maps{" +
                        "googleMaps:'" + googleMaps + '\'' +
                        '}';
            }
        }
        
        public static class Flags {
            private String png;
            private String svg;
        
            public String getPng() {
                return png;
            }
        
            public void setPng(String png) {
                this.png = png;
            }
        
            public String getSvg() {
                return svg;
            }
        
            public void setSvg(String svg) {
                this.svg = svg;
            }
        
            @Override
            public String toString() {
                return "Flags{" +
                        "png:'" + png + '\'' +
                        ", svg:'" + svg + '\'' +
                        '}';
            }
        }
        
    }
