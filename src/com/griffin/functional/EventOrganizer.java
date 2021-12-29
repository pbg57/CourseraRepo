package com.griffin.functional;

import java.util.ArrayList;
import java.util.List;

public class EventOrganizer {

    public static void main(String [] args) {

        EventDispatcherClient eventDispatcherClient = new EventOrganizer.EventDispatcherClient();
        eventDispatcherClient.runTests(new EventOrganizer.EventDispatcher());

    }

    public void testEvent(Event event) {

        event.onEventOccurance();

    }

    public void testEventProcess(EventProcess eventProcess) {

        eventProcess.onEventOccuranceProcess(1);

    }

    public void testEventResult(EventResult eventResult) {

        int result = eventResult.onEventOccuranceConfirm(1);

        // printing the return value of

        System.out.println("Print result value : " + result);

    }

    // Annotation optional for SAMs
    @FunctionalInterface
    public interface Event {

        public void onEventOccurance();

    }


    public interface EventProcess {

        public void onEventOccuranceProcess(int i);

    }

    public interface EventResult {

        public int onEventOccuranceConfirm(int i);

    }

    public class EventOrganizerTest {

        public void testEvent(Event event) {

            event.onEventOccurance();

        }

        public void testEventProcess(EventProcess eventProcess) {

            eventProcess.onEventOccuranceProcess(1);

        }

        public void testEventResult(EventResult eventResult) {

            int result = eventResult.onEventOccuranceConfirm(1);

            // printing the return value of

            System.out.println("Print result value : " + result);

        }

    }

    public class EventClient {

        public static void main(String[] args) {

            EventOrganizer eventTester = new EventOrganizer();

            // calling no- argument functional interface

            eventTester.testEvent(() -> System.out

                    .println("event occurred : no argument syntax"));

            // Note: example of replacing Lambda with method reference
            eventTester.testEvent(System.out::flush);
            eventTester.testEvent(System.out::flush);


            // calling single- argument method of functional interface
            // Note that any var name may be used (using 'j' instead of 'i')
            eventTester.testEventProcess((j) -> System.out

                    .println("event processed : one argument syntax : " + j));

            // calling alternative single- argument method of functional interface. alternative

            // syntax of omitting parenthesies

            eventTester.testEventProcess(i -> System.out

                    .println("event processed : one argument syntax : " + i));

            // calling alternative single- argument method of functional interface. alternative

            // syntax of wrapping expression body with curly braces

            eventTester.testEventProcess((i) -> {

                System.out.println("event processed : one argument syntax : " + i);

            });

            // calling single- argument method of functional interface which that returns

            // the result

            eventTester.testEventResult((i) -> {

                i = i + 1;

                return i;

            });

        }

    }
    public static class EventDispatcher {

        private List<Event> eventList = new ArrayList<Event>();

        private List<EventProcess> eventProcessList = new ArrayList<EventProcess>();

        private List<EventResult> eventResultList = new ArrayList<EventResult>();

        public void registerEventHandler(Event event) {

            eventList.add(event);

        }

        public void registerEventProcessHandler(EventProcess eventProcess) {

            eventProcessList.add(eventProcess);

        }

        public void registerEventResultHandler(EventResult eventResult) {

            eventResultList.add(eventResult);

        }

        public void dispatchEvent() {

            for (Event event : eventList) {

                event.onEventOccurance();

            }

        }

        public void processEvents() {

            int i = 1;

            for(EventProcess process : eventProcessList) {

                process.onEventOccuranceProcess(i++);

            }

        }



        public void compute() {

            final int i = 2;

            for(EventResult process : eventResultList) {

                int result = process.onEventOccuranceConfirm(i);

                System.out.println("return result : "+ result);

            }

        }

    }

    public static class EventDispatcherClient {

        public void runTests (EventDispatcher eventDispatcher) {

        //    EventDispatcher eventDispatcher = new EventDispatcher();

            // define no- argument functional interface

            Event event1 = () -> System.out.println("event 1 occurred : no argument syntax");

            Event event2 = () -> System.out.println("event 2 occurred : no argument syntax");



            // define single- argument method of functional interface

            EventProcess process1 = (i) -> System.out.println("event processed : one argument syntax : "+i);

            // define alternative single- argument method of functional interface. alternative

            // syntax of omitting parenthesies

            EventProcess process2 = i -> System.out.println("event processed : one argument syntax : "+i);

            // define alternative single- argument method of functional interface. alternative

            // syntax of wrapping expression body with curly braces

            EventProcess process3 = i -> {

                System.out.println("event processed : one argument syntax : "+i);

            };

            // define single- argument method of functional interface which that returns

            // the result

            EventResult result1 = i -> { i = i + 2; return i; };

            EventResult result2 = i -> { i = i - 2; return i; };

            EventResult result3 = i -> { i = i * 2; return i; };

            EventResult result4 = i -> { i = i / 2; return i; };

            EventResult result5 = i -> { i = i % 2; return i; };



            // registering events

            eventDispatcher.registerEventHandler(event1);

            eventDispatcher.registerEventHandler(event2);

            eventDispatcher.dispatchEvent();

            System.out.println("");

            // registering event processes

            eventDispatcher.registerEventProcessHandler(process1);

            eventDispatcher.registerEventProcessHandler(process2);

            eventDispatcher.registerEventProcessHandler(process3);

            eventDispatcher.processEvents();

            System.out.println("");



            // registering result- based events

            eventDispatcher.registerEventResultHandler(result1);

            eventDispatcher.registerEventResultHandler(result2);

            eventDispatcher.registerEventResultHandler(result3);

            eventDispatcher.registerEventResultHandler(result4);

            eventDispatcher.registerEventResultHandler(result5);

            eventDispatcher.compute();

        }

    }

}
