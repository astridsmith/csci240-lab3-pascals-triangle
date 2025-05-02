import java.util.ArrayList;

public class Triangle {
    private ArrayList<ArrayList<Integer>> triangleRows = new ArrayList<>();


    public Triangle buildTriangle(int numberOfRows){
        this.triangleRows = populateTriangle(numberOfRows);
    }

    private ArrayList<ArrayList<Integer>> populateTriangle(int numberOfRows) {
        ArrayList<ArrayList<Integer>> arrayListToPopulate = new ArrayList<>();
        for (int i = 0; i < numberOfRows; i++){
            ArrayList<Integer> thisRow = new ArrayList<Integer>();
            // TODO: Calculate the contents of each row
        }
    }
}
