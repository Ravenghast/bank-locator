package com.mybank.service;

import com.mybank.model.*;
import java.util.*;

public class LocatorService {

    public List<LocationWrapper> getLocations(String postCode, String serviceType) throws Exception {
        if (postCode == null || !isValidPostCode(postCode)) {
            throw new IllegalArgumentException("Invalid PostCode");
        }
        if (serviceType == null || !isValidServiceType(serviceType)) {
            throw new IllegalArgumentException("Invalid ServiceType: should be BRANCH, ATM, or BRANCH_and_ATM");
        }
        return findNearestLocations(postCode, serviceType);
    }

    private List<LocationWrapper> findNearestLocations(String postCode, String serviceType) throws Exception {
        LocationConnector connector = LocationConnector.getInstance();
        List<LocationWrapper> wrapper = connector.findNearestLocations(postCode, serviceType);
        if (wrapper == null || wrapper.isEmpty()) {
            wrapper = getNotMatchingWrapper();
        }
        return wrapper;
    }

    private List<LocationWrapper> getNotMatchingWrapper() {
        LocationWrapper wrapper = new LocationWrapper();
        wrapper.setNotes("No matching locations for this service within 20 miles of your location. Please try a different location");
        return Arrays.asList(wrapper);
    }

    private boolean isValidPostCode(String postCode) {
        String POSTCODE_REGEX = "([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9][A-Za-z]?))))\\s?[0-9][A-Za-z]{2})";
        return postCode.matches(POSTCODE_REGEX);
    }

    private boolean isValidServiceType(String serviceType) {
        ServiceType.valueOf(serviceType);
        return true;
    }

}
