import model.SegmentType;
import model.Shuttle;
import model.ShuttleState;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final List<Shuttle> shuttles = new ArrayList<>();

    public static void main(String[] args) {
        generateShuttles();

        transportShuttles();
    }

    private static void generateShuttles() {
        shuttles.add(new Shuttle(ShuttleState.NORMAL));
        shuttles.add(new Shuttle(ShuttleState.OVERLOADED));
        shuttles.add(new Shuttle(ShuttleState.NORMAL));
        shuttles.add(new Shuttle(ShuttleState.UNDERLOADED));
        shuttles.add(new Shuttle(ShuttleState.OVERLOADED));
    }

    private static boolean hasClearPath(Shuttle verifiedShuttle) {
        int ahead = 0;
        for (Shuttle shuttle : shuttles) {
            if (verifiedShuttle.getLastSegmentType().equals(SegmentType.HORIZONTAL) & verifiedShuttle.getLastSegment() == shuttle.getLastSegment()) {
                if (shuttle != verifiedShuttle && shuttle.getOffset() > verifiedShuttle.getOffset() && shuttle.getOffset() - verifiedShuttle.getOffset() < 20) {
                    if (ahead < 2) {
                        ahead++;
                    } else {
                        return false;
                    }
                }
            } else if (shuttle != verifiedShuttle && shuttle.getOffset() > verifiedShuttle.getOffset() && shuttle.getOffset() - verifiedShuttle.getOffset() < 20) {
                return false;
            }
        }
        return true;
    }

    private static void transportShuttles() {
        while (!shuttles.isEmpty()) {
            int i = 0;
            while (i < shuttles.size()) {
                Shuttle shuttle = shuttles.get(i);

                if (hasClearPath(shuttle)) {
                    if (!shuttle.advance()) {
                        shuttles.remove(shuttle);
                        System.out.println("Shuttle ===============================");
                        System.out.println(shuttle.getLog());
                        i--;
                    }
                } else {
                    shuttle.stop();
                }
                i++;
            }
        }
    }
}
