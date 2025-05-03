import java.util.ArrayList;
import java.util.Arrays;

public class Triangle {
    public static int testSuite(int n){
        return computeNthRowIter(n);
    }

    private static int computeNthRowRec(int n){
        // base case:
        if (n == 1){ // 1st row
            return 1;
        } else {
            // compute the nth row using the sum of the previous row
            return (2 * computeNthRowRec(n - 1));
        }
    }
    private static int computeNthRowIter(int n){
        int debugRowCnt = 0;
        boolean hasFirstRowBeenAdded = false;
        int toReturn = -1;
        if (n == 1){
            toReturn = 1;
        } else {
            ArrayList<Integer> rowAContents = new ArrayList<Integer>();
            ArrayList<Integer> rowBContents = new ArrayList<Integer>();
            char rowStatus = ' '; // blank value, should not be read before other logic changes it to a letter (A or B) - initializing it with SPACE to avoid compiler error.
            for (int i = 0; i < n; i++){
                System.out.println("Running main loop...");
                System.out.println("DEBUG: i:" + i + " n: " + n);




                int thisRowSum = 0;
                if (!hasFirstRowBeenAdded){
                    System.out.println("DEBUG: Adding first row...");




                    debugRowCnt++;





                    // set up:
                    thisRowSum = 1;
                    rowAContents.addAll(Arrays.asList(0, 1, 0)); // Arrays.asList allows multi-adding
                    hasFirstRowBeenAdded = true;
                    rowStatus = 'B'; // moved initial declaration to here in case rowStatus gets stuck on "A" during initial declaration
                } else if (hasFirstRowBeenAdded) {
                    switch (rowStatus){
                        case 'A':
                            debugRowCnt++;
                            System.out.println("DEBUG: Hit A!");
                            // case A code
                            // ensure emptyness:
                            rowAContents.clear();
                            // first, add a 0 to the left side of the row:
                            rowAContents.add(0);
                            // next, sum first 2 digits of the row:
                            int aPairIndexA = 0; // selectors for the pair
                            int aPairIndexB = 1;



                            System.out.println("Row A Contents array length: " + rowAContents.size());




                            for (int j = 0; j < (rowBContents.size() - 1); j++){ // move across row B and add to make row A
                                rowAContents.add(rowBContents.get(aPairIndexA) + rowBContents.get(aPairIndexB));

                                // TODO: Remove debug printer
                                int debugBAddResult = (rowAContents.get(aPairIndexA) + rowAContents.get(aPairIndexB));
                                System.out.println("[DEBUG] Case A Adding Result Was: " + debugBAddResult);
                                System.out.println("DEBUG: Now, row A contents are: " + rowAContents.toString());

                                aPairIndexA++; aPairIndexB++; // move the selectors to the next position
                            }
                            // Add 0 to end of the row:
                            rowAContents.add(0); // this sucker was my final bug!!!! I had rowAContents set as rowBContents, sending my endcap zero into a black hole and messing up my math lol. So glad I'm done!!!!

                            System.out.println("DEBUG: FINALLY, case A row is: " + rowAContents);


                            // sum the row:
                            for (int k = 0; k < rowBContents.size(); k++){
                                thisRowSum = thisRowSum + rowBContents.get(k);
                            }
                            System.out.println("[DEBUG] This row sum: " + thisRowSum);
                            System.out.println("[DEBUG] Checking if this row is the final row...");
                            if ((i + 1) == n){
                                System.out.println("DEBUG: Final row!");
                                toReturn = thisRowSum;
                            } else { // if not, switch to the other row and repeat!
                                rowStatus = 'B';
                                System.out.println("DEBUG: Switching to case B...");
                            }
                            break;
                        case 'B':
                            debugRowCnt++;
                            System.out.println("DEBUG: Hit B!");
                            // case B code
                            rowBContents.clear();
                            // first, add a 0 to the left side of the row:
                            rowBContents.add(0);
                            // next, sum first 2 digits of the row:
                            int bPairIndexA = 0; // selectors for the pair
                            int bPairIndexB = 1;
                            for (int j = 0; j < (rowAContents.size() - 1); j++){ // move across row A and add to make row B
                                rowBContents.add(rowAContents.get(bPairIndexA) + rowAContents.get(bPairIndexB));

                                // TODO: Remove debug printer
                                int debugBAddResult = (rowAContents.get(bPairIndexA) + rowAContents.get(bPairIndexB));
                                System.out.println("[DEBUG] Case B Adding Result Was: " + debugBAddResult);

                                System.out.println("DEBUG: Now, case B row is: " + rowBContents);


                                bPairIndexA++; bPairIndexB++; // move the selectors to the next position
                            }
                            // Add 0 to end of the row:
                            rowBContents.add(0);


                            System.out.println("DEBUG: FINALLY, case B row is: " + rowBContents);


                            // sum the row:
                            for (int k = 0; k < rowBContents.size(); k++){
                                thisRowSum = thisRowSum + rowBContents.get(k);
                            }
                            System.out.println("[DEBUG] This row sum: " + thisRowSum);
                            System.out.println("[DEBUG] Checking if this row is the final row...");
                            if ((i + 1) == n){
                                System.out.println("DEBUG: Final row!");
                                toReturn = thisRowSum;
                            } else { // if not, switch to the other row and repeat!
                                System.out.println("DEBUG: Switching to case A...");
                                rowStatus = 'A';
                            }
                            break;
                        default:
                            throw new RuntimeException("Logic Error: First row was not initialized correctly!");
                    }
                } else {throw new RuntimeException("Logic Error: could not determine first row status!");}

                // return result
               // return toReturn;
            }
        }
        // I hate that I didn't have the foresight to design this differently, and I hate what I have written as a result:
        if (toReturn == -1){
            throw new RuntimeException("Critical error: no value can return, calculation failed!");
        }

        System.out.println("Final Row Count: " + debugRowCnt);

        return toReturn;
    }

}
