package model;

public class Segment {
    private final SegmentType type;
    private final int distance;
    private final int speed;

    Segment(SegmentType type, int distance, int speed) {
        this.type = type;
        this.distance = distance;
        this.speed = speed;
    }

    SegmentType getType() {
        return type;
    }

    int getDistance() {
        return distance;
    }

    int getSpeed() {
        return speed;
    }
}
