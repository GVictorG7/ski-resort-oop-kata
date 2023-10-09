package model;

public class Shuttle {
    private final ShuttleState state;
    private String log;
    private int offset;
    private Segment lastSegment;

    public Shuttle(ShuttleState state) {
        this.state = state;
        this.offset = 0;
        this.lastSegment = Route.getSegmentByOffset(offset);
        this.log = "Segment - speed " + getActualSpeed() + "\n";
    }

    public String getLog() {
        return log;
    }

    public int getOffset() {
        return offset;
    }

    public Segment getLastSegment() {
        return lastSegment;
    }

    public SegmentType getLastSegmentType() {
        return lastSegment.getType();
    }

    private int getActualSpeed() {
        Segment segment = Route.getSegmentByOffset(offset);
        SegmentType segmentType = segment.getType();
        switch (state) {
            case NORMAL:
                switch (segmentType) {
                    case HORIZONTAL:
                        return segment.getSpeed();
                    case DOWN:
                        return segment.getSpeed() * 125 / 100;
                    case UP:
                        return segment.getSpeed() * 75 / 100;
                }
                break;
            case OVERLOADED:
                switch (segmentType) {
                    case HORIZONTAL:
                        return segment.getSpeed() * 75 / 100;
                    case DOWN:
                        return segment.getSpeed() * 150 / 100;
                    case UP:
                        return segment.getSpeed() * 50 / 100;
                }
                break;
            case UNDERLOADED:
                switch (segmentType) {
                    case HORIZONTAL:
                    case UP:
                        return segment.getSpeed() * 150 / 100;
                    case DOWN:
                        return segment.getSpeed();
                }
        }

        return 0;
    }

    public void stop() {
        log += "Stop: at " + offset + " DU\n";
    }

    public boolean advance() {
        offset += getActualSpeed();
        Segment segment = Route.getSegmentByOffset(offset);

        if (segment != null) {
            // still on track
            if (segment != lastSegment) {
                // new segment
                log += "Segment - speed " + getActualSpeed() + "\n";
                lastSegment = segment;
            }
            return true;
        } else {
            log += "Destination reached\n";
            return false;
        }
    }
}
