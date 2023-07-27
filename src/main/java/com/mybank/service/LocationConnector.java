package com.mybank.service;

import com.mybank.model.LocationWrapper;
import com.mybank.model.ServiceType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LocationConnector {

    private HashMap<String, LocationWrapper> branchLocationDB = new HashMap<>();
    private HashMap<String, LocationWrapper> atmLocationDB = new HashMap<>();
    private HashMap<String, LocationWrapper> bothLocationDB = new HashMap<>();
    private static LocationConnector connector;

    private LocationConnector() throws Exception {
        loadLocations();
    }

    public static LocationConnector getInstance() throws Exception {
        if (connector == null) {
            connector = new LocationConnector();
        }
        return connector;
    }

    public List<LocationWrapper> findNearestLocations(String postCode, String serviceType) {
        List<LocationWrapper> list = new ArrayList<>();
        LocationWrapper mainWrapper = getExactMatch(postCode, serviceType);
        if (mainWrapper != null) {
            list.add(mainWrapper);
        }
        List<LocationWrapper> otherMatches = getWithinRange(postCode, serviceType);
        if (!otherMatches.isEmpty()) {
            list.addAll(otherMatches);
        }
        return list;
    }

    private LocationWrapper getExactMatch(String postCode, String serviceType) {
        String prefix = postCode.substring(0, 2);
        String lastChar = prefix.substring(1,2);
        try {
            Integer.parseInt(lastChar);
            prefix = prefix.substring(0, 1);
        }
        catch (NumberFormatException e) {
            // 3 char post code
        }
        LocationWrapper wrapper = null;
        if (ServiceType.valueOf(serviceType) == ServiceType.BRANCH) {
            wrapper = branchLocationDB.get(prefix);
        }
        else if (ServiceType.valueOf(serviceType) == ServiceType.ATM) {
            wrapper = atmLocationDB.get(prefix);
        }
        else if (ServiceType.valueOf(serviceType) == ServiceType.BRANCH_AND_ATM) {
            wrapper = bothLocationDB.get(prefix);
        }
        if (wrapper != null) {
            wrapper.setDistance("1.5 miles");
        }
        return wrapper;
    }

    private List<LocationWrapper> getWithinRange(String postCode, String serviceType) {
        List<LocationWrapper> list = new ArrayList<>();
        switch (postCode.charAt(0)) {
            case 'A':
            case 'D':
            case 'F':
                if (ServiceType.valueOf(serviceType) == ServiceType.BRANCH) {
                    LocationWrapper w1 = branchLocationDB.get("EC");
                    w1.setDistance("12.3 miles");
                    list.add(w1);
                    LocationWrapper w2 = branchLocationDB.get("BN");
                    w2.setDistance("17.6 miles");
                    list.add(w2);
                }
                else if (ServiceType.valueOf(serviceType) == ServiceType.ATM) {
                    LocationWrapper w1 = branchLocationDB.get("W");
                    w1.setDistance("2.8 miles");
                    list.add(w1);
                    LocationWrapper w2 = branchLocationDB.get("BN");
                    w2.setDistance("17.6 miles");
                    list.add(w2);
                }
                else if (ServiceType.valueOf(serviceType) == ServiceType.BRANCH_AND_ATM) {
                    LocationWrapper w1 = branchLocationDB.get("BN");
                    w1.setDistance("17.6 miles");
                    list.add(w1);
                }
                break;
            case 'H':
            case 'I':
            case 'J':
            case 'K':
                if (ServiceType.valueOf(serviceType) == ServiceType.BRANCH) {
                    LocationWrapper w1 = branchLocationDB.get("B");
                    w1.setDistance("8.2 miles");
                    list.add(w1);
                    LocationWrapper w2 = branchLocationDB.get("S");
                    w2.setDistance("9.4 miles");
                    list.add(w2);
                    LocationWrapper w3 = branchLocationDB.get("BN");
                    w3.setDistance("19.1 miles");
                    list.add(w3);
                }
                else if (ServiceType.valueOf(serviceType) == ServiceType.ATM) {
                    LocationWrapper w1 = branchLocationDB.get("CO");
                    w1.setDistance("17.5 miles");
                    list.add(w1);
                    LocationWrapper w2 = branchLocationDB.get("S");
                    w2.setDistance("9.4 miles");
                    list.add(w2);
                    LocationWrapper w3 = branchLocationDB.get("BN");
                    w3.setDistance("19.1 miles");
                    list.add(w3);
                }
                else if (ServiceType.valueOf(serviceType) == ServiceType.BRANCH_AND_ATM) {
                    LocationWrapper w1 = branchLocationDB.get("S");
                    w1.setDistance("9.4 miles");
                    list.add(w1);
                    LocationWrapper w2 = branchLocationDB.get("BN");
                    w2.setDistance("19.1 miles");
                    list.add(w2);
                }
                break;
            case 'N':
            case 'O':
            case 'P':
            case 'R':
            case 'T':
            case 'U':
                if (ServiceType.valueOf(serviceType) == ServiceType.BRANCH) {
                    LocationWrapper w1 = branchLocationDB.get("G");
                    w1.setDistance("16.8 miles");
                    list.add(w1);
                }
                else if (ServiceType.valueOf(serviceType) == ServiceType.ATM) {
                    LocationWrapper w1 = branchLocationDB.get("G");
                    w1.setDistance("16.8 miles");
                    list.add(w1);
                }
                else if (ServiceType.valueOf(serviceType) == ServiceType.BRANCH_AND_ATM) {
                    LocationWrapper w1 = branchLocationDB.get("G");
                    w1.setDistance("16.8 miles");
                    list.add(w1);
                }
                break;
            case 'B':
            case 'C':
            case 'E':
            case 'G':
                if (ServiceType.valueOf(serviceType) == ServiceType.BRANCH) {
                    LocationWrapper w1 = branchLocationDB.get("S");
                    w1.setDistance("14.3 miles");
                    list.add(w1);
                }
                else if (ServiceType.valueOf(serviceType) == ServiceType.ATM) {
                    LocationWrapper w1 = branchLocationDB.get("S");
                    w1.setDistance("14.3 miles");
                    list.add(w1);
                }
                else if (ServiceType.valueOf(serviceType) == ServiceType.BRANCH_AND_ATM) {
                    LocationWrapper w1 = branchLocationDB.get("S");
                    w1.setDistance("14.3 miles");
                    list.add(w1);
                }
                break;
            case 'L':
            case 'M':
            case 'S':
                if (ServiceType.valueOf(serviceType) == ServiceType.BRANCH) {
                    LocationWrapper w1 = branchLocationDB.get("BN");
                    w1.setDistance("10.5 miles");
                    list.add(w1);
                }
                else if (ServiceType.valueOf(serviceType) == ServiceType.ATM) {
                    LocationWrapper w1 = branchLocationDB.get("BN");
                    w1.setDistance("10.5 miles");
                    list.add(w1);
                }
                else if (ServiceType.valueOf(serviceType) == ServiceType.BRANCH_AND_ATM) {
                    LocationWrapper w1 = branchLocationDB.get("BN");
                    w1.setDistance("10.5 miles");
                    list.add(w1);
                }
                break;
        }
        return list;
    }

    private void loadLocations() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/banklocations.db"));
        String line;
        while(true) {
            line = reader.readLine();
            if (line == null) {
                break;
            }
            String[] elements = line.split(";");
            LocationWrapper wrapper = new LocationWrapper();
            wrapper.setName(elements[1].trim());
            wrapper.setType(ServiceType.valueOf(elements[2].trim()));
            wrapper.setAddress(elements[3].trim());
            wrapper.setTelephone(elements[4].trim());
            wrapper.setLatitude(elements[5].trim());
            wrapper.setLongitude(elements[6].trim());
            wrapper.setTimings(elements[7].trim());
            wrapper.setNotes(elements[8].trim());
            if (wrapper.getType() == ServiceType.BRANCH) {
                branchLocationDB.put(elements[0].trim(),wrapper);
            }
            else if (wrapper.getType() == ServiceType.ATM) {
                atmLocationDB.put(elements[0].trim(),wrapper);
            }
            else if (wrapper.getType() == ServiceType.BRANCH_AND_ATM) {
                branchLocationDB.put(elements[0].trim(),wrapper);
                atmLocationDB.put(elements[0].trim(),wrapper);
                bothLocationDB.put(elements[0].trim(),wrapper);
            }
            System.out.println("Loaded "+elements[0]);
        }
    }

}
