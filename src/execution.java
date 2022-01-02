import java.util.Scanner;
import java.util.ArrayList;

public class execution {
    static boolean naive;
    static int numberOfRingProcesses;
    static ArrayList<String> ringProcesses;
    static ArrayList<Integer> estimatedRingProcessesTime;
    static ArrayList<process> processes;
    static int timeOfCycle;

    public static void main(String[] args) throws Exception {
        // scanner for user input
        Scanner scanner = new Scanner(System.in);
        boolean areAllProcessesDone = false;
        int quantum;
        int aliveProcesses = 0;
        int currentCycle = 0;
        int totalCycle = 0;
        int totalRuntime = 0;

        // initialize arraylists
        ringProcesses =  new ArrayList<String>();
        estimatedRingProcessesTime = new ArrayList<Integer>();
        processes = new ArrayList<process>();

        // check if the execution should be naive
        System.out.println("Do you want the execution to be naive? (y for yes, anything else for no)");
        String naiveString = scanner.nextLine();
        String naiveMessage = "y";
        naive = false;
        if (naiveString.equals(naiveMessage)) {
            naive = true;
        }

        // get the number of ring processes
        System.out.println("Enter the number of ring processes");
        numberOfRingProcesses = scanner.nextInt();

        // get the processes names and their estimated ring process time
        for (int i = 0; i < numberOfRingProcesses; i++) {
            System.out.println("Enter the process name for process number " + (i + 1));
            ringProcesses.add(scanner.next());

            System.out.println("Enter the estimated ring process time for process number " + (i + 1) + " in milliseconds");
            estimatedRingProcessesTime.add(scanner.nextInt());
        }

        // Get the time of cycle
        System.out.println("Enter the time of cycle");
        timeOfCycle = scanner.nextInt();

        // close the scanner
        scanner.close();

        // create the processes
        for (int i = 0; i < numberOfRingProcesses; i++) {
            processes.add(new process(ringProcesses.get(i), estimatedRingProcessesTime.get(i)));
        }

        // start execution
        while (!areAllProcessesDone){
            // check amount of live processes
            for (process process : processes){
                if (process.getEstimatedExecutionTime() != 0){
                    aliveProcesses++;
                }
            }

            // check if there are no more live processes
            if (aliveProcesses == 0) {
                areAllProcessesDone = true;
                break;
            }

            // update current cycle
            currentCycle++;

            // check if naive
            if (naive) {
                quantum = timeOfCycle / numberOfRingProcesses;
            }

            else {
                quantum = timeOfCycle / aliveProcesses;
            }

            // print every detail of the current execution loop instance
            System.out.println("There are " + aliveProcesses + " processes running");
            System.out.println("Each process got " + quantum + " milliseconds");

            // execute the processes and print out process info
            for (process process : processes){
                // has the process been finished in this loop instance?
                if (process.getEstimatedExecutionTime() < quantum && process.getEstimatedExecutionTime() != 0) {
                    totalCycle += process.getEstimatedExecutionTime();
                    System.out.println("The process " + process.getProcessName() + " started the execution needing " + (process.getEstimatedExecutionTime()) + " milliseconds");
                    process.setEstimatedExecutionTime(0);
                    process.setCycle(currentCycle);
                    System.out.println("The process has now completed execution");
                }

                // is the process still running?
                else if (process.getEstimatedExecutionTime() != 0) {
                    totalCycle += process.getEstimatedExecutionTime() - quantum;
                    System.out.println("The process " + process.getProcessName() + " started the execution needing " + (process.getEstimatedExecutionTime()) + " milliseconds");
                    process.setEstimatedExecutionTime(process.getEstimatedExecutionTime() - quantum);
                    process.setCycle(currentCycle);
                    System.out.println("It now needs only " + process.getEstimatedExecutionTime() + " milliseconds to complete the execution");
                }

                // is the process already finished?
                else{
                    System.out.println("The process " + process.getProcessName() + " is no longer running");
                }
            }

            // print out the slice number
            System.out.println("This was slice number " + currentCycle + ", it took " + totalCycle + " milliseconds to complete");

            // add the value of total cycle to total runtime
            totalRuntime += totalCycle;

            // reset the alive processes counter and total cycle
            aliveProcesses = 0;
            totalCycle = 0;
        }

        // print complete execution message
        System.out.println("Execution has completed in " + totalRuntime + " milliseconds");
        System.out.println("The average execution time of this ring was " + totalRuntime / numberOfRingProcesses + " milliseconds per process");
    }
}
