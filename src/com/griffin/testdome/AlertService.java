package com.griffin.testdome;

import java.util.*;

/*
Design an interface for an alert storage system allowing creation and retrieval of date-based alerts.
Provide an implementation of the alert interface.
 */
class AlertService {

    private final AlertDAO storage;

    /*
    Construct an instance of the AlertService based on the AlertDAO interface:
     */
    public AlertService(AlertDAO alertDAO) {
        this.storage = alertDAO;
    }

    public UUID raiseAlert() {
        return this.storage.addAlert(new Date());
    }

    public Date getAlertTime(UUID id) {
        return this.storage.getAlert(id);
    }

    public static void main(String [] args) {
        AlertService alertService = new AlertService(new MapAlertDAO());
        alertService.testRunner();
    }

    public void testRunner() {
        List<UUID> uuidList = new ArrayList<>();

        // Create/retrieve sample alerts
        uuidList.add(raiseAlert());
        uuidList.add(raiseAlert());
        uuidList.add(raiseAlert());
        uuidList.add(raiseAlert());
        uuidList.add(raiseAlert());

        // Retrieve each UUID and check that AlertService recognizes it
        for (UUID uuid: uuidList) {
            if (getAlertTime(uuid) == null) {
                System.out.println("AlertService error cannot find UUID : " + uuid);
            }
        }
    }
}

/*
The simple alert data-access-object interface, which provides alert persistence.
 */
interface AlertDAO {
    UUID addAlert(Date time);
    Date getAlert(UUID id);
}

/*
Interface implementation.
 */
class MapAlertDAO implements AlertDAO {
    private final Map<UUID, Date> alerts = new HashMap<>();

    public UUID addAlert(Date time) {
        UUID id = UUID.randomUUID();
        this.alerts.put(id, time);
        return id;
    }

    public Date getAlert(UUID id) {
        return this.alerts.get(id);     // No mapping for id will return null
    }
}