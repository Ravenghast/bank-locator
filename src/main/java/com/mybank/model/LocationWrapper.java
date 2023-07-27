package com.mybank.model;

import lombok.Getter;
import lombok.Setter;

public class LocationWrapper {

    private @Getter @Setter String name;
    private @Getter @Setter ServiceType type;
    private @Getter @Setter String address;
    private @Getter @Setter String distance;
    private @Getter @Setter String latitude;
    private @Getter @Setter String longitude;
    private @Getter @Setter String telephone;
    private @Getter @Setter String timings;
    private @Getter @Setter String notes;

}
