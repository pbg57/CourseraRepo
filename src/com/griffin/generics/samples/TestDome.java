package com.griffin.generics.samples;

import java.util.*;


public class TestDome {
    public static void main(String[] args) {
        TestDome td = new TestDome();
        td.testRunner();
    }

    void testRunner() {

        MathUtils.main(null);
        /*

         */
        String[] names1 = new String[]{"Ava", "Emma", "Olivia"};
        String[] names2 = new String[]{"Olivia", "Sophia", "Emma"};
        System.out.println(String.join(", ", MergeNames.uniqueNames(names1, names2))); // should print Ava, Emma, Olivia, Sophia
        /*
         */

    }
    /*

     */

    /*

     */


    /*

     */
    public class MathUtils {
        public static double average(int a, int b) {
            return ((double) a + (double) b) / 2;
        }

        public static void main(String[] args) {
            System.out.println(average(2, 1));
        }
    }

    /*

     */
    class AlertService {
        private final AlertDAO storage;

        public AlertService(AlertDAO alertDAO) {
            storage = alertDAO;
        }

        public UUID raiseAlert() {
            return this.storage.addAlert(new Date());
        }

        public Date getAlertTime(UUID id) {
            return this.storage.getAlert(id);
        }
    }

    interface AlertDAO {

        public UUID addAlert(Date time);

        public Date getAlert(UUID id);
    }

    class MapAlertDAO implements AlertDAO {
        private final Map<UUID, Date> alerts = new HashMap<UUID, Date>();

        public UUID addAlert(Date time) {
            UUID id = UUID.randomUUID();
            this.alerts.put(id, time);
            return id;
        }

        public Date getAlert(UUID id) {
            return this.alerts.get(id);
        }
    }

    /*

     */
    public class MergeNames {

        public static String[] uniqueNames(String[] names1, String[] names2) {

            // join two arrays, removing duplicate names and return in string array
            // Use the Set class, which requires unique values, to create an intermediate container for the result

            Set<String> intermediateResult = new HashSet<>();

            intermediateResult.addAll(Arrays.asList(names1));

            intermediateResult.addAll(Arrays.asList(names2));

            return intermediateResult.toArray(new String[0]);
        }
    }

    /*

     */
}
