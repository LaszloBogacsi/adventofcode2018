package main.java.twentynineteen.day3;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.*;
import static main.java.common.Util.readfile;

public class DAY3 {
    public static int [][] grid;
    public static List<List<List<Claim>>> claimGrid = new ArrayList<>();

    public static void main(String[] args) {

        // 1000 X 1000 grid
        grid = new int[1100][1100];
        final String path = "/Users/laszboga/learning/adventoOfCode/aoc/src/main/java/day3/input_day3.txt";
        final List<String> lines = readfile(path);
        final List<Claim> claims = createClaimsFrom(lines);
        initClaimGrid(claimGrid);
        claims.forEach(DAY3::addClaimToGrid);
        int overlaps = calculateOverlappingAreas(grid);
        List<String> idOfNonOverlappingArea = getIdOfNonOverlappingFabric(claimGrid);
        System.out.println("overlaps = " + overlaps);

    }

    private static void initClaimGrid(List<List<List<Claim>>> claimGrid) {
        for (int i = 0; i < claimGrid.size(); i++) {
            for (int j = 0; j < claimGrid.size(); j++) {
                List<Claim> emptyClaimList = new ArrayList<>();
                claimGrid.get(i).add(emptyClaimList);
                claimGrid.get(i).get(j);
            }

        }
    }

    private static List<String> getIdOfNonOverlappingFabric(List<List<List<Claim>>> grid) {
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.size(); j++) {
                if (grid.get(i).get(j).size() == 1) {
                    ids.add(grid.get(i).get(j).get(0).id);
                }
            }

        }
        return ids;
    }

    private static int calculateOverlappingAreas(int[][] grid) {
        int overlaps = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] > 1) {
                    overlaps++;
                }
            }

        }
        return overlaps;

    }

    private static void addClaimToGrid(Claim claim) {
        for (int i = claim.topLeftCoordinates.fromLeft; i < claim.topLeftCoordinates.fromLeft + claim.size.width; i++) {
            for (int j = claim.topLeftCoordinates.fromTop; j < claim.topLeftCoordinates.fromTop + claim.size.height; j++) {
                claimGrid.get(i).get(j).add(claim);
            }
        }
    }

    private static List<Claim> createClaimsFrom(List<String> lines) {
        return lines.stream().map(l -> {
            String id = l.replaceAll("#(\\d+)\\s+@.*", "$1");
            TopLeftCoordinates topLeftCoordinates = new TopLeftCoordinates(Integer.parseInt(l.replaceAll(".+@\\s+(\\d+),.+", "$1")), Integer.parseInt(l.replaceAll(".+,(\\d+):.+", "$1")));
            Size size = new Size(Integer.parseInt(l.replaceAll(".+:\\s+(\\d+)x.+", "$1")), Integer.parseInt(l.replaceAll(".+x(\\d+)$", "$1")));
            return new Claim(id, topLeftCoordinates, size);
        }).collect(toList());

    }


    private static class Claim {

        public final String id;
        public final TopLeftCoordinates topLeftCoordinates;
        public final Size size;

        private Claim(String id, TopLeftCoordinates topLeftCoordinates, Size size) {
            this.id = id;
            this.topLeftCoordinates = topLeftCoordinates;
            this.size = size;
        }
    }

    private static class TopLeftCoordinates {
        public final int fromTop;
        public final int fromLeft;

        private TopLeftCoordinates(int fromLeft, int fromTop) {
            this.fromTop = fromTop;
            this.fromLeft = fromLeft;
        }
    }

    private static class Size {
        public final int width;
        public final int height;

        private Size(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}
