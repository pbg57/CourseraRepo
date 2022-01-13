package com.griffin.functional;

import java.util.ArrayList;
import java.util.List;

//
// This code was copied from a course example and modified here.
//
public class EventOrganizer {

    public static void main(String[] args) {

        EventDispatcherClient eventDispatcherClient = new EventOrganizer.EventDispatcherClient();
        eventDispatcherClient.runTests(new EventOrganizer.EventDispatcher());

    }


    // Annotation optional for SAMs
    @FunctionalInterface
    public interface Event {

        void onEventOccurrence();

    }

    @FunctionalInterface
    public interface EventProcess {

        void onEventOccurrenceProcess(int i);

    }

    @FunctionalInterface
    public interface EventResult {

        int onEventOccurrenceConfirm(int i);

    }


    public static class EventDispatcher {

        // Store the dispatched events in the static class's List objects.

        private final List<Event> eventList = new ArrayList<>();

        private final List<EventProcess> eventProcessList = new ArrayList<>();

        private final List<EventResult> eventResultList = new ArrayList<>();

        public void registerEventHandler(Event event) {

            eventList.add(event);

        }

        public void registerEventProcessHandler(EventProcess eventProcess) {

            eventProcessList.add(eventProcess);

        }

        public void registerEventResultHandler(EventResult eventResult) {

            eventResultList.add(eventResult);

        }

        // Iterate over the list, calling the SAM implementation.

        public void dispatchEvent() {

            for (Event event : eventList) {

                event.onEventOccurrence();

            }

        }

        // Iterate over the list, calling the SAM implementation.

        public void processEvents() {

            int i = 1;

            for (EventProcess process : eventProcessList) {

                process.onEventOccurrenceProcess(i++);

            }

        }

        // Iterate over the list, calling the SAM implementation.

        public void compute() {

            final int i = 2;

            for (EventResult process : eventResultList) {

                int result = process.onEventOccurrenceConfirm(i);

                System.out.println("return result : " + result);

            }

        }

    }

    public static class EventDispatcherClient {

        public void runTests(EventDispatcher eventDispatcher) {


            // Define no-argument functional interface
            // Event is a functional i/f whose SAM takes no args and returns void.
            // The Event i/f instance event1 is implemented here by simply supplying a Lambda expression
            // taking no args and returning void.

            Event event1 = () -> System.out.println("event 1 occurred : no argument syntax");

            Event event2 = () -> System.out.println("event 2 occurred : no argument syntax");


            // Define single- argument method of functional interface
            // EventProcess is a functional i/f whose SAM takes one arg and returns void.
            // The EventProcess i/f instance process1 is implemented here by simply supplying a Lambda expression
            // taking one arg and returning void.
            // Note that the single arg var name can be defined here in the impl (i.e. can be i, j, jj, etc.)

            EventProcess process1 = (jj) -> System.out.println("event processed : one argument syntax : " + jj);

            // Use alternative syntax to define the Lambda. Alternative syntax is omitting parentheses.

            EventProcess process2 = i -> System.out.println("event processed : one argument syntax : " + i);

            // Use alternative syntax to define the Lambda. Alternative syntax is wrapping Lambda's expression
            // body with curly braces.

            EventProcess process3 = i -> {

                System.out.println("event processed : one argument syntax : " + i);

            };

            // Define single- argument method of functional interface which also returns a non-void result

            EventResult result1 = i -> {
                i = i + 2;
                return i;
            };

            EventResult result2 = i -> {
                i = i - 2;
                return i;
            };

            EventResult result3 = i -> {
                i = i * 2;
                return i;
            };

            EventResult result4 = i -> {
                i = i / 2;
                return i;
            };

            EventResult result5 = i -> {
                i = i % 2;
                return i;
            };


            // registering events. The EventDispatcher stores the Event events.

            eventDispatcher.registerEventHandler(event1);

            eventDispatcher.registerEventHandler(event2);

            // dispatchEvent() iterates over the stored events and calls its SAM method.
            eventDispatcher.dispatchEvent();

            System.out.println();

            // registering event processes.  The EventDispatcher stores the EventProcess events.

            eventDispatcher.registerEventProcessHandler(process1);

            eventDispatcher.registerEventProcessHandler(process2);

            eventDispatcher.registerEventProcessHandler(process3);

            // processEvent() iterates over the stored events and calls its SAM method.

            eventDispatcher.processEvents();

            System.out.println();


            // registering result- based events. The EventDispatcher stores the EventResult events.

            eventDispatcher.registerEventResultHandler(result1);

            eventDispatcher.registerEventResultHandler(result2);

            eventDispatcher.registerEventResultHandler(result3);

            eventDispatcher.registerEventResultHandler(result4);

            eventDispatcher.registerEventResultHandler(result5);

            // compute() iterates over the stored events and calls its SAM method.

            eventDispatcher.compute();

        }

    }

}

