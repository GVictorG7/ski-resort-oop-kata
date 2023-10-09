package model;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private static final List<Segment> segments = new ArrayList<>();

    static {
        segments.add(new Segment(SegmentType.DOWN, 300, 20));
        segments.add(new Segment(SegmentType.HORIZONTAL, 1200, 40));
        segments.add(new Segment(SegmentType.UP, 720, 24));
        segments.add(new Segment(SegmentType.HORIZONTAL, 600, 40));
        segments.add(new Segment(SegmentType.UP, 112, 8));
        segments.add(new Segment(SegmentType.HORIZONTAL, 300, 20));
        segments.add(new Segment(SegmentType.UP, 180, 12));
        segments.add(new Segment(SegmentType.UP, 240, 16));
    }

    private Route() {
    }

    public static Segment getSegmentByOffset(int offset) {
        for (Segment segment : segments) {
            offset -= segment.getDistance();
            if (offset <= 0) {
                return segment;
            }
        }
        return null;
    }
}
